package labs.service;

import labs.dao.AuthorDAO;
import labs.domain.Author;
import labs.domain.Book;
import labs.domain.Publisher;

import javax.ejb.Stateful;
import javax.inject.Inject;
import java.util.List;

@Stateful
public class AuthorServiceImpl implements AuthorService {

	@Inject
	private AuthorDAO authorDAO;

	@Override
	public boolean createNewAuthor(String email, String firstName, String surname, List<Publisher> publishers, List<Book> books) {
		checkNull(firstName, surname);
		Author author = new Author();
		author.setPublishers(publishers);
		author.setEmail(email);
		author.setSurname(surname);
		author.setFirstName(firstName);
		author.setBooks(books);
		authorDAO.save(author);
		return true;
	}

	@Override
	public boolean createNewAuthor(Author author) {
		authorDAO.save(author);
		return true;
	}

	@Override
	public List<Author> findAll() {
		return authorDAO.list();
	}

	@Override
	public Author find(long id) {
		return authorDAO.find(id);
	}

	private void checkNull(String firstName, String surname) {
		if (firstName == null || firstName.isEmpty())
			throw new IllegalArgumentException("First name have to be filled!");
		if (surname == null || surname.isEmpty())
			throw new IllegalArgumentException("Surname have to be filled!");
	}
}
