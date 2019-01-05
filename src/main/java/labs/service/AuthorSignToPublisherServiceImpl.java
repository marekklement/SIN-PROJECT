package labs.service;

import labs.dao.AuthorDAO;
import labs.dao.PublisherDAO;
import labs.domain.Author;
import labs.domain.Publisher;

import javax.ejb.Stateful;
import javax.inject.Inject;
import java.util.LinkedList;
import java.util.List;

@Stateful
public class AuthorSignToPublisherServiceImpl implements AuthorSignToPublisherService {

	@Inject
	private AuthorDAO authorDAO;

	@Inject
	private PublisherDAO publisherDAO;

	@Override
	public boolean authorSignToPublisher(long authorId, long publisherId) {
		Author author = authorDAO.find(authorId);
		if (author == null)
			throw new IllegalArgumentException("Author with Id " + authorId + " does not exist.");
		Publisher publisher = publisherDAO.find(publisherId);
		if (publisher == null)
			throw new IllegalArgumentException("Publisher with Id " + publisherId + " does not exist.");
		List<Author> authorsSigned;
		if (publisher.getAuthorsSigned() == null) {
			authorsSigned = new LinkedList<>();
		} else {
			authorsSigned = publisher.getAuthorsSigned();
		}
		authorsSigned.add(author);
		publisher.setAuthorsSigned(authorsSigned);
		publisher = publisherDAO.update(publisher);
		List<Publisher> publishers;
		if (author.getPublishers() == null) {
			publishers = new LinkedList<>();
		} else {
			publishers = author.getPublishers();
		}
		publishers.add(publisher);
		author.setPublishers(publishers);
		authorDAO.update(author);
		return true;
	}
}
