
import javax.mail.Authenticator;
import javax.mail.PasswordAuthentication;
public class Gmail extends Authenticator {

		PasswordAuthentication pa;
		
		public Gmail() {
			String mailId = "milkrenn";
			String mailPass = "dks01096";
			
			pa = new PasswordAuthentication("milkrenn", "dks01096");
		}
	
	@Override
	protected PasswordAuthentication getPasswordAuthentication() {
		return pa;
		
	}
	
}
