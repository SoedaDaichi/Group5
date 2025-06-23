package controller;

import java.io.IOException;
import java.util.Map;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import beans.loginAccount;
import services.ErrorMessageService;
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
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		ErrorMessageService.processSessionMessages(request);
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
			Queue<Map<String, String>> errorQueue = new ConcurrentLinkedQueue<>();
			errorQueue.add(errors);
			session.setAttribute("errorsQueue", errorQueue);
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
