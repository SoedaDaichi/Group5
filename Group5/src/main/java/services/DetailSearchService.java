package services;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

import beans.Accounts;
import beans.SalesData;
import daos.AccountsDao;
import daos.SalesDao;

public class DetailSearchService {

	public static void createSalesDetail(HttpServletRequest request) {
		int saleId = Integer.valueOf(request.getParameter("sale_id"));
		HttpSession session = request.getSession();

		SalesDao sd = new SalesDao();
		SalesData salesData = sd.identificationSalesData(saleId);

		session.setAttribute("saleId", saleId);
		session.setAttribute("salesData", salesData);
		
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
