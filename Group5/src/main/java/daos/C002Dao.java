package daos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.HashMap;
import java.util.Map;

import utils.Db;

public class C002Dao {

	static {
		try {
			Class.forName("org.mariadb.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("MariaDB JDBC Driver not found", e);
		}
	}

	//月の売上
	public int getMonthSales(int year, int month) {
		String sql = "select sum(unit_price * sale_number) as month_sales from sales where year(sale_date) = ? and month(sale_date) = ?";

		try (
				Connection conn = Db.open();
				PreparedStatement stmt = conn.prepareStatement(sql)) {
			stmt.setInt(1, year);
			stmt.setInt(2, month);

			try (ResultSet rs = stmt.executeQuery()) {
				if (rs.next()) {
					return rs.getInt("month_sales");
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return 0;
	}

	
	//年の売上
	public int getNennkannSales(int year) {
		String sql = "select sum(unit_price * sale_number) as nennkann_sales from sales where year(sale_date) = ?";

		try (
				Connection conn = Db.open();
				PreparedStatement stmt = conn.prepareStatement(sql)) {

			stmt.setInt(1, year);
			try (ResultSet rs = stmt.executeQuery()) {
				if (rs.next()) {
					return rs.getInt("nennkann_sales");
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return 0;
	}
	
	
//月間のカテゴリー別売上(グラフ)
	public Map<String, Integer> getCategorySalesMonth(int year, int month) {
		String sql ="SELECT c.category_name, SUM(s.unit_price * s.sale_number) AS total FROM sales s JOIN categories c ON s.category_id = c.category_id WHERE YEAR(s.sale_date) = ? AND MONTH(s.sale_date) = ? GROUP BY c.category_name";

		Map<String, Integer> map = new HashMap<>();

		try (Connection conn = Db.open();
				PreparedStatement stmt = conn.prepareStatement(sql)) {

			stmt.setInt(1, year);
			stmt.setInt(2, month);

			try (ResultSet rs = stmt.executeQuery()) {
				while (rs.next()) {
					String category = rs.getString("category_name");
					int total = rs.getInt("total");
					map.put(category, total);
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return map;
	}

//年間のカテゴリー別売上(グラフ)
	public Map<String, Integer> getCategorySalesYear(int year) {
		String sql ="SELECT c.category_name, SUM(s.unit_price * s.sale_number) AS total FROM sales s JOIN categories c ON s.category_id = c.category_id WHERE YEAR(s.sale_date) = ? GROUP BY c.category_name";

		Map<String, Integer> map = new HashMap<>();

		try (Connection conn = Db.open();
				PreparedStatement stmt = conn.prepareStatement(sql)) {

			stmt.setInt(1, year);

			try (ResultSet rs = stmt.executeQuery()) {
				while (rs.next()) {
					String category = rs.getString("category_name");
					int total = rs.getInt("total");
					map.put(category, total);
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return map;
	}
	
//全体のカテゴリー別売上(グラフ)
	public Map<String, Integer> getCategorySales() {
		//		String sql ="SELECT category_id AS categor, SUM(unit_price * sale_number) AS total FROM sales GROUP BY category_id";
		//		
		String sql = "select c.category_name, sum(s.unit_price * s.sale_number) as total from sales s join categories c on s.category_id = c.category_id group by c.category_name";
		Map<String, Integer> map = new HashMap<>();

		try (
				Connection conn = Db.open();
				PreparedStatement stmt = conn.prepareStatement(sql);
				ResultSet rs = stmt.executeQuery()) {

			while (rs.next()) {

				String category = rs.getString("category_name");
				int total = rs.getInt("total");
				map.put(category, total);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return map;
	}
}
