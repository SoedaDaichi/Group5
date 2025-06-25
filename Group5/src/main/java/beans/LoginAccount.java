package beans;

import lombok.Data;

@Data
public class LoginAccount {
	private int accountId;
	private String name;
	private String mail;
	private String pass;
	private int authority;
	
	public LoginAccount() {
	}

	public LoginAccount(int accountId, String name, String mail, String pass, int authority) {
		this.accountId = accountId;
		this.name = name;
		this.mail = mail;
		this.pass = pass;
		this.authority = authority;
	}
}
