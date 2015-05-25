package github.vikram.javaee.beanvalidation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;
import javax.validation.ReportAsSingleViolation;

@Constraint(validatedBy = { ZipCodeValidator.class })
@Target({ElementType.FIELD})
@Retention(value=RetentionPolicy.RUNTIME)
@ReportAsSingleViolation
public @interface ZipCode {
	
	
	String message() default "{vikram.javaee.beanvalidation.ZipCode.message}";
	Class<?>[] groups() default {};
	Class<? extends Payload>[] payload() default {};

}
