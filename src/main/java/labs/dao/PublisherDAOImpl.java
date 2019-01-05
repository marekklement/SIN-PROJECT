package labs.dao;

import labs.domain.Publisher;

import javax.persistence.TypedQuery;
import java.util.List;

public class PublisherDAOImpl extends GenericDAO<Publisher> implements PublisherDAO {
	@Override
	public List<Publisher> findByName(String name) {
		TypedQuery<Publisher> query = em.createNamedQuery("findAuthorByName", Publisher.class).setParameter("NAME", name);
		return query.getResultList();
	}
}
