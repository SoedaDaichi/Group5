package beans;

import lombok.Data;

@Data
public class AccountsData {
	private String name;
	private String mail;
	private String pass;
	private String confirmPass;
	private String authority;
	
	public AccountsData(String name, String mail, String pass, String confirmPass, String authority) {
		this.name = name;
		this.mail = mail;
		this.pass = pass;
		this.confirmPass = confirmPass;
		this.authority = authority;
	}
}
