package labs.converter;

import labs.domain.Author;
import labs.service.AuthorService;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.ConverterException;
import javax.inject.Inject;
import java.util.logging.Logger;

@ManagedBean
@RequestScoped
public class AuthorValueConventer implements Converter {

	@Inject
	Logger logger;

	@Inject
	private AuthorService authorService;

	@Override
	public Author getAsObject(FacesContext context, UIComponent component, String submittedValue) {
		logger.info("Submited value: "+ submittedValue);
		if (submittedValue == null || submittedValue.isEmpty()) {
			return null;
		}

		try {
			Author aut = authorService.find(Long.valueOf(submittedValue));
			logger.info("Id and value: "+aut.getId()+" "+aut.getSurname()+" ");
			return aut;
		} catch (NumberFormatException e) {
			throw new ConverterException(new FacesMessage(String.format("%s is not a valid User ID", submittedValue)), e);
		}
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component, Object modelValue) {
		if (modelValue == null) {
			return "";
		}

		if (modelValue instanceof Author) {
			return ((Author) modelValue).getId() + "";
		} else {
			throw new ConverterException(new FacesMessage(String.format("%s is not a valid User", modelValue)));
		}
	}
}
