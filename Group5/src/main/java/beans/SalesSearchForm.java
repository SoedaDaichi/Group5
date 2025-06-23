package beans;

import jakarta.servlet.http.HttpServletRequest;

import lombok.Data;

@Data
public class SalesSearchForm {
	private String firstStr;
	private String lastStr;
	private String accountIdStr;
	private String categoryIdStr;
	private String tradeName;
	private String note;
	
	public SalesSearchForm(HttpServletRequest request) {
		this.firstStr = request.getParameter("first");
		this.lastStr = request.getParameter("last");
		this.accountIdStr = request.getParameter("accountId");
		this.categoryIdStr = request.getParameter("categoryId");
		this.tradeName = request.getParameter("tradeName");
		this.note = request.getParameter("note");
	}

	public SalesSearchForm(String firstStr, String lastStr, String accountIdStr, String categoryIdStr,
			String tradeName, String note) {
		this.firstStr = firstStr;
		this.lastStr = lastStr;
		this.accountIdStr = accountIdStr;
		this.categoryIdStr = categoryIdStr;
		this.tradeName = tradeName;
		this.note = note;
	}
}
