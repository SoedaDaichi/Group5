package beans;

import lombok.Data;

@Data
public class AccountsSearchForm {
	private String name;
	private String mail;
	private int authority_0;
	private int authority_1;
	private int authority_2;
	private int authority_3;

	public AccountsSearchForm(String name, String mail, int authority_0, int authority_1, int authority_2,
			int authority_3) {
		this.name = name;
		this.mail = mail;
		this.authority_0 = authority_0;
		this.authority_1 = authority_1;
		this.authority_2 = authority_2;
		this.authority_3 = authority_3;
	}
}
