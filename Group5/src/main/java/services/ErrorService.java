package services;

import java.nio.charset.StandardCharsets;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Map;

import jakarta.servlet.http.HttpServletRequest;

import beans.loginAccount;
import daos.AccountsDao;
import daos.S0042Dao;
import daos.SalesDao;

public class ErrorService {

	Map<String, String> errors = new HashMap<>();

	public Map<String, String> ValidateLogin(String mail, String pass, String hashed_pass) {
		if (S0010Service.ValidNull(mail)) {
			errors.put("mail", "メールアドレスを入力して下さい。");
		} else if (mail.getBytes(StandardCharsets.UTF_8).length >= 100) {
			errors.put("mail", "メールアドレスが長すぎます。");
		} else if (!mail.matches("^[\\w\\-.]+@[\\w\\-]+\\.[a-zA-Z]{2,}$")) {
			errors.put("mail", "メールアドレスを正しく入力してください。");
		}

		if (S0010Service.ValidNull(pass)) {
			errors.put("pass", "パスワードが未入力です。");
		} else if (pass.getBytes(StandardCharsets.UTF_8).length >= 100) {
			errors.put("pass", "パスワードが長すぎます。");
		}

		loginAccount account = auth.findByEmail(mail);
		if (account == null && errors.isEmpty()) {
			errors.put("account", "メールアドレス、パスワードを正しく入力して下さい。");
			return errors;
		} else if (account != null && !auth.passCheck(account.getAccount_id(), hashed_pass)) {
			errors.put("account", "メールアドレス、パスワードを正しく入力して下さい。");
		}
		return errors;
	}

	public Map<String, String> ValidateSales(HttpServletRequest request) {
		String sale_dateStr = request.getParameter("sale_date");
		String account_idStr = request.getParameter("account_id");
		String category_idStr = request.getParameter("category_id");
		String trade_name = request.getParameter("trade_name");
		String unit_priceStr = request.getParameter("unit_price");
		String sale_numberStr = request.getParameter("sale_number");
		String note = request.getParameter("note");
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		sdf.setLenient(false);
		if (S0010Service.ValidNull(sale_dateStr)) {
			errors.put("sale_date", "販売日を入力してください。");
		} else if (!S0010Service.ValidDate(sale_dateStr)) {
			errors.put("sale_date", "販売日を正しく入力してください。");
		}

		SalesDao sd = new SalesDao();
		if (S0010Service.ValidNull(account_idStr)) {
			errors.put("account_id", "担当が未選択です。");
		} else if (sd.identificationAccount(Integer.valueOf(account_idStr)) == null) {
			errors.put("account_id", "アカウントテーブルに存在しません。");
		}
		if (S0010Service.ValidNull(category_idStr)) {
			errors.put("category_id", "商品カテゴリーが未選択です。");
		} else if (sd.identificationCategory(Integer.valueOf(category_idStr)) == null) {
			errors.put("category_id", "商品カテゴリーテーブルに存在しません。");
		}

		if (S0010Service.ValidNull(trade_name)) {
			errors.put("trade_name", "商品名を入力してください。");
		} else if (trade_name.getBytes(StandardCharsets.UTF_8).length > 100) {
			errors.put("trade_name", "商品名が長すぎます。");
		}

		if (S0010Service.ValidNull(unit_priceStr)) {
			errors.put("unit_price", "単価を入力してください。");
		} else if (unit_priceStr.getBytes(StandardCharsets.UTF_8).length >= 10) {
			errors.put("unit_price", "単価が長すぎます。");
		} else if (S0010Service.InValidInt(unit_priceStr)) {
			errors.put("unit_price", "単価を正しく入力してください。");
		}

		if (S0010Service.ValidNull(sale_numberStr)) {
			errors.put("sale_number", "個数を入力してください。");
		} else if (sale_numberStr.getBytes(StandardCharsets.UTF_8).length >= 10) {
			errors.put("sale_number", "個数が長すぎます。");
		} else if (S0010Service.InValidInt(sale_numberStr)) {
			errors.put("sale_number", "個数を正しく入力してください。");
		}

		if (note != null && note.getBytes(StandardCharsets.UTF_8).length >= 400) {
			errors.put("note", "備考が長すぎます。");
		}
		return errors;
	}

	public Map<String, String> ValidateSalesSearch(HttpServletRequest request) {
		String firstStr = request.getParameter("first");
		String lastStr = request.getParameter("last");
		
		if (!S0010Service.ValidNull(firstStr) && !S0010Service.ValidDate(firstStr)) {
			errors.put("first", "販売日（検索開始日）を正しく入力して下さい。");
		}
		if (!S0010Service.ValidNull(lastStr) && !S0010Service.ValidDate(lastStr)) {
			errors.put("last", "販売日（検索終了日）を正しく入力して下さい。");
		}
		return errors;
	}

