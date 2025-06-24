package services;

import java.util.ArrayList;

import beans.Accounts;
import beans.AccountsSearchForm;
import daos.S0040Dao;

public class S0040Service {

	private S0040Dao s0040dao = new S0040Dao();

	public ArrayList<Accounts> select(AccountsSearchForm asForm) {
		String name = asForm.getName();
		String mail = asForm.getMail();
		int authority0 = asForm.getAuthority0();
		int authority1 = asForm.getAuthority1();
		int authority2 = asForm.getAuthority2();
		int authority3 = asForm.getAuthority3();

		ArrayList<Integer> authority = new ArrayList<>();
		if (authority0 == 0) {
			authority.add(authority0);
		}
		if (authority1 == 1) {
			authority.add(authority1);
		}
		if (authority2 == 2) {
			authority.add(authority2);
		}
		if (authority3 == 3) {
			authority.add(authority3);
		}
		return s0040dao.select(name, mail, authority);
	}
	
	public int parseAuthority(String authorityStr) {
		if (authorityStr != null && !authorityStr.isEmpty()) {
			System.out.println("権限： " + authorityStr);
			return Integer.parseInt(authorityStr);
		}
		return 5;
	}
}
