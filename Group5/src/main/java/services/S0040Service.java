package services;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Collections;

import beans.Accounts;
import utils.Db;

public class S0040Service {
	
	public static ArrayList<Accounts> select(String name, String mail, int role0, int role1, int role10) {
		ArrayList<Accounts> accountList = new ArrayList<>();
		ArrayList<Object> sqlList = new ArrayList<>();
		ArrayList<Integer> roles = new ArrayList<>();
		ArrayList<String> where = new ArrayList<>();
		StringBuilder select = new StringBuilder("SELECT account_id, name, mail, authority FROM accounts");

		if (name != null && !name.isEmpty()) {
			// nullでないかつ空文字でない
			where.add("name LIKE ?");
			sqlList.add("%" + name + "%");
		}
		if (mail != null && !mail.isEmpty()) {
			where.add("mail = ?");
			sqlList.add(mail);
		}
		if (role0 == 0) {
			roles.add(role0);
		}
		if (role1 == 1) {
			roles.add(role1);
		}
		if (role10 == 2) {
			roles.add(role10);
		}
		if (!roles.isEmpty()) {
			where.add("authority IN (" +
					String.join(",", Collections.nCopies(roles.size(), "?")) + ")");
			sqlList.addAll(roles);
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
					accounts.setAccount_id(rs.getInt("account_id"));
					accounts.setName(rs.getString("name"));
					accounts.setMail(rs.getString("mail"));
					accounts.setAuthority(rs.getInt("authority"));
					accountList.add(accounts);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return accountList;
	}

}
