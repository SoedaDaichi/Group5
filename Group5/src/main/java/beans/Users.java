package beans;

import lombok.Data;

@Data
public class Users {
	private int account_id;
	private String name;
	private String mail;
	private String pass;
	private int authority;
}
