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
	<div class="container-fluid mx-5">
		<div class = "row">
			<div class = "col-8 offset-2">
				<p class="fs-1 fw-bold mx-5 mt-4">売上登録</p>
			</div>
		</div>
		<form id="create-sales-form" action="S0010Servlet" method="post">
			<div class="row my-3">
				<div class="col-2 offset-2 text-end">
					<h5>
						<span class>販売日</span> <span class="badge bg-secondary">必須</span>
					</h5>
				</div>
			<div class = "row my-3">				
				<div class="col-5 offset-3">
					<input type="date" id="date" name="saledate" class="form-control">
				</div>
			</div>
			<div class="row my-3">
				<div class="col-2 text-end">
					<h5>
						<span class>担当</span> <span class="badge bg-secondary">必須</span>
					</h5>
				</div>
				<div class="col-5  my-3">
					<select class="form-select form-select-sm"
						aria-label=".form-select-sm example"
							name = "account_name">
						<option selected>選択してください</option>
						<c:forEach var="account" items="${accountList}">
							<option value="${account.account_id}">${account.name}</option>
						</c:forEach>
					</select>
				</div>
			</div>
			<div class=row>
				<div class="col-2 text-end my-3">
					<h5>
						<span class>商品カテゴリー</span> <span class="badge bg-secondary">必須</span>
					</h5>
				</div>
				<div class="col-5 my-3">
					<select class="form-select form-select-sm"
						aria-label=".form-select-sm example"
							name = "category_name">
						<option selected>選択してください</option>
						<c:forEach var="account" items="${categoryList}">
							<option value="${categories.cotegory_id}">${cotegory.name}</option>
						</c:forEach>
					</select>
				</div>
			</div>
			<div class=row>
				<div class="col-2 text-end my-3">
					<h5>
						<span class>商品名</span> <span class="badge bg-secondary">必須</span>
					</h5>
				</div>
				<div class="col-5 my-3">
					<input type="text" class="form-control" 
					id="exampleFormControlInput1" name="trade" placeholder="商品名">
				</div>
			</div>
			<div class=row>
				<div class="col-2 text-end my-3">
					<h5>
						<span class>単価</span> <span class="badge bg-secondary">必須</span>
					</h5>
				</div>
				<div class="col-2 my-3">
					<input type="text" class="form-control" id="exampleFormControlInput1" name="unit_price" placeholder="単価">
				</div>
			</div>
			<div class=row>
				<div class="col-2 text-end my-3">
					<h5>
						<span class>個数</span> <span class="badge bg-secondary">必須</span>
					</h5>
				</div>
				<div class="col-2 my-3">
					<input type="text" class="form-control" id="exampleFormControlInput1" name="sale_num" placeholder="個数">
				</div>
			</div>
			<div class=row>
				<div class="col-2 text-end my-3">
					<h5>
						<span class>備考</span>
					</h5>
				</div>
				<div class="col-8 my-3">
				<textarea class="form-control" id="exampleFormControlTextarea1" name="note" rows="3" placeholder="備考"></textarea>
				</div>
			</div>
			<div class="row my-5" >
				<div class="col-2 offset-2 my-3">
					<button type="submit" class="btn btn-primary">✓登録</button>
		</form>
		</div>
	</body>
</html>