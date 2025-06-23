<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>S0010</title>
<link rel="stylesheet" href="css/bootstrap.min.css" />
</head>
<body>
	<%@ include file="navbar.jsp"%>
	<div class="container-fluid">
		<div class="col-9 offset-3">
			<p class="fs-1 fw-bold mx-5 mt-4">売上登録</p>

			<div class="row my-3">
				<div class=col-8>
					<c:choose>
						<c:when test="${not empty success}">
							<div class="alert alert-success text-center py-1">
								<c:out value="${success}" />
							</div>
						</c:when>
						<c:otherwise>
							<div class="alert alert-success text-center
									py-1"
								style="visibility: hidden;">&nbsp;</div>
						</c:otherwise>
					</c:choose>
				</div>
			</div>

			<form action="S0010.html" method="post">
				<div class=row>
					<div class="col-2 text-end my-3">
						<h5>
							<span class>販売日</span> <span class="badge bg-secondary">必須</span>
						</h5>
					</div>
					<div class="col-2 my-3">
						<input type="date" name="sale_date" class="form-control"

							value="${RegisterSalesForm.saleDate}">

						<c:choose>
							<c:when test="${not empty errors.saleDate}">
								<div class="text-danger small">
									<c:out value="${errors.saleDate}" />
								</div>
							</c:when>
							<c:otherwise>
								<div class="small" style="visibility: hidden;">&nbsp;</div>
							</c:otherwise>
						</c:choose>
					</div>
				</div>
				<div class=row>
					<div class="col-2 text-end my-3">
						<h5>
							<span class>担当</span> <span class="badge bg-secondary">必須</span>
						</h5>
					</div>
					<div class="col-2  my-3">
						<select class="form-select form-select-sm"
							aria-label=".form-select-sm example" name="accountId">
							<option value=""

								${empty RegisterSalesForm.accountId ? 'selected' : ''}>選択してください</option>
							<c:forEach var="account" items="${accountList}">
								<option value="${account.accountId}"
									${RegisterSalesForm.accountId eq account.accountId ? 'selected' : ''}>${account.name}</option>

							</c:forEach>
						</select>
						<c:choose>
							<c:when test="${not empty errors.accountId}">
								<div class="text-danger small">
									<c:out value="${errors.accountId}" />
								</div>
							</c:when>
							<c:otherwise>
								<div class="small" style="visibility: hidden;">&nbsp;</div>
							</c:otherwise>
						</c:choose>
					</div>
				</div>
				<div class=row>
					<div class="col-2 text-end my-3">
						<h5>
							<span class>商品カテゴリー</span> <span class="badge bg-secondary">必須</span>
						</h5>
					</div>
					<div class="col-4 my-3">
						<select class="form-select form-select-sm"
							aria-label=".form-select-sm example" name="category_id">
							<option value=""

								${empty RegisterSalesForm.categoryId ? 'selected' : ''}>選択してください</option>
							<c:forEach var="category" items="${categoryList}">
								<option value="${category.categoryId}"
									${RegisterSalesForm.categoryId eq category.categoryId ? 'selected' : ''}>${category.categoryName}</option>

							</c:forEach>
						</select>
						<c:choose>
							<c:when test="${not empty errors.categoryId}">
								<div class="text-danger small">
									<c:out value="${errors.categoryId}" />
								</div>
							</c:when>
							<c:otherwise>
								<div class="small" style="visibility: hidden;">&nbsp;</div>
							</c:otherwise>
						</c:choose>
					</div>
				</div>
				<div class=row>
					<div class="col-2 text-end my-3">
						<h5>
							<span class>商品名</span> <span class="badge bg-secondary">必須</span>
						</h5>
					</div>
					<div class="col-4 my-3">
						<input type="text" class="form-control"
							id="exampleFormControlInput1" name="trade_name"

							value="${RegisterSalesForm.tradeName}" placeholder="商品名">

						<c:choose>
							<c:when test="${not empty errors.tradeName}">
								<div class="text-danger small">
									<c:out value="${errors.tradeName}" />
								</div>
							</c:when>
							<c:otherwise>
								<div class="small" style="visibility: hidden;">&nbsp;</div>
							</c:otherwise>
						</c:choose>
					</div>
				</div>
				<div class=row>
					<div class="col-2 text-end my-3">
						<h5>
							<span class>単価</span> <span class="badge bg-secondary">必須</span>
						</h5>
					</div>
					<div class="col-2 my-3">
						<input type="text" class="form-control"
							id="exampleFormControlInput1" name="unit_price"

							value="${RegisterSalesForm.unitPrice}" placeholder="単価">

						<c:choose>
							<c:when test="${not empty errors.unitPrice}">
								<div class="text-danger small">
									<c:out value="${errors.unitPrice}" />
								</div>
							</c:when>
							<c:otherwise>
								<div class="small" style="visibility: hidden;">&nbsp;</div>
							</c:otherwise>
						</c:choose>
					</div>
				</div>
				<div class=row>
					<div class="col-2 text-end my-3">
						<h5>
							<span class>個数</span> <span class="badge bg-secondary">必須</span>
						</h5>
					</div>
					<div class="col-2 my-3">
						<input type="text" class="form-control"
							id="exampleFormControlInput1" name="sale_number"

							value="${RegisterSalesForm.saleNumber}" placeholder="個数">

						<c:choose>
							<c:when test="${not empty errors.saleNumber}">
								<div class="text-danger small">
									<c:out value="${errors.saleNumber}" />
								</div>
							</c:when>
							<c:otherwise>
								<div class="small" style="visibility: hidden;">&nbsp;</div>
							</c:otherwise>
						</c:choose>
					</div>
				</div>
				<div class=row>
					<div class="col-2 text-end my-3">
						<h5>
							<span class>備考</span>
						</h5>
					</div>
					<div class="col-5 my-3">
						<textarea class="form-control" id="exampleFormControlTextarea1"

							name="note" rows="5" placeholder="備考">${RegisterSalesForm.note}</textarea>

						<c:choose>
							<c:when test="${not empty errors.note}">
								<div class="text-danger small">
									<c:out value="${errors.note}" />
								</div>
							</c:when>
							<c:otherwise>
								<div class="small" style="visibility: hidden;">&nbsp;</div>
							</c:otherwise>
						</c:choose>
					</div>
				</div>
				<div class="row my-5">
					<div class="col-2 offset-2 my-3">
						<input type="submit" class="btn btn-primary" value=✓OK></input>
			</form>
		</div>
	</div>
	<script src="js/bootstrap.bundle.min.js"></script>
</body>
</html>