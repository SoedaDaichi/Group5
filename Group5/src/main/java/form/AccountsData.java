package form;

import jakarta.servlet.http.HttpServletRequest;

import lombok.Data;

@Data
public class AccountsData {
	private String name;
	private String mail;
	private String pass;
	private String confirmPass;
	private String authority;
	
	public AccountsData(String name, String mail, String pass, String confirmPass, String authority) {
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
		this.authority = request.getParameter("authority");

	}
}
