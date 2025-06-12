package daos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import utils.Db;

public class S0030Dao {
	
	public boolean accountNameCheck(String name) {
		String select = "SELECT COUNT(*) FROM accounts WHERE name = ?";

		try (
				Connection conn = Db.open();
				PreparedStatement pstmt = conn.prepareStatement(select);) {
			pstmt.setString(1, name);
			ResultSet rs = pstmt.executeQuery();
			if (rs.next()) {
				return rs.getInt(1) > 0;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}
	
	public boolean accountEmailCheck(String mail) {
		String select = "SELECT COUNT(*) FROM accounts WHERE mail = ?";

		try (
				Connection conn = Db.open();
				PreparedStatement pstmt = conn.prepareStatement(select);) {
			pstmt.setString(1, mail);
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
