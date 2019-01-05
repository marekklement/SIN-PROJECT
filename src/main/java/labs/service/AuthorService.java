package labs.service;

import labs.domain.Author;
import labs.domain.Book;
import labs.domain.Publisher;

import java.util.List;

public interface AuthorService {

	boolean createNewAuthor(String email, String firstName, String surname, List<Publisher> publishers, List<Book> books);

	boolean createNewAuthor(Author author);

	List<Author> findAll();

	Author find(long id);
}
