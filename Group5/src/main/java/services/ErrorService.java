package services;

import java.nio.charset.StandardCharsets;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import beans.Accounts;
import beans.LoginAccount;
import beans.SalesData;
import daos.AccountsDao;
import daos.SalesDao;

public class ErrorService {

    Map<String, String> errors = new HashMap<>();

    public Map<String, String> validateLogin(String mail, String pass, String hashedPass) {
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
        } else if (account != null && !Auth.passCheck(account.getAccountId(), hashedPass)) {
            errors.put("account", "メールアドレス、パスワードを正しく入力して下さい。");
        }
        return errors;
    }

    public Map<String, String> validateSales(String saleDateStr, String accountIdStr, String categoryIdStr,
            String tradeName, String unitPriceStr, String saleNumberStr, String note) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        sdf.setLenient(false);
        if (S0010Service.validNull(saleDateStr)) {
            errors.put("saleDate", "販売日を入力してください。");
        } else if (!S0010Service.validDate(saleDateStr)) {
            errors.put("saleDate", "販売日を正しく入力してください。");
        }

        SalesDao salesDao = new SalesDao();
        if (S0010Service.validNull(accountIdStr)) {
            errors.put("accountId", "担当が未選択です。");
        } else if (salesDao.identificationAccount(Integer.valueOf(accountIdStr)) == null) {
            errors.put("accountId", "アカウントテーブルに存在しません。");
        }
        if (S0010Service.validNull(categoryIdStr)) {
            errors.put("categoryId", "商品カテゴリーが未選択です。");
        } else if (salesDao.identificationCategory(Integer.valueOf(categoryIdStr)) == null) {
            errors.put("categoryId", "商品カテゴリーテーブルに存在しません。");
        }

        if (S0010Service.validNull(tradeName)) {
            errors.put("tradeName", "商品名を入力してください。");
        } else if (tradeName.getBytes(StandardCharsets.UTF_8).length > 100) {
            errors.put("tradeName", "商品名が長すぎます。");
        }

        if (S0010Service.validNull(unitPriceStr)) {
            errors.put("unitPrice", "単価を入力してください。");
        } else if (unitPriceStr.getBytes(StandardCharsets.UTF_8).length >= 10) {
            errors.put("unitPrice", "単価が長すぎます。");
        } else if (S0010Service.inValidInt(unitPriceStr)) {
            errors.put("unitPrice", "単価を正しく入力してください。");
        }

        if (S0010Service.validNull(saleNumberStr)) {
            errors.put("saleNumber", "個数を入力してください。");
        } else if (saleNumberStr.getBytes(StandardCharsets.UTF_8).length >= 10) {
            errors.put("saleNumber", "個数が長すぎます。");
        } else if (S0010Service.inValidInt(saleNumberStr)) {
            errors.put("saleNumber", "個数を正しく入力してください。");
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

    public Map<String, String> validateNotFoundSales(ArrayList<SalesData> salesList) {
        Map<String, String> notFound = new HashMap<>();
        if (salesList == null || salesList.isEmpty()) {
            notFound.put("salesNotFound", "ご指定の条件に該当するデータが見つかりませんでした。");
        }
        return notFound;
    }

    public Map<String, String> validateAccounts(String name, String mail, String pass, String confirmPass) {
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

        if (S0010Service.validNull(confirmPass)) {
            errors.put("confirmPass", "パスワード（確認）を入力して下さい。");
        }

        if (!S0010Service.validNull(pass) && !S0010Service.validNull(confirmPass) && !pass.equals(confirmPass)) {
            errors.put("pass", "パスワードとパスワード（確認）の入力内容が異なっています。");
            errors.put("confirmPass", "パスワードとパスワード（確認）の入力内容が異なっています。");
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
    
    public Map<String, String> validateNotFoundAccounts(ArrayList<Accounts> salesList) {
        Map<String, String> notFound = new HashMap<>();
        if (salesList == null || salesList.isEmpty()) {
            notFound.put("accountsNotFound", "ご指定の条件に該当するデータが見つかりませんでした。");
        }
        return notFound;
    }

    public Map<String, String> validateAccountsUpdate(int accountId, String name, String mail, String pass, String confirmPass) {
        AccountsDao accountsDao = new AccountsDao();

        if (S0010Service.validNull(name)) {
            errors.put("name", "氏名を入力して下さい。");
        } else if (name.getBytes(StandardCharsets.UTF_8).length > 20) {
            errors.put("name", "氏名が長すぎます。");
        } else if (accountsDao.accountUpdateNameCheck(name, accountId)) {
            errors.put("name", "このユーザー名は既に使用されています。");
        }

        if (S0010Service.validNull(mail)) {
            errors.put("mail", "メールアドレスを入力して下さい。");
        } else if (mail.getBytes(StandardCharsets.UTF_8).length > 100) {
            errors.put("mail", "メールアドレスが長すぎます。");
        } else if (!mail.matches("^[\\w\\-.]+@[\\w\\-]+\\.[a-zA-Z]{2,}$")) {
            errors.put("mail", "メールアドレスを正しく入力して下さい。");
        } else if (accountsDao.accountUpdateEmailCheck(mail, accountId)) {
            errors.put("mail", "このメールアドレスは既に使用されています。");
        }

        if (S0010Service.validNull(pass)) {
            errors.put("pass", "パスワードを入力して下さい。");
        } else if (pass.getBytes(StandardCharsets.UTF_8).length > 30) {
            errors.put("pass", "パスワードが長すぎます。");
        }

        if (S0010Service.validNull(confirmPass)) {
            errors.put("confirmPass", "パスワード（確認）を入力して下さい。");
        }

        if (!S0010Service.validNull(pass) && !S0010Service.validNull(confirmPass) && !pass.equals(confirmPass)) {
            errors.put("pass", "パスワードとパスワード（確認）の入力内容が異なっています。");
            errors.put("confirmPass", "パスワードとパスワード（確認）の入力内容が異なっています。");
        }
        return errors;
    }
}
