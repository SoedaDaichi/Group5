package beans;

import java.time.LocalDate;

import daos.S0010Dao;
import lombok.Data;

@Data
public class Sales {
	private int sale_id;
	private LocalDate sale_date;
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
	
	public SalesData toSalesData(S0010Dao dao) { // Sales -> SalesData
	    return new SalesData(
	        (LocalDate) getSale_date(),
	        dao.identificationAccount(getAccount_id()).getName(),
	        getAccount_id(),
	        dao.identificationCategory(getCategory_id()).getCategory_name(),
	        getCategory_id(),
	        getTrade_name(),
	        getUnit_price(),
	        getSale_number(),
	        getNote()
	    );
	}

}
