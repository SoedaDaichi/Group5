package daos;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import beans.SalesData;
import beans.SalesSearchForm;
import utils.Db;

public class S0020Dao {

	public ArrayList<SalesData> select(SalesSearchForm ssform) {
		ArrayList<SalesData> salesList = new ArrayList<>();
		ArrayList<Object> sqlList = new ArrayList<>();
		ArrayList<String> where = new ArrayList<>();
		StringBuilder select = new StringBuilder(
				"SELECT s.sale_id, s.sale_date, a.name, c.category_name, s.trade_name, s.unit_price, s.sale_number, "
						+ "s.unit_price * s.sale_number AS price_all "
						+ "FROM sales s INNER JOIN accounts a ON s.account_id = a.account_id "
						+ "INNER JOIN categories c ON s.category_id = c.category_id");

		String firstStr = ssform.getFirstStr();
		String lastStr = ssform.getLastStr();
		String account_idStr = ssform.getAccount_idStr();
		String category_idStr = ssform.getCategory_idStr();
		String trade_name = ssform.getTrade_name();
		String note = ssform.getNote();

		System.out.println("備考: " + note);
		System.out.println("商品名: " + trade_name);

		if (trade_name != null && !trade_name.isEmpty()) {
			// nullでないかつ空文字でない
			where.add("s.trade_name LIKE ?");
			sqlList.add("%" + trade_name + "%");
		}
		if (note != null && !note.isEmpty()) {
			where.add("s.note LIKE ?");
			sqlList.add("%" + note + "%");
		}
		if (firstStr != null && lastStr != null && !firstStr.isEmpty() && !lastStr.isEmpty()) {
			Date first = Date.valueOf(firstStr);
			Date last = Date.valueOf(lastStr);
			where.add("s.sale_date BETWEEN ? AND ?");
			sqlList.add(first);
			sqlList.add(last);
		} else if (firstStr != null && !firstStr.isEmpty()) { // 追加
			Date first = Date.valueOf(firstStr);
			where.add("s.sale_date >= ?");
			sqlList.add(first);
		} else if (lastStr != null && !lastStr.isEmpty()) { // 追加
			Date last = Date.valueOf(lastStr);
			where.add("s.sale_date <= ?");
			sqlList.add(last);
		}

		if (account_idStr != null && !account_idStr.isEmpty()) {
			int account_id = Integer.parseInt(account_idStr);
			System.out.println(account_id);
			where.add("s.account_id = ?");
			sqlList.add(account_id);
		}
		if (category_idStr != null && !category_idStr.isEmpty()) {
			int category_id = Integer.parseInt(category_idStr);
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
					SalesData salesdata = new SalesData(
							rs.getInt("sale_id"),
							rs.getDate("sale_date").toLocalDate(),
							rs.getString("name"),
							rs.getInt("account_id"), rs.getString("category_name"),
							rs.getInt("category_id"), rs.getString("trade_name"),
							rs.getInt("unit_price"),
							rs.getInt("sale_number"),
							rs.getString("note"), rs.getInt("price_all"));
					salesList.add(salesdata);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return salesList;
	}

}
