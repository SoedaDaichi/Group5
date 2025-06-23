package services;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

import beans.SalesData;

public class SessionConfirmService {
	public static void S0011SessionConfirmGetService(HttpServletRequest request) {
		HttpSession session = request.getSession(false);
		
		SalesData RegisterSalesdata = (SalesData) session.getAttribute("RegisterSalesdata");

		request.setAttribute("salesdata", RegisterSalesdata);
	}
	
	public static SalesData S0011SessionConfirmPostService(HttpServletRequest request) {
		HttpSession session = request.getSession(false);
		
		SalesData RegisterSalesdata = (SalesData) session.getAttribute("RegisterSalesdata");
		session.removeAttribute("RegisterSalesdata"); // Filter範囲外
 
	    return RegisterSalesdata;
	}

}
