package controller;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import services.DetailSearchService;
import services.SearchService;
import services.SuccessMessageService;

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

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		SearchService.AccountsSearchService(request);
		
		SuccessMessageService.processSessionMessages(request);
		


//		if (success != null) {
//			request.setAttribute("success", success);
//			session.removeAttribute("success");
//		} else if (error != null) {
//			request.setAttribute("error", error);
//			session.removeAttribute("error");
//		}
//
//		if ((accountsList == null || accountsList.isEmpty()) && asform != null) {
//			S0040Dao s0040dao = new S0040Dao();
//			ArrayList<Accounts> accountsListRe = s0040dao.select(asform);
//			request.setAttribute("accountsList", accountsListRe);
//			session.removeAttribute("accountList");
//			System.out.println("再検索");
//		} else if (asform != null) {
//			request.setAttribute("accountsList", accountsList);
//			session.removeAttribute("accountsList");
//			System.out.println("初回検索");
//		} else {
//			Map<String, String> notFound = new HashMap<>();
//			notFound.put("accounts_notfound", "エラーが発生しました。");
//			request.setAttribute("notFound", notFound); // 検索画面の上部にエラー文が出る
//			response.sendRedirect("S0040.html");
//			return;
//		}

		request.getRequestDispatcher("/S0041.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		String action = request.getParameter("action");
		
		DetailSearchService.createAccountsDetail(request);
		
		if ("edit".equals(action)) {
			response.sendRedirect("S0042.html");
		} else if ("delete".equals(action)) {
			response.sendRedirect("S0044.html");
		}
	}
}