	public Map<String, String> ValidateNotFoundSales(HttpServletRequest request) {
		Map<String, String> notFound = new HashMap<>();
		String sale_idStr = request.getParameter("sale_id"); // 検索結果の中身から確認
		
		if (sale_idStr == null || sale_idStr.isEmpty()) {
			notFound.put("sales_notfound", "ご指定の条件に該当するデータが見つかりませんでした。");
		}
		return notFound;
	}

	public Map<String, String> ValidateAccounts(String name, String mail, String pass, String confirm_pass) {
		AccountsDao ad = new AccountsDao();

		if (S0010Service.ValidNull(name)) {
			errors.put("name", "氏名を入力して下さい。");
		} else if (name.getBytes(StandardCharsets.UTF_8).length > 20) {
			errors.put("name", "氏名が長すぎます。");
		} else if (ad.accountNameCheck(name)) {
			errors.put("name", "このユーザー名は既に使用されています。");
		}

		if (S0010Service.ValidNull(mail)) {
			errors.put("mail", "メールアドレスを入力して下さい。");
		} else if (mail.getBytes(StandardCharsets.UTF_8).length > 100) {
			errors.put("mail", "メールアドレスが長すぎます。");
		} else if (!mail.matches("^[\\w\\-.]+@[\\w\\-]+\\.[a-zA-Z]{2,}$")) {
			errors.put("mail", "メールアドレスを正しく入力して下さい。");
		} else if (ad.accountEmailCheck(mail)) {
			errors.put("mail", "このメールアドレスは既に使用されています。");
		}

		if (S0010Service.ValidNull(pass)) {
			errors.put("pass", "パスワードを入力して下さい。");
		} else if (pass.getBytes(StandardCharsets.UTF_8).length > 30) {
			errors.put("pass", "パスワードが長すぎます。");
		}

		if (S0010Service.ValidNull(confirm_pass)) {
			errors.put("confirm_pass", "パスワード（確認）を入力して下さい。");
		}

		if (!S0010Service.ValidNull(pass) && !S0010Service.ValidNull(confirm_pass) && !pass.equals(confirm_pass)) {
			errors.put("pass", "パスワードとパスワード（確認）の入力内容が異なっています。");
			errors.put("confirm_pass", "パスワードとパスワード（確認）の入力内容が異なっています。");
		}
		return errors;
	}

	public Map<String, String> ValidateAccountsSearch(String name, String mail) {
		if (name.getBytes(StandardCharsets.UTF_8).length > 20) {
			errors.put("name", "氏名の指定が長すぎます。");
		}
		if (mail.getBytes(StandardCharsets.UTF_8).length > 20) {
			errors.put("mail", "メールアドレスの指定が長すぎます。");
		}
		return errors;
	}

	public Map<String, String> ValidateAccountsUpdate(int account_id, String name, String mail, String pass, String confirm_pass) {
		S0042Dao s0042dao = new S0042Dao();

		if (S0010Service.ValidNull(name)) {
			errors.put("name", "氏名を入力して下さい。");
		} else if (name.getBytes(StandardCharsets.UTF_8).length > 20) {
			errors.put("name", "氏名が長すぎます。");
		} else if (s0042dao.accountUpdateNameCheck(name, account_id)) {
			errors.put("name", "このユーザー名は既に使用されています。");
		}

		if (S0010Service.ValidNull(mail)) {
			errors.put("mail", "メールアドレスを入力して下さい。");
		} else if (mail.getBytes(StandardCharsets.UTF_8).length > 100) {
			errors.put("mail", "メールアドレスが長すぎます。");
		} else if (!mail.matches("^[\\w\\-.]+@[\\w\\-]+\\.[a-zA-Z]{2,}$")) {
			errors.put("mail", "メールアドレスを正しく入力して下さい。");
		} else if (s0042dao.accountUpdateEmailCheck(mail, account_id)) {
			errors.put("mail", "このメールアドレスは既に使用されています。");
		}

		if (S0010Service.ValidNull(pass)) {
			errors.put("pass", "パスワードを入力して下さい。");
		} else if (pass.getBytes(StandardCharsets.UTF_8).length > 30) {
			errors.put("pass", "パスワードが長すぎます。");
		}

		if (S0010Service.ValidNull(confirm_pass)) {
			errors.put("confirm_pass", "パスワード（確認）を入力して下さい。");
		}

		if (!S0010Service.ValidNull(pass) && !S0010Service.ValidNull(confirm_pass) && !pass.equals(confirm_pass)) {
			errors.put("pass", "パスワードとパスワード（確認）の入力内容が異なっています。");
			errors.put("confirm_pass", "パスワードとパスワード（確認）の入力内容が異なっています。");
		}
		return errors;
	}
}
