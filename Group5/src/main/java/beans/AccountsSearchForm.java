package beans;

import jakarta.servlet.http.HttpServletRequest;

import lombok.Data;

@Data
public class AccountsSearchForm {
	private String name;
	private String mail;
	private String[] authorityList;

	public AccountsSearchForm(HttpServletRequest request) {
		this.name = request.getParameter("name");
		this.mail = request.getParameter("mail");
		this.authorityList = request.getParameterValues("authority");
	}
	public AccountsSearchForm(String name, String mail, String[] authorityList) {
		this.name = name;
		this.mail = mail;
		this.authorityList = authorityList;
	}
}
