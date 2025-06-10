package services;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Base64;

import beans.Users;
import utils.Db;

public class UserService {

	public static void delete(int id) {
		String delete = "DELETE FROM users WHERE id = ?";

		try (
				Connection conn = Db.open();
				PreparedStatement pstmt = conn.prepareStatement(delete);) {

			pstmt.setInt(1, id);
			int result = pstmt.executeUpdate();
			System.out.println(result + "件のアカウントを削除");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void insert(String username, String email, String password) {
		String insert = "INSERT INTO users(username, email, password) "
				+ "VALUES(?, ?, ?)";

		try (
				Connection conn = Db.open();
				PreparedStatement pstmt = conn.prepareStatement(insert);) {
			pstmt.setString(1, username);
			pstmt.setString(2, email);
			pstmt.setString(3, password);
			int result = pstmt.executeUpdate();
			System.out.println(result + "件のアカウントを追加");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static boolean UserNameCheck(String username) {
		String select = "SELECT COUNT(*) FROM users WHERE username = ?";

		try (
				Connection conn = Db.open();
				PreparedStatement pstmt = conn.prepareStatement(select);) {
			pstmt.setString(1, username);
			ResultSet rs = pstmt.executeQuery();
			if (rs.next()) {
				return rs.getInt(1) > 0;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	public static boolean OtherUserNameCheck(int id, String username) {
		String select = "SELECT COUNT(*) FROM users WHERE username = ? AND id != ?";

		try (
				Connection conn = Db.open();
				PreparedStatement pstmt = conn.prepareStatement(select);) {
			pstmt.setString(1, username);
			pstmt.setInt(2, id);
			ResultSet rs = pstmt.executeQuery();
			if (rs.next()) {
				return rs.getInt(1) > 0;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	public static boolean UserEmailCheck(String email) {
		String select = "SELECT COUNT(*) FROM users WHERE email = ?";

		try (
				Connection conn = Db.open();
				PreparedStatement pstmt = conn.prepareStatement(select);) {
			pstmt.setString(1, email);
			ResultSet rs = pstmt.executeQuery();
			if (rs.next()) {
				return rs.getInt(1) > 0;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	public static boolean OtherUserEmailCheck(int id, String email) {
		String select = "SELECT COUNT(*) FROM users WHERE email = ? AND id  != ?";

		try (
				Connection conn = Db.open();
				PreparedStatement pstmt = conn.prepareStatement(select);) {
			pstmt.setString(1, email);
			pstmt.setInt(2, id);
			ResultSet rs = pstmt.executeQuery();
			if (rs.next()) {
				return rs.getInt(1) > 0;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
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

	public static Users selectDetail(int id) {
		// TODO 自動生成されたメソッド・スタブ
		Users user = null;
		String select = "SELECT * FROM users WHERE id = ?";

		try (
				Connection conn = Db.open();
				PreparedStatement pstmt = conn.prepareStatement(select);) {
			pstmt.setInt(1, id);

			try (ResultSet rs = pstmt.executeQuery();) {
				if (rs.next()) {
					user = new Users();
					user.setId(rs.getInt("id"));
					user.setUsername(rs.getString("username"));
					user.setEmail(rs.getString("email"));
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return user;
	}

	public static void updateCommon(int id, String username, String email) {
		String update = "UPDATE users set username = ?, email = ? WHERE id = ?";

		try (
				Connection conn = Db.open();
				PreparedStatement pstmt = conn.prepareStatement(update);) {

			pstmt.setString(1, username);
			pstmt.setString(2, email);
			pstmt.setInt(3, id);

			int result = pstmt.executeUpdate();
			System.out.println(result + "件のデータを更新");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void changePassword(int id, String hashedNewPassword) {
		String update = "UPDATE users set password = ? WHERE id = ?";

		try (
				Connection conn = Db.open();
				PreparedStatement pstmt = conn.prepareStatement(update);) {

			pstmt.setString(1, hashedNewPassword);
			pstmt.setInt(2, id);

			int result = pstmt.executeUpdate();
			System.out.println(result + "件のPWを更新");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static ArrayList<Users> select() {
		ArrayList<Users> account
		List = new ArrayList<>();
		String select = "SELECT * FROM tasks WHERE user_id = ?";

		try (
				Connection conn = Db.open();
				PreparedStatement pstmt = conn.prepareStatement(select);) {
			pstmt.setInt(1, user_id);

			try (ResultSet rs = pstmt.executeQuery();) {
				while (rs.next()) {
					Tasks task = new Tasks();
					task.setId(rs.getInt("id"));
					task.setName(rs.getString("name"));
					task.setDeadline(rs.getDate("deadline"));
					task.setStatus(rs.getString("status"));
					task.setAssignee(rs.getString("assignee"));
					taskList.add(task);
				}

			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return taskList;
	}

}
