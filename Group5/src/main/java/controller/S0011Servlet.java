package controller;

import java.io.IOException;
import java.sql.Date;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import dao.S0010Dao;

/**
 * Servlet implementation class S0011Servlet
 */
@WebServlet("/S0011.html")
public class S0011Servlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public S0011Servlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher("/S0011.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		S0010Dao ss = new S0010Dao();
		
		
		Date saledate =Date.valueOf(request.getParameter("saledate"));
		int account_id = Integer.parseInt(request.getParameter("account_name"));
		int category_id =Integer.parseInt(request.getParameter("category_name"));
		String trade = request.getParameter("trade");
		int unit_price = Integer.parseInt(request.getParameter("unit_price"));	
		int sale_num = Integer.parseInt(request.getParameter("sale_num"));	
		String note = request.getParameter("note");
		
		ss.insert(saledate, account_id,category_id, trade, unit_price, sale_num, note);
		response.sendRedirect("S0010.html");
	}

}
