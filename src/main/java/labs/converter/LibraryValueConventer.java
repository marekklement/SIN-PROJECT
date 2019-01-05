package labs.converter;

import labs.domain.Library;
import labs.service.LibraryService;

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
public class LibraryValueConventer implements Converter {

	@Inject
	Logger logger;

	@Inject
	private LibraryService libraryService;

	@Override
	public Library getAsObject(FacesContext context, UIComponent component, String submittedValue) {
		logger.info("Submited value: "+ submittedValue);
		if (submittedValue == null || submittedValue.isEmpty()) {
			return null;
		}

		try {
			Library bk = libraryService.find(Long.valueOf(submittedValue));
			logger.info("Id and value: "+bk.getId()+" "+bk.getName()+" ");
			return bk;
		} catch (NumberFormatException e) {
			throw new ConverterException(new FacesMessage(String.format("%s is not a valid Library Id", submittedValue)), e);
		}
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component, Object modelValue) {
		if (modelValue == null) {
			return "";
		}

		if (modelValue instanceof Library) {
			return ((Library) modelValue).getId() + "";
		} else {
			throw new ConverterException(new FacesMessage(String.format("%s is not a valid Library", modelValue)));
		}
	}
}
