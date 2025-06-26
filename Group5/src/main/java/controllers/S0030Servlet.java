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
import form.AccountsForm;
import services.MessageService;
import services.ErrorService;

/**
 * Servlet implementation class S0030Servlet
 */
@WebServlet("/S0030.html")
public class S0030Servlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public S0030Servlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession();

		AccountsForm registerAccountsForm = (AccountsForm) session.getAttribute("registerAccountsForm");
		Map<String, String> errors = MessageService.processSessionMessages(request);

		String success = (String) session.getAttribute("success");
		if (success != null) {
			request.setAttribute("success", success);
			session.removeAttribute("success");
		} else if (errors != null) {
			request.setAttribute("errors", errors);
			MessageService.moveAttribute(session, request, "registerAccountsForm", registerAccountsForm);
		}
		request.getRequestDispatcher("/S0030.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		System.out.println("----------アカウント登録-----------");

		ErrorService errorService = new ErrorService();
		Map<String, String> errors = errorService.validateAccounts(request);
		System.out.println("アカウント登録エラー: " + errors);
		HttpSession session = request.getSession();

		if (errors != null && !errors.isEmpty()) {
			AccountsForm registerAccountsForm = new AccountsForm(request);
			@SuppressWarnings("unchecked")
			Queue<Map<String, String>> errorQueue = (Queue<Map<String, String>>) session.getAttribute("errorQueue");
			if (errorQueue == null) {
				errorQueue = new LinkedList<>();
			}
			errorQueue.add(errors);
			session.setAttribute("registerAccountsForm", registerAccountsForm);
			session.setAttribute("errorQueue", errorQueue);
			response.sendRedirect("S0030.html");
			return;
		}

		AccountsData registerAccountsData = new AccountsData(request);
		session.setAttribute("registerAccountsData", registerAccountsData);
		response.sendRedirect("S0031.html");
	}
}