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

import form.AccountsForm;
import form.AccountsSearchForm;
import services.MessageService;
import services.ErrorService;

/**
 * Servlet implementation class S0040Servlet
 */
@WebServlet("/S0040.html")
public class S0040Servlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public S0040Servlet() {
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

		Map<String, String> errors = MessageService.processSessionMessages(request);
		request.setAttribute("errors", errors);
		Map<String, String> notFound = (Map<String, String>) session.getAttribute("notFound");
		AccountsSearchForm asForm = (AccountsSearchForm) request.getAttribute("asForm");

		if (errors != null) {
			request.setAttribute("errors", errors);
			session.removeAttribute("errors");
		}
		if (notFound != null) {
			request.setAttribute("notFound", notFound);
			session.removeAttribute("notFound");
		}
		if (asForm != null) {
			request.setAttribute("asForm", asForm);
			//			session.removeAttribute("ssForm");
		}
		request.getRequestDispatcher("/S0040.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession();

		System.out.println("----------アカウント検索-----------");

		ErrorService es = new ErrorService();
		Map<String, String> errors = es.validateAccountsSearch(request);

		AccountsForm registerAccountsForm = new AccountsForm(request);

		if (errors != null && !errors.isEmpty()) {
			session.setAttribute("registerAccountsForm", registerAccountsForm);
			@SuppressWarnings("unchecked")
			Queue<Map<String, String>> errorQueue = (Queue<Map<String, String>>) session.getAttribute("errorQueue");
			if (errorQueue == null) {
				errorQueue = new LinkedList<>();
			}
			errorQueue.add(errors);
			session.setAttribute("errorQueue", errorQueue);
			response.sendRedirect("S0040.html");
			return;
		}

		AccountsSearchForm asForm = new AccountsSearchForm(request);
		session.setAttribute("asForm", asForm);
		response.sendRedirect("S0041.html");
	}
}
