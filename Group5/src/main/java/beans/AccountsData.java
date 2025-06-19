package beans;

import lombok.Data;

@Data
public class AccountsData {
	private String name;
	private String mail;
	private String pass;
	private String confirm_pass;
	private String authorityStr;
	
	public AccountsData(String name, String mail, String pass, String confirm_pass, String authorityStr) {
		this.name = name;
		this.mail = mail;
		this.pass = pass;
		this.confirm_pass = confirm_pass;
		this.authorityStr = authorityStr;
	}
}
