package services;

import java.util.Map;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

import beans.SalesForm;

public class S0020ErrorMessageService {
	public static void processSessionMessages(HttpServletRequest request) {
		HttpSession session = request.getSession(false);

		ErrorMessageService.processSessionMessages(request);

		SalesForm ssForm = (SalesForm) session.getAttribute("ssForm");
		@SuppressWarnings("unchecked")
		Map<String, String> notFound = (Map<String, String>) session.getAttribute("notFound");

		ErrorMessageService.moveAttribute(session, request, "NotFound", notFound);
		ErrorMessageService.moveAttribute(session, request, "ssForm", ssForm);
	}
}
