package cz.cvut.fel.cs.sin.dao;

import cz.cvut.fel.cs.sin.entity.Person;

import javax.persistence.TypedQuery;
import java.util.List;


public class PersonDAOImpl extends GenericDAO<Person> implements PersonDAO {

	public List<Person> findByLastName(String lastName) {
		TypedQuery<Person> query = em.createNamedQuery("findPersonByLastName", Person.class).setParameter("name", lastName);
		return query.getResultList();
	}

	public List<Person> findByTeamName(String teamName) {
		TypedQuery<Person> query = em.createNamedQuery("findPersonFromTeamByName", Person.class).setParameter("name", teamName);
		return query.getResultList();
	}

}
