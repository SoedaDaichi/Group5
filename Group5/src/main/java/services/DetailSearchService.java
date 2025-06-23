package services;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

import beans.Accounts;
import beans.SalesData;
import daos.AccountsDao;
import daos.SalesDao;

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
		int accountId = Integer.valueOf(request.getParameter("accountId"));
		HttpSession session = request.getSession();

		AccountsDao ad = new AccountsDao();
		Accounts accountData = ad.getAccountsByAccount_id(accountId);
		
		

		session.setAttribute("accountId", accountId);
		session.setAttribute("accountData", accountData);
	}
}
