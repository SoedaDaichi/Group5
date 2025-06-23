package daos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import beans.SalesData;
import utils.Db;

public class S0021Dao {

	public SalesData identificationSalesData(int sale_id) {
		SalesData salesdata = null;
		String identificationSalesData = "SELECT sale_date, account_id, category_id, trade_name, unit_price, sale_number, note FROM sales WHERE sale_id = ?";
		S0010Dao s0010dao = new S0010Dao();
		try (
				Connection conn = Db.open();
				PreparedStatement pstmt = conn.prepareStatement(identificationSalesData);) {
			pstmt.setInt(1, sale_id);

			ResultSet rs = pstmt.executeQuery();
			if (rs.next()) {
				int account_id = rs.getInt("account_id");
				int category_id = rs.getInt("sale_id");
				new SalesData(
				rs.getDate("sale_date").toLocalDate(),
				s0010dao.identificationAccount(account_id).getName(),
				account_id,
				s0010dao.identificationCategory(category_id).getCategory_name(),
				category_id,
				rs.getString("trade_name"),
				rs.getInt("unit_price"),
				rs.getInt("sale_number"),
				rs.getString("note")
				);
				
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return salesdata;
	}
}
