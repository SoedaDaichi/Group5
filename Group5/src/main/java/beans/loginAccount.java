package beans;

import lombok.Data;

@Data
public class loginAccount {
	private int account_id;
	private String name;
	private String mail;
	private String pass;
	private int authority;
}
