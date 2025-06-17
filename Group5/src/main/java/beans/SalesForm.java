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

	public SalesForm(String sale_date, String account_id, String category_id, String trade_name, String unit_price,
			String sale_number, String note) {
		this.sale_date = sale_date;
		this.account_id = account_id;
		this.category_id = category_id;
		this.trade_name = trade_name;
		this.unit_price = unit_price;
		this.sale_number = sale_number;
		this.note = note;
	}
}
