package beans;

import jakarta.servlet.http.HttpServletRequest;

import lombok.Data;

@Data
public class AccountsData {
	private int accountId;
	private String name;
	private String mail;
	private String pass;
	private String confirmPass;
	private String authority;
	
	public AccountsData(String name, String mail, String pass, String confirmPass, String authorityStr) {
		this.name = name;
		this.mail = mail;
		this.pass = pass;
		this.confirmPass = confirmPass;
		this.authority = authorityStr;
	}
	public AccountsData(int accountId, String name, String mail, String pass, String confirmPass, String authorityStr) {
		this.accountId = accountId;
		this.name = name;
		this.mail = mail;
		this.pass = pass;
		this.confirmPass = confirmPass;
		this.authority = authorityStr;
	}

	public AccountsData(HttpServletRequest request) {
		this.name = request.getParameter("name");
		this.mail = request.getParameter("mail");
		this.pass = request.getParameter("pass");
		this.confirmPass = request.getParameter("confirmPass");
		this.authority = request.getParameter("authority");
	}
}
