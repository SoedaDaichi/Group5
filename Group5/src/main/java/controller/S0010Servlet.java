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
import services.S0010Service;

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
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ArrayList<Accounts> accountList = new ArrayList<>();
		ArrayList<Categories> categoryList = new ArrayList<>();
		
		S0010Service ss = new S0010Service();
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
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		
		Date saledate = Date.valueOf(request.getParameter("saledate"));
		String account_name = request.getParameter("account_name");
		String cotegory_name = request.getParameter("cotegory_name");
		String trade_name =request.getParameter("trade");
		int unit_price = Integer.valueOf(request.getParameter("unit_price"));
		int sale_number = Integer.valueOf(request.getParameter("sale_num"));
		String note = request.getParameter("note");
		
		System.out.println(account_name);
		
		request.setAttribute("saledate", saledate);
		request.setAttribute("account_name", account_name);
		request.setAttribute("cotegory_name", cotegory_name);
		request.setAttribute("trade_name", trade_name);
		request.setAttribute("unit_price", unit_price);
		request.setAttribute("sale_number",sale_number);
		request.setAttribute("note", note);
		
		request.getRequestDispatcher("S0011.html").forward(request, response);
	}
}
