package labs.dao;

import labs.domain.Book;

import java.util.List;

public interface BookDAO extends DAO<Book> {

	List<Book> findByName(String name);

	List<Book> findByGenre(String genre);
}
