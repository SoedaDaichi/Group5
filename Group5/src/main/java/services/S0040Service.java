package services;

import java.util.ArrayList;

import beans.Accounts;
import beans.AccountsSearchForm;
import daos.S0040Dao;

public class S0040Service {

	private S0040Dao s0040dao = new S0040Dao();

	public ArrayList<Accounts> select(AccountsSearchForm asform) {
		String name = asform.getName();
		String mail = asform.getMail();
		int authority_0 = asform.getAuthority_0();
		int authority_1 = asform.getAuthority_1();
		int authority_2 = asform.getAuthority_2();
		int authority_3 = asform.getAuthority_3();

		ArrayList<Integer> authority = new ArrayList<>();
		if (authority_0 == 0) {
			authority.add(authority_0);
		}
		if (authority_1 == 1) {
			authority.add(authority_1);
		}
		if (authority_2 == 2) {
			authority.add(authority_2);
		}
		if (authority_3 == 3) {
			authority.add(authority_3);
		}
		return s0040dao.select(name, mail, authority);
	}
}
