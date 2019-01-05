package labs.service;

import labs.dao.BookDAO;
import labs.dao.PublisherDAO;
import labs.domain.Book;
import labs.domain.Publisher;

import javax.ejb.Stateful;
import javax.inject.Inject;
import java.time.LocalDate;
import java.util.LinkedList;
import java.util.List;
import java.util.logging.Logger;

@Stateful
public class PublisherPublishBookServiceImpl implements PublisherPublishBookService {

	@Inject
	private Logger logger;

	// services
	@Inject
	private PublisherDAO publisherDAO;
	@Inject
	private BookDAO bookDAO;

	@Override
	public boolean publisherPublishBookService(long publisherId, String isbn) {
		Publisher publisher = publisherDAO.find(publisherId);
		if (publisher == null)
			throw new IllegalArgumentException("With UUID" + publisherId + ",it was not possible to find any libraries");
		Book book = bookDAO.find(isbn);
		if (book == null)
			throw new IllegalArgumentException("With isbn" + isbn + ",it was not possible to find any books");
		List<Book> publishedBooks;
		if (publisher.getBooksPublished() == null) {
			publishedBooks = new LinkedList<>();
		} else {
			publishedBooks = publisher.getBooksPublished();
		}
		publishedBooks.add(book);
		publisher.setBooksPublished(publishedBooks);
		publisher = publisherDAO.update(publisher);
		if (book.getPublicationDate() != null)
			book.setPublicationDate(LocalDate.now());
		List<Publisher> publishers;
		if (book.getPublishers() == null) {
			publishers = new LinkedList<>();
		} else {
			publishers = book.getPublishers();
		}
		publishers.add(publisher);
		logger.info("Publisher with ID: " + publisherId + " published new book with ISBN: " + isbn);
		return true;
	}
}
