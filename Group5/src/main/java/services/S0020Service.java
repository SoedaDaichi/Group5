package services;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import beans.Sales;
import utils.Db;

public class S0020Service {

	public static ArrayList<Sales> select(Date first, Date last, Integer account_id, Integer category_id, String trade,
			String note) {
		ArrayList<Sales> salesList = new ArrayList<>();
		ArrayList<Object> sqlList = new ArrayList<>();
		ArrayList<String> where = new ArrayList<>();
		StringBuilder select = new StringBuilder(
				"SELECT s.sale_id, s.sale_date, a.name, c.category_name, s.trade_name, s.unit_price, s.sale_number, "
				+ "s.unit_price * s.sale_number AS price_All "
				+ "FROM sales s INNER JOIN accounts a ON s.account_id = a.account_id "
				+ "INNER JOIN categories c ON s.category_id = c.category_id;");

		if (trade != null && !trade.isEmpty()) {
			// nullでないかつ空文字でない
			where.add("s.trade_name LIKE ?");
			sqlList.add("%" + trade + "%");
		}
		if (note != null && !note.isEmpty()) {
			where.add("s.note = ?");
			sqlList.add("%" +note+ "%");
		}
		if (first != null && last != null) {
			where.add("s.sale_date BETWEEN ? AND ?");
			sqlList.add(first);
			sqlList.add(last);
		}
		if (account_id != null) {
			where.add("s.account_id = ?");
			sqlList.add(account_id);
		}
		if (category_id != null) {
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
			System.out.println("SQL: " + select.toString());
			System.out.println("Params: " + sqlList.size());

			try (ResultSet rs = pstmt.executeQuery();) {
				while (rs.next()) {
					Sales sales = new Sales();
					sales.setSale_id(rs.getInt("sale_id"));
					sales.setSale_date(rs.getDate("sale_date"));
					sales.setAccount_id(rs.getInt("account_id"));
					sales.setName(rs.getString("name"));
					sales.setMail(rs.getString("mail"));
					sales.setAuthority(rs.getInt("authority"));
					salesList.add(sales);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return accountList;
	}

}
