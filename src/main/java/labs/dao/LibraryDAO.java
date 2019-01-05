package labs.dao;

import labs.domain.Library;

import java.util.List;

public interface LibraryDAO extends DAO<Library> {

	List<Library> findByName(String name);
}
