package github.vikram.beanvalidation;

import javax.inject.Inject;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.jboss.weld.environment.se.Weld;
import org.jboss.weld.environment.se.WeldContainer;

public class ZipCodeValidator implements ConstraintValidator<ZipCode, String>{

	@USA
	ZipCodeChecker zipChecker = null;
	
	private static Weld weld;
	private static WeldContainer container;
	
	
	public void initialize(ZipCode constraintAnnotation) {
		if (zipChecker == null) {
			weld = new Weld();
			container = weld.initialize();
			zipChecker = container.instance().select(ZipCodeChecker.class)
					.get();
			weld.shutdown();
		}
	}

	public boolean isValid(String value, ConstraintValidatorContext context) {
		return (zipChecker.isZipCodeValid(value));
		
	}

}
