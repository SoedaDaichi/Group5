package services;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

public class SuccessMessageService {

	public static void processSessionMessages(HttpServletRequest request) {
		HttpSession session = request.getSession(false);

		// エラーメッセージの処理
		processMessage(session, request, "success", "success");

		processMessage(session, request, "error", "error"); // 想定外エラー用
	}

	private static void processMessage(HttpSession session,
			HttpServletRequest request,
			String sessionKey,
			String requestKey) {
		String message = (String) session.getAttribute(sessionKey);
		if (message != null && !message.isEmpty()) {
			// メッセージを取り出し
			request.setAttribute(requestKey, message);
			session.removeAttribute(sessionKey);
		}
	}
}
