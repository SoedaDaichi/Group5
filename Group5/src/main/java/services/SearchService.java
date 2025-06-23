package services;

import java.util.ArrayList;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

import beans.Accounts;
import beans.AccountsSearchForm;
import beans.SalesData;
import beans.SalesSearchForm;
import daos.SalesDao;

public class SearchService {

	public static void SalesSearchService(HttpServletRequest request) {
		HttpSession session = request.getSession(false);
		SalesSearchForm ssform = (SalesSearchForm) session.getAttribute("ssform");

		SalesDao sd = new SalesDao();
		ArrayList<SalesData> salesList = sd.selectSearch(ssform);

		request.setAttribute("salesList", salesList);
	}

	public static void AccountsSearchService(HttpServletRequest request) {
		HttpSession session = request.getSession(false);
		
		AccountsSearchForm asform = (AccountsSearchForm)session.getAttribute("asform");
		
		S0040Dao s0040dao = new S0040Dao();
		ArrayList<Accounts> accountsList = s0040dao.select(asform);
		
		request.setAttribute("accountsList", accountsList);

	}

}
