package services;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;

import utils.Db;


public class SalesService {
	public SalesService() {
	}
	
	public void insert(Date sale_date, int account_id, int category_id ,String trade_name, 
						int unit_price ,int sale_number, String note) {

		String insert = "INSERT INTO sales (sale_date, account_id, category_id, trade_name, unit_price, sale_number, note)"
				+ " VALUES (?, ?, ?, ?, ?, ?, ?)";

		try (
				Connection conn = Db.open();
				PreparedStatement pstmt = conn.prepareStatement(insert);) {
			pstmt.setDate(1, sale_date);
			pstmt.setInt(2, account_id);
			pstmt.setInt(3, category_id);
			pstmt.setString(4, trade_name);
			pstmt.setInt(5, unit_price);
			pstmt.setInt(6, sale_number);
			if (note != null) {
				pstmt.setString(7, note);
			} else {
				pstmt.setNull(7, java.sql.Types.VARCHAR);
			};
			int result = pstmt.executeUpdate();
			System.out.println(result + "件のデータを追加");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
}
