package daos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import beans.Sales;
import utils.Db;

public class S0021Dao {

	public Sales identificationSales(int sales_id) {
		Sales sales = null;
		String identificationSales = "SELECT sale_date, account_id, category_id, trade_name, unit_price, sale_number, note WHERE sale_id = ?";

		try (
				Connection conn = Db.open();
				PreparedStatement pstmt = conn.prepareStatement(identificationSales);) {
			pstmt.setInt(1, sales_id);

			ResultSet rs = pstmt.executeQuery();
			if (rs.next()) {
				sales = new Sales();
				sales.setSale_date(rs.getDate("sale_date"));
				sales.setAccount_id(rs.getInt("account_id"));
				sales.setCategory_id(rs.getInt("category_id"));
				sales.setTrade_name(rs.getString("trade_name"));
				sales.setUnit_price(rs.getInt("unit_price"));
				sales.setSale_number(rs.getInt("sale_number"));
				sales.setNote(rs.getString("note"));
				
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return sales;
	}
}
