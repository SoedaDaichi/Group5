package services;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

import beans.AccountsData;

public class S0042DataSetService {
	
	public static AccountsData setAccountData(HttpServletRequest request) {
		HttpSession session = request.getSession();
		
	String name = request.getParameter("name");
	String mail = request.getParameter("mail");
	String pass = request.getParameter("pass");
	String confirmPass = request.getParameter("confirmPass");
	String authorityStr = request.getParameter("authority");
	int accountId = (int) session.getAttribute("accountId");

	return new AccountsData(accountId, name, mail, pass, confirmPass, authorityStr);
	}
}
