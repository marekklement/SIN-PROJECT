package labs.service;

import labs.domain.Author;
import labs.domain.Book;
import labs.domain.Publisher;

import java.time.LocalDate;
import java.util.List;

public interface BookService {

	public boolean createNewBook(String isbn, String name, String genre, Author author, LocalDate publicationDate, List<Publisher> publishers, long libraryId);

	boolean createNewBook(Book book);

	List<Book> findAll();

	Book find(String isbn);
}
