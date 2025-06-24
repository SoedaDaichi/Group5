package controller;

import java.io.IOException;
import java.sql.Date;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import beans.SalesData;
import daos.S0010Dao;

/**
 * Servlet implementation class S0010Servlet
 */
@WebServlet("/S0011.html")
public class S0011Servlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public S0011Servlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession();
		SalesData registerSalesData = (SalesData) session.getAttribute("registerSalesData");

		request.setAttribute("salesData", registerSalesData);
		request.getRequestDispatcher("/S0011.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		S0010Dao s0010Dao = new S0010Dao();

		HttpSession session = request.getSession();
		SalesData registerSalesData = (SalesData) session.getAttribute("registerSalesData");

		Date saleDate = registerSalesData.getSaleDate();
		int accountId = registerSalesData.getAccountId();
		int categoryId = registerSalesData.getCategoryId();
		String tradeName = registerSalesData.getTradeName();
		int unitPrice = registerSalesData.getUnitPrice();
		int saleNumber = registerSalesData.getSaleNumber();
		String note = registerSalesData.getNote();

		session.removeAttribute("registerSalesData"); // Filter範囲外

		boolean success = s0010Dao.insert(saleDate, accountId, categoryId, tradeName, unitPrice, saleNumber, note);

		if (success) {
			session.setAttribute("success", "商品が登録されました");
			response.sendRedirect("S0010.html");
		} else {
			session.setAttribute("error", "登録に失敗しました");
			response.sendRedirect("S0010.html");
		}
	}
}
