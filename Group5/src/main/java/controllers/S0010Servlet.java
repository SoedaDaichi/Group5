package controllers;

import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import daos.SalesDao;
import data.SalesData;
import form.Accounts;
import form.Categories;
import form.SalesForm;
import services.MessageService;
import services.ErrorService;

/**
 * Servlet implementation class S0010Servlet
 */
@WebServlet("/S0010.html")
public class S0010Servlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public S0010Servlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession();
		String success = (String) session.getAttribute("success");
		Map<String, String> errors = MessageService.processSessionMessages(request);
		//				System.out.println(errors);
		SalesForm registerSalesForm = (SalesForm) session.getAttribute("registerSalesForm");
		//		System.out.println("絶対呼ばれる: " + Register_salesform);

		if (success != null) {
			MessageService.moveAttribute(session, request, "success", success);
			session.removeAttribute("registerSalesForm");
		} else if (errors != null) {
			request.setAttribute("errors", errors);
			request.setAttribute("registerSalesForm", registerSalesForm);
		}

		ArrayList<Accounts> accountList = new ArrayList<>();
		ArrayList<Categories> categoryList = new ArrayList<>();

		SalesDao salesDao = new SalesDao();
		accountList = salesDao.selectAccount();
		System.out.println(accountList.size());
		categoryList = salesDao.selectCategory();

		request.setAttribute("accountList", accountList);
		request.setAttribute("categoryList", categoryList);

		request.getRequestDispatcher("/S0010.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		HttpSession session = request.getSession();

		ErrorService es = new ErrorService();
		Map<String, String> errors = es.validateSales(request);
		//		System.out.println(errors);
		SalesForm registerSalesForm = new SalesForm(request);
		System.out.println("入力保持" + registerSalesForm);

		if (errors != null && !errors.isEmpty()) {
			//			System.out.println("エラー時: " + Register_salesform);
			session.setAttribute("registerSalesForm", registerSalesForm);
			//			System.out.println("session保存後" + session.getAttribute("Register_salesform"));
			@SuppressWarnings("unchecked")
			Queue<Map<String, String>> errorQueue = (Queue<Map<String, String>>) session.getAttribute("errorQueue");
			if (errorQueue == null) {
				errorQueue = new LinkedList<>();
			}
			errorQueue.add(errors);
			session.setAttribute("errorQueue", errorQueue);
			response.sendRedirect("S0010.html");
			return;
		}

		SalesDao s0010Dao = new SalesDao();
		Accounts account = s0010Dao.identificationAccount(Integer.valueOf(registerSalesForm.getAccountIdStr()));
		String accountName = account.getName();

		Categories category = s0010Dao.identificationCategory(Integer.valueOf(registerSalesForm.getCategoryIdStr()));
		String categoryName = category.getCategoryName();

		SalesData registerSalesData = new SalesData(registerSalesForm, accountName, categoryName);

		session.setAttribute("registerSalesForm", registerSalesForm);
		session.setAttribute("registerSalesData", registerSalesData);
		// request.getRequestDispatcher("S0011.html").forward(request, response);
		response.sendRedirect("S0011.html");
	}
}