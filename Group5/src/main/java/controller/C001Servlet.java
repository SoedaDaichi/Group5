package controller;

import java.io.IOException;
import java.util.Map;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import beans.loginAccount;
import services.ErrorService;
import services.auth;

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
	@SuppressWarnings("unchecked")
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		HttpSession session = request.getSession(false);
		if (session != null) {
			Map<String, String> errors = (Map<String, String>) session.getAttribute("errors"); // 無視できるエラー
			if (errors != null) {
				request.setAttribute("errors", errors);
				session.removeAttribute("errors");
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
//		System.out.println(mail);
		String pass = request.getParameter("pass");
//		System.out.println(pass);
		String hashed_pass = auth.hashPassword(pass);

		ErrorService es = new ErrorService();
		Map<String, String> errors = es.ValidateLogin(mail, pass, hashed_pass);
		System.out.println(errors);
		HttpSession session = request.getSession();

		if (errors != null && !errors.isEmpty()) {
			session.setAttribute("errors", errors);
			response.sendRedirect("C001.html");
			return;
		}

		loginAccount loginAccount = auth.login(mail, pass); // ユーザー取得

		// ログイン成功処理
		session.setAttribute("loginAccount", loginAccount);
		System.out.println("sessionにログイン情報保存: " + session.getAttribute("loginAccount"));
		response.sendRedirect("C002.html");
	}

}
