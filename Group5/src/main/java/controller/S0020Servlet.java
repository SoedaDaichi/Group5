package controller;

import java.io.IOException;
import java.util.ArrayList;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import beans.Accounts;
import beans.Categories;
import beans.Sales;
import daos.S0020Dao;
import services.S0010Service;

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
		String firststr = request.getParameter("first");
		String laststr = request.getParameter("last");
		String account_idstr = request.getParameter("account_id");
		System.out.println("アカウントID: " + account_idstr);
		String category_idstr = request.getParameter("category_id");
		String trade = request.getParameter("trade");
		String note = request.getParameter("note");
		System.out.println(firststr);
		System.out.println(laststr);
		S0020Dao s0020dao = new S0020Dao();
		ArrayList<Sales> salesList = s0020dao.select(firststr, laststr, account_idstr, category_idstr, trade, note);
		
		request.setAttribute("salesList",salesList);
		request.getRequestDispatcher("/S0021.jsp").forward(request,response);
	}
}
