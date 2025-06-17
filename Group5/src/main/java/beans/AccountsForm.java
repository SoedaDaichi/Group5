package beans;

import lombok.Data;

@Data
public class AccountsForm {

	private String name;
	private String mail;
	private String roleStr;
	
	public AccountsForm(String name, String mail, String roleStr) {
		this.name = name;
		this.mail = mail;
		this.roleStr = roleStr;
	}
}
