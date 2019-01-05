package labs.service;

import cz.cvut.fel.cs.sin.util.Resource;
import labs.dao.AuthorDAO;
import labs.dao.AuthorDAOImpl;
import labs.dao.PublisherDAO;
import labs.dao.PublisherDAOImpl;
import labs.domain.Author;
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

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertTrue;
import static org.junit.Assert.assertNotNull;

@RunWith(Arquillian.class)
public class AuthorSignToPublisherServiceTest {

	@Inject
	private AuthorSignToPublisherService authorSignToPublisherService;
	@Inject
	private AuthorDAO authorDAO;
	@Inject
	private PublisherDAO publisherDAO;


	@Deployment
	public static WebArchive createDeployment() {
		return ShrinkWrap.create(WebArchive.class, "test.war").addPackage(Author.class.getPackage()).addPackage(AuthorDAOImpl.class.getPackage()).addPackage(Publisher.class.getPackage()).addPackage(PublisherDAOImpl.class.getPackage()).addPackage(AuthorSignToPublisherService.class.getPackage()).addClass(Resource.class).addAsResource("META-INF/test-persistence.xml", "META-INF/persistence.xml").addAsWebInfResource(EmptyAsset.INSTANCE, "beans.xml");
	}

	@Test
	@Transactional
	public void authorSignToPublisher() {
		//
		Author author = new Author();
		author.setFirstName("Test001Name");
		author.setSurname("Test001SurName");
		author.setEmail("text@test.cz");
		author = authorDAO.save(author);
		assertNotNull(author.getId());
		//
		Publisher publisher = new Publisher();
		publisher.setName("PublisherTest001");
		publisherDAO.save(publisher);
		boolean output = authorSignToPublisherService.authorSignToPublisher(author.getId(), publisher.getId());
		assertTrue(output);
		assertNotNull(publisher.getAuthorsSigned());
		assertNotNull(author.getPublishers());
		assertEquals("Should be one author signed by now", 1, publisher.getAuthorsSigned().size());
		assertEquals("Wrong author, expected something else", author.getId(), publisher.getAuthorsSigned().get(0).getId());
	}
}
