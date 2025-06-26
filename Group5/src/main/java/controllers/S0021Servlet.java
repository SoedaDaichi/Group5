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

import daos.SalesDao;
import data.SalesData;
import form.SalesForm;
import form.SalesSearchForm;
import services.ErrorService;

/**
 * Servlet implementation class S0021Servlet
 */
@WebServlet("/S0021.html")
public class S0021Servlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public S0021Servlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession();

		String success = (String) session.getAttribute("success");
		String error = (String) session.getAttribute("error");
		SalesSearchForm ssForm = (SalesSearchForm) session.getAttribute("ssForm");

		if (success != null) {
			request.setAttribute("success", success);
			session.removeAttribute("success");
		} else if (error != null) {
			request.setAttribute("error", error);
			session.removeAttribute("error");
		}
		
		SalesDao salesDao = new SalesDao();
		ArrayList<SalesForm> salesList = salesDao.selectSearch(ssForm);
		
		ErrorService es = new ErrorService();
		Map<String, String> notFound = es.validateNotFoundSales(salesList);
		if (notFound != null && !notFound.isEmpty()) {
			session.setAttribute("notFound", notFound);
			System.out.println("notFoundError" + notFound);
			response.sendRedirect("S0020.html");
			return;
		}
		request.setAttribute("salesList", salesList);
		request.getRequestDispatcher("/S0021.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		request.setCharacterEncoding("UTF-8");
		int saleId = Integer.valueOf(request.getParameter("saleId"));
		System.out.println("詳細を見る主キー: " + saleId);
		HttpSession session = request.getSession();

		SalesDao salesDao = new SalesDao();
		SalesData salesData = salesDao.identificationSalesData(saleId);
		
		session.setAttribute("saleId", saleId);
		session.setAttribute("salesData", salesData);

		response.sendRedirect("S0022.html");
	}

}
