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
import form.LoginAccount;

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
		HttpSession session = request.getSession();

		AccountsData accountsData = (AccountsData) session.getAttribute("accountsData");
		int accountId = (int) session.getAttribute("accountId");

		request.setAttribute("accountsData", accountsData);
		request.setAttribute("accountId", accountId);
		// session.removeAttribute("accounts");
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
			session.removeAttribute("accountsData");

			AccountsDao accountsDao = new AccountsDao();
			boolean success = accountsDao.deleteAccount(accountId);

			if (success) {
				session.setAttribute("success", "アカウントが削除されました。");
				// 自身のアカウントを削除した場合、ログイン画面へ戻る。
				LoginAccount loginAccount = (LoginAccount) session.getAttribute("loginAccount");
				if (accountId == loginAccount.getAccountId()) {
					session.setAttribute("error", "アカウントが存在しません。");
					response.sendRedirect("C001.html");
					return;
				}
				response.sendRedirect("S0041.html");
			} else {
				session.setAttribute("error", "削除に失敗しました。");
				response.sendRedirect("S0041.html");
			}
		} else if ("cancel".equals(action)) {
			response.sendRedirect("S0041.html");
		}
	}

}
