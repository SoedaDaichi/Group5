package controller;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import daos.S0043Dao;
import services.auth;

/**
 * Servlet implementation class S0043Servlet
 */
@WebServlet("/S0043.html")
public class S0043Servlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public S0043Servlet() {
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
		
		

		request.getRequestDispatcher("/S0043.jsp").forward(request, response);

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		//doGet(request, response);
		
		int accountId = Integer.parseInt(request.getParameter("account_id"));

		String action = request.getParameter("action");
		if ("cancel".equals(action)) {
			response.sendRedirect("S0042.html?id=" + request.getParameter("id"));
			return;
		}

		String name = request.getParameter("name");
		String mail = request.getParameter("mail");
		String pass = request.getParameter("pass");
		String role = request.getParameter("role");

		System.out.println(name);

		String hashedPass = auth.hashPassword(pass);

		S0043Dao s0043dao = new S0043Dao();
		boolean success = s0043dao.update(accountId, name, mail, hashedPass, role);

		HttpSession session = request.getSession();
		if (success) {
			session.setAttribute("success", "アカウントが更新されました。");
			//response.sendRedirect("S0042.html");
			response.sendRedirect("S0042.html?id=" + request.getParameter("id"));

		} else {
			session.setAttribute("error", "更新に失敗しました");
			//response.sendRedirect("S0042.html");
			response.sendRedirect("S0042.html?id=" + request.getParameter("id"));

		}

	}

}
