package daos;

import java.sql.Connection;
import java.sql.PreparedStatement;

import utils.Db;

public class S0044Dao {
	public boolean deleteAccount(int account_id) {
		String daleteAccount = "DELETE FROM accounts where account_id = ?";

		try (
				Connection conn = Db.open();
				PreparedStatement pstmt = conn.prepareStatement(daleteAccount);) {
			pstmt.setInt(1, account_id);
			 int result = pstmt.executeUpdate();
		        return result > 0;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
}
