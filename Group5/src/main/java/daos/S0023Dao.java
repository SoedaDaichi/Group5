package daos;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;

import utils.Db;

public class S0023Dao {

	public void updateSales(Date sale_date, int account_id, int category_id, String trade_name, int unit_price, int sale_number, String note, int sale_id) {
		String updateSales = "update sales set sale_date = ?, account_id = ?, category_id = ?, trade_name = ?, unit_price = ?, sale_number = ?, note = ? where sale_id = ?";

		try (
				Connection conn = Db.open();
				PreparedStatement pstmt = conn.prepareStatement(updateSales);) {
			pstmt.setDate(1, sale_date);
			pstmt.setInt(2, account_id);
			pstmt.setInt(3, category_id);
			pstmt.setString(4, trade_name);
			pstmt.setInt(5, unit_price);
			pstmt.setInt(6, sale_number);
			pstmt.setString(7, note);
			pstmt.setInt(8, sale_id);
			pstmt.executeUpdate();
		}

		catch (Exception e) {
			e.printStackTrace();
		}

	}
	
}
