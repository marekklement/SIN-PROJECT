package labs.dao;

import labs.domain.Book;

import javax.persistence.TypedQuery;
import java.util.List;

public class BookDAOImpl extends GenericDAO<Book> implements BookDAO {

	@Override
	public List<Book> findByName(String name) {
		TypedQuery<Book> query = em.createNamedQuery("findBookByName", Book.class).setParameter("name", name);
		return query.getResultList();
	}

	@Override
	public List<Book> findByGenre(String genre) {
		TypedQuery<Book> query = em.createNamedQuery("findBookByGenre", Book.class).setParameter("name", genre);
		return query.getResultList();
	}
}
