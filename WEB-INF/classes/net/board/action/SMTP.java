package net.board.action;

import javax.mail.Authenticator;
import javax.mail.PasswordAuthentication;

public class SMTP extends Authenticator {
	protected PasswordAuthentication getPasswordAuthentication() {
		return new PasswordAuthentication("milkrenn@gmail.com", "dks01096");
		
	}
}
