package controller;

import java.io.IOException;
import java.sql.Date;
import java.util.ArrayList;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import beans.Accounts;
import beans.Categories;
import beans.Sales;
import services.S0010Service;
import services.S0020Service;

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
		ArrayList<Accounts> accountList = new ArrayList<>();
		ArrayList<Categories> categoryList = new ArrayList<>();

		S0010Service ss = new S0010Service();
		accountList = ss.selectAccount();
		System.out.println(accountList.size());
		categoryList = ss.selectCategory();

		request.setAttribute("accountList", accountList);
		request.setAttribute("categoryList", categoryList);

		request.getRequestDispatcher("/S0020.jsp").forward(request, response);

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		Date first = Date.valueOf(request.getParameter("first"));
		Date last = Date.valueOf(request.getParameter("last"));
		int account_id = parseId(request.getParameter("acount_id"));
		int category_id = parseId(request.getParameter("category_id"));
		String trade = request.getParameter("trade");
		String note = request.getParameter("note");
		
		ArrayList<Sales> salesList = S0020Service.select(first, last, account_id, category_id, trade, note);
				
	}
	private Integer parseId(String idStr) {
		if (idStr != null && !idStr.isEmpty()) {
			return Integer.parseInt(idStr);
		}
		return null;
	}

}
