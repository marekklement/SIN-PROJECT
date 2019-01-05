package labs.service;

import labs.dao.PublisherDAO;
import labs.domain.Author;
import labs.domain.Book;
import labs.domain.Publisher;

import javax.ejb.Stateful;
import javax.inject.Inject;
import java.util.List;
import java.util.logging.Logger;

@Stateful
public class PublisherServiceImpl implements PublisherService {

	@Inject
	Logger logger;

	@Inject
	private PublisherDAO publisherDAO;

	@Override
	public boolean createNewPublisher(String name, String address, List<Book> booksPublished, List<Author> authorsSigned) {
		checkNullAndExist(name);
		Publisher publisher = new Publisher();
		publisher.setName(name);
		publisher.setAddress(address);
		publisher.setAuthorsSigned(authorsSigned);
		publisher.setBooksPublished(booksPublished);
		publisherDAO.save(publisher);
		return true;
	}

	@Override
	public boolean createNewPublisher(Publisher publisher) {
		publisherDAO.save(publisher);
		logger.info("Publisher saved successfully!");
		return true;
	}

	@Override
	public Publisher find(long id) {
		return publisherDAO.find(id);
	}

	@Override
	public List<Publisher> findAll() {
		return publisherDAO.list();
	}

	private void checkNullAndExist(String name) {
		if (name == null || name.isEmpty())
			throw new IllegalArgumentException("Name have to be filled!");
		if (publisherDAO.findByName(name).size() >= 1)
			throw new IllegalArgumentException("Library with name " + name + " already exists!");
	}
}
