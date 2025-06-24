package controller;

import java.io.IOException;
import java.util.Map;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import beans.Accounts;
import beans.AccountsData;
import services.ErrorService;

/**
 * Servlet implementation class S0042Servlet
 */
@WebServlet("/S0042.html")
public class S0042Servlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public S0042Servlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	@SuppressWarnings("unchecked")
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession();

		Map<String, String> errors = (Map<String, String>) session.getAttribute("errors"); // 無視できるエラー
		AccountsData accountsData = (AccountsData) session.getAttribute("accountsData");

		if (errors != null) {
			request.setAttribute("errors", errors);
			request.setAttribute("accountsData", accountsData);
			session.removeAttribute("errors");
			session.removeAttribute("accountsData");
		} else {
			Accounts accounts = (Accounts) session.getAttribute("accounts");
			request.setAttribute("accounts", accounts);
		}
		int accountId = (int) session.getAttribute("accountId");
		request.setAttribute("accountId", accountId);
		request.getRequestDispatcher("S0042.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		HttpSession session = request.getSession();

		String name = request.getParameter("name");
		String mail = request.getParameter("mail");
		String pass = request.getParameter("pass");
		String confirmPass = request.getParameter("confirmPass");
		String authorityStr = request.getParameter("authority");
		int accountId = (int) session.getAttribute("accountId");

		AccountsData accountsData = new AccountsData(name, mail, pass, confirmPass, authorityStr);

		ErrorService errorService = new ErrorService();
		Map<String, String> errors = errorService.validateAccountsUpdate(accountId, name, mail, pass, confirmPass);
		if (errors != null && !errors.isEmpty()) {
			session.setAttribute("accountsData", accountsData);
			session.setAttribute("errors", errors);
			response.sendRedirect("S0042.html");
			return;
		}

		session.setAttribute("accountsData", accountsData);
		response.sendRedirect("S0043.html");
	}

}
