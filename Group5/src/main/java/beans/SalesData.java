package beans;

import java.sql.Date;

import lombok.Data;

@Data
public class SalesData {
	private Date sale_date;
	private String name;
	private int account_id;
	private String category_name;
	private int category_id;
	private String trade_name;
	private int unit_price;
	private int sale_number;
	private String note;

	public SalesData(Date sale_date, String name, int account_id, String category_name, int category_id,
			String trade_name,
			int unit_price, int sale_number,
			String note) {
		this.sale_date = sale_date;
		this.name = name;
		this.account_id = account_id;
		this.category_name = category_name;
		this.category_id = category_id;
		this.trade_name = trade_name;
		this.unit_price = unit_price;
		this.sale_number = sale_number;
		this.note = note;
	}
}
