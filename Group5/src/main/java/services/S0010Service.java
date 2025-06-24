package services;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class S0010Service {
	public static boolean validDate(String dateStr) {
		dateStr = dateStr.trim();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		sdf.setLenient(false);
		try {
			Date parsedDate = sdf.parse(dateStr);
			String formattedDate = sdf.format(parsedDate);
			return formattedDate.equals(dateStr);
		} catch (ParseException e) {
			return false;
		}
	}

	public static boolean inValidInt(String intStr) {
		try {
			Integer.parseInt(intStr);
			return false;
		} catch (NumberFormatException e) {
			return true;
		}
	}
	
	public static boolean validInt(String intStr) {
		try {
			Integer.parseInt(intStr);
			return true;
		} catch (NumberFormatException e) {
			return false;
		}
	}

	public static boolean validNull(String str) {
		if (str == null || str.isEmpty()) {
			return true;
		} else {
			return false;
		}
	}
}
