package services;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import beans.Users;
import utils.Db;

public class auth {

	public static Users findByEmail(String mail) {
		Users user = null;
		String select = "SELECT * FROM accounts WHERE mail = ? AND authority = 11";
		try (Connection conn = Db.open();
				PreparedStatement ps = conn.prepareStatement(select)) {
			ps.setString(1, mail);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				user = new Users();
				user.setMail(rs.getString("mail"));
				user.setPass(rs.getString("password"));
				user.setAccount_id(rs.getInt("account_id"));
				user.setName(rs.getString("name"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return user;
	}

	public auth() {
	}

	public static Users login(String mail, String pass) {
		Users users = findByEmail(mail);
		if (users != null && pass.equals(users.getPass())) {
			return users;
		}
		return null;
	}

	public static boolean passCheck(int account_id, String cPass) {
		String select = "SELECT password FROM accounts WHERE account_id = ?";
		String pass = null;

		try (Connection conn = Db.open();
				PreparedStatement ps = conn.prepareStatement(select)) {
			ps.setInt(1, account_id);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
		        pass = rs.getString("password");
			}
			if (!pass.equals(cPass)) {
				return false;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return true;
	}
}
