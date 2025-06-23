package beans;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import jakarta.servlet.http.HttpServletRequest;

import daos.SalesDao;
import lombok.Data;

@Data
public class SalesData {
	private int sale_id;
	private LocalDate sale_date;
	private String name;
	private int account_id;
	private String category_name;
	private int category_id;
	private String trade_name;
	private int unit_price;
	private int sale_number;
	private String note;
	private int price_all;
	
	public SalesData(HttpServletRequest request, SalesDao sd) {
		String saleDateStr = request.getParameter("sale_date");
		int accountId = Integer.parseInt(request.getParameter("account_id"));
		int categoryId = Integer.parseInt(request.getParameter("category_id"));
		String tradeName = request.getParameter("trade_name");
		int unitPrice = Integer.parseInt(request.getParameter("unit_price"));
		int saleNumber = Integer.parseInt(request.getParameter("sale_number"));
		String note = request.getParameter("note");

		Accounts account = sd.identificationAccount(accountId);
		Categories category = sd.identificationCategory(categoryId);

		this.sale_date = LocalDate.parse(saleDateStr, DateTimeFormatter.ISO_DATE); // yyyy-MM-dd形式
		this.name = account.getName();
		this.account_id = accountId;
		this.category_name = category.getCategory_name();
		this.category_id = categoryId;
		this.trade_name = tradeName;
		this.unit_price = unitPrice;
		this.sale_number = saleNumber;
		this.note = note;
	}

	public SalesData(int sale_id, LocalDate sale_date, String name, int account_id, String category_name, int category_id,
			String trade_name,
			int unit_price, int sale_number,
			String note, int price_all) {
		this.sale_id = sale_id;
		this.sale_date = sale_date;
		this.name = name;
		this.account_id = account_id;
		this.category_name = category_name;
		this.category_id = category_id;
		this.trade_name = trade_name;
		this.unit_price = unit_price;
		this.sale_number = sale_number;
		this.note = note;
		this.price_all = price_all;
	}
}
