package controller;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import beans.Accounts;


/**
 * Servlet implementation class S0042Servlet
 */
@WebServlet("/S0042.html")
public class S0042Servlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public S0042Servlet() {
        super();
        // TODO Auto-generated constructor stub
    }
    
    private static final String DB_URL = "jdbc:mariadb://localhost:3306/group5";
	private static final String DB_USER = "root";
	private static final String DB_PASS = "root";

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//response.getWriter().append("Served at: ").append(request.getContextPath());

		
//		HttpSession session = request.getSession();
//		session.removeAttribute("success");
//		session.removeAttribute("error");

		
//		
//		String idStr = request.getParameter("id");
//		if (idStr == null || idStr.isEmpty()) {
//			//エラー処理とりあえず消してる
//			//response.sendRedirect("error.jsp");
//			//return;
//			idStr = "1"; // とりあえず確認用に書いてるだけ
//			}
//
//
//		int accountId = Integer.parseInt(idStr);
//		Accounts account = null;
//
//		try (Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASS)) {
//			String sql = "SELECT * FROM accounts WHERE account_id = ?";
//			PreparedStatement stmt = conn.prepareStatement(sql);
//			stmt.setInt(1, accountId);
//
//			ResultSet rs = stmt.executeQuery();
//			if (rs.next()) {
//				account = new Accounts();
//				account.setAccount_id(rs.getInt("account_id"));
//				account.setName(rs.getString("name"));
//				account.setMail(rs.getString("mail"));
//				account.setPass(rs.getString("pass"));
//				account.setAuthority(rs.getInt("authority"));
//			}
//
//			rs.close();
//			stmt.close();
//		} catch (Exception e) {
//			e.printStackTrace();
//			//エラー処理とりあえず消してる
//			//response.sendRedirect("error.jsp");
//            //return;
//		}
//			request.setAttribute("account", account);
//		request.getRequestDispatcher("/S0042.jsp").forward(request, response);
//		
//		
		
		String idStr = request.getParameter("id");
		System.out.println("aa" + idStr);
		Accounts account = null;
		
		if (idStr != null && !idStr.isEmpty()) {
		    int accountId = Integer.parseInt(idStr);
		    //Accounts account = null;

		    try (Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASS)) {
		        String sql = "SELECT * FROM accounts WHERE account_id = ?";
		        PreparedStatement stmt = conn.prepareStatement(sql);
		        stmt.setInt(1, accountId);

		        ResultSet rs = stmt.executeQuery();
		        if (rs.next()) {
		            account = new Accounts();
		            account.setAccount_id(rs.getInt("account_id"));
		            account.setName(rs.getString("name"));
		            account.setMail(rs.getString("mail"));
		            account.setPass(rs.getString("password"));
		            account.setConfirm_pass(account.getPass());
		            account.setAuthority(rs.getInt("authority"));
		        }
		    } catch (Exception e) {
		        e.printStackTrace();
		    }

		}

		request.setAttribute("account", account);
	    request.getRequestDispatcher("/S0042.jsp").forward(request, response);		
		
		
	}


	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//doGet(request, response);
		
		
		HttpSession session = request.getSession();
		String successMessage = (String) session.getAttribute("success");
		String errorMessage = (String) session.getAttribute("error");

		request.setAttribute("success", successMessage);
		request.setAttribute("error", errorMessage);

		// 一度使ったら削除
		session.removeAttribute("success");
		session.removeAttribute("error");
	
		
			String name = request.getParameter("name");
			String mail = request.getParameter("mail");
			String pass = request.getParameter("pass");
			String confirm_pass = request.getParameter("confirm_pass");
			String role = request.getParameter("role");

			request.setAttribute("name", name);
			request.setAttribute("mail", mail);
			request.setAttribute("pass", pass);
			request.setAttribute("confirm_pass", confirm_pass);
			request.setAttribute("role", role);

			request.getRequestDispatcher("/S0043.jsp").forward(request, response);
		}

}
