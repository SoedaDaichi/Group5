package controllers;

import java.io.IOException;
import java.sql.Date;
import java.util.ArrayList;
import java.util.Map;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import beans.Accounts;
import beans.Categories;
import beans.SalesData;
import beans.SalesForm;
import daos.SalesDao;
import services.ErrorService;

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
	@SuppressWarnings("unchecked")
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		HttpSession session = request.getSession();

		Map<String, String> errors = (Map<String, String>) session.getAttribute("errors"); // 無視できるエラー
		SalesForm salesform = (SalesForm) session.getAttribute("salesform");

		if (errors != null) {
			request.setAttribute("errors", errors);
			request.setAttribute("salesform", salesform);
			session.removeAttribute("errors");
			session.removeAttribute("salesform");
		} else {
			SalesData salesdata = (SalesData) session.getAttribute("salesdata");
			request.setAttribute("salesdata", salesdata);
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
		HttpSession session = request.getSession();

		String saleDateStr = request.getParameter("saleDate");
		String accountIdStr = request.getParameter("accountId");
		String categoryIdStr = request.getParameter("categoryId");
		String tradeName = request.getParameter("tradeName");
		String unitPriceStr = request.getParameter("unitPrice");
		String saleNumberStr = request.getParameter("saleNumber");
		String note = request.getParameter("note");

		ErrorService errorService = new ErrorService();

		Map<String, String> errors = errorService.validateSales(
				saleDateStr, accountIdStr, categoryIdStr, tradeName,
				unitPriceStr, saleNumberStr, note);

		if (errors != null && !errors.isEmpty()) {
			System.out.println("エラー: " + errors);
			SalesForm salesForm = new SalesForm(
					saleDateStr, accountIdStr, categoryIdStr, tradeName,
					unitPriceStr, saleNumberStr, note);
			session.setAttribute("salesForm", salesForm);
			session.setAttribute("errors", errors);
			response.sendRedirect("S0023.html");
			return;
		}

		Date saleDate = Date.valueOf(request.getParameter("saleDate"));
		int accountId = Integer.valueOf(request.getParameter("accountId"));
		int categoryId = Integer.valueOf(request.getParameter("categoryId"));
		int unitPrice = Integer.valueOf(request.getParameter("unitPrice"));
		int saleNumber = Integer.valueOf(request.getParameter("saleNumber"));

		SalesDao salesDao = new SalesDao();
		Accounts account = salesDao.identificationAccount(accountId);
		String name = account.getName();

		Categories category = salesDao.identificationCategory(categoryId);
		String categoryName = category.getCategoryName();

		SalesData salesData = new SalesData(
				saleDate, name, accountId, categoryName, categoryId, tradeName,
				unitPrice, saleNumber, note);

		session.setAttribute("salesData", salesData);

		response.sendRedirect("S0024.html");
	}

}
