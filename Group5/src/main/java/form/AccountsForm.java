package form;

import jakarta.servlet.http.HttpServletRequest;

import lombok.Data;

@Data
public class AccountsForm {

	private String name;
	private String mail;
	private String pass;
	private String confirmPass;
	private String authority;
	
	public AccountsForm(String name, String mail, String authority) {
		this.name = name;
		this.mail = mail;
		this.authority = authority;
	}

	public AccountsForm(HttpServletRequest request) {
		this.name = request.getParameter("name");
		this.mail = request.getParameter("mail");
		this.authority = request.getParameter("authority");
		this.pass = request.getParameter("pass");
		this.confirmPass = request.getParameter("confirmPass");
	}
}
