package services;

import java.nio.charset.StandardCharsets;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import jakarta.servlet.http.HttpServletRequest;

import daos.AccountsDao;
import daos.SalesDao;
import form.Accounts;
import form.LoginAccount;
import form.SalesForm;

public class ErrorService {

    Map<String, String> errors = new HashMap<>();

    public Map<String, String> validateLogin(String mail, String pass, String hashedPass) {
        if (ErrorMethodService.validNull(mail)) {
            errors.put("mail", "メールアドレスを入力して下さい。");
        } else if (mail.getBytes(StandardCharsets.UTF_8).length >= 100) {
            errors.put("mail", "メールアドレスが長すぎます。");
        } else if (!mail.matches("^[\\w\\-.]+@[\\w\\-]+\\.[a-zA-Z]{2,}$")) {
            errors.put("mail", "メールアドレスを正しく入力してください。");
        }

        if (ErrorMethodService.validNull(pass)) {
            errors.put("pass", "パスワードが未入力です。");
        } else if (pass.getBytes(StandardCharsets.UTF_8).length >= 100) {
            errors.put("pass", "パスワードが長すぎます。");
        }

        LoginAccount account = Auth.findByEmail(mail);
        if (account == null && errors.isEmpty()) {
        	System.out.println("データベースにない");
            errors.put("account", "メールアドレス、パスワードを正しく入力して下さい。");
            return errors;
        } else if (account != null && !Auth.passCheck(account.getAccountId(), hashedPass)) {
            errors.put("account", "メールアドレス、パスワードを正しく入力して下さい。");
        }
        return errors;
    }

    public Map<String, String> validateSales(HttpServletRequest request) {
		String saleDateStr = request.getParameter("saleDate");
		String accountIdStr = request.getParameter("accountId");
		String categoryIdStr = request.getParameter("categoryId");
		String tradeName = request.getParameter("tradeName");
		String unitPriceStr = request.getParameter("unitPrice");
		String saleNumberStr = request.getParameter("saleNumber");
		String note = request.getParameter("note");
		
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        sdf.setLenient(false);
        if (ErrorMethodService.validNull(saleDateStr)) {
            errors.put("saleDate", "販売日を入力してください。");
        } else if (!ErrorMethodService.validDate(saleDateStr)) {
            errors.put("saleDate", "販売日を正しく入力してください。");
        }

        SalesDao salesDao = new SalesDao();
        if (ErrorMethodService.validNull(accountIdStr)) {
            errors.put("accountId", "担当が未選択です。");
        } else if (salesDao.identificationAccount(Integer.valueOf(accountIdStr)) == null) {
            errors.put("accountId", "アカウントテーブルに存在しません。");
        }
        if (ErrorMethodService.validNull(categoryIdStr)) {
            errors.put("categoryId", "商品カテゴリーが未選択です。");
        } else if (salesDao.identificationCategory(Integer.valueOf(categoryIdStr)) == null) {
            errors.put("categoryId", "商品カテゴリーテーブルに存在しません。");
        }

        if (ErrorMethodService.validNull(tradeName)) {
            errors.put("tradeName", "商品名を入力してください。");
        } else if (tradeName.getBytes(StandardCharsets.UTF_8).length > 100) {
            errors.put("tradeName", "商品名が長すぎます。");
        }

        if (ErrorMethodService.validNull(unitPriceStr)) {
            errors.put("unitPrice", "単価を入力してください。");
        } else if (unitPriceStr.getBytes(StandardCharsets.UTF_8).length >= 10) {
            errors.put("unitPrice", "単価が長すぎます。");
        } else if (ErrorMethodService.inValidInt(unitPriceStr)) {
            errors.put("unitPrice", "単価を正しく入力してください。");
        }

        if (ErrorMethodService.validNull(saleNumberStr)) {
            errors.put("saleNumber", "個数を入力してください。");
        } else if (saleNumberStr.getBytes(StandardCharsets.UTF_8).length >= 10) {
            errors.put("saleNumber", "個数が長すぎます。");
        } else if (ErrorMethodService.inValidInt(saleNumberStr)) {
            errors.put("saleNumber", "個数を正しく入力してください。");
        }

        if (note != null && note.getBytes(StandardCharsets.UTF_8).length >= 400) {
            errors.put("note", "備考が長すぎます。");
        }
        return errors;
    }

    public Map<String, String> validateSalesSearch(HttpServletRequest request) {
    	String firstStr = request.getParameter("first");
    	String lastStr = request.getParameter("last");
    	
        if (!ErrorMethodService.validNull(firstStr) && !ErrorMethodService.validDate(firstStr)) {
            errors.put("first", "販売日（検索開始日）を正しく入力して下さい。");
        }
        if (!ErrorMethodService.validNull(lastStr) && !ErrorMethodService.validDate(lastStr)) {
            errors.put("last", "販売日（検索終了日）を正しく入力して下さい。");
        }
        return errors;
    }

    public Map<String, String> validateNotFoundSales(ArrayList<SalesForm> salesList) {
        Map<String, String> notFound = new HashMap<>();
        if (salesList == null || salesList.isEmpty()) {
            notFound.put("salesNotFound", "ご指定の条件に該当するデータが見つかりませんでした。");
        }
        return notFound;
    }

