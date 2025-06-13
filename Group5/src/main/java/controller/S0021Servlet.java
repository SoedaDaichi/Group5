package controller;

import java.io.IOException;
import java.sql.Date;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import beans.Accounts;
import beans.Categories;
import beans.Sales;
import daos.S0010Dao;
import daos.S0021Dao;

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
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		request.setCharacterEncoding("UTF-8");
		int sale_id = Integer.valueOf(request.getParameter("id"));
		
		S0021Dao s0021dao = new S0021Dao();
		Sales sale = s0021dao.identificationSales(sale_id);
		
		Date sale_date = (Date) sale.getSale_date();
		int account_id = sale.getAccount_id();
		int category_id = sale.getCategory_id();
		String trade_name = sale.getTrade_name();
		int unit_price = sale.getUnit_price();
		int sale_number = sale.getSale_number();
		String nete = sale.getNote();
		
		//取り出したsalesテーブルのaccount_idとcategory_idを
		//各テーブルの紐づいたNAMEを取ってくる作業
		S0010Dao ss = new S0010Dao();
		Accounts account = ss.identificationAccount(account_id);
		String name = account.getName();
		int accountID = account.getAccount_id();
		
		Categories category = ss.identificationCategory(category_id);
		String category_name = category.getCategory_name();
		int categoryID = category.getCategory_id();
		System.out.println(name);
		request.setAttribute("sale_date", sale_date);
		request.setAttribute("name", name);
		request.setAttribute("account_id", accountID);
		request.setAttribute("category_name", category_name);
		request.setAttribute("category_id", categoryID);
		request.setAttribute("trade_name", trade_name);
		request.setAttribute("unit_price", unit_price);
		request.setAttribute("sale_number", sale_number);
		request.setAttribute("nete", nete);
		
		request.getRequestDispatcher("S0022.jsp").forward(request, response);
		
		}
	}

