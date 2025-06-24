package services;

import java.util.Queue;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

import beans.AccountsData;
import beans.SalesForm;

public class SessionFormService {

	public static void sessionSalesForm(HttpServletRequest request) {
		HttpSession session = request.getSession(false);
		Queue<?> errorQueue = (Queue<?>) session.getAttribute("errorQueue"); // ?はどの型でもOK
		if (errorQueue != null && !errorQueue.isEmpty()) {
			SalesForm salesForm = (SalesForm) session.getAttribute("salesForm");
			if (salesForm != null) {
				request.setAttribute("salesForm", salesForm);
				session.removeAttribute("salesForm");
			}
		}
	}

	public static void sessionSalesRegisterForm(HttpServletRequest request) {
		HttpSession session = request.getSession(false);
		Queue<?> errorQueue = (Queue<?>) session.getAttribute("errorQueue"); // ?はどの型でもOK
		if (errorQueue != null && !errorQueue.isEmpty()) {
			SalesForm registerSalesForm = (SalesForm) session.getAttribute("registerSalesForm");
			if (registerSalesForm != null) {
				request.setAttribute("registerSalesForm", registerSalesForm);
				session.removeAttribute("registerSalesForm");
			}
		}
	}
	
	public static void sessionAccountsRegisterForm(HttpServletRequest request) {
		HttpSession session = request.getSession(false);
		Queue<?> errorQueue = (Queue<?>) session.getAttribute("errorQueue"); // ?はどの型でもOK
		if (errorQueue != null && !errorQueue.isEmpty()) {
			AccountsData registerAccountsForm = (AccountsData) session.getAttribute("registerAccountsForm");
			if (registerAccountsForm != null) {
				request.setAttribute("registerAccountsForm", registerAccountsForm);
				session.removeAttribute("registerAccountsForm");
			}
		}
	}
}
