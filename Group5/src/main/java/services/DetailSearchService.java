package services;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

import beans.Accounts;
import beans.SalesData;

public class DetailSearchService {

	public static void createSalesDetail(HttpServletRequest request) {
		int sale_id = Integer.valueOf(request.getParameter("sale_id"));
		HttpSession session = request.getSession();
		
		SalesDao sd = new SalesDao();
		SalesData salesdata = sd.identificationSalesData(sale_id);

		session.setAttribute("sale_id", sale_id);
		session.setAttribute("salesdata", salesdata);
	}
	
	public static void createAccountsDetail(HttpServletRequest request) {
		int account_id = Integer.valueOf(request.getParameter("account_id"));
		HttpSession session = request.getSession();
		
		S0041Dao s0041dao = new S0041Dao();
		Accounts accountdata = s0041dao .getAccountsByAccount_id(account_id);
		
		session.setAttribute("account_id", account_id);
		session.setAttribute("accountdata", accountdata);
	}
}
