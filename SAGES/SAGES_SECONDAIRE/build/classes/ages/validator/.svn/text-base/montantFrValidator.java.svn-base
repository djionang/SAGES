package ages.validator;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;

/**
 * Validateur du montant d'un montant floatant
 * @author Bri
 *
 */

@FacesValidator("MontantFrValidator")
public class montantFrValidator implements Validator{
	private static final String FLOAT_PATTERN = "([0-9])+([0-9])*(\\.([0-9]){0,1}([0-9]){0,1}){0,1}";
	 
	public montantFrValidator(){
		
	}
	
	@Override
	public void validate(FacesContext context, UIComponent component,
			Object value) throws ValidatorException {
		
		// si la valeur est vide on ressort sans probleme; le validateur de vide va gerer
		if(!value.toString().matches(FLOAT_PATTERN)){
  
			FacesMessage msg = 
				new FacesMessage("Montant invalide, "+value.toString()+" doit etre un nombre entier ou réel.", "format incompatible.");
			msg.setSeverity(FacesMessage.SEVERITY_ERROR);
			throw new ValidatorException(msg);
 
		}
	}
}
