package daos;

import java.sql.Connection;
import java.sql.PreparedStatement;

import utils.Db;

public class S0025Dao {
	public void daleteSales(int sale_id) {
		String daleteSales = "DELETE FROM sales where sale_id = ?";

		try (
				Connection conn = Db.open();
				PreparedStatement pstmt = conn.prepareStatement(daleteSales);) {
			pstmt.setInt(1, sale_id);
			pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
