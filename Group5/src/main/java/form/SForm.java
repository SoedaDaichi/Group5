package form;

import lombok.Data;

@Data
public class SForm {
	private String saleDate;
	private String accountId;
	private String categoryId;
	private String tradeName;
	private String unitPrice;
	private String saleNumber;
	private String note;

	public SForm(String saleDate, String accountId, String categoryId, String tradeName,
			String unitPrice, String saleNumber, String note) {
		this.saleDate = saleDate;
		this.accountId = accountId;
		this.categoryId = categoryId;
		this.tradeName = tradeName;
		this.unitPrice = unitPrice;
		this.saleNumber = saleNumber;
		this.note = note;
	}
}
