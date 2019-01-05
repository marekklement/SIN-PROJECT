package labs.dao;

import labs.domain.Author;

import javax.persistence.TypedQuery;
import java.util.List;

public class AuthorDAOImpl extends GenericDAO<Author> implements AuthorDAO {
	@Override
	public List<Author> findByFirstName(String firstName) {
		TypedQuery<Author> query = em.createNamedQuery("findAuthorByFirstName", Author.class).setParameter("name", firstName);
		return query.getResultList();
	}

	@Override
	public List<Author> findBySurname(String surname) {
		TypedQuery<Author> query = em.createNamedQuery("findAuthorBySurname", Author.class).setParameter("name", surname);
		return query.getResultList();
	}

	@Override
	public List<Author> findByFullName(String surname, String firstName) {
		TypedQuery<Author> query = em.createNamedQuery("findAuthorByFullName", Author.class).setParameter("surname", surname).setParameter("firstName", firstName);
		return query.getResultList();
	}
}
