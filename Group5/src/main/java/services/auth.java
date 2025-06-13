package services;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Base64;

import beans.Accounts;
import utils.Db;

public class auth {

	public static Accounts findByEmail(String mail) {
		Accounts accounts = null;
		String select = "SELECT mail, password, account_id, name, authority FROM accounts WHERE mail = ?";
		try (Connection conn = Db.open();
				PreparedStatement ps = conn.prepareStatement(select)) {
			ps.setString(1, mail);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				accounts = new Accounts();
				accounts.setMail(rs.getString("mail"));
				accounts.setPass(rs.getString("password"));
				accounts.setAccount_id(rs.getInt("account_id"));
				accounts.setName(rs.getString("name"));
				accounts.setAuthority(rs.getInt("authority"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return accounts;
	}
	
	public static String checkManager(Accounts accounts) {
		if (accounts == null) {
			return "メールアドレス、またはパスワードが違います。";
		}
		return null;
	}

	public auth() {
	}

	public static Accounts login(String mail, String pass) {
		Accounts accounts = findByEmail(mail);
			return accounts;
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
	
	public static String hashPassword(String password) {
		try {
			MessageDigest md = MessageDigest.getInstance("SHA-256");
			md.update(password.getBytes());
			byte[] hashBytes = md.digest();
			return Base64.getEncoder().encodeToString(hashBytes);
		} catch (NoSuchAlgorithmException e) {
			throw new RuntimeException(e);
		}
	}
}
