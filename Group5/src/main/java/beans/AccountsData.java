package beans;

import jakarta.servlet.http.HttpServletRequest;

import daos.AccountsDao;
import lombok.Data;

@Data
public class AccountsData {
	private String name;
	private String mail;
	private String pass;
	private String confirm_pass;
	private String authority;
	
	public AccountsData(String name, String mail, String pass, String confirm_pass, String authorityStr) {
		this.name = name;
		this.mail = mail;
		this.pass = pass;
		this.confirm_pass = confirm_pass;
		this.authority = authorityStr;
	}

	public AccountsData(HttpServletRequest request, AccountsDao ad) {
		this.name = request.getParameter("name");
		this.mail = request.getParameter("mail");
		this.pass = request.getParameter("pass");
		this.confirmPass = request.getParameter("confirmPass");
		this.authority = request.getParameter("authority");
	}
}
