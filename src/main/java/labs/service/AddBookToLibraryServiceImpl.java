package labs.service;

import labs.dao.BookDAO;
import labs.dao.LibraryDAO;
import labs.domain.Book;
import labs.domain.Library;

import javax.ejb.Stateful;
import javax.inject.Inject;
import java.util.LinkedList;
import java.util.List;
import java.util.logging.Logger;

@Stateful
public class AddBookToLibraryServiceImpl implements AddBookToLibraryService {

	@Inject
	private Logger logger;

	// services
	@Inject
	private LibraryDAO libraryDAO;
	@Inject
	private BookDAO bookDAO;

	@Override
	public boolean addBookToLibrary(long libraryUUID, String isbn) {
		Library library = libraryDAO.find(libraryUUID);
		if (library == null)
			throw new IllegalArgumentException("With UUID" + libraryUUID + ",it was not possible to find any libraries");
		Book book = bookDAO.find(isbn);
		if (book == null)
			throw new IllegalArgumentException("With isbn" + isbn + ",it was not possible to find any books");
		List<Book> ownedBooks;
		if (library.getOwnedBooks() == null) {
			ownedBooks = new LinkedList<>();
		} else {
			ownedBooks = library.getOwnedBooks();
		}
		ownedBooks.add(book);
		library.setOwnedBooks(ownedBooks);
		libraryDAO.update(library);
		logger.info("Book with ISBN: " + isbn + " was added to library with ID: " + libraryUUID);
		return true;
	}
}
