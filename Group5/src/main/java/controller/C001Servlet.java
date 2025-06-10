package controller;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import beans.Users;
import services.UserService;
import services.auth;

/**
 * Servlet implementation class LoginServlet
 */
@WebServlet("/C001Servlet")
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
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		String mail = request.getParameter("mail");
		String pass = UserService.hashPassword(request.getParameter("pass"));

		Users user = auth.login(mail, pass);
		String noManeger = auth.checkManeger(user);

		if (user != null) {
			HttpSession session = request.getSession();
			session.setAttribute("loginUser", user);
			response.sendRedirect("C002Servlet");
		} else if (noManeger != null) {
			request.setAttribute("error", noManeger);
			request.getRequestDispatcher("C001.jsp");
		} else {
			request.setAttribute("error", "メールアドレス、またはパスワードが違います。");
			request.getRequestDispatcher("C001.jsp").forward(request, response);
		}
	}

}