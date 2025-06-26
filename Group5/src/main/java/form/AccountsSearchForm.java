package form;

import jakarta.servlet.http.HttpServletRequest;

import lombok.Data;

@Data
public class AccountsSearchForm {
	private String name;
	private String mail;
	private String[] authority;

	public AccountsSearchForm(String name, String mail, String[] authority) {
		this.name = name;
		this.mail = mail;
		this.authority = authority;
	}

	public AccountsSearchForm(HttpServletRequest request) {
		this.name = request.getParameter("name");
		this.mail = request.getParameter("mail");
		this.authority = request.getParameterValues("authority");
	}
}
