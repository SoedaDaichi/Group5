package services;

import java.util.ArrayList;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

import beans.Sales;
import beans.SalesSearchForm;
import daos.S0020Dao;

public class SearchService {
	
	public static void SalesSearchService(HttpServletRequest request) {
		HttpSession session = request.getSession(false);
		SalesSearchForm ssform = (SalesSearchForm) session.getAttribute("ssform"); 
		
		S0020Dao s0020dao = new S0020Dao();
		ArrayList<Sales> salesList = s0020dao.select(ssform);
		
		request.setAttribute("salesList", salesList);
	}
	
	public static void AccountsSearchService(HttpServletRequest request) {
		
	}

}
