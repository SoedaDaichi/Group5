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
import beans.SalesSearchForm;
import daos.S0010Dao;
import services.ErrorService;

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
	@SuppressWarnings("unchecked")
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		HttpSession session = request.getSession();
		Map<String, String> errors = (Map<String, String>) session.getAttribute("errors");
		Map<String, String> notFound = (Map<String, String>) session.getAttribute("notFound");
		SalesSearchForm ssform = (SalesSearchForm) session.getAttribute("ssform");

		if (errors != null) {
			request.setAttribute("errors", errors);
			session.removeAttribute("errors");
		}
		if (notFound != null) {
			request.setAttribute("notFound", notFound);
			session.removeAttribute("notFound");
		}
		if (ssform != null) {
			request.setAttribute("ssform", ssform);
			session.removeAttribute("ssform");
		}

		S0010Dao ss = new S0010Dao();
		ArrayList<Accounts> accountList = ss.selectAccount();
		//		System.out.println(accountList.size());
		ArrayList<Categories> categoryList = ss.selectCategory();

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

		Map<String, String> errors = es.ValidateSalesSearch(request);
		System.out.println("日付エラー: " + errors);
		if (errors != null && !errors.isEmpty()) {
			Queue<Map<String, String>> errorQueue = new ConcurrentLinkedQueue<>();
			errorQueue.add(errors);
			session.setAttribute("errorsQueue", errorQueue);
			response.sendRedirect("S0020.html");
			return;
		}

		SalesSearchForm ssform = new SalesSearchForm(request);
		session.setAttribute("ssform", ssform);
		response.sendRedirect("S0021.html");
	}
}
