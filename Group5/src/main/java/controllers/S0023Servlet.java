package controllers;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;
import java.util.Queue;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import daos.SalesDao;
import data.SalesData;
import form.Accounts;
import form.Categories;
import form.SalesForm;
import services.ErrorService;
import services.MessageService;

/**
 * Servlet implementation class S0023Servlet
 */
@WebServlet("/S0023.html")
public class S0023Servlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public S0023Servlet() {
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
		SalesForm salesForm = (SalesForm) session.getAttribute("salesForm");
		if (errors != null) {
			request.setAttribute("errors", errors);
		}
		if (salesForm != null) {
			MessageService.moveAttribute(session, request, "salesForm", salesForm);
		} else {
			SalesData salesData = (SalesData) session.getAttribute("salesData");
			request.setAttribute("salesData", salesData);
		}

		SalesDao salesDao = new SalesDao();
		ArrayList<Accounts> accountList = salesDao.selectAccount();
		ArrayList<Categories> categoryList = salesDao.selectCategory();

		request.setAttribute("accountList", accountList);
		request.setAttribute("categoryList", categoryList);

		request.getRequestDispatcher("/S0023.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String action = request.getParameter("action");

		if ("update".equals(action)) {
			HttpSession session = request.getSession();

			ErrorService errorService = new ErrorService();
			Map<String, String> errors = errorService.validateSales(request);

			SalesForm salesForm = new SalesForm(request);
			if (errors != null && !errors.isEmpty()) {
				System.out.println("エラー: " + errors);
				Queue<Map<String, String>> errorQueue = MessageService.errorIntoQueue(request, errors);
				session.setAttribute("errorQueue", errorQueue);
				session.setAttribute("salesForm", salesForm);
				response.sendRedirect("S0023.html");
				return;
			}

			SalesDao salesDao = new SalesDao();
			Accounts account = salesDao.identificationAccount(Integer.valueOf(salesForm.getAccountIdStr()));
			String name = account.getName();

			Categories category = salesDao.identificationCategory(Integer.valueOf(salesForm.getCategoryIdStr()));
			String categoryName = category.getCategoryName();

			SalesData salesData = new SalesData(
					salesForm, name, categoryName);

			session.setAttribute("salesData", salesData);

			response.sendRedirect("S0024.html");
		} else if ("cancel".equals(action)) {
			response.sendRedirect("S0022.html");
		}
	}

}
