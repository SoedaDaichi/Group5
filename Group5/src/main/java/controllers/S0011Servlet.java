package controllers;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import beans.SalesData;
import daos.SalesDao;
import services.SessionConfirmService;
import services.SuccessMessageService;
import services.SuccessMessageService.SuccessMessage;

/**
 * Servlet implementation class S0011Servlet
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
		SessionConfirmService.S0011SessionConfirmPostService(request); // 入力内容をJSPにセット
		
		request.getRequestDispatcher("/S0011.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");

		// 入力内容をsessionから獲得
		SalesData RegisterSalesdata = SessionConfirmService.S0011SessionConfirmPostService(request);

		SalesDao sd = new SalesDao();
		boolean success = sd.insert(RegisterSalesdata);
		
		// S0010ページ上部の処理成功、失敗メッセージをsessionにセット
		SuccessMessageService.SuccessSet(request, success, SuccessMessage.S0011Success, SuccessMessage.S0011Error);
	
		response.sendRedirect("S0010Servlet");
	}
}
