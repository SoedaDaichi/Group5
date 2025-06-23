package controllers;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import beans.SalesData;
import daos.SalesDao;

/**
 * Servlet implementation class S0025Servret
 */
@WebServlet("/S0025.html")
public class S0025Servret extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public S0025Servret() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		SalesData salesdata = (SalesData) session.getAttribute("salesdata");
		
		request.setAttribute("salesdata", salesdata);
		session.removeAttribute("salesdata");
		request.getRequestDispatcher("/S0025.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		
		int sale_id = (int) session.getAttribute("sale_id");
		System.out.println("削除"+sale_id);
	    session.removeAttribute("sale_id");
	    
	    SalesDao sd = new SalesDao();
	    sd.deleteSales(sale_id);
	    
	    response.sendRedirect("S0021.html");	    
	}

}
