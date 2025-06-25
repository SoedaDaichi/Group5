package controllers;

import java.io.IOException;
import java.time.LocalDate;
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
	@SuppressWarnings("unchecked")
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession();
		String success = (String) session.getAttribute("success");
		Map<String, String> errors = (Map<String, String>) session.getAttribute("errors"); // 無視できるエラー
//		System.out.println(errors);
		SalesForm registerSalesForm = (SalesForm) session.getAttribute("registerSalesForm");
		//		System.out.println("絶対呼ばれる: " + Register_salesform);

		if (success != null) {
			request.setAttribute("success", success);
			session.removeAttribute("success");
		} else if (errors != null) {
			request.setAttribute("errors", errors);
			System.out.println(request.getAttribute("errors"));
			request.setAttribute("registerSalesForm", registerSalesForm);
			session.removeAttribute("errors");
			session.removeAttribute("registerSalesForm");
		}

		ArrayList<Accounts> accountList = new ArrayList<>();
		ArrayList<Categories> categoryList = new ArrayList<>();

		SalesDao salesDao = new SalesDao();
		accountList = salesDao.selectAccount();
		System.out.println(accountList.size());
		categoryList = salesDao.selectCategory();

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
		

		String saleDateStr = request.getParameter("saleDate");
		String accountIdStr = request.getParameter("accountId");
		System.out.println(accountIdStr);
		String categoryIdStr = request.getParameter("categoryId");
		String tradeName = request.getParameter("tradeName");
		String unitPriceStr = request.getParameter("unitPrice");
		String saleNumberStr = request.getParameter("saleNumber");
		String note = request.getParameter("note");

		ErrorService es = new ErrorService();
		Map<String, String> errors = es.validateSales(saleDateStr, accountIdStr, categoryIdStr, tradeName,
				unitPriceStr,
				saleNumberStr,
				note);
//		System.out.println(errors);

		if (errors != null && !errors.isEmpty()) {
			SalesForm registerSalesForm = new SalesForm(saleDateStr, accountIdStr, categoryIdStr, tradeName,
					unitPriceStr,
					saleNumberStr, note);
			//			System.out.println("エラー時: " + Register_salesform);
			session.setAttribute("registerSalesForm", registerSalesForm);
			//			System.out.println("session保存後" + session.getAttribute("Register_salesform"));
			session.setAttribute("errors", errors);
			response.sendRedirect("S0010.html");
			return;
		}

		if (errors == null || errors.isEmpty()) {

			LocalDate saleDate = LocalDate.parse(saleDateStr);
			int accountId = Integer.valueOf(accountIdStr);
			int categoryId = Integer.valueOf(categoryIdStr);
			int unitPrice = Integer.valueOf(unitPriceStr);
			int saleNumber = Integer.valueOf(saleNumberStr);

			SalesDao s0010Dao = new SalesDao();
			Accounts account = s0010Dao.identificationAccount(accountId);
			String accountName = account.getName();

			Categories category = s0010Dao.identificationCategory(categoryId);
			String categoryName = category.getCategoryName();

			SalesData registerSalesData = new SalesData(
					saleDate,
					accountName,
					accountId,
					categoryName,
					categoryId,
					tradeName,
					unitPrice,
					saleNumber,
					note);

			session.setAttribute("registerSalesData", registerSalesData);
			// request.getRequestDispatcher("S0011.html").forward(request, response);
			response.sendRedirect("S0011.html");
		}
	}
}