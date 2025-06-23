package services;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

import beans.SalesData;

public class SessionConfirmService {
	public static void S0011SessionConfirmGetService(HttpServletRequest request) {
		HttpSession session = request.getSession(false);
		
		SalesData registerSalesData = (SalesData) session.getAttribute("registerSalesData");

		request.setAttribute("salesData", registerSalesData);
	}
	
	public static SalesData S0011SessionConfirmPostService(HttpServletRequest request) {
		HttpSession session = request.getSession(false);
		
		SalesData registerSalesData = (SalesData) session.getAttribute("registerSalesData");
		session.removeAttribute("RegisterSalesdata"); // Filter範囲外
 
	    return registerSalesData;
	}

}
