package labs.controller;

import labs.domain.Library;
import labs.service.LibraryService;

import javax.annotation.PostConstruct;
import javax.enterprise.inject.Model;
import javax.enterprise.inject.Produces;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import java.util.logging.Logger;

@Model
public class AddNewLibraryController {

	@Inject
	Logger logger;

	@Inject
	private LibraryService libraryService;

	@Produces
	@Named
	private Library newLibrary;

	@Inject
	private FacesContext facesContext;

	@PostConstruct
	public void initNewLibrary(){
		newLibrary = new Library();
	}

	public void addNewLibrary(){
		libraryService.createNewLibrary(newLibrary);
		FacesMessage m = new FacesMessage(FacesMessage.SEVERITY_INFO, "Library added!", "Added successfully");
		facesContext.addMessage(null, m);
		initNewLibrary();
		logger.info("\"Library added!");
	}
}
