package beans;

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
}
