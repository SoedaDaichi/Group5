package controllers;

import java.io.IOException;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import data.AccountsData;
import form.Accounts;
import form.AccountsForm;
import services.MessageService;
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
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession();

		Map<String, String> errors = MessageService.processSessionMessages(request);
		AccountsData accountsData = (AccountsData) session.getAttribute("accountsData");

		if (errors != null) {
			request.setAttribute("errors", errors);
			MessageService.moveAttribute(session, request, "accountsData", accountsData);
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
		int accountId = (int) session.getAttribute("accountId");

		AccountsForm accountsForm = new AccountsForm(request);

		ErrorService errorService = new ErrorService();
		Map<String, String> errors = errorService.validateAccountsUpdate(accountId, request);
		if (errors != null && !errors.isEmpty()) {
			@SuppressWarnings("unchecked")
			Queue<Map<String, String>> errorQueue = (Queue<Map<String, String>>) session.getAttribute("errorQueue");
			if (errorQueue == null) {
				errorQueue = new LinkedList<>();
			}
			errorQueue.add(errors);
			session.setAttribute("errorQueue", errorQueue);
			session.setAttribute("accountsForm", accountsForm);
			response.sendRedirect("S0042.html");
			return;
		}
		
		AccountsData accountsData = new AccountsData(request);

		session.setAttribute("accountsData", accountsData);
		response.sendRedirect("S0043.html");
	}

}
