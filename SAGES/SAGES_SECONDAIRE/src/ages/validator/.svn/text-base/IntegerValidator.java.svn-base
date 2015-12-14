package ages.validator;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;


/**
 * Validateur des nombres entiers
 * @author Bri
 *
 */

@FacesValidator("IntegerValidator")
public class IntegerValidator implements Validator{
	private static final String INT_PATTERN = "([1-9])([0-9])*";
	 
	private Pattern pattern;
	private Matcher matcher;
 
	public  IntegerValidator() {
		// TODO Auto-generated constructor stub
		pattern = Pattern.compile(INT_PATTERN);
	}

	@Override
	public void validate(FacesContext context, UIComponent component,
			Object value) throws ValidatorException {
		
		// si la valeur est vide on ressort sans probleme; le validateur de vide va gerer
		if((value==null)||value.toString().isEmpty()) return;
 
		matcher = pattern.matcher(value.toString());
		if(!matcher.matches()){
 
			FacesMessage msg = 
				new FacesMessage("Format nombre incorrect.", 
						"donnée incompatible.");
			msg.setSeverity(FacesMessage.SEVERITY_ERROR);
			throw new ValidatorException(msg);
 
		}
	}
}
