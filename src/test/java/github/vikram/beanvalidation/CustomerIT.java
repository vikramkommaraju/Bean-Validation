package vikram.javaee.beanvalidation;

import static org.junit.Assert.assertEquals;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

public class CustomerIT {
	
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
	
	public void printViolations(Set<ConstraintViolation<Customer>> violations) {
		
		
		for(ConstraintViolation<Customer> v : violations) {
			System.out.println("####################");
			System.out.println("Violation Property: " + v.getPropertyPath());
			System.out.println("Violation Message: " + v.getMessage());
			System.out.println("Violation Bean: " + v.getRootBean().getClass());
			System.out.println("####################");
		}
		
	}
	
	public Date createDateObject(String inputStr) {
		DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
		Date d = null;
		try {
			d = dateFormat.parse(inputStr);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return d;
	}
	
	@Test
	public void TestPostiveCase() {
		Address a = new Address("973", "A Terrace", "Los Angeles", "CA", "90009", "USA");
		Date d = createDateObject("1-1-2010");
		Customer c = new Customer("John", "Smith", "john@abc.com", "111-222-3333",d, a);
		Set<ConstraintViolation<Customer>> violations = validator.validate(c);
		printViolations(violations);
		assertEquals(0, violations.size());
	}
	

	@Test
	public void TestFirstNameNull() {
		Address a = new Address("973", "A Terrace", "Los Angeles", "CA", "90009", "USA");
		Date d = createDateObject("1-1-2010");
		Customer c = new Customer(null, "Smith", "john@abc.com", "111-222-3333", d, a);
		Set<ConstraintViolation<Customer>> violations = validator.validate(c);
		printViolations(violations);
		assertEquals(1, violations.size());
	}
	
	@Test
	public void TestFutureDate() {
		Address a = new Address("973", "A Terrace", "Los Angeles", "CA", "90009", "USA");
		Date d = createDateObject("1-1-2020");
		Customer c = new Customer("John", "Smith", "john@abc.com", "111-222-3333", d, a);
		Set<ConstraintViolation<Customer>> violations = validator.validate(c);
		printViolations(violations);
		assertEquals(1, violations.size());
	}
	
	@Test
	public void TestFirstNameSize() {
		Address a = new Address("973", "A Terrace", "Los Angeles", "CA", "90009", "USA");
		Date d = createDateObject("1-1-2010");
		Customer c = new Customer("J", "Smith", "john@abc.com", "111-222-3333", d, a);
		Set<ConstraintViolation<Customer>> violations = validator.validate(c);
		printViolations(violations);
		assertEquals(1, violations.size());
	}
	
	@Test
	public void TestEmailNull() {
		Address a = new Address("973", "A Terrace", "Los Angeles", "CA", "90009", "USA");
		Date d = createDateObject("1-1-2010");
		Customer c = new Customer("John", "Smith", null, "111-222-3333", d, a);
		Set<ConstraintViolation<Customer>> violations = validator.validate(c);
		printViolations(violations);
		assertEquals(1, violations.size());
	}
	
	@Test
	public void TestEmailFormat() {
		Address a = new Address("973", "A Terrace", "Los Angeles", "CA", "90009", "USA");
		Date d = createDateObject("1-1-2010");
		Customer c = new Customer("John", "Smith", "email", "111-222-3333", d, a);
		Set<ConstraintViolation<Customer>> violations = validator.validate(c);
		printViolations(violations);
		assertEquals(1, violations.size());
	}
	


}
