package labs.controller;

import labs.domain.Author;
import labs.domain.Book;
import labs.service.AuthorService;
import labs.service.BookService;

import javax.annotation.PostConstruct;
import javax.enterprise.inject.Model;
import javax.enterprise.inject.Produces;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import java.util.LinkedList;
import java.util.List;
import java.util.logging.Logger;

@Model
public class AddNewBookController {

	@Inject
	Logger logger;

	@Inject
	private BookService addNewBookService;

	@Inject
	private AuthorService authorService;

	@Produces
	@Named
	private Book newBook;

	@Inject
	private FacesContext facesContext;

	@PostConstruct
	public void initNewBook(){
		newBook = new Book();
		authors = authorService.findAll();
		if(authors!=null && authors.size()>=1) {
			author = authors.get(0);
		} else {
			authors = new LinkedList<>();
			author = null;
		}
	}

	public void addNewBook(){
		newBook.setAuthor(author);
		addNewBookService.createNewBook(newBook);
		FacesMessage m = new FacesMessage(FacesMessage.SEVERITY_INFO, "Book added!", "Added successfully");
		facesContext.addMessage(null, m);
		initNewBook();
		logger.info("\"Book added!");
	}

	private List<Author> authors;

	private Author author;

	public Author getAuthor() {
		return author;
	}

	public List<Author> getAuthors() {
		return authors;
	}
	public void setAuthors(List<Author> authors) {
		this.authors = authors;
	}

	public void setAuthor(Author author) {
		this.author = author;
	}
}
