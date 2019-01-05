package labs.controller;

import labs.domain.Book;
import labs.domain.Library;
import labs.service.AddBookToLibraryService;
import labs.service.BookService;
import labs.service.LibraryService;

import javax.annotation.PostConstruct;
import javax.enterprise.inject.Model;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import java.util.LinkedList;
import java.util.List;
import java.util.logging.Logger;

@Model
public class AddBookToLibraryController {

	@Inject
	Logger logger;

	@Inject
	private AddBookToLibraryService addBookToLibraryService;
	@Inject
	private LibraryService libraryService;
	@Inject
	private BookService bookService;

	@Inject
	private FacesContext facesContext;

	private List<Book> books;
	private Book book;
	private List<Library> libraries;
	private Library library;

	public List<Book> getBooks() {
		return books;
	}

	public Book getBook() {
		return book;
	}

	public void setBook(Book book) {
		this.book = book;
	}

	public List<Library> getLibraries() {
		return libraries;
	}

	public Library getLibrary() {
		return library;
	}

	public void setLibrary(Library library) {
		this.library = library;
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
		libraries = libraryService.findAll();
		if(libraries!=null && libraries.size()>=1) {
			library = libraries.get(0);
		} else {
			libraries = new LinkedList<>();
			library = null;
		}
	}

	public void addBookToLibrary(){
		addBookToLibraryService.addBookToLibrary(library.getId(),book.getIsbn());
		FacesMessage m = new FacesMessage(FacesMessage.SEVERITY_INFO, "Book added to library!", "Added successfully");
		facesContext.addMessage(null, m);
		init();
		logger.info("\"Book added to library!");
	}
}
