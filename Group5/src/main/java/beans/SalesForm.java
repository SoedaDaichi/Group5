package beans;

import jakarta.servlet.http.HttpServletRequest;

import lombok.Data;

@Data
public class SalesForm {
	private String saleDate;
	private String accountId;
	private String categoryId;
	private String tradeName;
	private String unitPrice;
	private String saleNumber;
	private String note;

	public SalesForm(String saleDateStr, String accountIdStr, String categoryIdStr, String tradeName,
			String unitPriceStr,
			String saleNumberStr, String note) {
		this.saleDate = saleDateStr;
		this.accountId = accountIdStr;
		this.categoryId = categoryIdStr;
		this.tradeName = tradeName;
		this.unitPrice = unitPriceStr;
		this.saleNumber = saleNumberStr;
		this.note = note;
	}

	public SalesForm(HttpServletRequest request) {
		this.saleDate = request.getParameter("saleData");
		this.accountId = request.getParameter("accountId");
		this.categoryId = request.getParameter("categoryId");
		this.tradeName = request.getParameter("tradeName");
		this.unitPrice = request.getParameter("unitPrice");
		this.saleNumber = request.getParameter("saleNumber");
		this.note = request.getParameter("note");
	}
}
