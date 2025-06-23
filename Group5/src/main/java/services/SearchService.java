package services;

import java.util.ArrayList;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

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

	}

}
