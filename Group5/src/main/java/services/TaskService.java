package services;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import beans.Tasks;
import utils.Db;

public class TaskService {
	public TaskService() {
	}

	public static ArrayList<Tasks> select(int user_id) {
		ArrayList<Tasks> taskList = new ArrayList<>();
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

	public void insert(String name, Date deadline, String assignee, String status, int user_id) {

		String insert = "INSERT INTO tasks (name, deadline, assignee, status, user_id)"
				+ " VALUES (?, ?, ?, ?, ?)";

		try (
				Connection conn = Db.open();
				PreparedStatement pstmt = conn.prepareStatement(insert);) {
			pstmt.setString(1, name);
			if (deadline != null) {
				pstmt.setDate(2, deadline);
			} else {
				pstmt.setNull(2, java.sql.Types.DATE);
			}
			if (assignee != null) {
				pstmt.setString(3, assignee);
			} else {
				pstmt.setNull(3, java.sql.Types.VARCHAR);
			}
			pstmt.setString(4, status);
			pstmt.setInt(5, user_id);
			int result = pstmt.executeUpdate();
			System.out.println(result + "件のデータを追加");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static Tasks selectDetail(int id) {
		Tasks task = null;
		String select = "SELECT * FROM tasks WHERE id = ?";

		try (
				Connection conn = Db.open();
				PreparedStatement pstmt = conn.prepareStatement(select);) {
			pstmt.setInt(1, id);

			try (ResultSet rs = pstmt.executeQuery();) {
				if (rs.next()) {
					task = new Tasks();
					task.setId(rs.getInt("id"));
					task.setName(rs.getString("name"));
					task.setDeadline(rs.getDate("deadline"));
					task.setStatus(rs.getString("status"));
					task.setAssignee(rs.getString("assignee"));
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return task;
	}

	public void update(int id, String name, Date deadline, String assignee, String status) {
		String update = "UPDATE tasks set name = ?, deadline = ?, assignee = ?, status = ? WHERE id = ?";

		try (
				Connection conn = Db.open();
				PreparedStatement pstmt = conn.prepareStatement(update);) {

			pstmt.setString(1, name);
			if (deadline != null) {
				pstmt.setDate(2, deadline);
			} else {
				pstmt.setNull(2, java.sql.Types.DATE);
			}
			if (assignee != null) {
				pstmt.setString(3, assignee);
			} else {
				pstmt.setNull(3, java.sql.Types.VARCHAR);
			}
			pstmt.setString(4, status);
			pstmt.setInt(5, id);
			int result = pstmt.executeUpdate();
			System.out.println(result + "件のデータを更新");

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void delete(int id) {
		String delete = "DELETE FROM tasks WHERE id = ?";

		try (
				Connection conn = Db.open();
				PreparedStatement pstmt = conn.prepareStatement(delete);) {

			pstmt.setInt(1, id);
			int result = pstmt.executeUpdate();
			System.out.println(result + "件のデータを削除");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void deleteAllByUserId(int user_id) {
		String delete = "DELETE FROM tasks WHERE user_id = ?";

		try (
				Connection conn = Db.open();
				PreparedStatement pstmt = conn.prepareStatement(delete);) {

			pstmt.setInt(1, user_id);
			int result = pstmt.executeUpdate();
			System.out.println(result + "件のデータを削除");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
