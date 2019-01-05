package labs.controller;

import labs.domain.Publisher;
import labs.service.PublisherService;

import javax.annotation.PostConstruct;
import javax.enterprise.inject.Model;
import javax.enterprise.inject.Produces;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import java.util.logging.Logger;

@Model
public class AddNewPublisherController {

	@Inject
	Logger logger;

	@Inject
	private PublisherService publisherService;

	@Produces
	@Named
	private Publisher newPublisher;

	@Inject
	private FacesContext facesContext;

	@PostConstruct
	public void initNewPublisher(){
		newPublisher = new Publisher();
	}

	public void addNewPublisher(){
		publisherService.createNewPublisher(newPublisher);
		FacesMessage m = new FacesMessage(FacesMessage.SEVERITY_INFO, "Publisher added!", "Added successfully");
		facesContext.addMessage(null, m);
		initNewPublisher();
		logger.info("\"Publisher added!");
	}
}
