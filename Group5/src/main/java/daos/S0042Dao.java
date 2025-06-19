package daos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import utils.Db;

public class S0042Dao {
	public boolean accountUpdateNameCheck(String name, int account_id) {
		String select = "SELECT COUNT(*) FROM accounts WHERE name = ? AND account_id <> ?" ;

		try (
				Connection conn = Db.open();
				PreparedStatement pstmt = conn.prepareStatement(select);) {
			pstmt.setString(1, name);
			pstmt.setInt(2, account_id);
			ResultSet rs = pstmt.executeQuery();
			if (rs.next()) {
				return rs.getInt(1) > 0;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	public boolean accountUpdateEmailCheck(String mail, int account_id) {
		String select = "SELECT COUNT(*) FROM accounts WHERE mail = ? AND account_id <> ?";

		try (
				Connection conn = Db.open();
				PreparedStatement pstmt = conn.prepareStatement(select);) {
			pstmt.setString(1, mail);
			pstmt.setInt(2, account_id);
			ResultSet rs = pstmt.executeQuery();
			if (rs.next()) {
				return rs.getInt(1) > 0;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

}
