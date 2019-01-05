package labs.converter;

import labs.domain.Book;
import labs.service.BookService;

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
public class BookValueConventer implements Converter {

	@Inject
	Logger logger;

	@Inject
	private BookService bookService;

	@Override
	public Book getAsObject(FacesContext context, UIComponent component, String submittedValue) {
		logger.info("Submited value: "+ submittedValue);
		if (submittedValue == null || submittedValue.isEmpty()) {
			return null;
		}

		try {
			Book bk = bookService.find(submittedValue);
			logger.info("Id and value: "+bk.getIsbn()+" "+bk.getName()+" ");
			return bk;
		} catch (NumberFormatException e) {
			throw new ConverterException(new FacesMessage(String.format("%s is not a valid Book ISBN", submittedValue)), e);
		}
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component, Object modelValue) {
		if (modelValue == null) {
			return "";
		}

		if (modelValue instanceof Book) {
			return ((Book) modelValue).getIsbn();
		} else {
			throw new ConverterException(new FacesMessage(String.format("%s is not a valid Book", modelValue)));
		}
	}
}
