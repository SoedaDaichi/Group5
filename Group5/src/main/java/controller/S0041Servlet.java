package controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import beans.Accounts;
import beans.AccountsSearchForm;
import daos.S0041Dao;
import services.S0040Service;

/**
 * Servlet implementation class S0041Servlet
 */
@WebServlet("/S0041.html")
public class S0041Servlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public S0041Servlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	@SuppressWarnings("unchecked")
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession();

		// セッションから検索結果とキーワードを取得
		String success = (String) session.getAttribute("success");
		String error = (String) session.getAttribute("error");
		AccountsSearchForm asform = (AccountsSearchForm) session.getAttribute("asform");
		ArrayList<Accounts> accontsList = (ArrayList<Accounts>) session.getAttribute("accountsList");

		if (success != null) {
			request.setAttribute("success", success);
			session.removeAttribute("success");
		} else if (error != null) {
			request.setAttribute("error", error);
			session.removeAttribute("error");
		}

		if ((accontsList == null || accontsList.isEmpty()) && asform != null) {
			S0040Service s0040service = new S0040Service();
			ArrayList<Accounts> accountsListRe = s0040service.select(asform);
			request.setAttribute("accontsList", accountsListRe);
		} else if (asform != null) {
			request.setAttribute("accontsList", accontsList);
			session.removeAttribute("accontsList");
		} else {
			Map<String, String> notFound = new HashMap<>();
			notFound.put("acconts_notfound", "エラーが発生しました。");
			request.setAttribute("notFound", notFound); // 検索画面の上部にエラー文が出る
			response.sendRedirect("S0040.html");
			return;
		}

		request.getRequestDispatcher("/S0041.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");

		int account_id = Integer.valueOf(request.getParameter("account_id"));
		String action = request.getParameter("action");
		HttpSession session = request.getSession();

		S0041Dao s0041dao = new S0041Dao();
		Accounts accounts = s0041dao.getAccountsByAccount_id(account_id);

		session.setAttribute("account_id", account_id);
		session.setAttribute("accounts", accounts);

		if ("edit".equals(action)) {
			response.sendRedirect("S0042.html");
		} else if ("delete".equals(action)) {
			response.sendRedirect("S0044.html");
		}
	}
}