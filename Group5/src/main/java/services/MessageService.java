package services;

import java.util.Map;
import java.util.Queue;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

public class MessageService {
	public static Map<String, String> processSessionMessages(HttpServletRequest request) {
		HttpSession session = request.getSession();
		return processMessage(session, "errorQueue");
	}

	@SuppressWarnings("unchecked")
	public static Map<String, String> processMessage(HttpSession session, String sessionKey) {
		Queue<Map<String, String>> queue = (Queue<Map<String, String>>) session.getAttribute(sessionKey);
		if (queue != null && !queue.isEmpty()) {
			Map<String, String> message = queue.poll();
			if (queue.isEmpty()) {
				session.removeAttribute(sessionKey);
			}
			return message; // 取り出したメッセージだけ返す
		}
		return null;
	}

	// jspにset&session削除用メソッド
	public static void moveAttribute(HttpSession session, HttpServletRequest request, String attributeName,
			Object value) {
		if (value != null) {
			request.setAttribute(attributeName, value);
			session.removeAttribute(attributeName);
		}
	}
}
