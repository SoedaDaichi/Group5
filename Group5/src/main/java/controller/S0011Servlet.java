package controller;

import java.io.IOException;
import java.sql.Date;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import beans.SalesData;
import daos.S0010Dao;

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
		HttpSession session = request.getSession();
		SalesData salesdata = (SalesData) session.getAttribute("salesdata");
		
		request.setAttribute("salesdata", salesdata);
		request.getRequestDispatcher("/S0011.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		S0010Dao s0010dao = new S0010Dao();
		
		HttpSession session = request.getSession();
		SalesData salesdata = (SalesData) session.getAttribute("data");
		
		Date sale_date = salesdata.getSale_date();
	    int account_id = salesdata.getAccount_id();
	    int category_id = salesdata.getCategory_id();
	    String trade_name = salesdata.getTrade_name();
	    int unit_price = salesdata.getUnit_price();
	    int sale_number = salesdata.getSale_number();
	    String note = salesdata.getNote();
		
	    session.removeAttribute("salesdata");
		
	    boolean success = s0010dao.insert(sale_date, account_id,category_id, trade_name,
	    									unit_price, sale_number, note);
		
		if (success) {
			session.setAttribute("success", "商品が登録されました");
			response.sendRedirect("S0010.html");
		} else {
			session.setAttribute("error", "登録に失敗しました");
			response.sendRedirect("S0010.html");
		}	
	}
}
