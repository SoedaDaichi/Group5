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
	<div class="container-fluid mx-5">
		<div class="row">
			<div class="col-10 offset-3">
				<p class="fs-1 fw-bold mx-5 mt-4">売上詳細表示</p>
			</div>
		</div>

		<div class="row">
			<div class="col-10 offset-3">
				<div class="row my-4">
					<div class="col-2 text-end">
						<h5>
							<span class>販売日</span>
						</h5>
					</div>
					<div class="col-3">
						<h5>${salesdata.sale_date}</h5>
					</div>
				</div>
				<div class="row my-4">
					<div class="col-2 text-end">
						<h5>担当</h5>
					</div>
					<div class="col-3">
						<h5><c:out value="${salesdata.name}" /></h5>
					</div>
				</div>
				<div class="row my-4">
					<div class="col-2 text-end">
						<h5>商品カテゴリー</h5>
					</div>
					<div class="col-3">
						<h5>${salesdata.category_name}</h5>
					</div>
				</div>
				<div class="row my-4">
					<div class="col-2 text-end">
						<h5>商品名</h5>
					</div>
					<div class="col-3">
						<h5>${salesdata.trade_name}</h5>
					</div>
				</div>
				<div class="row my-4">
					<div class="col-2 text-end">
						<h5>単価</h5>
					</div>
					<div class="col-3">
						<h5>${salesdata.unit_price}</h5>
					</div>
				</div>
				<div class="row my-4">
					<div class="col-2 text-end">
						<h5>個数</h5>
					</div>
					<div class="col-3">
						<h5>${salesdata.sale_number}</h5>
					</div>
				</div>
				<div class="row my-4">
					<div class="col-2 text-end">
						<h5>備考</h5>
					</div>
					<div class="col-3">
						<h5>${salesdata.note}</h5>
					</div>
				</div>
				<div class="row">
					<div class="col-3 my-3 text-end">
						
						<form method="get" action="S0023.html" class="d-inline">
							<button type="submit" class="btn btn-primary">✓編集</button>
						</form>

					
						<form method="get" action="S0025.html" class="d-inline">
							<button type="submit" class="btn btn-danger">×削除</button>
						</form>
						
						<a href="#" button type="submit" class="btn btn-outline-secondary">
							キャンセル </a>
					</div>
				</div>
			</div>
		</div>
</body>
</html>