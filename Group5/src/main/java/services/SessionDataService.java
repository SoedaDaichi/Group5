package services;

import java.util.Queue;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

import beans.AccountsData;
import beans.SalesData;
import daos.SalesDao;

public class SessionDataService {
	
	public static void SalesDataSession(HttpServletRequest request) {
		HttpSession session = request.getSession(false);
		
		session.getAttribute("salesData");
		Queue<?> errorQueue = (Queue<?>) session.getAttribute("errorQueue"); // ?はどの型でもOK
		
		if (errorQueue == null || errorQueue.isEmpty()) {
			SalesData salesData = (SalesData) session.getAttribute("salesData");
			request.setAttribute("salesData", salesData);
		}
	}
	
	public static void SalesRegisterDataSession(HttpServletRequest request) {
		SalesDao sd = new SalesDao();
		
		SalesData registerSaleData = new SalesData(request, sd);
		
		request.setAttribute("registerSaleData", registerSaleData);
	}
	
	public static void AccountsDataSession(HttpServletRequest request) {
		HttpSession session = request.getSession(false);
		
		session.getAttribute("accountdata");
		Queue<?> errorQueue = (Queue<?>) session.getAttribute("errorQueue");
		
		if (errorQueue == null || errorQueue.isEmpty()) {
			AccountsData accountsData = (AccountsData) session.getAttribute("accountssData");
			request.setAttribute("accountsData", accountsData);
		}
	}
	
	public static void AccountsRegisterDataSession(HttpServletRequest request) {
		
		AccountsData registerAccountsData = new AccountsData(request);
		
		request.setAttribute("registerAccountsData", registerAccountsData);
	}
}
