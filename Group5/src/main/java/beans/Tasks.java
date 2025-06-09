package beans;

import java.util.Date;

import lombok.Data;

@Data
public class Tasks {
	private int id;
	private String name;
	private Date deadline;
	private String assignee;
	private String status;
	private int user_id;
	
	public Tasks() {
	}
}
