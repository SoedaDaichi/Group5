package beans;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import jakarta.servlet.http.HttpServletRequest;

import daos.SalesDao;
import lombok.Data;

@Data
public class SalesData {
	private int saleId;
	private LocalDate saleDate;
	private String name;
	private int accountId;
	private String categoryName;
	private int categoryId;
	private String tradeName;
	private int unitPrice;
	private int saleNumber;
	private String note;
	private int priceAll;
	
	public SalesData(HttpServletRequest request, SalesDao sd) {
		String saleDateStr = request.getParameter("saleDate");
		int accountId = Integer.parseInt(request.getParameter("accountId"));
		int categoryId = Integer.parseInt(request.getParameter("categoryId"));
		String tradeName = request.getParameter("tradeName");
		int unitPrice = Integer.parseInt(request.getParameter("unitPrice"));
		int saleNumber = Integer.parseInt(request.getParameter("saleNumber"));
		String note = request.getParameter("note");

		Accounts account = sd.identificationAccount(accountId);
		Categories category = sd.identificationCategory(categoryId);

		this.saleDate = LocalDate.parse(saleDateStr, DateTimeFormatter.ISO_DATE); // yyyy-MM-dd形式
		this.name = account.getName();
		this.accountId = accountId;
		this.categoryName = category.getCategoryName();
		this.categoryId = categoryId;
		this.tradeName = tradeName;
		this.unitPrice = unitPrice;
		this.saleNumber = saleNumber;
		this.note = note;
	}

	public SalesData(int saleId, LocalDate saleDate, String name, int accountId, String categoryName, int categoryId,
			String tradeName,
			int unitPrice, int saleNumber,
			String note, int priceAll) {
		this.saleId = saleId;
		this.saleDate = saleDate;
		this.name = name;
		this.accountId = accountId;
		this.categoryName = categoryName;
		this.categoryId = categoryId;
		this.tradeName = tradeName;
		this.unitPrice = unitPrice;
		this.saleNumber = saleNumber;
		this.note = note;
		this.priceAll = priceAll;
	}
}
