package cz.cvut.fel.cs.sin.dao;

import cz.cvut.fel.cs.sin.entity.Person;

import java.util.List;


public interface PersonDAO extends DAO<Person> {

	List<Person> findByLastName(String lastName);

	List<Person> findByTeamName(String teamName);

}
