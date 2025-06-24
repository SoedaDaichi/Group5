package controllers;

import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import beans.Accounts;
import beans.Categories;
import beans.SalesData;
import beans.SalesSearchForm;
import daos.SalesDao;
import services.ErrorMessageService;
import services.ErrorService;

/**
 * Servlet implementation class S0020Servlet
 */
@WebServlet("/S0020.html")
public class S0020Servlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public S0020Servlet() {
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
		//		Map<String, String> errors = (Map<String, String>) session.getAttribute("errors");
		Map<String, String> errors = ErrorMessageService.processSessionMessages(request);
		if (errors != null) {
			request.setAttribute("errors", errors); // 必要な属性名でセット
		}

		Map<String, String> notFound = (Map<String, String>) session.getAttribute("notFound");
		SalesSearchForm ssForm = (SalesSearchForm) session.getAttribute("ssForm");

		//		if (errors != null) {
		//			request.setAttribute("errors", errors);
		//			session.removeAttribute("errors");
		//		}
		if (notFound != null) {
			request.setAttribute("notFound", notFound);
			session.removeAttribute("notFound");
		}
		if (ssForm != null) {
			request.setAttribute("ssForm", ssForm);
			session.removeAttribute("ssForm");
		}

		SalesDao salesDao = new SalesDao();
		ArrayList<Accounts> accountList = salesDao.selectAccount();
		ArrayList<Categories> categoryList = salesDao.selectCategory();

		request.setAttribute("accountList", accountList);
		request.setAttribute("categoryList", categoryList);

		request.getRequestDispatcher("/S0020.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession();

		String firstStr = request.getParameter("first");
		String lastStr = request.getParameter("last");
		String accountIdStr = request.getParameter("account_id");
		String categoryIdStr = request.getParameter("category_id");
		String tradeName = request.getParameter("trade_name");
		String note = request.getParameter("note");

		ErrorService es = new ErrorService();

		Map<String, String> errors = es.validateSalesSearch(firstStr, lastStr);
		if (errors != null && !errors.isEmpty()) {
			@SuppressWarnings("unchecked")
			Queue<Map<String, String>> errorQueue = (Queue<Map<String, String>>) session.getAttribute("errorQueue");
			if (errorQueue == null) {
				errorQueue = new LinkedList<>();
			}
			errorQueue.add(errors);
			session.setAttribute("errorQueue", errorQueue);
			response.sendRedirect("S0020.html");
			return;
		}

		SalesSearchForm ssForm = new SalesSearchForm(firstStr, lastStr, accountIdStr, categoryIdStr, tradeName, note);
		session.setAttribute("ssForm", ssForm);

		SalesDao salesDao = new SalesDao();
		ArrayList<SalesData> salesList = salesDao.selectSearch(ssForm);

		Map<String, String> notFound = es.validateNotFoundSales(salesList);
		if (notFound != null && !notFound.isEmpty()) {
			session.setAttribute("notFound", notFound);
			response.sendRedirect("S0020.html");
			return;
		}

		session.setAttribute("salesList", salesList);
		response.sendRedirect("S0021.html");
	}
}
