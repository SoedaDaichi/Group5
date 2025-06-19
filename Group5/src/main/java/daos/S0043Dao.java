package daos;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

public class S0043Dao {
	private static final String DB_URL = "jdbc:mariadb://localhost:3306/group5";
	private static final String DB_USER = "root";
	private static final String DB_PASS = "root";

	public boolean update(int account_id, String name, String mail, String hashedPass, String authority) {
		String sql = "UPDATE accounts SET name=?, mail=?, password=?, authority=? WHERE account_id=?";

		try (Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASS);
			 PreparedStatement pstmt = conn.prepareStatement(sql)) {

			// ※ account_id の取得はとりあえず
			//int accountId = 1;

			pstmt.setString(1, name);
			pstmt.setString(2, mail);
			pstmt.setString(3, hashedPass);
			pstmt.setInt(4, Integer.parseInt(authority));
			pstmt.setInt(5, account_id);

			int rowsAffected = pstmt.executeUpdate();
			return rowsAffected > 0;

		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
	
//	public boolean delete(int accountId) {
//	    String sql = "DELETE FROM accounts WHERE account_id = ?";
//
//	    try (Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASS);
//	         PreparedStatement stmt = conn.prepareStatement(sql)) {
//
//	        stmt.setInt(1, accountId);
//	        int rowsAffected = stmt.executeUpdate();
//	        return rowsAffected > 0;
//
//	    } catch (Exception e) {
//	        e.printStackTrace();
//	        return false;
//	    }
//	}
	
	public boolean delete(int accountId) {
	    try (Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASS)) {
	        String sql = "DELETE FROM accounts WHERE account_id = ?";
	        PreparedStatement stmt = conn.prepareStatement(sql);
	        stmt.setInt(1, accountId);
	        int rowsAffected = stmt.executeUpdate();
	        return rowsAffected > 0;
	    } catch (Exception e) {
	        e.printStackTrace();
	        return false;
	    }
	}
	
}