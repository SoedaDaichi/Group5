package services;

import java.util.Queue;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

import beans.SalesData;
import daos.S0010Dao;

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
		S0010Dao s0010dao = new S0010Dao();
		
		SalesData RegisterSaleData = new SalesData(request, s0010dao);
		
		request.setAttribute("RegisterSaleData", RegisterSaleData);
	}
}
