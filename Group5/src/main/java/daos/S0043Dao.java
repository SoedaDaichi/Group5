package daos;

import java.sql.Connection;
import java.sql.PreparedStatement;

import utils.Db;

public class S0043Dao {

	public boolean update(int account_id, String name, String mail, String hashedPass, String authority) {
		String sql = "UPDATE accounts SET name=?, mail=?, password=?, authority=? WHERE account_id=?";

		try (
			 Connection conn = Db.open();	
			 PreparedStatement pstmt = conn.prepareStatement(sql)) {

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
	
	
	public boolean delete(int accountId) {
	    try (
	        Connection conn = Db.open()){
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