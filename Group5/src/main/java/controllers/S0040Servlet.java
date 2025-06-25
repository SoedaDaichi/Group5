package controllers;

import java.io.IOException;
import java.util.Map;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import beans.AccountsSearchForm;
import services.ErrorService;

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
	@SuppressWarnings("unchecked")
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		HttpSession session = request.getSession();
		
		Map<String, String> errors = (Map<String, String>) session.getAttribute("errors");
		Map<String, String> notFound = (Map<String, String>) session.getAttribute("accountsNotFound");
		AccountsSearchForm asForm = (AccountsSearchForm) request.getAttribute("asForm");
		
		if (errors != null) {
			request.setAttribute("errors", errors);
			session.removeAttribute("errors");
		}
		if (notFound != null) {
			request.setAttribute("accountsNotFound", notFound);
			session.removeAttribute("notFound");
		}
		if (asForm != null) {
			request.setAttribute("asForm", asForm);
			session.removeAttribute("ssForm");
		}
		request.getRequestDispatcher("/S0040.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession();

		System.out.println("----------アカウント検索-----------");

		String name = request.getParameter("name");
		System.out.println("アカウント名： " + name);
		String mail = request.getParameter("mail");
		System.out.println("メールアドレス： " + mail);

		String[] authorityArray = request.getParameterValues("authority");
		ErrorService es = new ErrorService();
		Map<String, String> errors = es.validateAccountsSearch(name, mail);
		if (errors != null && !errors.isEmpty()) {
			session.setAttribute("errors", errors);
			response.sendRedirect("S0040.html");
			return;
		}

		AccountsSearchForm asForm = new AccountsSearchForm(name, mail, authorityArray);
		

		session.setAttribute("asForm", asForm);
		response.sendRedirect("S0041.html");
	}
}
