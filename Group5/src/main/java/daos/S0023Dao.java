package daos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.time.LocalDate;

import beans.SalesData;
import utils.Db;

public class S0023Dao {

	public boolean updateSales(int sale_id, SalesData salesdata) {
		String updateSales = "update sales set sale_date = ?, account_id = ?, category_id = ?, trade_name = ?, unit_price = ?, sale_number = ?, note = ? where sale_id = ?";
		
		LocalDate sale_date = salesdata.getSale_date();
		int account_id = salesdata.getAccount_id();
		int category_id = salesdata.getCategory_id();
		String trade_name = salesdata.getTrade_name();
		int unit_price  = salesdata.getUnit_price();
		int sale_number = salesdata.getSale_number();
		String note = salesdata.getNote();
		
		try (
				Connection conn = Db.open();
				PreparedStatement pstmt = conn.prepareStatement(updateSales);) {
			pstmt.setObject(1, sale_date);
			pstmt.setInt(2, account_id);
			pstmt.setInt(3, category_id);
			pstmt.setString(4, trade_name);
			pstmt.setInt(5, unit_price);
			pstmt.setInt(6, sale_number);
			pstmt.setString(7, note);
			pstmt.setInt(8, sale_id);
			
			int rowsAffected = pstmt.executeUpdate();
			return rowsAffected > 0;
		}

		catch (Exception e) {
			e.printStackTrace();
			return false;
		}

	}
	
}
