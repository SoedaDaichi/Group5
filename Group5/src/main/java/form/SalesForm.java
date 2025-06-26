package form;

import java.time.LocalDate;

import jakarta.servlet.http.HttpServletRequest;

import lombok.Data;

@Data
public class SalesForm {
	private Integer saleId;
	private String saleDateStr;
	private LocalDate saleDate;
	private String name;
	private String accountIdStr;
	private Integer accountId;
	private String categoryName;
	private String categoryIdStr;
	private Integer categoryId;
	private String tradeName;
	private String unitPriceStr;
	private Integer unitPrice;
	private String saleNumberStr;
	private Integer saleNumber;
	private String note;
	
	private Integer priceAll;

	//小計あり
	public SalesForm(Integer saleId, LocalDate saleDate, String name, Integer accountId, String categoryName,
			Integer categoryId,
			String tradeName, Integer unitPrice, Integer saleNumber, String note, Integer priceAll) {
		this.saleId = saleId;
		this.saleDate = saleDate;
		this.name = name;
		this.accountId = accountId;
		this.categoryName = categoryName;
		this.categoryId = categoryId;
		this.tradeName = tradeName;
		this.unitPrice = unitPrice;
		this.saleNumber = saleNumber;
		this.note = note;
		this.priceAll = priceAll;
	}

	//主キーあり
	public SalesForm(Integer saleId, LocalDate saleDate, String name, Integer accountId, String categoryName,
			Integer categoryId,
			String tradeName, Integer unitPrice, Integer saleNumber, String note) {
		this.saleId = saleId;
		this.saleDate = saleDate;
		this.name = name;
		this.accountId = accountId;
		this.categoryName = categoryName;
		this.categoryId = categoryId;
		this.tradeName = tradeName;
		this.unitPrice = unitPrice;
		this.saleNumber = saleNumber;
		this.note = note;
	}

	//主キーなし
	public SalesForm(LocalDate saleDate, String name, int accountId, String categoryName, int categoryId,
			String tradeName, int unitPrice, int saleNumber, String note) {
		this.saleDate = saleDate;
		this.name = name;
		this.accountId = accountId;
		this.categoryName = categoryName;
		this.categoryId = categoryId;
		this.tradeName = tradeName;
		this.unitPrice = unitPrice;
		this.saleNumber = saleNumber;
		this.note = note;
	}

	// null許容
	public SalesForm(HttpServletRequest request) {
		this.saleDateStr = request.getParameter("saleDate");
		this.accountIdStr = request.getParameter("accountId");
		this.categoryIdStr = request.getParameter("categoryId");
		this.tradeName = request.getParameter("tradeName");
		this.unitPriceStr = request.getParameter("unitPrice");
		this.saleNumberStr = request.getParameter("saleNumber");
		this.note = request.getParameter("note");
	}

	public SalesForm(HttpServletRequest request, String name, String categoryName) {
		this.saleDateStr = request.getParameter("saleDate");
		this.name = name;
		this.accountIdStr = request.getParameter("accountId");
		this.categoryName = categoryName;
		this.categoryIdStr = request.getParameter("categoryId");
		this.tradeName = request.getParameter("tradeName");
		this.unitPriceStr = request.getParameter("unitPrice");
		this.saleNumberStr = request.getParameter("saleNumber");
		this.note = request.getParameter("note");
	}
}
