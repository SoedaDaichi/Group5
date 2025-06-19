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
import daos.S0023Dao;

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
		SalesData salesdata = (SalesData) session.getAttribute("salesdata");

		request.setAttribute("salesdata", salesdata);
		request.getRequestDispatcher("/S0024.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		HttpSession session = request.getSession();
		SalesData salesdata = (SalesData) session.getAttribute("salesdata");
		int sale_id = (int) session.getAttribute("sale_id");
		Date sale_date = salesdata.getSale_date();
		int account_id = salesdata.getAccount_id();
		int category_id = salesdata.getCategory_id();
		String trade_name = salesdata.getTrade_name();
		int unit_price = salesdata.getUnit_price();
		int sale_number = salesdata.getSale_number();
		String note = salesdata.getNote();

		session.removeAttribute("sale_id");
		session.removeAttribute("salesdata");

		S0023Dao s0023dao = new S0023Dao();
		boolean success = s0023dao.updateSales(sale_date, account_id, category_id, trade_name, unit_price, sale_number,
				note, sale_id);

		if (success) {
			session.setAttribute("success", "売上が更新されました。");
			response.sendRedirect("S0021.html");

		} else {
			session.setAttribute("error", "更新に失敗しました");
			response.sendRedirect("S0021.html");
		}
	}

}
