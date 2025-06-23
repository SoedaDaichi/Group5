package controllers;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import beans.AccountsData;
import services.ErrorMessageService;
import services.ErrorService;
import services.SessionFormService;
import services.SuccessMessageService;

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
		// TODO Auto-generated method stub
		//response.getWriter().append("Served at: ").append(request.getContextPath());
		SessionFormService.accountsRegisterFormSession(request);
		SuccessMessageService.processSessionMessages(request);
		ErrorMessageService.processSessionMessages(request);

		request.getRequestDispatcher("/S0030.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		//doGet(request, response);
		System.out.println("----------アカウント登録-----------");

		ErrorService es = new ErrorService();
		Map<String, String> errors = new HashMap<>();
		errors = es.ValidateAccounts(request);
		System.out.println("アカウント登録エラー: " + errors);
		HttpSession session = request.getSession();
		AccountsData registerAccountsForm = new AccountsData(request);

		if (errors != null && !errors.isEmpty()) {
			session.setAttribute("RegisterAccountsForm", registerAccountsForm);
			Queue<Map<String, String>> errorQueue = new ConcurrentLinkedQueue<>();
			errorQueue.add(errors);
			session.setAttribute("errorsQueue", errorQueue);
			response.sendRedirect("S0030.html");
			return;
		}

		AccountsData registerAccountsData = new AccountsData(request);
		session.setAttribute("registerAccountsData", registerAccountsData);
		response.sendRedirect("S0031.html");
	}
}