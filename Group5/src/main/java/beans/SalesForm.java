package beans;

import lombok.Data;

@Data
public class SalesForm {
	private String sale_dateStr;
	private String account_idStr;
	private String category_idStr;
	private String trade_name;
	private String unit_priceStr;
	private String sale_numberStr;
	private String note;

	public SalesForm(String sale_dateStr, String account_idStr, String category_idStr, String trade_name,
			String unit_priceStr,
			String sale_numberStr, String note) {
		this.sale_dateStr = sale_dateStr;
		this.account_idStr = account_idStr;
		this.category_idStr = category_idStr;
		this.trade_name = trade_name;
		this.unit_priceStr = unit_priceStr;
		this.sale_numberStr = sale_numberStr;
		this.note = note;
	}
}
