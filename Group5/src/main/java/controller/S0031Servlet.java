package controller;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import beans.AccountsData;
import daos.S0031Dao;
import services.auth;

/**
 * Servlet implementation class S0031Servlet
 */
@WebServlet("/S0031.html")
public class S0031Servlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public S0031Servlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		//response.getWriter().append("Served at: ").append(request.getContextPath());
		HttpSession session = request.getSession();
		AccountsData accountsdata = (AccountsData) session.getAttribute("accountsdata");
		
		request.setAttribute("accountsdata", accountsdata);
		request.getRequestDispatcher("/S0031.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		//doGet(request, response);
		HttpSession session = request.getSession();
		AccountsData accountsdata = (AccountsData) session.getAttribute("accountsdata");
		
		String name = accountsdata.getName();
		String mail = accountsdata.getMail();
		String pass = accountsdata.getPass();
		String role = accountsdata.getRole();

		String hashedPass = auth.hashPassword(pass);

		S0031Dao s0031dao = new S0031Dao();
		boolean success = s0031dao.insert(name, mail, hashedPass, role);

		if (success) {
			session.setAttribute("success", "アカウントが作成されました。");
			response.sendRedirect("S0030.html");
		} else {
			session.setAttribute("error", "登録に失敗しました");
			response.sendRedirect("S0030.html");
		}

		//request.getRequestDispatcher("/C002.jsp").forward(request, response);

	}

}
