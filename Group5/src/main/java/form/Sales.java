package form;

import java.util.Date;

import lombok.Data;

@Data
public class Sales {
	private int saleId;
	private Date saleDate;
	private int accountId;
	private int categoryId;
	private String tradeName;
	private int unitPrice;
	private int saleNumber;
	private String note;

	// その他
	private String name;
	private String categoryName;
	private int priceAll; // unitPrice * saleNumber

	public Sales() {
	}
}
