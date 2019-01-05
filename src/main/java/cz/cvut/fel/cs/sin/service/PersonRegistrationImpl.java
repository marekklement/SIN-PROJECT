package cz.cvut.fel.cs.sin.service;

import cz.cvut.fel.cs.sin.dao.PersonDAOImpl;
import cz.cvut.fel.cs.sin.entity.Person;

import javax.ejb.Stateless;
import javax.inject.Inject;
import java.util.logging.Logger;

@Stateless
public class PersonRegistrationImpl implements PersonRegistration {

	@Inject
	private PersonDAOImpl personDAO;

	@Inject
	private Logger logger;

	public void register(Person person) {
		personDAO.save(person);
		logger.info("Created persion " + person.getFirstName() + " " + person.getLastName());
	}

}
