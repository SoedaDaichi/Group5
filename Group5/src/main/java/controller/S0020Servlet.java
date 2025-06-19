package controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import beans.Accounts;
import beans.Categories;
import beans.Sales;
import beans.SalesSearchForm;
import daos.S0010Dao;
import daos.S0020Dao;
import services.ErrorService;

/**
 * Servlet implementation class S0020Servlet
 */
@WebServlet("/S0020.html")
public class S0020Servlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public S0020Servlet() {
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
		Map<String, String> notFound = (Map<String, String>) session.getAttribute("notFound");
		SalesSearchForm ssform = (SalesSearchForm) session.getAttribute("ssform");

		if (errors != null) {
			request.setAttribute("errors", errors);
			session.removeAttribute("errors");
		}
		if (notFound != null) {
			request.setAttribute("notFound", notFound);
			session.removeAttribute("notFound");
		}
		if (ssform != null) {
			request.setAttribute("ssform", ssform);
			session.removeAttribute("ssform");
		}

		ArrayList<Accounts> accountList = new ArrayList<>();
		ArrayList<Categories> categoryList = new ArrayList<>();

		S0010Dao ss = new S0010Dao();
		accountList = ss.selectAccount();
		System.out.println(accountList.size());
		categoryList = ss.selectCategory();

		request.setAttribute("accountList", accountList);
		request.setAttribute("categoryList", categoryList);

		request.getRequestDispatcher("/S0020.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		HttpSession session = request.getSession();

		System.out.println("----------売上検索-----------");
		String firstStr = request.getParameter("first");
		System.out.print("日付範囲: " + firstStr + "～");
		String lastStr = request.getParameter("last");
		System.out.println(lastStr);

		String account_idStr = request.getParameter("account_id");
		System.out.println("アカウントID: " + account_idStr);
		String category_idStr = request.getParameter("category_id");
		System.out.println("カテゴリーID： " + category_idStr);
		String trade_name = request.getParameter("trade_name");
		System.out.println("商品名" + trade_name);
		String note = request.getParameter("note");
		System.out.println("備考： " + note);

		ErrorService es = new ErrorService();
		
		Map<String, String> errors = es.ValidateSalesSearch(firstStr, lastStr);
		System.out.println("日付エラー: " + errors);
		if (errors != null && !errors.isEmpty()) {
			session.setAttribute("errors", errors);
			response.sendRedirect("S0020.html");
			return;
		}

		SalesSearchForm ssform = new SalesSearchForm(firstStr, lastStr, account_idStr, category_idStr,
				trade_name, note);
		session.setAttribute("ssform", ssform);


		S0020Dao s0020dao = new S0020Dao();
		ArrayList<Sales> salesList = s0020dao.select(ssform);
		System.out.println("検索結果: " + salesList);

		Map<String, String> notFound = es.ValidateNotFoundSales(salesList);
		System.out.println("検索結果なし: " + notFound);

		if (notFound != null && !notFound.isEmpty()) {
			session.setAttribute("notFound", notFound);
			response.sendRedirect("S0020.html");
			return;
		}

		session.setAttribute("salesList", salesList);
		response.sendRedirect("S0021.html");
	}
}
