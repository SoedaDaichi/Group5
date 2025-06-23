package services;

import java.util.ArrayList;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

import beans.Accounts;
import beans.AccountsSearchForm;
import beans.SalesData;
import beans.SalesSearchForm;
import daos.AccountsDao;
import daos.SalesDao;

public class SearchService {

	public static void SalesSearchService(HttpServletRequest request) {
		HttpSession session = request.getSession();
		SalesSearchForm ssform = (SalesSearchForm) session.getAttribute("ssform");

		SalesDao sd = new SalesDao();
		ArrayList<SalesData> salesList = sd.selectSearch(ssform);

		request.setAttribute("salesList", salesList);
	}

	public static void AccountsSearchService(HttpServletRequest request) {
		HttpSession session = request.getSession();
		
		AccountsSearchForm asform = (AccountsSearchForm)session.getAttribute("asform");
		
		AccountsDao ad = new AccountsDao();
		ArrayList<Accounts> accountsList = ad.selectSearch(asform);
		
		request.setAttribute("accountsList", accountsList);

	}

}
