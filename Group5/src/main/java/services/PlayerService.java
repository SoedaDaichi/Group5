package services;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Scanner;

import beans.Player;
import utils.Db;

public class PlayerService{
	private Scanner sc;
	
	public PlayerService() {
    }

	public static ArrayList<Player> select() {
		ArrayList<Player> playerList = new ArrayList<>();
		String select = "SELECT * FROM players";
		//				Player player = new Player(id, country_id, uniform_num, position, name, club, birth, height, weight);
	
		try (
				Connection conn = Db.open();
				Statement stmt = conn.createStatement();
				ResultSet rs = stmt.executeQuery(select);) {
			while (rs.next()) {
				Player player = new Player();
				player.setId(rs.getInt("id"));
				player.setCountry_id(rs.getInt("country_id"));
				player.setUniform_num(rs.getInt("uniform_num"));
				player.setPosition(rs.getString("position"));
				player.setName(rs.getString("name"));
				player.setClub(rs.getString("club"));
				player.setBirth(rs.getDate("birth"));
				player.setHeight(rs.getInt("height"));
				player.setWeight(rs.getInt("weight"));
				playerList.add(player);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return playerList;

	}

	public void insert(int cid, int unum, String position, String name, String club, Date birth, int height, int weight) {
		
		String insert = "INSERT INTO players (country_id, uniform_num, position, name, club, birth, height, weight)"
				+ " VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

		try (
				Connection conn = Db.open();
				PreparedStatement pstmt = conn.prepareStatement(insert);) {
			pstmt.setInt(1, cid);
			pstmt.setInt(2, unum);
			pstmt.setString(3, position);
			pstmt.setString(4, name);
			pstmt.setString(5, club);
			pstmt.setDate(6, birth);
			pstmt.setInt(7, height);
			pstmt.setInt(8, weight);
			int result = pstmt.executeUpdate();
			System.out.println(result + "件のデータを追加");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void update() {

		System.out.println("選手ID＞ ");
		int id = sc.nextInt();
		System.out.println("国番号＞ ");
		int cid = sc.nextInt();
		System.out.println("背番号＞ ");
		int unum = sc.nextInt();
		System.out.println("ポジション＞");
		String position = sc.next();
		System.out.println("名前＞ ");
		String name = sc.next();
		System.out.println("所属クラブ＞ ");
		String club = sc.next();
		System.out.println("生年月日＞ ");
		Date birth = Date.valueOf(sc.next());
		System.out.println("身長＞ ");
		int height = sc.nextInt();
		System.out.println("体重＞ ");
		int weight = sc.nextInt();
		String update = "UPDATE players SET country_id = ?, uniform_num = ?, position = ?, name = ?, club = ?, birth = ?, height = ?, weight= ?"
				+ " WHERE id = ?";
		try (
				Connection conn = Db.open();
				PreparedStatement pstmt = conn.prepareStatement(update);) {
			pstmt.setInt(1, cid);
			pstmt.setInt(2, unum);
			pstmt.setString(3, position);
			pstmt.setString(4, name);
			pstmt.setString(5, club);
			pstmt.setDate(6, birth);
			pstmt.setInt(7, height);
			pstmt.setInt(8, weight);
			pstmt.setInt(9, id);
			int result = pstmt.executeUpdate();
			System.out.println(result + "件を更新しました");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void delete() {
		String delete = "DELETE FROM players WHERE id = ?";
		System.out.println("削除する選手のID＞ ");
		int id = sc.nextInt();
		
		try (
				Connection conn = Db.open();
				PreparedStatement pstmt = conn.prepareStatement(delete);) {
			pstmt.setInt(1, id);
			int result = pstmt.executeUpdate();
			System.out.println(result + "件を削除しました");
		} catch (Exception e) {
			e.printStackTrace();
		} 
	}
}
