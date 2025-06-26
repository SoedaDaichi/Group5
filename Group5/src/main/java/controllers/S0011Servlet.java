package controllers;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import beans.SalesData;
import daos.SalesDao;

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
		SalesDao salesDao = new SalesDao();

		HttpSession session = request.getSession();
		String action = request.getParameter("action");
		if ("insert".equals(action)) {
			SalesData registerSalesData = (SalesData) session.getAttribute("registerSalesData");

			session.removeAttribute("registerSalesData"); // Filter範囲外

			boolean success = salesDao.insert(registerSalesData);

			if (success) {
				session.setAttribute("success", "商品が登録されました");
				response.sendRedirect("S0010.html");
			} else {
				session.setAttribute("error", "登録に失敗しました");
				response.sendRedirect("S0010.html");
			}
		} else if ("cancel".equals(action)) {
			response.sendRedirect("S0010.html");
		}
	}
}
