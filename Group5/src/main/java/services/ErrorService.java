package services;

import java.nio.charset.StandardCharsets;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import beans.LoginAccount;
import beans.Sales;
import daos.AccountsDao;
import daos.SalesDao;

public class ErrorService {

	Map<String, String> errors = new HashMap<>();

	public Map<String, String> validateLogin(String mail, String pass, String hashed_pass) {
		if (S0010Service.validNull(mail)) {
			errors.put("mail", "メールアドレスを入力して下さい。");
		} else if (mail.getBytes(StandardCharsets.UTF_8).length >= 100) {
			errors.put("mail", "メールアドレスが長すぎます。");
		} else if (!mail.matches("^[\\w\\-.]+@[\\w\\-]+\\.[a-zA-Z]{2,}$")) {
			errors.put("mail", "メールアドレスを正しく入力してください。");
		}

		if (S0010Service.validNull(pass)) {
			errors.put("pass", "パスワードが未入力です。");
		} else if (pass.getBytes(StandardCharsets.UTF_8).length >= 100) {
			errors.put("pass", "パスワードが長すぎます。");
		}

		LoginAccount account = Auth.findByEmail(mail);
		if (account == null && errors.isEmpty()) {
			errors.put("account", "メールアドレス、パスワードを正しく入力して下さい。");
			return errors;
		} else if (account != null && !Auth.passCheck(account.getAccountId(), hashed_pass)) {
			errors.put("account", "メールアドレス、パスワードを正しく入力して下さい。");
		}
		return errors;
	}

	public Map<String, String> validateSales(String sale_dateStr, String account_idStr, String category_idStr,
			String trade_name, String unit_priceStr, String sale_numberStr, String note) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		sdf.setLenient(false);
		if (S0010Service.validNull(sale_dateStr)) {
			errors.put("sale_date", "販売日を入力してください。");
		} else if (!S0010Service.validDate(sale_dateStr)) {
			errors.put("sale_date", "販売日を正しく入力してください。");
		}

		SalesDao salesDao = new SalesDao();
		if (S0010Service.validNull(account_idStr)) {
			errors.put("account_id", "担当が未選択です。");
		} else if (salesDao.identificationAccount(Integer.valueOf(account_idStr)) == null) {
			errors.put("account_id", "アカウントテーブルに存在しません。");
		}
		if (S0010Service.validNull(category_idStr)) {
			errors.put("category_id", "商品カテゴリーが未選択です。");
		} else if (salesDao.identificationCategory(Integer.valueOf(category_idStr)) == null) {
			errors.put("category_id", "商品カテゴリーテーブルに存在しません。");
		}

		if (S0010Service.validNull(trade_name)) {
			errors.put("trade_name", "商品名を入力してください。");
		} else if (trade_name.getBytes(StandardCharsets.UTF_8).length > 100) {
			errors.put("trade_name", "商品名が長すぎます。");
		}

		if (S0010Service.validNull(unit_priceStr)) {
			errors.put("unit_price", "単価を入力してください。");
		} else if (unit_priceStr.getBytes(StandardCharsets.UTF_8).length >= 10) {
			errors.put("unit_price", "単価が長すぎます。");
		} else if (S0010Service.inValidInt(unit_priceStr)) {
			errors.put("unit_price", "単価を正しく入力してください。");
		}

		if (S0010Service.validNull(sale_numberStr)) {
			errors.put("sale_number", "個数を入力してください。");
		} else if (sale_numberStr.getBytes(StandardCharsets.UTF_8).length >= 10) {
			errors.put("sale_number", "個数が長すぎます。");
		} else if (S0010Service.inValidInt(sale_numberStr)) {
			errors.put("sale_number", "個数を正しく入力してください。");
		}

