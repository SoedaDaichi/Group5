package services;

import java.util.ArrayList;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;


import beans.Accounts;
import beans.AccountsSearchForm;
import beans.Sales;

import beans.SalesData;

import beans.SalesSearchForm;
import daos.S0020Dao;
import daos.S0040Dao;

public class SearchService {

	public static void SalesSearchService(HttpServletRequest request) {
		HttpSession session = request.getSession(false);
		SalesSearchForm ssform = (SalesSearchForm) session.getAttribute("ssform");

		S0020Dao s0020dao = new S0020Dao();
		ArrayList<SalesData> salesList = s0020dao.select(ssform);

		request.setAttribute("salesList", salesList);
	}

	public static void AccountsSearchService(HttpServletRequest request) {

		HttpSession session = request.getSession(false);
		AccountsSearchForm asform = (AccountsSearchForm)session.getAttribute("asform");
		
		S0040Dao s0040dao = new S0040Dao();
		ArrayList<Accounts> accountsList = s0040dao.select(asform);
		
		request.setAttribute("accountList", accountsList);

	}

}
