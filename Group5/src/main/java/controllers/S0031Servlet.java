package controllers;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import daos.AccountsDao;
import data.AccountsData;

/**
 * Servlet implementation class S0031Servlet
 */
@WebServlet("/S0031.html")
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
		HttpSession session = request.getSession();
		AccountsData registerAccountsData = (AccountsData) session.getAttribute("registerAccountsData");

		request.setAttribute("registerAccountsData", registerAccountsData);
		request.getRequestDispatcher("/S0031.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String action = request.getParameter("action");
		HttpSession session = request.getSession();
		
		if ("register".equals(action)) {
		AccountsData registerAccountsData = (AccountsData) session.getAttribute("registerAccountsData");

		AccountsDao accountsDao = new AccountsDao();
		boolean success = accountsDao.insert(registerAccountsData);

		if (success) {
			session.setAttribute("success", "アカウントが作成されました。");
			session.removeAttribute("registerAccountsForm");
			session.removeAttribute("registerAccountsData");
			response.sendRedirect("S0030.html");
		} else {
			session.setAttribute("error", "登録に失敗しました");
			response.sendRedirect("S0030.html");
		}
		} else if ("cancel".equals(action)) {
			response.sendRedirect("S0030.html");
		}
	}

}
