package data;

import java.time.LocalDate;

import form.SalesForm;
import lombok.Data;

@Data
public class SalesData {
	private Integer saleId;
	private LocalDate saleDate;
	private String name;
	private Integer accountId;
	private String categoryName;
	private Integer categoryId;
	private String tradeName;
	private Integer unitPrice;
	private Integer saleNumber;
	private String note;
	private Integer priceAll;

	// 売上登録
	public SalesData(SalesForm salesForm, String name, String categoryName) {
		this.saleDate = LocalDate.parse(salesForm.getSaleDateStr());
		this.name = name;
		this.accountId = Integer.valueOf(salesForm.getAccountIdStr());
		this.categoryName = categoryName;
		this.categoryId = Integer.valueOf(salesForm.getCategoryIdStr());
		this.tradeName = salesForm.getTradeName();
		this.unitPrice = Integer.valueOf(salesForm.getUnitPriceStr());
		this.saleNumber = Integer.valueOf(salesForm.getSaleNumberStr());
		this.note = salesForm.getNote();
	}

	public SalesData(Integer saleId, LocalDate saleDate, String name, Integer accountId, String categoryName,
			Integer categoryId, String tradeName, Integer unitPrice, Integer saleNumber, String note,
			Integer priceAll) {
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
