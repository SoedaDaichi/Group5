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
	
	public Sales() {
	}
}
