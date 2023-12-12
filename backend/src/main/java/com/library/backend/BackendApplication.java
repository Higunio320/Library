package com.library.backend;

import com.library.backend.api.auth.data.RegisterRequest;
import com.library.backend.api.auth.interfaces.AuthController;
import com.library.backend.entities.book.Book;
import com.library.backend.entities.book.interfaces.BookRepository;
import com.library.backend.entities.bookloan.BookLoan;
import com.library.backend.entities.bookloan.interfaces.BookLoanRepository;
import com.library.backend.entities.user.Role;
import com.library.backend.entities.user.User;
import com.library.backend.entities.user.interfaces.UserRepository;
import com.library.backend.utils.exceptions.users.UserNotFoundException;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.time.LocalDate;
import java.util.List;

@SpringBootApplication
public class BackendApplication {

	public static void main(String[] args) {
		SpringApplication.run(BackendApplication.class, args);
	}


	@Bean
	public CommandLineRunner initData(BookRepository bookRepository,
									  BookLoanRepository bookLoanRepository,
									  UserRepository userRepository,
									  AuthController authController) {
		return args -> {
			User user = createUser1(authController, userRepository);
			createAdmin(authController, userRepository);
			createBooks(bookRepository);
			createLoans(user, bookLoanRepository, bookRepository);
			createUser2(authController);
		};
	}

	private void createAdmin(AuthController authController, UserRepository userService) {
		RegisterRequest request = RegisterRequest.builder()
				.email("admin@mail.pl")
				.firstName("Admin")
				.lastName("Adminowski")
				.password("abc123")
				.build();

		authController.register(request);

		User user = userService.findByEmail(request.email()).orElse(null);
		user.setRole(Role.ADMIN);
		userService.save(user);
	}

	private User createUser1(AuthController authController, UserRepository userRepository) {
		RegisterRequest request = RegisterRequest.builder()
				.email("user1@mail.pl")
				.firstName("User")
				.lastName("Userowski")
				.password("abc123")
				.build();

		authController.register(request);

		return userRepository.findByEmail(request.email()).orElseThrow(() ->
				new UserNotFoundException("User not found"));
	}

	private void createUser2(AuthController authController) {

		RegisterRequest request = RegisterRequest.builder()
				.email("user2@mail.pl")
				.firstName("Michał")
				.lastName("Userowski")
				.password("abc123")
				.build();

		authController.register(request);
	}

	private void createBooks(BookRepository bookRepository) {
		for (int i = 0; i < 10; i++) {
			Book book = Book.builder()
					.title(bookNames[i])
					.description(bookDescriptions[i])
					.author(bookAuthors[i])
					.genre(bookGenres[i])
					.availableNumber(10)
					.totalNumber(10)
					.build();
			bookRepository.save(book);
		}
	}

	private void createLoans(User user, BookLoanRepository bookLoanRepository, BookRepository bookRepository) {
		List<Book> books = bookRepository.findAll();
		int max = books.size() / 2;
		LocalDate loanDate = LocalDate.now();
		LocalDate returnDate = loanDate.plusDays(14);

		for (int i = 0; i < max; i++) {
			BookLoan bookLoan = BookLoan.builder()
					.book(books.get(i))
					.user(user)
					.loanDate(loanDate)
					.endDate(returnDate)
					.build();
			bookLoanRepository.save(bookLoan);
		}

	}


	private String[] bookNames = new String[]{
			"Wędrowcy Czasu",
			"Labirynt Umysłu",
			"Szept Cieni",
			"Przez Krainę Mgły",
			"Symfonia Nocy",
			"Ostatni Bastion",
			"Intryga Równowagi",
			"Klątwa Skrytobójcy",
			"Skarby Pustyni",
			"Rój Marzeń"
	};

	private String[] bookDescriptions = new String[]{
			"Epicka podróż przez czas i przestrzeń, pełna niezwykłych przygód i tajemnic.",
			"Zagadkowy thriller psychologiczny, który testuje granice ludzkiego umysłu.",
			"Mroczna opowieść o znikających cieniach i ukrytych zagrożeniach.",
			"Fantastyczna podróż przez krainę mgieł, gdzie magia miesza się z rzeczywistością.",
			"Romantyczna historia o niewidzialnej więzi, która łączy ludzi podczas nocy.",
			"Świat staje w obliczu zagłady, a ostatni bastion nadziei walczy o przetrwanie.",
			"Polityczna intryga, w której równowaga świata jest na wadze.",
			"Przeklęta misja skrytobójcy, który musi stawić czoło swojemu przeznaczeniu.",
			"Ekscytująca wyprawa na skarby ukryte głęboko w bezkresie pustyni.",
			"Marzenia zamieniają się w koszmary, gdy tajemniczy roj zaczyna nawiedzać sny."
	};

	private String[] bookAuthors = new String[]{
			"Anna Kowalska",
			"Jan Nowak",
			"Magda Zielona",
			"Piotr Nieznany",
			"Karolina Mrożek",
			"Marek Wiatr",
			"Ewa Zręczyńska",
			"Tomasz Cicha Rzeka",
			"Artur Piaskowy",
			"Alicja Światło"
	};

	private String[] bookGenres = new String[]{
			"Science Fiction",
			"Thriller",
			"Fantasy",
			"Przygodowa",
			"Romans",
			"Postapokaliptyczna",
			"Polityczna",
			"Akcji",
			"Sensacyjna",
			"Surrealistyczna"
	};


}
