package labs.service;

import labs.dao.BookDAO;
import labs.dao.LibraryDAO;
import labs.domain.Author;
import labs.domain.Book;
import labs.domain.Publisher;

import javax.ejb.Stateful;
import javax.inject.Inject;
import java.time.LocalDate;
import java.util.List;

@Stateful
public class BookServiceImpl implements BookService {

	@Inject
	private BookDAO bookDAO;
	@Inject
	private LibraryDAO libraryDAO;

	@Override
	public boolean createNewBook(String isbn, String name, String genre, Author author, LocalDate publicationDate, List<Publisher> publishers, long libraryId) {
		checkNullAndExists(isbn, name, author);
		Book book = new Book();
		book.setIsbn(isbn);
		book.setName(name);
		book.setPublishers(publishers);
		book.setPublicationDate(publicationDate);
		book.setGenre(genre);
		book.setAuthor(author);
		book.setLibrary(libraryDAO.find(libraryId));
		bookDAO.save(book);
		return true;
	}

	@Override
	public boolean createNewBook(Book book) {
		System.out.println();
		bookDAO.save(book);
		return true;
	}

	@Override
	public List<Book> findAll() {
		return bookDAO.list();
	}

	@Override
	public Book find(String isbn) {
		return bookDAO.find(isbn);
	}

	private void checkNullAndExists(String isbn, String name, Author authors) {
		if (isbn == null)
			throw new IllegalArgumentException("ISBN is null!");
		if (name == null)
			throw new IllegalArgumentException("Name is null!");
		if (authors == null)
			throw new IllegalArgumentException("Authors are null!");
		if (bookDAO.find(isbn) != null)
			throw new IllegalArgumentException("Book with isbn " + isbn + " already exist!");
	}
}
