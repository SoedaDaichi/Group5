package controller;

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
import daos.S0010Dao;
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

		S0010Dao ss = new S0010Dao();
		ArrayList<Accounts> accountList = ss.selectAccount();
		ArrayList<Categories> categoryList = ss.selectCategory();

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

		String sale_dateStr = request.getParameter("sale_date");
		String account_idStr = request.getParameter("account_id");
		String category_idStr = request.getParameter("category_id");
		String trade_name = request.getParameter("trade_name");
		String unit_priceStr = request.getParameter("unit_price");
		String sale_numberStr = request.getParameter("sale_number");
		String note = request.getParameter("note");

		ErrorService es = new ErrorService();

		Map<String, String> errors = es.ValidateSales(sale_dateStr, account_idStr, category_idStr, trade_name,
				unit_priceStr, sale_numberStr, note);

		if (errors != null && !errors.isEmpty()) {
			System.out.println("エラー: " + errors); 
			SalesForm salesform = new SalesForm(sale_dateStr, account_idStr, category_idStr, trade_name, unit_priceStr,
					sale_numberStr, note);
			session.setAttribute("salesform", salesform);
			session.setAttribute("errors", errors);
			response.sendRedirect("S0023.html");
			return;
		}

		Date sale_date = Date.valueOf(request.getParameter("sale_date"));
		int account_id = Integer.valueOf(request.getParameter("account_id"));
		int category_id = Integer.valueOf(request.getParameter("category_id"));
		int unit_price = Integer.valueOf(request.getParameter("unit_price"));
		int sale_number = Integer.valueOf(request.getParameter("sale_number"));

		S0010Dao ss = new S0010Dao();
		Accounts account = ss.identificationAccount(account_id);
		String name = account.getName();

		Categories category = ss.identificationCategory(category_id);
		String category_name = category.getCategory_name();

		SalesData salesdata = new SalesData(sale_date, name, account_id, category_name, category_id, trade_name,
				unit_price,
				sale_number, note);

		session.setAttribute("salesdata", salesdata);

		response.sendRedirect("S0024.html");
	}

}
