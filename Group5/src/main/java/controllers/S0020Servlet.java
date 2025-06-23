package controllers;

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
import beans.SalesSearchForm;
import daos.SalesDao;
import services.ErrorService;
import services.S0020ErrorMessageService;

/**
 * Servlet implementation class S0020Servlet
 */
@WebServlet("/S0020.html")
public class S0020Servlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public S0020Servlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		S0020ErrorMessageService.processSessionMessages(request);
		
		SalesDao sd = new SalesDao();
		ArrayList<Accounts> accountList = sd.selectAccount();
		//		System.out.println(accountList.size());
		ArrayList<Categories> categoryList = sd.selectCategory();

		request.setAttribute("accountList", accountList);
		request.setAttribute("categoryList", categoryList);

		request.getRequestDispatcher("/S0020.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		HttpSession session = request.getSession();
		ErrorService es = new ErrorService();

		SalesSearchForm ssform = new SalesSearchForm(request);
		session.setAttribute("ssform", ssform);

		Map<String, String> errors = es.ValidateSalesSearch(request);
		System.out.println("日付エラー: " + errors);
		if (errors != null && !errors.isEmpty()) {
			Queue<Map<String, String>> errorQueue = new ConcurrentLinkedQueue<>();
			errorQueue.add(errors);
			session.setAttribute("errorsQueue", errorQueue);
			response.sendRedirect("S0020.html");
			return;
		}
		Map<String, String> notFound = es.ValidateNotFoundSales(request);
		if (notFound != null && !notFound.isEmpty()) {
			session.setAttribute("notFound", notFound);
			response.sendRedirect("S0020.html");
			return;
		}

		response.sendRedirect("S0021.html");
	}
}
