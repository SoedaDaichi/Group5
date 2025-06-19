package controller;

import java.io.IOException;
import java.util.ArrayList;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import beans.Accounts;
import beans.AccountsSearchForm;
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
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.getRequestDispatcher("/S0040.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		HttpSession session = request.getSession();
		
		System.out.println("----------アカウント検索-----------");

		String name = request.getParameter("name");
		System.out.println("アカウント名： " + name);
		String mail = request.getParameter("mail");
		System.out.println("メールアドレス： " + mail);

		int authority_0 = parseAuthority(request.getParameter("authority_0"));
		int authority_1 = parseAuthority(request.getParameter("authority_1"));
		int authority_2 = parseAuthority(request.getParameter("authority_2"));
		int authority_3 = parseAuthority(request.getParameter("authority_3"));

		S0040Service s0040service = new S0040Service();
		AccountsSearchForm asform = new AccountsSearchForm(name, mail, authority_0, authority_1, authority_2,
				authority_3);
		session.setAttribute("asform", asform);
		ArrayList<Accounts> accountsList = s0040service.select(asform);
		System.out.println("検索結果: " + accountsList);

		session.setAttribute("accountsList", accountsList);
		response.sendRedirect("S0041.html");
	}

	private int parseAuthority(String authorityStr) {
		if (authorityStr != null && !authorityStr.isEmpty()) {
			System.out.println("権限： " + authorityStr);
			return Integer.parseInt(authorityStr);
		}
		return 5;
	}

}
