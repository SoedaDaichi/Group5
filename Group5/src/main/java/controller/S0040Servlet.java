package controller;

import java.io.IOException;
import java.sql.Connection;
import java.util.ArrayList;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import beans.Users;
import services.UserService;
import utils.Db;

/**
 * Servlet implementation class S0040Servlet
 */
@WebServlet("/S0040Servlet")
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
		String role0Str = request.getParameter("role0");
		int role0 = 3;
		if (role0Str != null && !role0Str.isEmpty()) {
			role0 = Integer.valueOf(role0Str);
		}
		String role1Str = request.getParameter("role1");
		int role1 = 3;
		if (role1Str != null && !role0Str.isEmpty()) {
			role1 = Integer.valueOf(role1Str);
		}
		String role10Str = request.getParameter("role10");
		int role10 = 3;
		if (role10Str != null && !role0Str.isEmpty()) {
			role10 = Integer.valueOf(role10Str);
		}
		ArrayList<Users> accountList = new ArrayList<>();
		try (Connection conn = Db.open();) {
			accountList = UserService.select(name, mail, role0, role1, role10);
		} catch (Exception e) {
			e.printStackTrace();
		}

	request.setAttribute("accountList",accountList);
	request.getRequestDispatcher("S0041.jsp").forward(request,response);

	}

}
