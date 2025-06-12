package daos;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import beans.Sales;
import utils.Db;

public class S0020Dao {

	public ArrayList<Sales> select(String firststr, String laststr, String account_idstr, String category_idstr, String trade,
			String note) {
		ArrayList<Sales> salesList = new ArrayList<>();
		ArrayList<Object> sqlList = new ArrayList<>();
		ArrayList<String> where = new ArrayList<>();
		StringBuilder select = new StringBuilder(
				"SELECT s.sale_id, s.sale_date, a.name, c.category_name, s.trade_name, s.unit_price, s.sale_number, "
						+ "s.unit_price * s.sale_number AS price_all "
						+ "FROM sales s INNER JOIN accounts a ON s.account_id = a.account_id "
						+ "INNER JOIN categories c ON s.category_id = c.category_id");
		System.out.println("備考: " + note);
		System.out.println("商品名: " + trade);

		if (trade != null && !trade.isEmpty()) {
			// nullでないかつ空文字でない
			where.add("s.trade_name LIKE ?");
			sqlList.add("%" + trade + "%");
		}
		if (note != null && !note.isEmpty()) {
			where.add("s.note LIKE ?");
			sqlList.add("%" + note + "%");
		}
		if (firststr != null && laststr != null && !firststr.isEmpty() && !laststr.isEmpty()) {
			Date first = Date.valueOf(firststr);
			Date last = Date.valueOf(laststr);
			where.add("s.sale_date BETWEEN ? AND ?");
			sqlList.add(first);
			sqlList.add(last);
		}
		if (account_idstr != null && !account_idstr.isEmpty()) {
			int account_id = Integer.parseInt(account_idstr);
			System.out.println(account_id);
			where.add("s.account_id = ?");
			sqlList.add(account_id);
		}
		if (category_idstr != null && !category_idstr.isEmpty()) {
			int category_id = Integer.parseInt(category_idstr);
			where.add("s.category_id = ?");
			sqlList.add(category_id);
		}

		if (!where.isEmpty()) {
			select.append(" WHERE ");
			select.append(String.join(" AND ", where));
		}
		try (
				Connection conn = Db.open();
				PreparedStatement pstmt = conn.prepareStatement(select.toString());) {

			for (int i = 0; i < sqlList.size(); i++) {
				pstmt.setObject(i + 1, sqlList.get(i));
			}
			System.out.println("SQL文: " + select.toString());
			System.out.println("挿入した要素数: " + sqlList.size());

			try (ResultSet rs = pstmt.executeQuery();) {
				while (rs.next()) {
					Sales sales = new Sales();
					sales.setSale_id(rs.getInt("sale_id"));
					sales.setSale_date(rs.getDate("sale_date"));
					sales.setName(rs.getString("name"));
					sales.setCategory_name(rs.getString("category_name"));
					sales.setTrade_name(rs.getString("trade_name"));
					sales.setUnit_price(rs.getInt("unit_price"));
					sales.setSale_number(rs.getInt("sale_number"));
					sales.setPrice_all(rs.getInt("price_all"));
					salesList.add(sales);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return salesList;
	}

}
