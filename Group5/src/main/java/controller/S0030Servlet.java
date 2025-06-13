package controller;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import daos.S0030Dao;

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
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		//response.getWriter().append("Served at: ").append(request.getContextPath());
		HttpSession session = request.getSession(false);

		if (session != null) {
			String success = (String) session.getAttribute("success");
			String error = (String) session.getAttribute("error");

			if (success != null) {
				request.setAttribute("success", success);
				session.removeAttribute("success");
			} else if (error != null) {
				request.setAttribute("error", error);
				session.removeAttribute("error");
			}
			request.getRequestDispatcher("/S0030.jsp").forward(request, response);
		}
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

		HttpSession session = request.getSession();

		S0030Dao s0030dao = new S0030Dao();
		if (s0030dao.accountNameCheck(name)) {
			session.setAttribute("error", "このユーザ名は既に使用されています。");
			response.sendRedirect("S0030.html");
			return;
		}

		if (s0030dao.accountEmailCheck(mail)) {
			session.setAttribute("error", "このメールアドレスは既に使用されています。");
			response.sendRedirect("S0030.html");
			return;
		}

		if (!pass.equals(confirm_pass)) {
			session.setAttribute("error", "パスワードが一致していません。");
			response.sendRedirect("S0030.html");
			return;
		}

		//		try {
		//			String hashedPass = UserService.hashPassword(pass);
		//
		//			UserService.insert(name, mail, hashedPass);
		//
		//			response.sendRedirect("S0031.jsp");
		//		} catch (Exception e) {
		//			e.printStackTrace();
		//			request.setAttribute("error", "登録に失敗しました。");
		//			request.getRequestDispatcher("S0030.jsp").forward(request, response);
		//		}

		request.setAttribute("name", name);
		request.setAttribute("mail", mail);
		request.setAttribute("pass", pass);
		request.setAttribute("confirm_pass", confirm_pass);
		request.setAttribute("role", role);

		request.getRequestDispatcher("/S0031.jsp").forward(request, response);
	}
}