package services;

import java.util.Map;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

import beans.SalesForm;

public class S0020ErrorMessageService {
	public static void processSessionMessages(HttpServletRequest request) {
		HttpSession session = request.getSession(false);

		ErrorMessageService.processSessionMessages(request);

		SalesForm ssform = (SalesForm) session.getAttribute("ssform");
		@SuppressWarnings("unchecked")
		Map<String, String> notFound = (Map<String, String>) session.getAttribute("NotFound");

		ErrorMessageService.moveAttribute(session, request, "NotFound", notFound);
		ErrorMessageService.moveAttribute(session, request, "ssform", ssform);
	}
}
