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
import beans.SalesData;
import daos.S0010Dao;
import daos.S0023Dao;

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
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		HttpSession session = request.getSession();
		
		ArrayList<Accounts> accountList = new ArrayList<>();
		ArrayList<Categories> categoryList = new ArrayList<>();

		S0010Dao ss = new S0010Dao();
		accountList = ss.selectAccount();
		categoryList = ss.selectCategory();
		
		SalesData data = (SalesData) session.getAttribute("data");
		
		request.setAttribute("data", data);
		session.removeAttribute("data");
		
		
	    request.setAttribute("accountList", accountList);
		request.setAttribute("categoryList", categoryList);

	    request.getRequestDispatcher("/S0023.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		
		int sale_id = (int) session.getAttribute("sale_id");
		Date sale_date = Date.valueOf(request.getParameter("sale_date"));
		int account_id = Integer.valueOf(request.getParameter("account_id"));
		int category_id = Integer.valueOf(request.getParameter("category_id"));
		String trade = request.getParameter("trade_name");
		int unit_price = Integer.valueOf(request.getParameter("unit_price"));
		int sale_number = Integer.valueOf(request.getParameter("sale_number"));
		String note = request.getParameter("note");
		System.out.println(sale_id);
		
		session.removeAttribute("sale_id");
		
		S0023Dao s0023dao = new S0023Dao();
			s0023dao.updateSales(sale_date, account_id, category_id, trade,unit_price, sale_number, note, sale_id);
			
			response.sendRedirect("S0020.html");
		
	}

}
