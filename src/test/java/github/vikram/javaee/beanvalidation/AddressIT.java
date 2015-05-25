package github.vikram.javaee.beanvalidation;

import static org.junit.Assert.*;
import github.vikram.javaee.beanvalidation.Address;

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

public class AddressIT {
	
	private static ValidatorFactory vf;
	private static Validator validator;
	
	@BeforeClass
	public static void init() {
		vf = Validation.buildDefaultValidatorFactory();
		validator = vf.getValidator();
	}
	
	@AfterClass
	public static void shutdown() {
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
	public void TestPostiveCase() {
		Address a = new Address("973", "Asilomar Terrace", "Sunnyvale", "CA", "94086", "USA");
		Set<ConstraintViolation<Address>> violations = validator.validate(a);
		printViolations(violations);
		assertEquals(0, violations.size());
	}
	
	@Test
	public void TestStreetNull() {
		Address a = new Address(null, "Asilomar Terrace", "Sunnyvale", "CA", "94086", "USA");
		Set<ConstraintViolation<Address>> violations = validator.validate(a);
		printViolations(violations);
		assertEquals(1, violations.size());
	}
	
	@Test
	public void TestZipCodeNull() {
		Address a = new Address("973", "Asilomar Terrace", "Sunnyvale", "CA", null, "USA");
		Set<ConstraintViolation<Address>> violations = validator.validate(a);
		printViolations(violations);
		assertEquals(1, violations.size());
	}
	
	@Test
	public void TestZipCodeFormat() {
		Address a = new Address("973", "Asilomar Terrace", "Sunnyvale", "CA", "123", "USA");
		Set<ConstraintViolation<Address>> violations = validator.validate(a);
		printViolations(violations);
		assertEquals(1, violations.size());
	}
	
	

}
