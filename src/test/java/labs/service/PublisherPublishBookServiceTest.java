package labs.service;

import cz.cvut.fel.cs.sin.util.Resource;
import labs.dao.AuthorDAO;
import labs.dao.AuthorDAOImpl;
import labs.dao.BookDAO;
import labs.dao.BookDAOImpl;
import labs.dao.PublisherDAO;
import labs.dao.PublisherDAOImpl;
import labs.domain.Author;
import labs.domain.Book;
import labs.domain.Publisher;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.arquillian.transaction.api.annotation.Transactional;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.EmptyAsset;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.junit.Test;
import org.junit.runner.RunWith;

import javax.inject.Inject;
import java.time.LocalDate;

import static junit.framework.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

@RunWith(Arquillian.class)
public class PublisherPublishBookServiceTest {

	@Inject
	private PublisherPublishBookService publisherPublishBookService;
	@Inject
	private BookDAO bookDAO;
	@Inject
	private AuthorDAO authorDAO;
	@Inject
	private PublisherDAO publisherDAO;

	@Deployment
	public static WebArchive createDeployment() {
		return ShrinkWrap.create(WebArchive.class, "test.war").addPackage(Book.class.getPackage()).addPackage(BookDAOImpl.class.getPackage()).addPackage(Author.class.getPackage()).addPackage(AuthorDAOImpl.class.getPackage()).addPackage(Publisher.class.getPackage()).addPackage(PublisherDAOImpl.class.getPackage()).addPackage(PublisherPublishBookService.class.getPackage()).addClass(Resource.class).addAsResource("META-INF/test-persistence.xml", "META-INF/persistence.xml").addAsWebInfResource(EmptyAsset.INSTANCE, "beans.xml");
	}

	@Test
	@Transactional
	public void addBookToLibraryTest() {
		// author set
		Author author = new Author();
		author.setFirstName("Test001Name");
		author.setSurname("Test001SurName");
		author.setEmail("text@test.cz");
		author = authorDAO.save(author);
		assertNotNull(author.getId());
		// book set
		Book book = new Book();
		book.setAuthor(author);
		book.setName("TestBook");
		book.setIsbn("SomeISBN");
		book.setGenre("drama");
		book.setPublicationDate(LocalDate.now());
		book = bookDAO.save(book);
		// library set
		Publisher publisher = new Publisher();
		publisher.setName("PublisherTest001");
		publisherDAO.save(publisher);
		publisherPublishBookService.publisherPublishBookService(publisher.getId(), book.getIsbn());
		assertNotNull(publisher.getBooksPublished());
		assertEquals("Should be one book published by now", 1, publisher.getBooksPublished().size());
		assertEquals("Wrong book, expected something else", book.getIsbn(), publisher.getBooksPublished().get(0).getIsbn());
	}

}