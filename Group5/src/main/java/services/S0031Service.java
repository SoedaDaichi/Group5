package services;

import java.sql.Connection;
import java.sql.PreparedStatement;

import utils.Db;

public class S0031Service {
	
	public static boolean insert(String name, String email, String hashedPass, String role) {
        try (Connection con = Db.open()) {
            String sql = "INSERT INTO accounts(name, email, password, role) VALUES (?, ?, ?, ?)";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, name);
            ps.setString(2, email);
            ps.setString(3, hashedPass);
            ps.setInt(4, Integer.parseInt(role));
            ps.executeUpdate();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
	
	
}