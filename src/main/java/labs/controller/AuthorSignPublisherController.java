package labs.controller;

import labs.domain.Author;
import labs.domain.Publisher;
import labs.service.AuthorService;
import labs.service.AuthorSignToPublisherService;
import labs.service.PublisherService;

import javax.annotation.PostConstruct;
import javax.enterprise.inject.Model;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import java.util.LinkedList;
import java.util.List;
import java.util.logging.Logger;

@Model
public class AuthorSignPublisherController {

	@Inject
	Logger logger;

	@Inject
	private AuthorSignToPublisherService authorSignToPublisherService;
	@Inject
	private AuthorService authorService;
	@Inject
	private PublisherService publisherService;

	@Inject
	private FacesContext facesContext;

	private List<Author> authors;
	private Author author;
	private List<Publisher> publishers;
	private Publisher publisher;

	public List<Author> getAuthors() {
		return authors;
	}

	public Author getAuthor() {
		return author;
	}

	public void setAuthor(Author author) {
		this.author = author;
	}

	public List<Publisher> getPublishers() {
		return publishers;
	}

	public Publisher getPublisher() {
		return publisher;
	}

	public void setPublisher(Publisher publisher) {
		this.publisher = publisher;
	}

	@PostConstruct
	public void init(){
		authors = authorService.findAll();
		if(authors!=null && authors.size()>=1) {
			author = authors.get(0);
		} else {
			authors = new LinkedList<>();
			author = null;
		}
		publishers = publisherService.findAll();
		if(publishers!=null && publishers.size()>=1) {
			publisher = publishers.get(0);
		} else {
			publishers = new LinkedList<>();
			publisher = null;
		}
	}

	public void signToPublisher(){
		authorSignToPublisherService.authorSignToPublisher(author.getId(),publisher.getId());
		FacesMessage m = new FacesMessage(FacesMessage.SEVERITY_INFO, "Author signed to publisher!", "Added successfully");
		facesContext.addMessage(null, m);
		init();
		logger.info("\"Author signed to publisher!");
	}
}
