package services;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Base64;

import beans.Accounts;
import beans.LoginAccount;
import utils.Db;

public class Auth {

	public static LoginAccount findByEmail(String mail) {
		LoginAccount loginAccount = null;
		String select = "SELECT mail, password, account_id, name, authority FROM accounts WHERE mail = ?";
		try (Connection conn = Db.open();
				PreparedStatement ps = conn.prepareStatement(select)) {
			ps.setString(1, mail);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				loginAccount = new LoginAccount();
				loginAccount.setMail(rs.getString("mail"));
				loginAccount.setPass(rs.getString("password"));
				loginAccount.setAccountId(rs.getInt("account_id"));
				loginAccount.setName(rs.getString("name"));
				loginAccount.setAuthority(rs.getInt("authority"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return loginAccount;
	}

	public static String checkManager(Accounts accounts) {
		if (accounts == null) {
			return "メールアドレス、またはパスワードが違います。";
		}
		return null;
	}

	public Auth() {
	}

	public static LoginAccount login(String mail, String pass) {
		LoginAccount loginAccount = findByEmail(mail);
		return loginAccount;
	}

	public static boolean passCheck(int accountId, String cPass) {
		String select = "SELECT password FROM accounts WHERE account_id = ?";
		String pass = null;

		try (Connection conn = Db.open();
				PreparedStatement ps = conn.prepareStatement(select)) {
			ps.setInt(1, accountId);
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
