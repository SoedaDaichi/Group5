package beans;

import lombok.Data;

@Data
public class SalesSearchForm {
	private String firstStr;
	private String lastStr;
	private String account_idStr;
	private String category_idStr;
	private String trade_name;
	private String note;

	public SalesSearchForm(String firstStr, String lastStr, String account_idStr, String category_idStr,
			String trade_name, String note) {
		this.firstStr = firstStr;
		this.lastStr = lastStr;
		this.account_idStr = account_idStr;
		this.category_idStr = category_idStr;
		this.trade_name = trade_name;
		this.note = note;
	}
}
