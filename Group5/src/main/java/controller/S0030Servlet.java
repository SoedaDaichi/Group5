package controller;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import services.UserService;

/**
 * Servlet implementation class S0030Servlet
 */
@WebServlet("/S0030Servlet")
public class S0030Servlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public S0030Servlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		//response.getWriter().append("Served at: ").append(request.getContextPath());
		request.getRequestDispatcher("/S0030.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		//doGet(request, response);

		String name = request.getParameter("name");
		String mail = request.getParameter("mail");
		String pass = request.getParameter("pass");
		String confirm_pass = request.getParameter("confirm_pass");
		String role = request.getParameter("role");

		if (UserService.UserNameCheck(name)) {
			request.setAttribute("error", "このユーザ名は既に使用されています。");
			request.getRequestDispatcher("S0030.jsp").forward(request, response);
			return;
		}

		if (UserService.UserEmailCheck(mail)) {
			request.setAttribute("error", "このメールアドレスは既に使用されています。");
			request.getRequestDispatcher("S0030.jsp").forward(request, response);
			return;
		}

		try {
			String hashedPass = UserService.hashPassword(pass);

			UserService.insert(name, mail, hashedPass);

			response.sendRedirect("S0031.jsp");
		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("error", "登録に失敗しました。");
			request.getRequestDispatcher("S0030.jsp").forward(request, response);
		}

	}
}