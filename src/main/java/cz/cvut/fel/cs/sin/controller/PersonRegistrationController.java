package cz.cvut.fel.cs.sin.controller;

import cz.cvut.fel.cs.sin.entity.Person;
import cz.cvut.fel.cs.sin.service.PersonRegistration;

import javax.annotation.PostConstruct;
import javax.enterprise.inject.Model;
import javax.enterprise.inject.Produces;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

@Model // See http://www.cdi-spec.org/faq/#accordion6
public class PersonRegistrationController {

	@Inject
	private PersonRegistration personRegistration;

	@Produces
	@Named
	private Person newPerson;

	@Inject
	private FacesContext facesContext;

	@PostConstruct
	public void initNewPerson() {
		newPerson = new Person();
	}

	public void registerNewPerson() {
		personRegistration.register(newPerson);
		FacesMessage m = new FacesMessage(FacesMessage.SEVERITY_INFO, "Person registered!", "Registration successful");
		facesContext.addMessage(null, m);
		initNewPerson();
	}

}
