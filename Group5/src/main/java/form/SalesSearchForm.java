package form;

import jakarta.servlet.http.HttpServletRequest;

import lombok.Data;

@Data
public class SalesSearchForm {
	private String first;
	private String last;
	private String accountId;
	private String categoryId;
	private String tradeName;
	private String note;

	public SalesSearchForm(String first, String last, String accountId, String categoryId,
			String tradeName, String note) {
		this.first = first;
		this.last = last;
		this.accountId = accountId;
		this.categoryId = categoryId;
		this.tradeName = tradeName;
		this.note = note;
	}
	
	public SalesSearchForm(HttpServletRequest request) {
		this.first = request.getParameter("first");
		this.last = request.getParameter("last");
		this.accountId = request.getParameter("accountId");
		this.categoryId = request.getParameter("categoryId");
		this.tradeName = request.getParameter("tradeName");
		this.note = request.getParameter("note");
	}
}
