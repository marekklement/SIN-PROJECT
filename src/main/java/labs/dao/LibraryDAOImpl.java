package labs.dao;

import labs.domain.Library;

import javax.persistence.TypedQuery;
import java.util.List;

public class LibraryDAOImpl extends GenericDAO<Library> implements LibraryDAO {
	@Override
	public List<Library> findByName(String name) {
		TypedQuery<Library> query = em.createNamedQuery("findLibraryByName", Library.class).setParameter("name", name);
		return query.getResultList();
	}
}
