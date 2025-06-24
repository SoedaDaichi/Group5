package controller;

import java.io.IOException;
import java.sql.Date;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

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
import beans.SalesSearchForm;
import daos.S0010Dao;
import daos.S0020Dao;
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
		String error = (String) session.getAttribute("error");
		SalesSearchForm ssForm = (SalesSearchForm) session.getAttribute("ssForm");
		ArrayList<Sales> salesList = (ArrayList<Sales>) session.getAttribute("salesList");

		if (success != null) {
			request.setAttribute("success", success);
			session.removeAttribute("success");
		} else if (error != null) {
			request.setAttribute("error", error);
			session.removeAttribute("error");
		}

		if ((salesList == null || salesList.isEmpty()) && ssForm != null) {
			S0020Dao s0020Dao = new S0020Dao();
			ArrayList<Sales> salesListRe = s0020Dao.select(ssForm);
			request.setAttribute("salesList", salesListRe);
			session.removeAttribute("salesList");
		} else if (ssForm != null) {
			System.out.println("検索結果: " + salesList);
			request.setAttribute("salesList", salesList);
			session.removeAttribute("salesList");
		} else {
			Map<String, String> notFound = new HashMap<>();
			notFound.put("salesNotFound", "エラーが発生しました。");
			request.setAttribute("notFound", notFound);
			response.sendRedirect("S0020.html");
			return;
		}
		request.getRequestDispatcher("/S0021.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		request.setCharacterEncoding("UTF-8");
		int saleId = Integer.valueOf(request.getParameter("saleId"));
		HttpSession session = request.getSession();

		S0021Dao s0021Dao = new S0021Dao();
		Sales sale = s0021Dao.identificationSales(saleId);

		Date saleDate = (Date) sale.getSaleDate();
		int accountId = sale.getAccountId();
		int categoryId = sale.getCategoryId();
		String tradeName = sale.getTradeName();
		int unitPrice = sale.getUnitPrice();
		int saleNumber = sale.getSaleNumber();
		String note = sale.getNote();

		// 取り出したsalesテーブルのaccountIdとcategoryIdを
		// 各テーブルの紐づいたnameを取ってくる作業
		S0010Dao s0010Dao = new S0010Dao();
		Accounts account = s0010Dao.identificationAccount(accountId);
		String name = account.getName();
		Categories category = s0010Dao.identificationCategory(categoryId);
		String categoryName = category.getCategoryName();

		SalesData salesData = new SalesData(
				saleDate, name, accountId, categoryName, categoryId, tradeName,
				unitPrice, saleNumber, note);

		session.setAttribute("saleId", saleId);
		session.setAttribute("salesData", salesData);

		response.sendRedirect("S0022.html");
	}

}
