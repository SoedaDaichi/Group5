package controller;

import java.io.IOException;
import java.util.ArrayList;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import beans.Accounts;
import services.S0040Service;

/**
 * Servlet implementation class S0040Servlet
 */
@WebServlet("/S0040.html")
public class S0040Servlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public S0040Servlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.getRequestDispatcher("/S0040.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String name = request.getParameter("name");
		String mail = request.getParameter("mail");
		
		int role0 = parseRole(request.getParameter("role0"));
		int role1 = parseRole(request.getParameter("role1"));
		int role10 = parseRole(request.getParameter("role10"));
		
		S0040Service s0040service = new S0040Service();
		ArrayList<Accounts> accountList = s0040service.select(name, mail, role0, role1, role10);

	request.setAttribute("accountList",accountList);
	request.getRequestDispatcher("/S0041.jsp").forward(request,response);
	}
	
	private int parseRole(String roleStr) {
	    if (roleStr != null && !roleStr.isEmpty()) {
	        return Integer.parseInt(roleStr);
	    }
	    return 5;
	}

}
