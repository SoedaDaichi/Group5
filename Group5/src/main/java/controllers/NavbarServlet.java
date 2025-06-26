package controllers;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

/**
 * Servlet implementation class NavbarServlet
 */
@WebServlet("/NavbarServlet")
public class NavbarServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public NavbarServlet() {
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
		// TODO Auto-generated method stub
		HttpSession session = request.getSession(false);
		if (session != null) {
		    Enumeration<String> attrNames = session.getAttributeNames();
		    List<String> removeList = new ArrayList<>();
		    while (attrNames.hasMoreElements()) {
		        String name = attrNames.nextElement();
		        if (!"loginAccount".equals(name)) {
		            removeList.add(name);
		        }
		    }
		    for (String name : removeList) {
		        session.removeAttribute(name);
		    }
		    System.out.println("ログインセッション以外を削除");
		}
		
		String action = request.getParameter("action");
		if ("salesRegister".equals(action)) {
			response.sendRedirect("S0010.html");
			return;
		}
		if ("salesSearch".equals(action)) {
			response.sendRedirect("S0020.html");
			return;
		}
		if ("accountsRegister".equals(action)) {
			response.sendRedirect("S0030.html");
			return;
		}
		if ("accountsSearch".equals(action)) {
			response.sendRedirect("S0040.html");
			return;
		}
		
	}

}
