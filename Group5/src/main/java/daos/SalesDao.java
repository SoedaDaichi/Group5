package daos;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.LocalDate;
import java.util.ArrayList;

import beans.Accounts;
import beans.Categories;
import beans.SalesData;
import beans.SalesSearchForm;
import utils.Db;

public class SalesDao {
	public ArrayList<Accounts> selectAccount() {
		ArrayList<Accounts> accountList = new ArrayList<>();
		String selectAccount = "SELECT account_id, name FROM accounts WHERE authority = 1 OR authority = 3";

		try (
				Connection conn = Db.open();
				PreparedStatement pstmt = conn.prepareStatement(selectAccount);) {
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				Accounts accounts = new Accounts();
				accounts.setAccountId(rs.getInt("account_id"));
				accounts.setName(rs.getString("name"));
				accountList.add(accounts);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println("Service : " + accountList.size());
		return accountList;
	}

	public ArrayList<Categories> selectCategory() {
		ArrayList<Categories> categoryList = new ArrayList<>();
		String selectAccount = "SELECT category_id, category_name FROM categories";

		try (
				Connection conn = Db.open();
				PreparedStatement pstmt = conn.prepareStatement(selectAccount);) {
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				Categories categories = new Categories();
				categories.setCategoryId(rs.getInt("category_id"));
				categories.setCategoryName(rs.getString("category_name"));
				categoryList.add(categories);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return categoryList;
	}

	public Accounts identificationAccount(int account_id) {
		Accounts account = null;
		String identificationaccount = "SELECT name, account_id FROM accounts WHERE account_id = ?";

		try (
				Connection conn = Db.open();
				PreparedStatement pstmt = conn.prepareStatement(identificationaccount);) {
			pstmt.setInt(1, account_id);

			ResultSet rs = pstmt.executeQuery();
			if (rs.next()) {
				account = new Accounts();
				account.setAccountId(rs.getInt("account_id"));
				account.setName(rs.getString("name"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return account;
	}

	public Categories identificationCategory(int category_id) {
		Categories category = null;
		String identificationcategory = "SELECT category_name, category_id FROM categories WHERE category_id = ?";

		try (
				Connection conn = Db.open();
				PreparedStatement pstmt = conn.prepareStatement(identificationcategory);) {
			pstmt.setInt(1, category_id);

			ResultSet rs = pstmt.executeQuery();
			if (rs.next()) {
				category = new Categories();
				category.setCategoryName(rs.getString("category_name"));
				category.setCategoryId(rs.getInt("category_id"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return category;
	}

	public boolean insert(SalesData RegisterSalesdata) {
		LocalDate sale_date = RegisterSalesdata.getSaleDate();
		int account_id = RegisterSalesdata.getAccountId();
		int category_id = RegisterSalesdata.getCategoryId();
		String trade_name = RegisterSalesdata.getTradeName();
		int unit_price = RegisterSalesdata.getUnitPrice();
		int sale_number = RegisterSalesdata.getSaleNumber();
		String note = RegisterSalesdata.getNote();

		String insert = """
				INSERT INTO sales (
				    sale_date,
				    account_id,
				    category_id,
				    trade_name,
				    unit_price,
				    sale_number,
				    note
				) VALUES (?, ?, ?, ?, ?, ?, ?)
				""";

		try (
				Connection conn = Db.open();
				PreparedStatement pstmt = conn.prepareStatement(insert);) {
			pstmt.setObject(1, sale_date);
			pstmt.setInt(2, account_id);
			pstmt.setInt(3, category_id);
			pstmt.setString(4, trade_name);
			pstmt.setInt(5, unit_price);
			pstmt.setInt(6, sale_number);
			if (note != null) {
				pstmt.setString(7, note);
			} else {
				pstmt.setNull(7, java.sql.Types.VARCHAR);
			}
			;
			int result = pstmt.executeUpdate();
			System.out.println(result + "件のデータを追加");
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	public ArrayList<SalesData> selectSearch(SalesSearchForm ssform) {
		ArrayList<SalesData> salesList = new ArrayList<>();
		ArrayList<Object> sqlList = new ArrayList<>();
		ArrayList<String> where = new ArrayList<>();
		StringBuilder select = new StringBuilder(
				"""
						SELECT
						    s.sale_id,
						    s.sale_date,
						    a.name,
						    c.category_name,
						    s.trade_name,
						    s.unit_price,
						    s.sale_number,
						    s.unit_price * s.sale_number AS price_all
						FROM sales s
						    INNER JOIN accounts a ON s.account_id = a.account_id
						    INNER JOIN categories c ON s.category_id = c.category_id
						""");

		String firstStr = ssform.getFirstStr();
		String lastStr = ssform.getLastStr();
		String account_idStr = ssform.getAccountIdStr();
		String category_idStr = ssform.getCategoryIdStr();
		String trade_name = ssform.getTradeName();
		String note = ssform.getNote();

		System.out.println("備考: " + note);
		System.out.println("商品名: " + trade_name);

		if (trade_name != null && !trade_name.isEmpty()) {
			// nullでないかつ空文字でない
			where.add("s.trade_name LIKE ?");
			sqlList.add("%" + trade_name + "%");
		}
		if (note != null && !note.isEmpty()) {
			where.add("s.note LIKE ?");
			sqlList.add("%" + note + "%");
		}
		if (firstStr != null && lastStr != null && !firstStr.isEmpty() && !lastStr.isEmpty()) {
			Date first = Date.valueOf(firstStr);
			Date last = Date.valueOf(lastStr);
			where.add("s.sale_date BETWEEN ? AND ?");
			sqlList.add(first);
			sqlList.add(last);
		} else if (firstStr != null && !firstStr.isEmpty()) { // 追加
			Date first = Date.valueOf(firstStr);
			where.add("s.sale_date >= ?");
			sqlList.add(first);
		} else if (lastStr != null && !lastStr.isEmpty()) { // 追加
			Date last = Date.valueOf(lastStr);
			where.add("s.sale_date <= ?");
			sqlList.add(last);
		}

		if (account_idStr != null && !account_idStr.isEmpty()) {
			int account_id = Integer.parseInt(account_idStr);
			System.out.println(account_id);
			where.add("s.account_id = ?");
			sqlList.add(account_id);
		}
		if (category_idStr != null && !category_idStr.isEmpty()) {
			int category_id = Integer.parseInt(category_idStr);
			where.add("s.category_id = ?");
			sqlList.add(category_id);
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
			System.out.println("SQL文: " + select.toString());
			System.out.println("挿入した要素数: " + sqlList.size());

			try (ResultSet rs = pstmt.executeQuery();) {
				while (rs.next()) {
					SalesData salesdata = new SalesData(
							rs.getInt("sale_id"),
							rs.getDate("sale_date").toLocalDate(),
							rs.getString("name"),
							rs.getInt("account_id"), rs.getString("category_name"),
							rs.getInt("category_id"), rs.getString("trade_name"),
							rs.getInt("unit_price"),
							rs.getInt("sale_number"),
							rs.getString("note"), rs.getInt("price_all"));
					salesList.add(salesdata);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return salesList;
	}

	public SalesData identificationSalesData(int saleId) {
		SalesData salesData = null;
		String identificationSalesData = """
				SELECT sale_date, account_id, category_id, trade_name,
				unit_price, sale_number, note, unit_price * sale_number AS price_all
				FROM sales WHERE sale_id = ?
				""";
		SalesDao sd = new SalesDao();
		try (
				Connection conn = Db.open();
				PreparedStatement pstmt = conn.prepareStatement(identificationSalesData);) {
			pstmt.setInt(1, saleId);

			ResultSet rs = pstmt.executeQuery();
			if (rs.next()) {
				int account_id = rs.getInt("account_id");
				int category_id = rs.getInt("sale_id");
				new SalesData(
						saleId,
						rs.getDate("sale_date").toLocalDate(),
						sd.identificationAccount(account_id).getName(),
						account_id,
						sd.identificationCategory(category_id).getCategoryName(),
						category_id,
						rs.getString("trade_name"),
						rs.getInt("unit_price"),
						rs.getInt("sale_number"),
						rs.getString("note"),
						rs.getInt("price_all"));

			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return salesData;
	}

	public boolean updateSales(int saleId, SalesData salesData) {
		String updateSales = """
				UPDATE sales SET
				sale_date = ?, account_id = ?, category_id = ?,
				trade_name = ?, unit_price = ?, sale_number = ?, note = ?
				WHERE sale_id = ?
				""";

		LocalDate saleDate = salesData.getSaleDate();
		int accountId = salesData.getAccountId();
		int categoryId = salesData.getCategoryId();
		String tradeName = salesData.getTradeName();
		int unitPrice = salesData.getUnitPrice();
		int saleNumber = salesData.getSaleNumber();
		String note = salesData.getNote();

		try (
				Connection conn = Db.open();
				PreparedStatement pstmt = conn.prepareStatement(updateSales);) {
			pstmt.setObject(1, saleDate);
			pstmt.setInt(2, accountId);
			pstmt.setInt(3, categoryId);
			pstmt.setString(4, tradeName);
			pstmt.setInt(5, unitPrice);
			pstmt.setInt(6, saleNumber);
			pstmt.setString(7, note);
			pstmt.setInt(8, saleId);

			int rowsAffected = pstmt.executeUpdate();
			return rowsAffected > 0;
		}

		catch (Exception e) {
			e.printStackTrace();
			return false;

		}
	}

	public void deleteSales(int saleId) {
		String deleteSales = "DELETE FROM sales where sale_id = ?";

		try (
				Connection conn = Db.open();
				PreparedStatement pstmt = conn.prepareStatement(deleteSales);) {
			pstmt.setInt(1, saleId);
			pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
