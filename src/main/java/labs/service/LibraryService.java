package labs.service;

import labs.domain.Book;
import labs.domain.Library;

import java.util.List;

public interface LibraryService {

	boolean createNewLibrary(String name, String address, List<Book> ownedBooks);

	boolean createNewLibrary(Library library);

	Library find(long id);

	List<Library> findAll();
}
