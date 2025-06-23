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
		
		session.getAttribute("salesdata");
		Queue<?> errorQueue = (Queue<?>) session.getAttribute("errorQueue"); // ?はどの型でもOK
		
		if (errorQueue == null || errorQueue.isEmpty()) {
			SalesData salesdata = (SalesData) session.getAttribute("salesdata");
			request.setAttribute("salesdata", salesdata);
		}
	}
	
	public static void SalesRegisterDataSession(HttpServletRequest request) {
		SalesDao sd = new SalesDao();
		
		SalesData RegisterSaleData = new SalesData(request, sd);
		
		request.setAttribute("RegisterSaleData", RegisterSaleData);
	}
	
	public static void AccountsDataSession(HttpServletRequest request) {
		HttpSession session = request.getSession(false);
		
		session.getAttribute("accountdata");
		Queue<?> errorQueue = (Queue<?>) session.getAttribute("errorQueue");
		
		if (errorQueue == null || errorQueue.isEmpty()) {
			AccountsData accountsdata = (AccountsData) session.getAttribute("accountssdata");
			request.setAttribute("accountsdata", accountsdata);
		}
	}
	
	public static void AccountsRegisterDataSession(HttpServletRequest request) {
		
		AccountsData RegisterAccountsdata = new AccountsData(request);
		
		request.setAttribute("RegisterAccountsdata", RegisterAccountsdata);
	}
}
