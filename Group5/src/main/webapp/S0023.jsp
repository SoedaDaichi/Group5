<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="ja">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>S0022</title>
<link rel="stylesheet" href="css/bootstrap.min.css">
</head>
<body>
	<%@ include file="navbar.jsp"%>
	<div class="container-fluid">
		<div class="row">
			<div class="col-9 offset-3">
				<p class="fs-1 fw-bold mx-5 mt-4">売上詳細編集</p>
			</div>
		</div>

		<form id="create-task-form" action="S0023.html" method="post">
			<div class="row">
				<div class="col-9 offset-3">
					<div class="row my-4">
						<div class="col-2 text-end">
							<h5>
								<span>販売日</span>
							</h5>
						</div>
						<div class="col-3">
							<input type="date" id="date" name="saleDate" class="form-control"
								value="${not empty salesForm ? salesForm.saleDateStr : salesData.saleDate}" />
							<c:if test="${not empty errors.saleDate}">
								<div class="text-danger small">${errors.saleDate}</div>
							</c:if>
						</div>
					</div>
					<div class="row my-4">
						<div class="col-2 text-end">
							<h5>
								<span>担当</span>
							</h5>
						</div>
						<div class="col-3">
							<select class="form-select form-select-sm" name="accountId">
								<c:forEach var="account" items="${accountList}">
									<option value="${account.accountId}"
										${ (not empty salesForm ? salesForm.accountIdStr : salesData.accountId) eq account.accountId ? 'selected' : ''}>${account.name}</option>
								</c:forEach>
							</select>
						</div>
					</div>
					<div class="row my-4">
						<div class="col-2 text-end">
							<h5>
								<span>商品カテゴリー</span>
							</h5>
						</div>
						<div class="col-3">
							<select class="form-select form-select-sm" name="categoryId">
								<c:forEach var="categories" items="${categoryList}">
									<option value="${categories.categoryId}"
										${ (not empty salesForm ? salesForm.categoryIdStr : salesData.categoryId) eq categories.categoryId ? 'selected' : ''}>${categories.categoryName}</option>
								</c:forEach>
							</select>
						</div>
					</div>
					<div class="row my-4">
						<div class="col-2 text-end">
							<h5>
								<span>商品名</span>
							</h5>
						</div>
						<div class="col-3">
							<input type="text" name="tradeName" class="form-control"
								value="${not empty salesForm ? salesForm.tradeName : salesData.tradeName}" />
							<c:if test="${not empty errors.tradeName}">
								<div class="text-danger small">${errors.tradeName}</div>
							</c:if>
						</div>
					</div>
					<div class="row my-4">
						<div class="col-2 text-end">
							<h5>
								<span>単価</span>
							</h5>
						</div>
						<div class="col-3">
							<input type="text" name="unitPrice" class="form-control"
								value="${not empty salesForm ? salesForm.unitPriceStr : salesData.unitPrice}" />
							<c:if test="${not empty errors.unitPrice}">
								<div class="text-danger small">${errors.unitPrice}</div>
							</c:if>
						</div>
					</div>
					<div class="row my-4">
						<div class="col-2 text-end">
							<h5>
								<span>個数</span>
							</h5>
						</div>
						<div class="col-3">
							<input type="text" name="saleNumber" class="form-control"
								value="${not empty salesForm ? salesForm.saleNumberStr : salesData.saleNumber}" />
							<c:if test="${not empty errors.saleNumber}">
								<div class="text-danger small">${errors.saleNumber}</div>
							</c:if>
						</div>
					</div>
					<div class="row my-4">
						<div class="col-2 text-end">
							<h5>
								<span>備考</span>
							</h5>
						</div>
						<div class="col-3">
							<textarea name="note" class="form-control" rows="4">${not empty salesForm ? salesForm.note : salesData.note}</textarea>
							<c:if test="${not empty errors.note}">
								<div class="text-danger small">${errors.note}</div>
							</c:if>
						</div>
					</div>
					<div class="row">
						<div class="col-3 my-3 text-end">
							<button type="submit" name="action" value="update"
								class="btn btn-primary">✓更新</button>
							<button type="submit" name="action" value="cancel"
								class="btn btn-outline-secondary">キャンセル</button>
						</div>
					</div>
				</div>
			</div>
		</form>
	</div>
	<script src="js/bootstrap.bundle.min.js"></script>
</body>
</html>
