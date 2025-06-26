package form;

import lombok.Data;

@Data
public class Accounts {
	private Integer accountId;
	private String name;
	private String mail;
	private String pass;
	private String confirmPass;
	private Integer authority;
	
//	public Accounts(int account_id, String name, String mail, String pass, String confirm_pass, int authority) {
//		this.account_id = account_id;
//		this.name = name;
//		this.mail = mail;
//		this.pass = pass;
//		this.confirm_pass = confirm_pass;
//		this.authority = authority;
//	}
}
