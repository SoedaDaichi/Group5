package services;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

import lombok.Getter;

public class SuccessMessageService {

	public enum SuccessMessage {
		S0011Success("商品が登録されました"), S0011Error("登録に失敗しました");

		@Getter
		private String message;

		SuccessMessage(String message) {
			this.message = message;
		}
	}

	public static void SuccessSet(HttpServletRequest request, boolean success, SuccessMessage successMessage,
			SuccessMessage errorMessage) {
		HttpSession session = request.getSession(false);

		if (success) {
			session.setAttribute("success", successMessage.getMessage());
		} else {
			session.setAttribute("error", errorMessage.getMessage());
		}
	}

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
