package controller;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import beans.Accounts;
import daos.S0044Dao;

/**
 * Servlet implementation class S0044Servlet
 */
@WebServlet("/S0044.html")
public class S0044Servlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public S0044Servlet() {
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
		HttpSession session = request.getSession();

		Accounts accounts = (Accounts) session.getAttribute("accounts");
		int accountId = (int) session.getAttribute("accountId");

		request.setAttribute("accounts", accounts);
		request.setAttribute("accountId", accountId);
		//		session.removeAttribute("accounts");
		request.getRequestDispatcher("/S0044.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession();
		String action = request.getParameter("action");
		if ("delete".equals(action)) {
			int accountId = (int) session.getAttribute("accountId");

			session.removeAttribute("accountId");
			session.removeAttribute("accounts");

			S0044Dao s0044dao = new S0044Dao();
			boolean success = s0044dao.deleteAccount(accountId);

			if (success) {
				session.setAttribute("success", "アカウントが削除されました。");
				//response.sendRedirect("S0042.html");
				response.sendRedirect("S0041.html");

			} else {
				session.setAttribute("error", "削除に失敗しました");
				//response.sendRedirect("S0042.html");
				response.sendRedirect("S0041.html");
			}
		} else if ("cancel".equals(action)) {
			response.sendRedirect("S0041.html");
		}
	}
}
