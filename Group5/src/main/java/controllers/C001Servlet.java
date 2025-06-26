package controllers;

import java.io.IOException;
import java.util.Map;
import java.util.Queue;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import form.LoginAccount;
import services.Auth;
import services.ErrorService;
import services.MessageService;

/**
 * Servlet implementation class LoginServlet
 */
@WebServlet("/C001.html")
public class C001Servlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */

	public C001Servlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		HttpSession session = request.getSession(false);
		if (session != null) {
			String error = (String) session.getAttribute("error");
			Map<String, String> errors = MessageService.processSessionMessages(request);
			if (error != null) {
				MessageService.moveAttribute(session, request, "error", error);
			}
			if (errors != null) {
				request.setAttribute("errors", errors);
			}
		}
		request.getRequestDispatcher("/C001.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String mail = request.getParameter("mail");
		System.out.println(mail);
		String pass = request.getParameter("pass");
		System.out.println(pass);
		String hashedPass = Auth.hashPassword(pass);

		ErrorService es = new ErrorService();
		Map<String, String> errors = es.validateLogin(mail, pass, hashedPass);
		System.out.println(errors);
		HttpSession session = request.getSession();

		if (errors != null && !errors.isEmpty()) {
			System.out.println("エラー: " + errors);
			Queue<Map<String, String>> errorQueue = MessageService.errorIntoQueue(request, errors);
			session.setAttribute("errorQueue", errorQueue);
			response.sendRedirect("C001.html");
			return;
		}

		LoginAccount loginAccount = Auth.login(mail, pass); // ユーザー取得
		System.out.println("Auth.login終わりのloginAccount" + loginAccount);

		// ログイン成功処理
		session.setAttribute("loginAccount", loginAccount);
		System.out.println("sessionにログイン情報保存: " + session.getAttribute("loginAccount"));
		response.sendRedirect("C002.html");
	}

}
