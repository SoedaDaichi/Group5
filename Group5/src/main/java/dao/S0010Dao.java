package dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import beans.Accounts;
import beans.Categories;
import utils.Db;

public class S0010Dao {
	

	public ArrayList<Accounts> selectAccount() {
		ArrayList<Accounts> accountList = new ArrayList<>();
		String selectAccount = "SELECT account_id, name FROM accounts WHERE authority = 1 OR authority = 3";
		
		try (
				Connection conn = Db.open();
				PreparedStatement pstmt = conn.prepareStatement(selectAccount);) {
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				Accounts accounts = new Accounts();
				accounts.setAccount_id(rs.getInt("account_id"));
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
				categories.setCategory_id(rs.getInt("category_id"));
				categories.setCategory_name(rs.getString("category_name"));
				categoryList.add(categories);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return categoryList;
	}
	
	public Accounts identificationAccount(int account_id) {
	    Accounts account = null;
		String identificationaccount = "SELECT name FROM accounts WHERE account_id = ?";
		
		 try (
			        Connection conn = Db.open();
			        PreparedStatement pstmt = conn.prepareStatement(identificationaccount);
			    ) {
			        pstmt.setInt(1, account_id); 

			        ResultSet rs = pstmt.executeQuery();
			        if (rs.next()) {
			            account = new Accounts();
			            account.setName(rs.getString("name"));
			        }
			    } catch (Exception e) {
			        e.printStackTrace();
			    }

			    return account;
			}
	
	public Categories identificationCategory(int category_id) {
		Categories category = null;
		String identificationcategory = "SELECT category_name FROM categories WHERE category_id = ?";
		
		 try (
			        Connection conn = Db.open();
			        PreparedStatement pstmt = conn.prepareStatement(identificationcategory);
			    ) {
			        pstmt.setInt(1, category_id); 

			        ResultSet rs = pstmt.executeQuery();
			        if (rs.next()) {
			            category = new Categories();
			            category.setCategory_name(rs.getString("Category_name"));
			        }
			    } catch (Exception e) {
			        e.printStackTrace();
			    }

			    return category;
			}

	public void insert(Date sale_date, int account_id, int category_id, String trade_name,
			int unit_price, int sale_number, String note) {

		String insert = "INSERT INTO sales (sale_date, account_id, category_id, trade_name, unit_price, sale_number, note)"
				+ " VALUES (?, ?, ?, ?, ?, ?, ?)";

		try (
				Connection conn = Db.open();
				PreparedStatement pstmt = conn.prepareStatement(insert);) {
			pstmt.setDate(1, sale_date);
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
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
