package controller;

import java.io.IOException;
import java.sql.Date;
import java.util.ArrayList;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import beans.Categories;
import beans.Users;
import services.S0010Service;

/**
 * Servlet implementation class S0010Servlet
 */
@WebServlet("/S0010Servlet")
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
		ArrayList<Users> accountList = new ArrayList<>();
		ArrayList<Categories> categoryList = new ArrayList<>();
		
		S0010Service ss = new S0010Service();
		accountList = ss.selectAccount();
		categoryList = ss.selectCategory();
		
		request.setAttribute("accountList", accountList);
		request.setAttribute("categoryList", categoryList);
		
		request.getRequestDispatcher("S0010.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		S0010Service ss = new S0010Service();
		
		
		Date saledate =Date.valueOf(request.getParameter("saledate"));
		int staff = Integer.parseInt(request.getParameter("staff"));
		int category =Integer.parseInt( request.getParameter("category"));
		String trage = request.getParameter("trage");
		int unitprice = Integer.parseInt(request.getParameter("unitp_rice"));	
		int sale_num = Integer.parseInt(request.getParameter("sale_num"));	
		String note = request.getParameter("note");
		
		ss.insert(saledate, staff,category, trage, unitprice, sale_num, note);
		response.sendRedirect("S0011Servlet");
	}

}
