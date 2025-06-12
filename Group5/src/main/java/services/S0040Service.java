package services;

import java.util.ArrayList;

import beans.Accounts;
import daos.S0040Dao;

public class S0040Service {

	private S0040Dao s0040dao = new S0040Dao();

	public ArrayList<Accounts> select(String name, String mail, int role0, int role1, int role10) {
		
		ArrayList<Integer> roles = new ArrayList<>();
		if (role0 == 0) {
			roles.add(role0);
		}
		if (role1 == 1) {
			roles.add(role1);
		}
		if (role10 == 2) {
			roles.add(role10);
		}
		return s0040dao.select(name, mail, roles);
	}
}