    public Map<String, String> validateAccounts(HttpServletRequest request) {
		String name = request.getParameter("name");
		System.out.println("アカウント名： " + name);
		String mail = request.getParameter("mail");
		System.out.println("メールアドレス： " + mail);
		String pass = request.getParameter("pass");
		String confirmPass = request.getParameter("confirmPass");
		String authority = request.getParameter("authority");
		System.out.println("権限： " + authority);
        AccountsDao accountsDao = new AccountsDao();

        if (ErrorMethodService.validNull(name)) {
            errors.put("name", "氏名を入力して下さい。");
        } else if (name.getBytes(StandardCharsets.UTF_8).length > 20) {
            errors.put("name", "氏名が長すぎます。");
        } else if (accountsDao.accountNameCheck(name)) {
            errors.put("name", "このユーザー名は既に使用されています。");
        }

        if (ErrorMethodService.validNull(mail)) {
            errors.put("mail", "メールアドレスを入力して下さい。");
        } else if (mail.getBytes(StandardCharsets.UTF_8).length > 100) {
            errors.put("mail", "メールアドレスが長すぎます。");
        } else if (!mail.matches("^[\\w\\-.]+@[\\w\\-]+\\.[a-zA-Z]{2,}$")) {
            errors.put("mail", "メールアドレスを正しく入力して下さい。");
        } else if (accountsDao.accountEmailCheck(mail)) {
            errors.put("mail", "このメールアドレスは既に使用されています。");
        }

        if (ErrorMethodService.validNull(pass)) {
            errors.put("pass", "パスワードを入力して下さい。");
        } else if (pass.getBytes(StandardCharsets.UTF_8).length > 30) {
            errors.put("pass", "パスワードが長すぎます。");
        }

        if (ErrorMethodService.validNull(confirmPass)) {
            errors.put("confirmPass", "パスワード（確認）を入力して下さい。");
        }

        if (!ErrorMethodService.validNull(pass) && !ErrorMethodService.validNull(confirmPass) && !pass.equals(confirmPass)) {
            errors.put("pass", "パスワードとパスワード（確認）の入力内容が異なっています。");
            errors.put("confirmPass", "パスワードとパスワード（確認）の入力内容が異なっています。");
        }
        return errors;
    }

    public Map<String, String> validateAccountsSearch(HttpServletRequest request) {
		String name = request.getParameter("name");
		System.out.println("アカウント名： " + name);
		String mail = request.getParameter("mail");
		System.out.println("メールアドレス： " + mail);
    	
        if (name.getBytes(StandardCharsets.UTF_8).length > 20) {
            errors.put("name", "氏名の指定が長すぎます。");
        }
        if (mail.getBytes(StandardCharsets.UTF_8).length > 20) {
            errors.put("mail", "メールアドレスの指定が長すぎます。");
        }
        return errors;
    }
    
    public Map<String, String> validateNotFoundAccounts(ArrayList<Accounts> accountsList) {
        Map<String, String> notFound = new HashMap<>();
        if (accountsList == null || accountsList.isEmpty()) {
            notFound.put("accountsNotFound", "ご指定の条件に該当するデータが見つかりませんでした。");
        }
        return notFound;
    }

    public Map<String, String> validateAccountsUpdate(int accountId, HttpServletRequest request) {
    	String name = request.getParameter("name");
    	String mail = request.getParameter("mail");
    	String pass = request.getParameter("pass");
    	String confirmPass = request.getParameter("confirmPass");
    	
        AccountsDao accountsDao = new AccountsDao();

        if (ErrorMethodService.validNull(name)) {
            errors.put("name", "氏名を入力して下さい。");
        } else if (name.getBytes(StandardCharsets.UTF_8).length > 20) {
            errors.put("name", "氏名が長すぎます。");
        } else if (accountsDao.accountUpdateNameCheck(name, accountId)) {
            errors.put("name", "このユーザー名は既に使用されています。");
        }

        if (ErrorMethodService.validNull(mail)) {
            errors.put("mail", "メールアドレスを入力して下さい。");
        } else if (mail.getBytes(StandardCharsets.UTF_8).length > 100) {
            errors.put("mail", "メールアドレスが長すぎます。");
        } else if (!mail.matches("^[\\w\\-.]+@[\\w\\-]+\\.[a-zA-Z]{2,}$")) {
            errors.put("mail", "メールアドレスを正しく入力して下さい。");
        } else if (accountsDao.accountUpdateEmailCheck(mail, accountId)) {
            errors.put("mail", "このメールアドレスは既に使用されています。");
        }

        if (ErrorMethodService.validNull(pass)) {
            errors.put("pass", "パスワードを入力して下さい。");
        } else if (pass.getBytes(StandardCharsets.UTF_8).length > 30) {
            errors.put("pass", "パスワードが長すぎます。");
        }

        if (ErrorMethodService.validNull(confirmPass)) {
            errors.put("confirmPass", "パスワード（確認）を入力して下さい。");
        }

        if (!ErrorMethodService.validNull(pass) && !ErrorMethodService.validNull(confirmPass) && !pass.equals(confirmPass)) {
            errors.put("pass", "パスワードとパスワード（確認）の入力内容が異なっています。");
            errors.put("confirmPass", "パスワードとパスワード（確認）の入力内容が異なっています。");
        }
        return errors;
    }
}
