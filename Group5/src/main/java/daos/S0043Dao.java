package daos;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

public class S0043Dao {
	private static final String DB_URL = "jdbc:mysql://localhost:3306/group5";
	private static final String DB_USER = "root";
	private static final String DB_PASS = "root";

	public boolean insert(String name, String mail, String hashedPass, String role) {
		String sql = "UPDATE accounts SET name=?, mail=?, pass=?, authority=? WHERE account_id=?";

		try (Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASS);
			 PreparedStatement stmt = conn.prepareStatement(sql)) {

			// ※ account_id の取得はとりあえず
			int accountId = 1;

			stmt.setString(1, name);
			stmt.setString(2, mail);
			stmt.setString(3, hashedPass);
			stmt.setInt(4, Integer.parseInt(role));
			stmt.setInt(5, accountId);

			int rowsAffected = stmt.executeUpdate();
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
