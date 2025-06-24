package controllers;

import java.io.IOException;
import java.util.ArrayList;
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
import beans.SalesSearchForm;
import daos.S0010Dao;
import daos.S0020Dao;
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
		HttpSession session = request.getSession();
		Map<String, String> errors = (Map<String, String>) session.getAttribute("errors");
		Map<String, String> notFound = (Map<String, String>) session.getAttribute("notFound");
		SalesSearchForm ssForm = (SalesSearchForm) session.getAttribute("ssForm");

		if (errors != null) {
			request.setAttribute("errors", errors);
			session.removeAttribute("errors");
		}
		if (notFound != null) {
			request.setAttribute("notFound", notFound);
			session.removeAttribute("notFound");
		}
		if (ssForm != null) {
			request.setAttribute("ssForm", ssForm);
			session.removeAttribute("ssForm");
		}

		S0010Dao ss = new S0010Dao();
		ArrayList<Accounts> accountList = ss.selectAccount();
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
		HttpSession session = request.getSession();

		String firstStr = request.getParameter("first");
		String lastStr = request.getParameter("last");
		String accountIdStr = request.getParameter("account_id");
		String categoryIdStr = request.getParameter("category_id");
		String tradeName = request.getParameter("trade_name");
		String note = request.getParameter("note");

		ErrorService es = new ErrorService();

		Map<String, String> errors = es.validateSalesSearch(firstStr, lastStr);
		if (errors != null && !errors.isEmpty()) {
			session.setAttribute("errors", errors);
			response.sendRedirect("S0020.html");
			return;
		}

		SalesSearchForm ssForm = new SalesSearchForm(firstStr, lastStr, accountIdStr, categoryIdStr, tradeName, note);
		session.setAttribute("ssForm", ssForm);

		S0020Dao s0020Dao = new S0020Dao();
		ArrayList<Sales> salesList = s0020Dao.select(ssForm);

		Map<String, String> notFound = es.validateNotFoundSales(salesList);
		if (notFound != null && !notFound.isEmpty()) {
			session.setAttribute("notFound", notFound);
			response.sendRedirect("S0020.html");
			return;
		}

		session.setAttribute("salesList", salesList);
		response.sendRedirect("S0021.html");
	}
}
