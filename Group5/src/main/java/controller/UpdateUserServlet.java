package controller;

import java.io.IOException;
import java.sql.Connection;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import beans.Users;
import services.UserService;
import utils.Db;

/**
 * Servlet implementation class UpdateUserServlet
 */
@WebServlet("/UpdateUserServlet")
public class UpdateUserServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public UpdateUserServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		Users user = new Users();
		HttpSession session = request.getSession(false);

		Users loginUser = (Users) session.getAttribute("loginUser");
		int id = loginUser.getId();

		try (Connection conn = Db.open();) {
			user = UserService.selectDetail(id);
		} catch (Exception e) {
			e.printStackTrace();
		}

		String success = (String) session.getAttribute("success");
		if (success != null) {
			request.setAttribute("success", success);
			session.removeAttribute("success");
			//			 ChangePasswordServletから引継ぎ、表示できる。
			//			その後successの中身を削除することで一度だけ表示する。
		}
		request.setAttribute("user", user);
		request.getRequestDispatcher("/updateUser.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		HttpSession session = request.getSession(false);
		Users loginUser = (Users) session.getAttribute("loginUser");
		int id = loginUser.getId();
		String username = request.getParameter("username");
		String email = request.getParameter("email");

		boolean otherNameCheck = UserService.OtherUserNameCheck(id, username);
		boolean otherEmailCheck = UserService.OtherUserEmailCheck(id, email);

		if (otherNameCheck == true) {
			Users user = new Users();
			user.setId(id);
			user.setUsername(username);
			user.setEmail(email);
			request.setAttribute("user", user);
			request.setAttribute("error", "このユーザ名は既に使用されています。");
			request.getRequestDispatcher("updateUser.jsp").forward(request, response);
		} else if (otherEmailCheck == true) {
			Users user = new Users();
			user.setId(id);
			user.setUsername(username);
			user.setEmail(email);
			request.setAttribute("user", user);
			request.setAttribute("error", "このメールアドレスは既に使用されています。");
			request.getRequestDispatcher("updateUser.jsp").forward(request, response);
		} else {
			try {
				UserService.updateCommon(id, username, email);
			} catch (Exception e) {
				e.printStackTrace();
				e.getMessage();
			}
			request.getRequestDispatcher("TodoServlet").forward(request, response);
		}

	}
}
