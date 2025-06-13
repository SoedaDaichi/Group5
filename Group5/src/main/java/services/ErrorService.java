package services;

import java.util.HashMap;
import java.util.Map;

import beans.Accounts;

public class ErrorService {

	public Map<String, String> ValidateLogin(String mail, String pass, String hashed_pass) {
		Map<String, String> errors = new HashMap<>();
		if (mail == null || mail.isEmpty()) {
			errors.put("mail", "メールアドレスを入力して下さい。");
		} else if (mail.getBytes().length >= 100) {
			errors.put("mail", "メールアドレスが長すぎます。");
		} else if (!mail.matches("^[\\w\\-.]+@[\\w\\-]+\\.[a-zA-Z]{2,}$")) {
			errors.put("mail", "メールアドレスを正しく入力してください。");
		}

		if (pass == null || pass.isEmpty()) {
			errors.put("pass", "パスワードが未入力です。");
		} else if (pass.getBytes().length >= 100) {
			errors.put("pass", "パスワードが長すぎます。");
		}

		Accounts account = auth.findByEmail(mail);
		if (account == null && errors.isEmpty()) {
			errors.put("account", "メールアドレス、パスワードを正しく入力して下さい。");
			return errors;
		} else if (account != null && !auth.passCheck(account.getAccount_id(), hashed_pass)) {
			errors.put("account", "メールアドレス、パスワードを正しく入力して下さい。");
		}
		return errors;
	}

}
