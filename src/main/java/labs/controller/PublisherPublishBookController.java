package labs.controller;

import labs.domain.Book;
import labs.domain.Publisher;
import labs.service.BookService;
import labs.service.PublisherPublishBookService;
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
public class PublisherPublishBookController {

	@Inject
	Logger logger;

	@Inject
	private PublisherPublishBookService publisherPublishBookService;
	@Inject
	private PublisherService publisherService;
	@Inject
	private BookService bookService;

	@Inject
	private FacesContext facesContext;

	private List<Publisher> publishers;
	private Publisher publisher;
	private List<Book> books;
	private Book book;

	public List<Publisher> getPublishers() {
		return publishers;
	}

	public Publisher getPublisher() {
		return publisher;
	}

	public void setPublisher(Publisher publisher) {
		this.publisher = publisher;
	}

	public List<Book> getBooks() {
		return books;
	}

	public Book getBook() {
		return book;
	}

	public void setBook(Book book) {
		this.book = book;
	}

	@PostConstruct
	public void init(){
		books = bookService.findAll();
		if(books!=null && books.size()>=1) {
			book = books.get(0);
		} else {
			books = new LinkedList<>();
			book = null;
		}
		publishers = publisherService.findAll();
		if(publishers!=null && publishers.size()>=1) {
			publisher = publishers.get(0);
		} else {
			publishers = new LinkedList<>();
			publisher = null;
		}
	}

	public void publishBook(){
		publisherPublishBookService.publisherPublishBookService(publisher.getId(),book.getIsbn());
		FacesMessage m = new FacesMessage(FacesMessage.SEVERITY_INFO, "Publisher published book!", "Added successfully");
		facesContext.addMessage(null, m);
		init();
		logger.info("\"Publisher published book!");
	}
}