		if (note != null && note.getBytes(StandardCharsets.UTF_8).length >= 400) {
			errors.put("note", "備考が長すぎます。");
		}
		return errors;
	}

	public Map<String, String> validateSalesSearch(String firstStr, String lastStr) {
		if (!S0010Service.validNull(firstStr) && !S0010Service.validDate(firstStr)) {
			errors.put("first", "販売日（検索開始日）を正しく入力して下さい。");
		}
		if (!S0010Service.validNull(lastStr) && !S0010Service.validDate(lastStr)) {
			errors.put("last", "販売日（検索終了日）を正しく入力して下さい。");
		}
		return errors;
	}

	public Map<String, String> validateNotFoundSales(ArrayList<Sales> sales) {
		Map<String, String> notFound = new HashMap<>();
		if (sales == null || sales.isEmpty()) {
			notFound.put("sales_notfound", "ご指定の条件に該当するデータが見つかりませんでした。");
		}
		return notFound;
	}

	public Map<String, String> validateAccounts(String name, String mail, String pass, String confirm_pass) {
		AccountsDao accountsDao = new AccountsDao();

		if (S0010Service.validNull(name)) {
			errors.put("name", "氏名を入力して下さい。");
		} else if (name.getBytes(StandardCharsets.UTF_8).length > 20) {
			errors.put("name", "氏名が長すぎます。");
		} else if (accountsDao.accountNameCheck(name)) {
			errors.put("name", "このユーザー名は既に使用されています。");
		}

		if (S0010Service.validNull(mail)) {
			errors.put("mail", "メールアドレスを入力して下さい。");
		} else if (mail.getBytes(StandardCharsets.UTF_8).length > 100) {
			errors.put("mail", "メールアドレスが長すぎます。");
		} else if (!mail.matches("^[\\w\\-.]+@[\\w\\-]+\\.[a-zA-Z]{2,}$")) {
			errors.put("mail", "メールアドレスを正しく入力して下さい。");
		} else if (accountsDao.accountEmailCheck(mail)) {
			errors.put("mail", "このメールアドレスは既に使用されています。");
		}

		if (S0010Service.validNull(pass)) {
			errors.put("pass", "パスワードを入力して下さい。");
		} else if (pass.getBytes(StandardCharsets.UTF_8).length > 30) {
			errors.put("pass", "パスワードが長すぎます。");
		}

		if (S0010Service.validNull(confirm_pass)) {
			errors.put("confirm_pass", "パスワード（確認）を入力して下さい。");
		}

		if (!S0010Service.validNull(pass) && !S0010Service.validNull(confirm_pass) && !pass.equals(confirm_pass)) {
			errors.put("pass", "パスワードとパスワード（確認）の入力内容が異なっています。");
			errors.put("confirm_pass", "パスワードとパスワード（確認）の入力内容が異なっています。");
		}
		return errors;
	}

	public Map<String, String> validateAccountsSearch(String name, String mail) {
		if (name.getBytes(StandardCharsets.UTF_8).length > 20) {
			errors.put("name", "氏名の指定が長すぎます。");
		}
		if (mail.getBytes(StandardCharsets.UTF_8).length > 20) {
			errors.put("mail", "メールアドレスの指定が長すぎます。");
		}
		return errors;
	}

	public Map<String, String> validateAccountsUpdate(int account_id, String name, String mail, String pass, String confirm_pass) {
		AccountsDao accountsDao = new AccountsDao();

		if (S0010Service.validNull(name)) {
			errors.put("name", "氏名を入力して下さい。");
		} else if (name.getBytes(StandardCharsets.UTF_8).length > 20) {
			errors.put("name", "氏名が長すぎます。");
		} else if (accountsDao.accountUpdateNameCheck(name, account_id)) {
			errors.put("name", "このユーザー名は既に使用されています。");
		}

		if (S0010Service.validNull(mail)) {
			errors.put("mail", "メールアドレスを入力して下さい。");
		} else if (mail.getBytes(StandardCharsets.UTF_8).length > 100) {
			errors.put("mail", "メールアドレスが長すぎます。");
		} else if (!mail.matches("^[\\w\\-.]+@[\\w\\-]+\\.[a-zA-Z]{2,}$")) {
			errors.put("mail", "メールアドレスを正しく入力して下さい。");
		} else if (accountsDao.accountUpdateEmailCheck(mail, account_id)) {
			errors.put("mail", "このメールアドレスは既に使用されています。");
		}

		if (S0010Service.validNull(pass)) {
			errors.put("pass", "パスワードを入力して下さい。");
		} else if (pass.getBytes(StandardCharsets.UTF_8).length > 30) {
			errors.put("pass", "パスワードが長すぎます。");
		}

		if (S0010Service.validNull(confirm_pass)) {
			errors.put("confirm_pass", "パスワード（確認）を入力して下さい。");
		}

		if (!S0010Service.validNull(pass) && !S0010Service.validNull(confirm_pass) && !pass.equals(confirm_pass)) {
			errors.put("pass", "パスワードとパスワード（確認）の入力内容が異なっています。");
			errors.put("confirm_pass", "パスワードとパスワード（確認）の入力内容が異なっています。");
		}
		return errors;
	}
}
