package controller;

import java.io.IOException;
import java.sql.Date;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

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

	    // セッションから情報を取り出す
		Date saledate = (Date) session.getAttribute("saledate");
	    String name = (String) session.getAttribute("name");
	    String category_name = (String) session.getAttribute("category_name");
	    String trade = (String) session.getAttribute("trade_name");
	    int unit_price = (int) session.getAttribute("unit_price");
	    int sale_number = (int) session.getAttribute("sale_number");
	    String note = (String) session.getAttribute("note");
	    
	    session.removeAttribute("sale_date");
	    session.removeAttribute("name");
	    session.removeAttribute("category_name");
	    session.removeAttribute("trade_name");
	    session.removeAttribute("unit_price");
	    session.removeAttribute("sale_number");
	    session.removeAttribute("note");
	    
	    session.setAttribute("saledate", saledate);
		session.setAttribute("name", name);
		session.setAttribute("category_name", category_name);
		session.setAttribute("trade", trade);
		session.setAttribute("unit_price", unit_price);
		session.setAttribute("sale_number", sale_number);
		session.setAttribute("note", note);
	    
		request.getRequestDispatcher("/S0025.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
