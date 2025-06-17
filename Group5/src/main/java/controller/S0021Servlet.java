package controller;

import java.io.IOException;
import java.sql.Date;
import java.util.ArrayList;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import beans.Accounts;
import beans.Categories;
import beans.Sales;
import beans.SalesData;
import daos.S0010Dao;
import daos.S0021Dao;

/**
 * Servlet implementation class S0021Servlet
 */
@WebServlet("/S0021.html")
public class S0021Servlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public S0021Servlet() {
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

		if (success != null) {
			request.setAttribute("success", success);
			session.removeAttribute("success");
		}

		ArrayList<Sales> salesList = (ArrayList<Sales>) session.getAttribute("salesList");
		System.out.println("検索結果: " + salesList);
		request.setAttribute("salesList", salesList);
		//		session.removeAttribute("salesList");
		request.getRequestDispatcher("/S0021.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		request.setCharacterEncoding("UTF-8");
		int sale_id = Integer.valueOf(request.getParameter("id"));
		HttpSession session = request.getSession();

		S0021Dao s0021dao = new S0021Dao();
		Sales sale = s0021dao.identificationSales(sale_id);

		Date sale_date = (Date) sale.getSale_date();
		int account_id = sale.getAccount_id();
		int category_id = sale.getCategory_id();
		String trade_name = sale.getTrade_name();
		int unit_price = sale.getUnit_price();
		int sale_number = sale.getSale_number();
		String note = sale.getNote();

		//取り出したsalesテーブルのaccount_idとcategory_idを
		//各テーブルの紐づいたNAMEを取ってくる作業
		S0010Dao ss = new S0010Dao();
		Accounts account = ss.identificationAccount(account_id);
		String name = account.getName();
		Categories category = ss.identificationCategory(category_id);
		String category_name = category.getCategory_name();

		SalesData salesdata = new SalesData(sale_date, name, account_id, category_name, category_id, trade_name,
				unit_price,
				sale_number, note);

		session.setAttribute("sale_id", sale_id);
		session.setAttribute("salesdata", salesdata);

		response.sendRedirect("S0022.html");

	}
}
