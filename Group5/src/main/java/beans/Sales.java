package beans;

import java.util.Date;

import lombok.Data;

@Data
public class Sales {
	private int sale_id;
	private Date sale_date;
	private int account_id;
	private int category_id;
	private String trade_name;
	private int unit_price;
	private int sale_number;
	private String note;
	
	// その他
	private String name;
	private String category_name;
	private int price_all; // unit_price * sale_number
	
	public Sales() {
	}
}
