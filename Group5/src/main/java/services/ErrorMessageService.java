package services;

import java.util.Map;
import java.util.Queue;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

public class ErrorMessageService {
	public static void processSessionMessages(HttpServletRequest request) {
		HttpSession session = request.getSession();

		// エラーメッセージの処理
		processMessage(session, request, "errorQueue", "errors");
	}

	@SuppressWarnings("unchecked")
	public static void processMessage(HttpSession session,
			HttpServletRequest request,
			String sessionKey,
			String requestKey) {
		Queue<Map<String, String>> queue = (Queue<Map<String, String>>) session.getAttribute(sessionKey);
		if (queue != null && !queue.isEmpty()) {
			// キューからメッセージを取り出し
			Map<String, String> message = queue.poll();
			request.setAttribute(requestKey, message);

			// キューが空になったらセッションから削除
			if (queue.isEmpty()) {
				session.removeAttribute(sessionKey);
			}
		}
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
