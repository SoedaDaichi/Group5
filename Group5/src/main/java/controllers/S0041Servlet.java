package controllers;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import daos.AccountsDao;
import data.AccountsData;
import form.Accounts;
import form.AccountsSearchForm;
import services.MessageService;
import services.ErrorService;

/**
 * Servlet implementation class S0041Servlet
 */
@WebServlet("/S0041.html")
public class S0041Servlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public S0041Servlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession();

		// セッションから検索結果とキーワードを取得
		String success = (String) session.getAttribute("success");
		String error = (String) session.getAttribute("error");
		AccountsSearchForm asForm = (AccountsSearchForm) session.getAttribute("asForm");
		System.out.println("検索入力保持: " + asForm);

		if (success != null) {
			MessageService.moveAttribute(session, request, "success", success);
		} else if (error != null) {
			MessageService.moveAttribute(session, request, "error", error);
		}

		AccountsDao ad = new AccountsDao();
		ArrayList<Accounts> accountsList = ad.selectSearch(asForm);
		
		ErrorService es = new ErrorService();
		Map<String, String> notFound = es.validateNotFoundAccounts(accountsList);
		if (notFound != null && !notFound.isEmpty()) {
			session.setAttribute("notFound", notFound);
			System.out.println("notFoundError");
			response.sendRedirect("S0040.html");
			return;
		}
		
		request.setAttribute("accountsList", accountsList);
		System.out.println(request.getAttribute("accountsList"));
		request.getRequestDispatcher("/S0041.jsp").forward(request, response);

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		HttpSession session = request.getSession();
		String action = request.getParameter("action");

		int accountId = Integer.valueOf(request.getParameter("accountId"));
		AccountsDao accountsDao = new AccountsDao();
		AccountsData accountsData = accountsDao.getAccountsByAccountId(accountId);

		
		session.setAttribute("accountId", accountId);
		session.setAttribute("accountsData", accountsData);

		if ("edit".equals(action)) {
			response.sendRedirect("S0042.html");
		} else if ("delete".equals(action)) {
			response.sendRedirect("S0044.html");
		}
	}

}