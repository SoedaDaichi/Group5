package daos;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class C002Dao {

	private static final String URL = "jdbc:mariadb://localhost:3306/group5";
	private static final String USER = "root";
	private static final String PASSWORD = "root";

	static {
		try {
			Class.forName("org.mariadb.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("MariaDB JDBC Driver not found", e);
		}
	}

	public int getMonthSales(int year, int month) {
		String sql = "select sum(unit_price * sale_number) as month_sales from sales where year(sale_date) = ? and month(sale_date) = ?";

		try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
				PreparedStatement stmt = conn.prepareStatement(sql)) {
			stmt.setInt(1, year);
			stmt.setInt(2, month);

			try (ResultSet rs = stmt.executeQuery()) {
				if (rs.next()) {
					return rs.getInt("month_sales");
				}
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return 0;
	}

	public int getNennkannSales(int year) {
		String sql = "select sum(unit_price * sale_number) as nennkann_sales from sales where year(sale_date) = ?";

		try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
				PreparedStatement stmt = conn.prepareStatement(sql)) {

			stmt.setInt(1, year);
			try (ResultSet rs = stmt.executeQuery()) {
				if (rs.next()) {
					return rs.getInt("nennkann_sales");
				}
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return 0;
	}
	
	
//	public Map<String ,Integer> getCategorySales() {
////		String sql ="SELECT category_id AS categor, SUM(unit_price * sale_number) AS total FROM sales GROUP BY category_id";
////		
//		String sql ="select c.category_name, sum(s.unit_price * s.sale_number) as total from sales s join categories c on s.category_id = c.category_id group by c.category_name";
//		Map<String ,Integer> map = new WMap<>();
//		
//		
//		try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
//				PreparedStatement stmt = conn.prepareStatement(sql);
//		        ResultSet rs = stmt.executeQuery()){
//
//			while
//			stmt.setInt();
//			try () {
//				if (rs.next()) {
//					return rs.getInt("");
//				}
//			}
//
//		} catch (SQLException e) {
//			e.printStackTrace();
//		}
//		return 0;
//	}
}
	

