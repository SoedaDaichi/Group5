package controller;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import beans.Accounts;
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
		request.getRequestDispatcher("/C001.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String mail = request.getParameter("mail");
		String pass = auth.hashPassword(request.getParameter("pass"));

		Accounts account = auth.login(mail, pass); // ユーザー取得
		String noManager = auth.checkManager(account);

		if (account == null) {
			request.setAttribute("error", "メールアドレス、またはパスワードが違います。");
			request.getRequestDispatcher("/C001.jsp").forward(request, response);
			return;
		} else if (noManager != null) {
			request.setAttribute("error", noManager);
			request.getRequestDispatcher("/C001.jsp").forward(request, response);
			return;
		}

		// ログイン成功処理
		HttpSession session = request.getSession();
		session.setAttribute("loginUser", account);
		response.sendRedirect("C002.html");
	}

}
