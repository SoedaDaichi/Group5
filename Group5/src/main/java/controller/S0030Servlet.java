package controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import beans.AccountsData;
import beans.AccountsForm;
import services.ErrorService;

/**
 * Servlet implementation class S0030Servlet
 */
@WebServlet("/S0030.html")
public class S0030Servlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public S0030Servlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	@SuppressWarnings("unchecked")
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		//response.getWriter().append("Served at: ").append(request.getContextPath());
		HttpSession session = request.getSession();
		Map<String, String> errors = (Map<String, String>) session.getAttribute("errors");
		AccountsForm RegisterAccountsform = (AccountsForm) session.getAttribute("RegisterAccountsform");

		String success = (String) session.getAttribute("success");
		if (success != null) {
			request.setAttribute("success", success);
			session.removeAttribute("success");
		} else if (errors != null) {
			request.setAttribute("errors", errors);
			request.setAttribute("RegisterAccountsform", RegisterAccountsform);
			session.removeAttribute("errors");
			session.removeAttribute("RegisterAccountsform");
		}
		request.getRequestDispatcher("/S0030.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		//doGet(request, response);
		System.out.println("----------アカウント登録-----------");

		String name = request.getParameter("name");
		System.out.println("アカウント名： " + name);
		String mail = request.getParameter("mail");
		System.out.println("メールアドレス： " + mail);
		String pass = request.getParameter("pass");
		String confirmPass = request.getParameter("confirmPass");
		String authority = request.getParameter("authority");
		System.out.println("権限： " + authority);

		ErrorService es = new ErrorService();
		Map<String, String> errors = new HashMap<>();
		errors = es.ValidateAccounts(name, mail, pass, confirmPass);
		System.out.println("アカウント登録エラー: " + errors);
		HttpSession session = request.getSession();

		if (errors != null && !errors.isEmpty()) {
			AccountsForm RegisterAccountsform = new AccountsForm(name, mail, authority);
			session.setAttribute("RegisterAccountsform", RegisterAccountsform);
			session.setAttribute("errors", errors);
			response.sendRedirect("S0030.html");
			return;
		}

		if (errors == null || errors.isEmpty()) {
			AccountsData RegisterAccountsdata = new AccountsData(name, mail, pass, confirmPass, authority);
			session.setAttribute("RegisterAccountsdata", RegisterAccountsdata);
			response.sendRedirect("S0031.html");
		}
	}
}