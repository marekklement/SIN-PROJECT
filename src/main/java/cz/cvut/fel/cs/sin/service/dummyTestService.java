package cz.cvut.fel.cs.sin.service;

import cz.cvut.fel.cs.sin.entity.Person;
import cz.cvut.fel.cs.sin.entity.Team;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.EntityManager;
import java.util.List;

@Named("dummy")
@Stateless
public class dummyTestService {

	@Inject
	EntityManager em;

	public void trySomething() {
		System.out.println("Creating person");
		Person p = new Person();
		p.setAddress("Karlovo namesti 13");
		p.setEmail("neco@nekde.cz");
		p.setFirstName("John");
		p.setLastName("Roe");
		Team t = new Team();
		t.setName("Super team");
		em.persist(t);
		p.setTeam(t);
		em.persist(p);
		Person p2 = new Person();
		p2.setAddress("Dejvicka 3");
		p2.setEmail("neco@jinde.cz");
		p2.setFirstName("Bob");
		p2.setLastName("Deer");
		p2.setTeam(t);
		em.persist(p2);
		em.flush();

		//Needs to be done through TypedQuery<Person> to retain types
		List<Person> persons = em.createNamedQuery("findPersonFromTeamByName", Person.class).setParameter("name", "Super team").getResultList();
		persons.forEach(person -> System.out.println(person.getLastName()));
	}
}
