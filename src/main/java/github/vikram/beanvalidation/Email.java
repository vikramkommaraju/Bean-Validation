package github.vikram.beanvalidation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;
import javax.validation.ReportAsSingleViolation;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Target({ElementType.TYPE, ElementType.METHOD, ElementType.PARAMETER, ElementType.FIELD})
@Retention(value=RetentionPolicy.RUNTIME)
@NotNull
@Size(min=7)
@Pattern(regexp = "[a-z0-9!#$%&'*+/=?^_`{|}∼-]+(?:\\."
        + "[a-z0-9!#$%&'*+/=?^_`{|}∼-]+)*"
        + "@(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?")
@ReportAsSingleViolation
@Constraint(validatedBy = {})
public @interface Email {
	
	String message() default "{vikram.javaee.beanvalidation.Email.message}";
	Class<?>[] groups() default {};
	Class<? extends Payload>[] payload() default {};
	
	int min() default 1;
	int max() default 100;
	

}
