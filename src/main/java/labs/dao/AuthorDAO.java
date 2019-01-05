package labs.dao;

import labs.domain.Author;

import java.util.List;

public interface AuthorDAO extends DAO<Author> {

	List<Author> findByFirstName(String firstName);

	List<Author> findBySurname(String surname);

	List<Author> findByFullName(String surname, String firstName);
}
