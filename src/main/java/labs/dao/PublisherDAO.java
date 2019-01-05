package labs.dao;

import labs.domain.Publisher;

import java.util.List;

public interface PublisherDAO extends DAO<Publisher> {

	List<Publisher> findByName(String name);
}
