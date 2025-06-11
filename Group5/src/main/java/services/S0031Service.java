package services;

import java.sql.Connection;
import java.sql.PreparedStatement;

import utils.Db;

public class S0031Service {
	
	public boolean insert(String name, String mail, String hashedPass, String authority) {
		String sql = "INSERT INTO accounts(name, mail, password, authority) VALUES (?, ?, ?, ?)";
        try (Connection con = Db.open()) {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, name);
            ps.setString(2, mail);
            ps.setString(3, hashedPass);
            ps.setInt(4, Integer.parseInt(authority));
            ps.executeUpdate();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
	
	
}