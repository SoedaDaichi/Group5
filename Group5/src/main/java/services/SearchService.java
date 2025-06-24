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

	public static ArrayList<SalesData> SalesSearchService(HttpServletRequest request) {
		HttpSession session = request.getSession();
		SalesSearchForm ssForm = (SalesSearchForm) session.getAttribute("ssForm");

		SalesDao sd = new SalesDao();
		ArrayList<SalesData> salesList = sd.selectSearch(ssForm);

		return salesList;
	}

	public static void AccountsSearchService(HttpServletRequest request) {
		HttpSession session = request.getSession();
		
		AccountsSearchForm asForm = (AccountsSearchForm)session.getAttribute("asfForm");
		
		AccountsDao ad = new AccountsDao();
		ArrayList<Accounts> accountsList = ad.selectSearch(asForm);
		
		request.setAttribute("accountsList", accountsList);

	}

}
