package services;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import beans.Accounts;
import utils.Db;

public class S0041Service {
	
	
	public List<Accounts> getAccountsByKeyword(String keyword) {
	    List<Accounts> accountList = new ArrayList<>();
	    
	    // SQL例：名前またはメールアドレスにキーワードが含まれるレコードを取得
	    String sql = "SELECT * FROM accounts WHERE name LIKE ? OR mail LIKE ?";
	    
	    try (
	        Connection conn = Db.open();
	        PreparedStatement pstmt = conn.prepareStatement(sql);
	    ) {
	        String likeKeyword = "%" + keyword + "%";
	        pstmt.setString(1, likeKeyword);
	        pstmt.setString(2, likeKeyword);
	        
	        ResultSet rs = pstmt.executeQuery();
	        
	        while (rs.next()) {
	            Accounts account = new Accounts();
	            account.setAccount_id(rs.getInt("account_id"));
	            account.setName(rs.getString("name"));
	            account.setMail(rs.getString("mail"));
	            account.setPass(rs.getString("password"));
	            account.setAuthority(rs.getInt("authority"));
	            
	            accountList.add(account);
	        }
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	    
	    return accountList;
	}

	
	
	

    public List<Accounts> getAccountsByUserId(int userId) {
        List<Accounts> taskList = new ArrayList<>();
        String sql = "SELECT * FROM accounts WHERE user_id = ?";

        try (
            Connection conn = Db.open();
            PreparedStatement pstmt = conn.prepareStatement(sql);
        ) {
            pstmt.setInt(1, userId);
            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
            	Accounts task = new Accounts();
                task.setAccount_id(rs.getInt("account_id"));
                task.setName(rs.getString("name"));
                task.setMail(rs.getString("mail"));
                task.setPass(rs.getString("password"));
                task.setAuthority(rs.getInt("authority"));
                

                taskList.add(task);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return taskList;
    }
}
