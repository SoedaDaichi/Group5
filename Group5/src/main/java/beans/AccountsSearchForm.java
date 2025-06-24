package beans;

import lombok.Data;

@Data
public class AccountsSearchForm {
	private String name;
	private String mail;
	private int authority0;
	private int authority1;
	private int authority2;
	private int authority3;

	public AccountsSearchForm(String name, String mail, int authority0, int authority1, int authority2,
			int authority3) {
		this.name = name;
		this.mail = mail;
		this.authority0 = authority0;
		this.authority1 = authority1;
		this.authority2 = authority2;
		this.authority3 = authority3;
	}
}
