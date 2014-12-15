package validators;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.ValidatorException;

@FacesValidator("IsPositiveValidator")
public class IsPositive {
	public void validate(FacesContext fc, UIComponent uic, Object o)
			throws ValidatorException {
		
		String positivo = String.valueOf((Integer)o);
		
		Integer i = Integer.valueOf(positivo);
		
		if(i<0) {
			FacesMessage msg = new FacesMessage("Escreva uma idade positiva!");
			msg.setSeverity(FacesMessage.SEVERITY_ERROR);
			throw new ValidatorException(msg);
		}
	}

}