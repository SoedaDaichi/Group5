package services;

import java.util.Queue;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

import beans.SalesForm;

public class SessionFormService {

	public static void salesFormSession(HttpServletRequest request) {
		HttpSession session = request.getSession(false);
		Queue<?> errorQueue = (Queue<?>) session.getAttribute("errorQueue"); // ?はどの型でもOK
		if (errorQueue != null && !errorQueue.isEmpty()) {
			SalesForm salesform = (SalesForm) session.getAttribute("salesform");
			if (salesform != null) {
				request.setAttribute("salesform", salesform);
				session.removeAttribute("salesform");
			}
		}
	}

	public static void salesRegisterFormSession(HttpServletRequest request) {
		HttpSession session = request.getSession(false);
		Queue<?> errorQueue = (Queue<?>) session.getAttribute("errorQueue"); // ?はどの型でもOK
		if (errorQueue != null && !errorQueue.isEmpty()) {
			SalesForm RegisterSalesForm = (SalesForm) session.getAttribute("RegisterSalesForm");
			if (RegisterSalesForm != null) {
				request.setAttribute("RegisterSalesForm", RegisterSalesForm);
				session.removeAttribute("RegisterSalesForm");
			}
		}
	}
}
