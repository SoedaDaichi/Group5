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
import daos.SalesDao;
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

		HttpSession session = request.getSession();
		String success = (String) session.getAttribute("success");
		Map<String, String> errors = (Map<String, String>) session.getAttribute("errors"); // 無視できるエラー
		SalesForm registerSalesform = (SalesForm) session.getAttribute("registerSalesform");
//		System.out.println("絶対呼ばれる: " + Register_salesform);

		if (success != null) {
			request.setAttribute("success", success);
			session.removeAttribute("success");
		} else if (errors != null) {
			request.setAttribute("errors", errors);
			request.setAttribute("registerSalesform", registerSalesform);
			session.removeAttribute("errors");
			session.removeAttribute("registerSalesform");
		}

		
		SessionFormService.salesRegisterFormSession(request);
		SuccessMessageService.processSessionMessages(request);
		ErrorMessageService.processSessionMessages(request);


		ArrayList<Accounts> accountList = new ArrayList<>();
		ArrayList<Categories> categoryList = new ArrayList<>();

		SalesDao sd = new SalesDao();
		accountList = sd.selectAccount();
		System.out.println(accountList.size());
		categoryList = sd.selectCategory();

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


		String saleDateStr = request.getParameter("sale_date");
		String accountIdStr = request.getParameter("account_id");
		//System.out.println(account_idStr);
		String categoryIdStr = request.getParameter("category_id");
		String tradeName = request.getParameter("trade_name");
		String unitPriceStr = request.getParameter("unit_price");
		String saleNumberStr = request.getParameter("sale_number");
		String note = request.getParameter("note");

		ErrorService es = new ErrorService();
		Map<String, String> errors = es.ValidateSales(saleDateStr, accountIdStr, categoryIdStr, tradeName,
				unitPriceStr,
				saleNumberStr,
				note);
		System.out.println(errors);

		if (errors != null && !errors.isEmpty()) {
			SalesForm registerSalesform = new SalesForm(saleDateStr, accountIdStr, categoryIdStr, tradeName, unitPriceStr,
					saleNumberStr, note);
//			System.out.println("エラー時: " + Register_salesform);
			session.setAttribute("registerSalesform", registerSalesform);
//			System.out.println("session保存後" + session.getAttribute("Register_salesform"));
			session.setAttribute("errors", errors);

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


		if (errors == null || errors.isEmpty()) {

			Date saleDate = Date.valueOf(saleDateStr);
			int accountId = Integer.valueOf(accountIdStr);
			int categoryId = Integer.valueOf(categoryIdStr);
			int unitPrice = Integer.valueOf(unitPriceStr);
			int saleNumber = Integer.valueOf(saleNumberStr);

			S0010Dao ss = new S0010Dao();
			Accounts account = ss.identificationAccount(accountId);
			String name = account.getName();

			Categories category = ss.identificationCategory(categoryId);
			String categoryName = category.getCategory_name();

			SalesData registerSalesdata = new SalesData(saleDate, name, accountId, categoryName, categoryId, tradeName,
					unitPrice,
					saleNumber, note);

			session.setAttribute("registerSalesdata", registerSalesdata);
			//			request.getRequestDispatcher("S0011.html").forward(request, response);
			response.sendRedirect("S0011.html");
		}

		SessionDataService.SalesRegisterDataSession(request);
		
		response.sendRedirect("S0011.html");

	}
}