package services;

import java.text.ParseException;
import java.text.SimpleDateFormat;

public class S0010Service {
	public static boolean ValidDate(String dateStr) {

		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		sdf.setLenient(false);
		try {
			sdf.parse(dateStr);
			return false;
		} catch (ParseException e) {
			return true;
		}
	}

	public static boolean InValidInt(String intStr) {
		try {
			Integer.parseInt(intStr);
			return false;
		} catch (NumberFormatException e) {
			return true;
		}
	}
	
	public static boolean ValidInt(String intStr) {
		try {
			Integer.parseInt(intStr);
			return true;
		} catch (NumberFormatException e) {
			return false;
		}
	}

	public static boolean ValidNull(String str) {
		if (str == null || str.isEmpty()) {
			return true;
		} else {
			return false;
		}
	}

}
