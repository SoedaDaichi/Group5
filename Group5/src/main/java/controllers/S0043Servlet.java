package controllers;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import beans.AccountsData;
import beans.LoginAccount;
import daos.AccountsDao;
import services.Auth;

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
		HttpSession session = request.getSession();

		AccountsData accountsData = (AccountsData) session.getAttribute("accountsData");
		int accountId = (int) session.getAttribute("accountId");

		request.setAttribute("accountsData", accountsData);
		request.setAttribute("accountId", accountId);
		request.getRequestDispatcher("/S0043.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession();
		String action = request.getParameter("action");
		if ("update".equals(action)) {
			int accountId = (int) session.getAttribute("accountId");
			AccountsData accountsData = (AccountsData) session.getAttribute("accountsData");
			String name = accountsData.getName();
			String mail = accountsData.getMail();
			String pass = accountsData.getPass();
			String authority = accountsData.getAuthority();
			String hashedPass = Auth.hashPassword(pass);

			session.removeAttribute("accountId");
			session.removeAttribute("accountsData");

			AccountsDao accountsDao = new AccountsDao();
			boolean success = accountsDao.update(accountId, name, mail, hashedPass, authority);

			if (success) {
				session.setAttribute("success", "アカウントが更新されました。");
				// ログインユーザー自身がアカウント編集を行った場合、ログインユーザーの情報を書き換える。
				LoginAccount loginAccount = (LoginAccount) session.getAttribute("loginAccount");
				if (accountId == loginAccount.getAccountId()) {
					LoginAccount newLoginAccount = new LoginAccount(accountId, name, mail, hashedPass,
							Integer.valueOf(authority));
					session.setAttribute("loginAccount", newLoginAccount);
				}
				response.sendRedirect("S0041.html");
			} else {
				session.setAttribute("error", "更新に失敗しました");
				response.sendRedirect("S0041.html");
			}
		} else if ("cancel".equals(action)) {
			response.sendRedirect("S0042.html");
		}
	}
}
