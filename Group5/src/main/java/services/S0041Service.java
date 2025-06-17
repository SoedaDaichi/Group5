package services;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import beans.Accounts;
import utils.Db;

public class S0041Service {

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
