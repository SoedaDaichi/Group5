package beans;

import java.sql.Date;

public class Player {
	private int id;
	private int country_id;
	private int uniform_num;
	private String position;
	private String name;
	private String club;
	private Date birth;
	private int height;
	private int weight;
	
//	public Player(int id, int country_id, int uniform_num, String position, String name, String club, Date birth, int height, int weight) {
	public Player() {
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getCountry_id() {
		return country_id;
	}

	public void setCountry_id(int country_id) {
		this.country_id = country_id;
	}

	public int getUniform_num() {
		return uniform_num;
	}

	public void setUniform_num(int uniform_num) {
		this.uniform_num = uniform_num;
	}

	public String getPosition() {
		return position;
	}

	public void setPosition(String position) {
		this.position = position;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getClub() {
		return club;
	}

	public void setClub(String club) {
		this.club = club;
	}

	public Date getBirth() {
		return birth;
	}

	public void setBirth(Date birth) {
		this.birth = birth;
	}

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}

	public int getWeight() {
		return weight;
	}

	public void setWeight(int weight) {
		this.weight = weight;
	}
	
	@Override
	public String toString() {
	    return String.format(
	        "ID:%d, 国番号:%d, 背番号:%d, ポジション:%s, 名前:%s, クラブ:%s, 生年月日:%s, 身長:%d, 体重:%d",
	        id, country_id, uniform_num, position, name, club, birth, height, weight
	    );
	}

}