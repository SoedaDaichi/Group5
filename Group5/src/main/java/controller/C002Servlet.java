package controller;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class C002Servlet
 */
@WebServlet("/C002.html")
public class C002Servlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private static final String URL = "jdbc:mariadb://localhost:3306/group5";
	private static final String USER = "root";
	private static final String PASSWORD = "root";

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public C002Servlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		int totalSales = 0;
		int nennkannSales = 0;
		int kotoshi = java.time.Year.now().getValue();

		String totalSql = "SELECT SUM(unit_price * sale_number) AS total_sales FROM sales";
		String nennkannSql = "SELECT SUM(unit_price * sale_number) AS nennkann_sales FROM sales WHERE YEAR(sale_date) = ?";

		try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
				PreparedStatement totalStmt = conn.prepareStatement(totalSql);
				PreparedStatement nennkannStmt = conn.prepareStatement(nennkannSql)) {

			//総売り上げ
			try (ResultSet rs = totalStmt.executeQuery()) {
				if (rs.next()) {
					totalSales = rs.getInt("total_sales");
				}
			}

			//年間売り上げ
			nennkannStmt.setInt(1, kotoshi);
			try (ResultSet rs = nennkannStmt.executeQuery()) {
				if (rs.next()) {
					nennkannSales = rs.getInt("nennkann_sales");
				}
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		request.setAttribute("totalSales", totalSales);
		request.setAttribute("annualSales", nennkannSales);
		request.getRequestDispatcher("/C002.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
