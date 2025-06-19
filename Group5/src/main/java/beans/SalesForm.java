package beans;

import lombok.Data;

@Data
public class SalesForm {
	private String sale_date;
	private String account_id;
	private String category_id;
	private String trade_name;
	private String unit_price;
	private String sale_number;
	private String note;

	public SalesForm(String sale_dateStr, String account_idStr, String category_idStr, String trade_name,
			String unit_priceStr,
			String sale_numberStr, String note) {
		this.sale_date = sale_dateStr;
		this.account_id = account_idStr;
		this.category_id = category_idStr;
		this.trade_name = trade_name;
		this.unit_price = unit_priceStr;
		this.sale_number = sale_numberStr;
		this.note = note;
	}
}
