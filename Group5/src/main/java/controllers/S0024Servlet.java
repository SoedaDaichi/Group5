package controllers;

import java.io.IOException;
import java.time.LocalDate;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import beans.SalesData;
import daos.SalesDao;

/**
 * Servlet implementation class S0024Servlet
 */
@WebServlet("/S0024.html")
public class S0024Servlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public S0024Servlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession();
		SalesData salesData = (SalesData) session.getAttribute("salesData");

		request.setAttribute("salesData", salesData);
		request.getRequestDispatcher("/S0024.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		HttpSession session = request.getSession();
		SalesData salesData = (SalesData) session.getAttribute("salesData");
		int saleId = (int) session.getAttribute("saleId");
		LocalDate saleDate = salesData.getSaleDate();
		int accountId = salesData.getAccountId();
		int categoryId = salesData.getCategoryId();
		String tradeName = salesData.getTradeName();
		int unitPrice = salesData.getUnitPrice();
		int saleNumber = salesData.getSaleNumber();
		String note = salesData.getNote();

		session.removeAttribute("saleId");
		session.removeAttribute("salesData");

		SalesDao salesDao = new SalesDao();
		boolean success = salesDao.updateSales(
				saleDate, accountId, categoryId, tradeName, unitPrice, saleNumber, note, saleId);

		if (success) {
			session.setAttribute("success", "売上が更新されました。");
			response.sendRedirect("S0021.html");
		} else {
			session.setAttribute("error", "更新に失敗しました");
			response.sendRedirect("S0021.html");
		}
	}

}
