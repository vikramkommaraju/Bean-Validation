package vikram.javaee.beanvalidation;

@USA
public class ZipCodeChecker {

	public boolean isZipCodeValid(String zipCode) {
		if(zipCode == null) {
			return true;
		}
		
		if(zipCode.length() != 5) {
			return false;
		}
		
		return true;
	}
}
