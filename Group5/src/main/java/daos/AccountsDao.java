package daos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Collections;

import data.AccountsData;
import form.Accounts;
import form.AccountsSearchForm;
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
		String authorityStr = accountsData.getAuthorityStr();
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

	public ArrayList<Accounts> selectSearch(AccountsSearchForm asForm) {
		ArrayList<Accounts> accountsList = new ArrayList<>();
		ArrayList<Object> sqlList = new ArrayList<>();
		ArrayList<String> where = new ArrayList<>();
		StringBuilder select = new StringBuilder("SELECT account_id, name, mail, authority FROM accounts");

		String name = asForm.getName();
		String mail = asForm.getMail();
		String[] authority = asForm.getAuthority();
		System.out.println("権限条件選択: " + authority);

		if (name != null && !name.isEmpty()) {
			// nullでないかつ空文字でない
			where.add("name LIKE ?");
			sqlList.add("%" + name + "%");
		}
		if (mail != null && !mail.isEmpty()) {
			where.add("mail = ?");
			sqlList.add(mail);
		}

		if (authority != null) {
			where.add("authority IN (" +
					String.join(",", Collections.nCopies(authority.length, "?")) + ")");
			for (String a : authority) {
				sqlList.add(a);
			}
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

	public AccountsData getAccountsByAccountId(int accountId) {
		String sql = "SELECT account_id, name,  mail, password, authority FROM accounts WHERE account_id = ?";
		AccountsData accountsData = null;
		try (
				Connection conn = Db.open();
				PreparedStatement pstmt = conn.prepareStatement(sql);) {
			pstmt.setInt(1, accountId);
			ResultSet rs = pstmt.executeQuery();

			if (rs.next()) {
			accountsData = new AccountsData(
				rs.getInt("account_id"),
				rs.getString("name"),
				rs.getString("mail"),
				rs.getString("password"),
				rs.getInt("authority")
				);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return accountsData;
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

	public boolean update(Integer accountId, String name, String mail, String hashedPass, Integer authority) {
		String sql = "UPDATE accounts SET name=?, mail=?, password=?, authority=? WHERE account_id=?";

		try (
				Connection conn = Db.open();
				PreparedStatement pstmt = conn.prepareStatement(sql)) {

			pstmt.setString(1, name);
			pstmt.setString(2, mail);
			pstmt.setString(3, hashedPass);
			pstmt.setInt(4, authority);
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
