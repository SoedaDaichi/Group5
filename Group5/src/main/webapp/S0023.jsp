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

		<c:set var="formData"
			value="${not empty salesform ? salesform : salesdata}" />

		<form action="S0023.html" method="post">
			<div class="row">
				<div class="col-9 offset-3">
					<div class="row my-4">
						<div class="col-2 text-end">
							<h5>
								<span>販売日</span>
							</h5>
						</div>
						<div class="col-3">
							<input type="date"name="sale_date"
								class="form-control" value="${formData.sale_date}" />
							<c:if test="${not empty errors.sale_date}">
								<div class="text-danger small">${errors.sale_date}</div>
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
							<select class="form-select form-select-sm" name="account_id">
								<c:forEach var="account" items="${accountList}">
									<option value="${account.account_id}"
										${formData.account_id eq account.account_id ? 'selected' : ''}>${account.name}</option>
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
							<select class="form-select form-select-sm" name="category_id">
								<c:forEach var="categories" items="${categoryList}">
									<option value="${categories.category_id}"
										${formData.category_id eq categories.category_id ? 'selected' : ''}>${categories.category_name}</option>
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
							<input type="text" name="trade_name" class="form-control"
								value="${formData.trade_name}" />
							<c:if test="${not empty errors.trade_name}">
								<div class="text-danger small">${errors.trade_name}</div>
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
							<input type="text" name="unit_price" class="form-control"
								value="${formData.unit_price}" />
							<c:if test="${not empty errors.unit_price}">
								<div class="text-danger small">${errors.unit_price}</div>
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
							<input type="text" name="sale_number" class="form-control"
								value="${formData.sale_number}" />
							<c:if test="${not empty errors.sale_number}">
								<div class="text-danger small">${errors.sale_number}</div>
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
							<textarea name="note" class="form-control" rows="4">${formData.note}</textarea>
						</div>
					</div>
					<div class="row">
						<div class="col-3 my-3 text-end">
							<button type="submit" class="btn btn-primary">✓更新</button>
							<a href="S0021.html" class="btn btn-outline-secondary">キャンセル</a>
						</div>
					</div>
				</div>
			</div>
		</form>
	</div>
	<script src="js/bootstrap.bundle.min.js"></script>
</body>
</html>
