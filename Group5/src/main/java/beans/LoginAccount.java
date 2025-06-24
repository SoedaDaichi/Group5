package beans;

import lombok.Data;

@Data
public class LoginAccount {
	private int accountId;
	private String name;
	private String mail;
	private String pass;
	private int authority;
}
