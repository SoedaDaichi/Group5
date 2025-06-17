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
		HttpSession session = request.getSession(false);

		if (session != null) {
			String success = (String) session.getAttribute("success");
			Map<String, String> errors = (Map<String, String>) session.getAttribute("errors");
			AccountsForm accountsform = null;

			if (success != null) {
				request.setAttribute("success", success);
				session.removeAttribute("success");
			} else if (errors != null) {
				request.setAttribute("error", errors);
				accountsform = (AccountsForm) session.getAttribute("accountsform");
				request.setAttribute("accountsform", accountsform);
				session.removeAttribute("errors");
				session.removeAttribute("accountsform");
			}
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
		String confirm_pass = request.getParameter("confirm_pass");
		String role = request.getParameter("role");
		System.out.println("権限： " + role);

		ErrorService es = new ErrorService();
		Map<String, String> errors = new HashMap<>();
		errors = es.ValidateAccounts(name, mail, pass, confirm_pass);
		System.out.println("アカウント登録エラー: " + errors);
		HttpSession session = request.getSession();

		if (errors != null && !errors.isEmpty()) {
			AccountsForm accountsform = new AccountsForm(name, mail, role);
			session.setAttribute("accountsform", accountsform);
			session.setAttribute("errors", errors);
			response.sendRedirect("S0010.html");
			return;
		}
		
		if (errors == null || errors.isEmpty()) {
			AccountsData accountsdata = new AccountsData(name, mail, pass, role);
			session.setAttribute("accountsdata", accountsdata);
			response.sendRedirect("S0031.html");
		}
	}
}