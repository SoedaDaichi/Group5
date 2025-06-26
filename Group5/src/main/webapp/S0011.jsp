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
			<p class="fs-1 fw-bold mx-5 mt-4">売上登録確認</p>

			<form id="create-sales-form" action="S0011.html" method="post">
				<div class=row>
					<div class="col-2 text-end my-3">
						<h5>
							<span class>販売日</span> <span class="badge bg-secondary">必須</span>
						</h5>
					</div>
					<div class="col-2 my-3">
						<input type="date" name="saleNate" class="form-control"
							value="${registerSalesData.saleDate}" disabled>
					</div>
				</div>
				<div class=row>
					<div class="col-2 text-end my-3">
						<h5>
							<span class>担当</span> <span class="badge bg-secondary">必須</span>
						</h5>
					</div>
					<div class="col-2  my-3">
						<class = input type="text" class="form-control"
							 name="accountName"
							value="${registerSalesData.accountId}" disabled>${registerSalesData.name}</class>
					</div>
				</div>
				<div class=row>
					<div class="col-2 text-end my-3">
						<h5>
							<span class>商品カテゴリー</span> <span class="badge bg-secondary">必須</span>
						</h5>
					</div>
					<div class="col-4 my-3">
						<class = input type="text" class="form-control"
							 name="categoryName" var="category"
							value="${registerSalesData.categoryId}" disabled>${registerSalesData.categoryName}</class>
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
							 name="tradeName"
							value="${registerSalesData.tradeName}" disabled>
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
							 name="unitPrice" placeholder="単価"
							value="${registerSalesData.unitPrice}" disabled>
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
							name="saleNumber" placeholder="個数"
							value="${registerSalesData.saleNumber}" disabled>
					</div>
				</div>
				<div class=row>
					<div class="col-2 text-end my-3">
						<h5>
							<span class>備考</span>
						</h5>
					</div>
					<div class="col-5 my-3">
						<textarea class="form-control" 
							name="note" rows="5" placeholder="備考" disabled>${registerSalesData.note}</textarea>
					</div>
				</div>
				<div class="row my-5">
					<div class="col-3 text-end">
						<button type="submit" name = "action" value = "insert" class="btn btn-primary">✓登録</button>
						<button type="submit" name = "action" value = "cancel" class="btn btn-outline-secondary">キャンセル</a>
					</div>
				</div>
			</form>
		</div>
	</div>
	<script src="js/bootstrap.bundle.min.js"></script>
</body>
</html>