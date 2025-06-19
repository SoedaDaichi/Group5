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
		// TODO Auto-generated method stub
		HttpSession session = request.getSession();

		Map<String, String> errors = (Map<String, String>) session.getAttribute("errors"); // 無視できるエラー
		AccountsData accountsdata = (AccountsData) session.getAttribute("accountsdata");

		if (errors != null) {
			request.setAttribute("errors", errors);
			request.setAttribute("accountsdata", accountsdata);
			session.removeAttribute("errors");
			session.removeAttribute("accountsdata");
		} else {
			Accounts accounts = (Accounts) session.getAttribute("accounts");
			request.setAttribute("accounts", accounts);
		}
		int account_id = (int) session.getAttribute("account_id");
		request.setAttribute("account_id", account_id);
		request.getRequestDispatcher("S0042.jsp").forward(request, response);

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		//doGet(request, response);

		HttpSession session = request.getSession();

		String name = request.getParameter("name");
		String mail = request.getParameter("mail");
		String pass = request.getParameter("pass");
		String confirm_pass = request.getParameter("confirm_pass");
		String authorityStr = request.getParameter("authority");
		int account_id = (int) session.getAttribute("account_id");

		AccountsData accountsdata = new AccountsData(name, mail, pass, confirm_pass, authorityStr);

		ErrorService es = new ErrorService();
		Map<String, String> errors = es.ValidateAccountsUpdate(account_id, name, mail, pass, confirm_pass);
		if (errors != null && !errors.isEmpty()) {
			session.setAttribute("accountsdata", accountsdata);
			session.setAttribute("errors", errors);
			response.sendRedirect("S0042.html");
			return;
		}

		session.setAttribute("accountsdata", accountsdata);
		response.sendRedirect("S0043.html");
	}

}
