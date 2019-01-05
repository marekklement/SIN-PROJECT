package labs.service;

import labs.dao.LibraryDAO;
import labs.domain.Book;
import labs.domain.Library;

import javax.ejb.Stateful;
import javax.inject.Inject;
import java.util.List;
import java.util.logging.Logger;

@Stateful
public class LibraryServiceImpl implements LibraryService {

	@Inject
	Logger logger;

	@Inject
	private LibraryDAO libraryDAO;

	@Override
	public boolean createNewLibrary(String name, String address, List<Book> ownedBooks) {
		checkNullAndExists(name);
		Library library = new Library();
		library.setName(name);
		library.setOwnedBooks(ownedBooks);
		library.setAddress(address);
		libraryDAO.save(library);
		return true;
	}

	@Override
	public boolean createNewLibrary(Library library) {
		libraryDAO.save(library);
		logger.info("Library saved successfully!");
		return true;
	}

	@Override
	public Library find(long id) {
		return libraryDAO.find(id);
	}

	@Override
	public List<Library> findAll() {
		return libraryDAO.list();
	}

	private void checkNullAndExists(String name) {
		if (name == null)
			throw new IllegalArgumentException("Name is null!");
		if (libraryDAO.findByName(name).size() >= 1)
			throw new IllegalArgumentException("Name " + name + " already exists!");
	}
}
