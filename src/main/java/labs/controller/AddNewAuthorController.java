package labs.controller;

import labs.domain.Author;
import labs.service.AuthorService;

import javax.annotation.PostConstruct;
import javax.enterprise.inject.Model;
import javax.enterprise.inject.Produces;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

@Model
public class AddNewAuthorController {

	@Inject
	private AuthorService addNewAuthorService;

	@Produces
	@Named
	private Author newAuthor;

	@Inject
	private FacesContext facesContext;

	@PostConstruct
	public void initNewAuthor(){
		newAuthor = new Author();
	}

	public void addNewAuthor(){
		addNewAuthorService.createNewAuthor(newAuthor);
		FacesMessage m = new FacesMessage(FacesMessage.SEVERITY_INFO, "Author added!", "Added successfully");
		facesContext.addMessage(null, m);
		initNewAuthor();
	}
}
