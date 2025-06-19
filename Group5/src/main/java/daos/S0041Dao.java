package daos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import beans.Accounts;
import utils.Db;

public class S0041Dao {
	
	
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

	
	
	

    public Accounts getAccountsByAccount_id(int account_id) {
        String sql = "SELECT account_id, name,  mail, password, authority FROM accounts WHERE account_id = ?";
        Accounts accounts = null;
        try (
            Connection conn = Db.open();
            PreparedStatement pstmt = conn.prepareStatement(sql);
        ) {
            pstmt.setInt(1, account_id);
            ResultSet rs = pstmt.executeQuery();

            accounts = new Accounts();
            if (rs.next()) {
            	accounts.setAccount_id(rs.getInt("account_id"));
                accounts.setName(rs.getString("name"));
                accounts.setMail(rs.getString("mail"));
                accounts.setPass(rs.getString("password"));
                accounts.setAuthority(rs.getInt("authority"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return accounts;
    }
}
