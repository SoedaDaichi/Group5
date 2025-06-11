package controller;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import services.S0031Service;
import services.UserService;

/**
 * Servlet implementation class S0031Servlet
 */
@WebServlet("/S0031Servlet")
public class S0031Servlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public S0031Servlet() {
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
		request.getRequestDispatcher("/S0031.jsp").forward(request, response);
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
		String role = request.getParameter("role");

		System.out.println(name);

		String hashedPass = UserService.hashPassword(pass);

		S0031Service s0031Service = new S0031Service();
		boolean success = s0031Service.insert(name, mail, hashedPass, role);

		if (success) {
			HttpSession session = request.getSession();
			session.setAttribute("success", "アカウントが作成されました。");
			response.sendRedirect("S0030Servlet");
		} else {
			request.setAttribute("error", "登録に失敗しました");
			request.getRequestDispatcher("/S0030.jsp").forward(request, response);
		}

		//request.getRequestDispatcher("/C002.jsp").forward(request, response);

	}

}
