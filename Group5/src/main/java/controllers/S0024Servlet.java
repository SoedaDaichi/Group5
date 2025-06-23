
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
import services.SessionDataService;
import services.SuccessMessageService;
import services.SuccessMessageService.SuccessMessage;

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
		SessionDataService.SalesDataSession(request);
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

		session.removeAttribute("saleId");
		session.removeAttribute("salesData");

		SalesDao sd = new SalesDao();
		boolean success = sd.updateSales(saleId, salesData);

		// S0010ページ上部の処理成功、失敗メッセージをsessionにセット
		SuccessMessageService.SuccessSet(
				request, success, SuccessMessage.S0024Success, SuccessMessage.S0024Error);

		response.sendRedirect("S0021.html");
	}

}
