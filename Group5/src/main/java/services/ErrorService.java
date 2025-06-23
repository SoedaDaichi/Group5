package services;

import java.nio.charset.StandardCharsets;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Map;

import jakarta.servlet.http.HttpServletRequest;

import beans.AccountsData;
import beans.loginAccount;
import daos.AccountsDao;
import daos.SalesDao;

public class ErrorService {

	Map<String, String> errors = new HashMap<>();

	public Map<String, String> ValidateLogin(String mail, String pass, String hashedPass) {
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
		} else if (account != null && !auth.passCheck(account.getAccountId(), hashedPass)) {
			errors.put("account", "メールアドレス、パスワードを正しく入力して下さい。");
		}
		return errors;
	}

	public Map<String, String> ValidateSales(HttpServletRequest request) {
		String saleDateStr = request.getParameter("saleDate");
		String accountIdStr = request.getParameter("accountId");
		String categoryIdStr = request.getParameter("categoryId");
		String tradeName = request.getParameter("tradeName");
		String unitPriceStr = request.getParameter("unitPrice");
		String saleNumberStr = request.getParameter("saleNumber");
		String note = request.getParameter("note");

		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		sdf.setLenient(false);
		if (S0010Service.ValidNull(saleDateStr)) {
			errors.put("saleDate", "販売日を入力してください。");
		} else if (!S0010Service.ValidDate(saleDateStr)) {
			errors.put("saleDate", "販売日を正しく入力してください。");
		}

		SalesDao sd = new SalesDao();
		if (S0010Service.ValidNull(accountIdStr)) {
			errors.put("accountId", "担当が未選択です。");
		} else if (sd.identificationAccount(Integer.valueOf(accountIdStr)) == null) {
			errors.put("accountId", "アカウントテーブルに存在しません。");
		}
		if (S0010Service.ValidNull(categoryIdStr)) {
			errors.put("categoryId", "商品カテゴリーが未選択です。");
		} else if (sd.identificationCategory(Integer.valueOf(categoryIdStr)) == null) {
			errors.put("categoryId", "商品カテゴリーテーブルに存在しません。");
		}

		if (S0010Service.ValidNull(tradeName)) {
			errors.put("tradeName", "商品名を入力してください。");
		} else if (tradeName.getBytes(StandardCharsets.UTF_8).length > 100) {
			errors.put("tradeName", "商品名が長すぎます。");
		}

		if (S0010Service.ValidNull(unitPriceStr)) {
			errors.put("unitPrice", "単価を入力してください。");
		} else if (unitPriceStr.getBytes(StandardCharsets.UTF_8).length >= 10) {
			errors.put("unitPrice", "単価が長すぎます。");
		} else if (S0010Service.InValidInt(unitPriceStr)) {
			errors.put("unitPrice", "単価を正しく入力してください。");
		}

		if (S0010Service.ValidNull(saleNumberStr)) {
			errors.put("saleNumber", "個数を入力してください。");
		} else if (saleNumberStr.getBytes(StandardCharsets.UTF_8).length >= 10) {
			errors.put("saleNumber", "個数が長すぎます。");
		} else if (S0010Service.InValidInt(saleNumberStr)) {
			errors.put("saleNumber", "個数を正しく入力してください。");
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
		String saleIdStr = request.getParameter("saleId"); // 検索結果の中身から確認

		if (saleIdStr == null || saleIdStr.isEmpty()) {
			notFound.put("sales_notfound", "ご指定の条件に該当するデータが見つかりませんでした。");
		}
		return notFound;
	}

	public Map<String, String> ValidateAccounts(HttpServletRequest request) {
		String name = request.getParameter("name");
		System.out.println("アカウント名： " + name);
		String mail = request.getParameter("mail");
		System.out.println("メールアドレス： " + mail);
		String pass = request.getParameter("pass");
		String confirmPass = request.getParameter("confirmPass");
		String authority = request.getParameter("authority");
		System.out.println("権限： " + authority);
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

		if (S0010Service.ValidNull(confirmPass)) {
			errors.put("confirm_pass", "パスワード（確認）を入力して下さい。");
		}

		if (!S0010Service.ValidNull(pass) && !S0010Service.ValidNull(confirmPass) && !pass.equals(confirmPass)) {
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

	public Map<String, String> ValidateAccountsUpdate(AccountsData accountsdata) {
		int accountId = accountsdata.getAccountId();
		String name = accountsdata.getName();
		String mail = accountsdata.getMail();
		String pass = accountsdata.getPass();
		String confirmPass = accountsdata.getConfirmPass();
		AccountsDao ad = new AccountsDao();

		if (S0010Service.ValidNull(name)) {
			errors.put("name", "氏名を入力して下さい。");
		} else if (name.getBytes(StandardCharsets.UTF_8).length > 20) {
			errors.put("name", "氏名が長すぎます。");
		} else if (ad.accountUpdateNameCheck(name, accountId)) {
			errors.put("name", "このユーザー名は既に使用されています。");
		}

		if (S0010Service.ValidNull(mail)) {
			errors.put("mail", "メールアドレスを入力して下さい。");
		} else if (mail.getBytes(StandardCharsets.UTF_8).length > 100) {
			errors.put("mail", "メールアドレスが長すぎます。");
		} else if (!mail.matches("^[\\w\\-.]+@[\\w\\-]+\\.[a-zA-Z]{2,}$")) {
			errors.put("mail", "メールアドレスを正しく入力して下さい。");
		} else if (ad.accountUpdateEmailCheck(mail, accountId)) {
			errors.put("mail", "このメールアドレスは既に使用されています。");
		}

		if (S0010Service.ValidNull(pass)) {
			errors.put("pass", "パスワードを入力して下さい。");
		} else if (pass.getBytes(StandardCharsets.UTF_8).length > 30) {
			errors.put("pass", "パスワードが長すぎます。");
		}

		if (S0010Service.ValidNull(confirmPass)) {
			errors.put("confirm_pass", "パスワード（確認）を入力して下さい。");
		}

		if (!S0010Service.ValidNull(pass) && !S0010Service.ValidNull(confirmPass) && !pass.equals(confirmPass)) {
			errors.put("pass", "パスワードとパスワード（確認）の入力内容が異なっています。");
			errors.put("confirm_pass", "パスワードとパスワード（確認）の入力内容が異なっています。");
		}
		return errors;
	}
}
