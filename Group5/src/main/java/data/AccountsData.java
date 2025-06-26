package data;

import jakarta.servlet.http.HttpServletRequest;

import lombok.Data;

@Data
public class AccountsData {
	private String accountIdStr;
	private Integer accountId;
	private String name;
	private String mail;
	private String pass;
	private String confirmPass;
	private String authorityStr;
	private Integer authority;
	
	public AccountsData(String name, String mail, String pass, String confirmPass, Integer authority) {
		this.name = name;
		this.mail = mail;
		this.pass = pass;
		this.confirmPass = confirmPass;
		this.authority = authority;
	}

	public AccountsData(HttpServletRequest request) {
		this.name = request.getParameter("name");
		this.mail = request.getParameter("mail");
		this.pass = request.getParameter("pass");
		this.confirmPass = request.getParameter("confirmPass");
		this.authority = Integer.valueOf(request.getParameter("authority"));

	}

	public AccountsData(Integer accountId, String name, String mail, String pass, Integer authority) {
		this.accountId = accountId;
		this.name = name;
		this.mail = mail;
		this.pass = pass;
		this.authority = authority;
	}
}
