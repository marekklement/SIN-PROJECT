package labs.service;

import labs.domain.Author;
import labs.domain.Book;
import labs.domain.Publisher;

import java.util.List;

public interface PublisherService {

	boolean createNewPublisher(String name, String address, List<Book> booksPublished, List<Author> authorsSigned);

	boolean createNewPublisher(Publisher publisher);

	Publisher find(long id);

	List<Publisher> findAll();
}
