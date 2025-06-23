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
import beans.SalesData;
import beans.SalesForm;
import daos.S0010Dao;
import services.ErrorMessageService;
import services.ErrorService;
import services.SessionDataService;
import services.SessionFormService;

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

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		ErrorMessageService.processSessionMessages(request); // error文をjspにセット
		SessionFormService.salesFormSession(request); // error文がセットされている場合、入力情報もセット

		// 上のメソッドでerrorがセットされていない場合（初回）
		SessionDataService.SalesDataSession(request);

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
		ErrorService es = new ErrorService();

		Map<String, String> errors = es.ValidateSales(request);

		if (errors != null && !errors.isEmpty()) {
			System.out.println("エラー: " + errors);
			Queue<Map<String, String>> errorQueue = new ConcurrentLinkedQueue<>();
			errorQueue.add(errors);
			SalesForm salesform = new SalesForm(request);
			session.setAttribute("salesform", salesform);
			session.setAttribute("errorQueue", errorQueue);
			response.sendRedirect("S0023.html");
			return;
		}

		S0010Dao ss = new S0010Dao();
		SalesData salesdata = new SalesData(request, ss);
		session.setAttribute("salesdata", salesdata);
		response.sendRedirect("S0024.html");
	}

}
