package controller;

import java.io.IOException;
import java.sql.Date;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

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
		int CID = (int) session.getAttribute("category_id");
		System.out.println(CID);
		request.getRequestDispatcher("/S0011.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		S0010Dao s0010dao = new S0010Dao();
		
		HttpSession session = request.getSession();
		
		Date saledate = (Date) session.getAttribute("saledate");
	    int account_id = (int) session.getAttribute("account_id");
	    int category_id = (int) session.getAttribute("category_id");
	    String trade = (String) session.getAttribute("trade");
	    int unit_price = (int) session.getAttribute("unit_price");
	    int sale_num = (int) session.getAttribute("sale_number");
	    String note = (String) session.getAttribute("note");
		

		
	    boolean success = s0010dao.insert(saledate, account_id,category_id, trade,
	    									unit_price, sale_num, note);
		
		if (success) {
			session.setAttribute("success", "商品が登録されました");
			response.sendRedirect("S0010.html");
		} else {
			session.setAttribute("error", "登録に失敗しました");
			response.sendRedirect("S0010.html");
		}	
	}
}
