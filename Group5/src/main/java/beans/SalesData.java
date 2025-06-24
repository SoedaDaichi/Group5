package beans;

import java.sql.Date;

import lombok.Data;

@Data
public class SalesData {
	private Date saleDate;
	private String name;
	private int accountId;
	private String categoryName;
	private int categoryId;
	private String tradeName;
	private int unitPrice;
	private int saleNumber;
	private String note;

	public SalesData(Date saleDate, String name, int accountId, String categoryName, int categoryId,
			String tradeName, int unitPrice, int saleNumber, String note) {
		this.saleDate = saleDate;
		this.name = name;
		this.accountId = accountId;
		this.categoryName = categoryName;
		this.categoryId = categoryId;
		this.tradeName = tradeName;
		this.unitPrice = unitPrice;
		this.saleNumber = saleNumber;
		this.note = note;
	}
}
