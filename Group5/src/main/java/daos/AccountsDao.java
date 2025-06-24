package daos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import beans.Accounts;
import beans.AccountsData;
import beans.AccountsSearchForm;
import services.Auth;
import utils.Db;

public class AccountsDao {
	public boolean accountNameCheck(String name) {
		String select = "SELECT COUNT(*) FROM accounts WHERE name = ?";

		try (
				Connection conn = Db.open();
				PreparedStatement pstmt = conn.prepareStatement(select);) {
			pstmt.setString(1, name);
			ResultSet rs = pstmt.executeQuery();
			if (rs.next()) {
				return rs.getInt(1) > 0;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	public boolean accountEmailCheck(String mail) {
		String select = "SELECT COUNT(*) FROM accounts WHERE mail = ?";

		try (
				Connection conn = Db.open();
				PreparedStatement pstmt = conn.prepareStatement(select);) {
			pstmt.setString(1, mail);
			ResultSet rs = pstmt.executeQuery();
			if (rs.next()) {
				return rs.getInt(1) > 0;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	public boolean insert(AccountsData accountsData) {
		String name = accountsData.getName();
		String mail = accountsData.getMail();
		String pass = accountsData.getPass();
		String authorityStr = accountsData.getAuthority();
		String hashedPass = Auth.hashPassword(pass);

		System.out.println("insert called with: " + name + ", " + mail);

		String sql = "INSERT INTO accounts(name, mail, password, authority) VALUES (?, ?, ?, ?)";
		try (Connection con = Db.open()) {
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, name);
			ps.setString(2, mail);
			ps.setString(3, hashedPass);
			ps.setInt(4, Integer.parseInt(authorityStr));
			ps.executeUpdate();
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	public ArrayList<Accounts> selectSearch(AccountsSearchForm asform) {
		ArrayList<Accounts> accountsList = new ArrayList<>();
		ArrayList<Object> sqlList = new ArrayList<>();
		ArrayList<String> where = new ArrayList<>();
		List<Integer> authorityList = new ArrayList<>();
		StringBuilder select = new StringBuilder("SELECT account_id, name, mail, authority FROM accounts");

		String name = asform.getName();
		String mail = asform.getMail();

		if (name != null && !name.isEmpty()) {
			// nullでないかつ空文字でない
			where.add("name LIKE ?");
			sqlList.add("%" + name + "%");
		}
		if (mail != null && !mail.isEmpty()) {
			where.add("mail = ?");
			sqlList.add(mail);
		}

		if (!authorityList.isEmpty()) {
			where.add("authority IN (" +
					String.join(",", Collections.nCopies(authorityList.size(), "?")) + ")");
			sqlList.addAll(authorityList);
		}

		if (!where.isEmpty()) {
			select.append(" WHERE ");
			select.append(String.join(" AND ", where));
		}
		try (
				Connection conn = Db.open();
				PreparedStatement pstmt = conn.prepareStatement(select.toString());) {

			for (int i = 0; i < sqlList.size(); i++) {
				pstmt.setObject(i + 1, sqlList.get(i));
			}
			System.out.println("SQL: " + select.toString());
			System.out.println("Params: " + sqlList.size());

			try (ResultSet rs = pstmt.executeQuery();) {
				while (rs.next()) {
					Accounts accounts = new Accounts();
					accounts.setAccountId(rs.getInt("account_id"));
					accounts.setName(rs.getString("name"));
					accounts.setMail(rs.getString("mail"));
					accounts.setAuthority(rs.getInt("authority"));
					accountsList.add(accounts);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return accountsList;
	}

	public Accounts getAccountsByAccountId(int accountId) {
		String sql = "SELECT account_id, name,  mail, password, authority FROM accounts WHERE account_id = ?";
		Accounts accounts = null;
		try (
				Connection conn = Db.open();
				PreparedStatement pstmt = conn.prepareStatement(sql);) {
			pstmt.setInt(1, accountId);
			ResultSet rs = pstmt.executeQuery();

			accounts = new Accounts();
			if (rs.next()) {
				accounts.setAccountId(rs.getInt("account_id"));
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

	public boolean accountUpdateNameCheck(String name, int accountId) {
		String select = "SELECT COUNT(*) FROM accounts WHERE name = ? AND account_id <> ?";

		try (
				Connection conn = Db.open();
				PreparedStatement pstmt = conn.prepareStatement(select);) {
			pstmt.setString(1, name);
			pstmt.setInt(2, accountId);
			ResultSet rs = pstmt.executeQuery();
			if (rs.next()) {
				return rs.getInt(1) > 0;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	public boolean accountUpdateEmailCheck(String mail, int accountId) {
		String select = "SELECT COUNT(*) FROM accounts WHERE mail = ? AND account_id <> ?";

		try (
				Connection conn = Db.open();
				PreparedStatement pstmt = conn.prepareStatement(select);) {
			pstmt.setString(1, mail);
			pstmt.setInt(2, accountId);
			ResultSet rs = pstmt.executeQuery();
			if (rs.next()) {
				return rs.getInt(1) > 0;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	public boolean update(int accountId, String name, String mail, String hashedPass, String authority) {
		String sql = "UPDATE accounts SET name=?, mail=?, password=?, authority=? WHERE account_id=?";

		try (
				Connection conn = Db.open();
				PreparedStatement pstmt = conn.prepareStatement(sql)) {

			pstmt.setString(1, name);
			pstmt.setString(2, mail);
			pstmt.setString(3, hashedPass);
			pstmt.setInt(4, Integer.parseInt(authority));
			pstmt.setInt(5, accountId);

			int rowsAffected = pstmt.executeUpdate();
			return rowsAffected > 0;

		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	public boolean deleteAccount(int accountId) {
		try (
				Connection conn = Db.open()) {
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
