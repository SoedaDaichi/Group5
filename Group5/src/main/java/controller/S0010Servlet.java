package controller;

import java.io.IOException;
import java.sql.Date;
import java.util.ArrayList;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import beans.Accounts;
import beans.Categories;
import daos.S0010Dao;

/**
 * Servlet implementation class S0010Servlet
 */
@WebServlet("/S0010.html")
public class S0010Servlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public S0010Servlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		HttpSession session = request.getSession(false);
		if (session != null) {
			String success = (String) session.getAttribute("success");
			String error = (String) session.getAttribute("error");

			if (success != null) {
				request.setAttribute("success", success);
				session.removeAttribute("success");
			} else if (error != null) {
				request.setAttribute("error", error);
				session.removeAttribute("error");
			}
		}

		ArrayList<Accounts> accountList = new ArrayList<>();
		ArrayList<Categories> categoryList = new ArrayList<>();

		S0010Dao ss = new S0010Dao();
		accountList = ss.selectAccount();
		System.out.println(accountList.size());
		categoryList = ss.selectCategory();

		request.setAttribute("accountList", accountList);
		request.setAttribute("categoryList", categoryList);

		request.getRequestDispatcher("/S0010.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		HttpSession session = request.getSession();

		Date saledate = Date.valueOf(request.getParameter("saledate"));
		int account_id = Integer.valueOf(request.getParameter("account_id"));
		int category_id = Integer.valueOf(request.getParameter("category_id"));
		String trade = request.getParameter("trade");
		int unit_price = Integer.valueOf(request.getParameter("unit_price"));
		int sale_number = Integer.valueOf(request.getParameter("sale_num"));
		String note = request.getParameter("note");

		S0010Dao ss = new S0010Dao();
		Accounts account = ss.identificationAccount(account_id);
		String name = account.getName();
		int accountID = account.getAccount_id();

		Categories category = ss.identificationCategory(category_id);
		String categoryid = category.getCategory_name();
		int categoryID = category.getCategory_id();

		session.setAttribute("saledate", saledate);
		session.setAttribute("name", name);
		session.setAttribute("account_id", accountID);
		session.setAttribute("category_name", categoryid);
		session.setAttribute("category_id", categoryID);
		session.setAttribute("trade", trade);
		session.setAttribute("unit_price", unit_price);
		session.setAttribute("sale_number", sale_number);
		session.setAttribute("note", note);

		request.getRequestDispatcher("S0011.jsp").forward(request, response);
	}
}
