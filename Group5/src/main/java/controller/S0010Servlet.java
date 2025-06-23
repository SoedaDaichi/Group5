package controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import beans.Accounts;
import beans.Categories;
import daos.S0010Dao;
import services.ErrorMessageService;
import services.ErrorService;
import services.SessionDataService;
import services.SessionFormService;
import services.SuccessMessageService;

/**
 * Servlet implementation class S0010Servlet
 */
@WebServlet("/S0010.html")
public class S0010Servlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public S0010Servlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		SessionFormService.salesRegisterFormSession(request);
		SuccessMessageService.processSessionMessages(request);
		ErrorMessageService.processSessionMessages(request);

		ArrayList<Accounts> accountList = new ArrayList<>();
		ArrayList<Categories> categoryList = new ArrayList<>();

		S0010Dao ss = new S0010Dao();
		accountList = ss.selectAccount();
		System.out.println(accountList.size());
		categoryList = ss.selectCategory();

		request.setAttribute("accountList", accountList);
		request.setAttribute("categoryList", categoryList);

		request.getRequestDispatcher("/S0010.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		HttpSession session = request.getSession();

		ErrorService es = new ErrorService();
		Map<String, String> errors = es.ValidateSales(request);
		System.out.println(errors);

		if (errors != null && !errors.isEmpty()) {
			Queue<Map<String, String>> errorQueue = new ConcurrentLinkedQueue<>();
			errorQueue.add(errors);
			session.setAttribute("errorsQueue", errorQueue);
			session.setAttribute("RegisterSalesForm", request);
			response.sendRedirect("S0010.html");
			return;
		}

		SessionDataService.SalesRegisterDataSession(request);
		
		response.sendRedirect("S0011.html");
	}
}