package beans;

import lombok.Data;

@Data
public class AccountsForm {

	private String name;
	private String mail;
	private String authority;
	
	public AccountsForm(String name, String mail, String authority) {
		this.name = name;
		this.mail = mail;
		this.authority = authority;
	}
}
