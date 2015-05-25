package github.vikram.javaee.beanvalidation;

import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import javax.validation.groups.Default;

import junit.framework.TestCase;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

public class AdressIT extends TestCase {
	
	private ValidatorFactory vf;
	private Validator validator;
	
	@BeforeClass
	public void initialize() {
		vf = Validation.buildDefaultValidatorFactory();
		validator = vf.getValidator();
	}
	
	@AfterClass
	public void shutdown() {
		vf.close();
	}
	
	public void printViolations(Set<ConstraintViolation<Address>> violations) {
		
		
		for(ConstraintViolation<Address> v : violations) {
			System.out.println("####################");
			System.out.println("Violation Property: " + v.getPropertyPath());
			System.out.println("Violation Message: " + v.getMessage());
			System.out.println("Violation Bean: " + v.getRootBean().getClass());
			System.out.println("####################");
		}
		
	}
	
	@Test
	public void addressStreetMustNotBeNull() {
		Address a = new Address(null, "Asilomar Terrace", "Sunnyvale", "CA", "94086", "USA");
		Set<ConstraintViolation<Address>> violations = validator.validate(a, Default.class);
		printViolations(violations);
		assertEquals(1, violations.size());
	}

}
