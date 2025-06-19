<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="ja">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>S0021</title>
<link rel="stylesheet" href="css/bootstrap.min.css">
</head>
<body>
	<%@ include file="navbar.jsp"%>

	<div class="container-fluid mt-2">
		<div class="row">
			<div class="col-9 offset-3">
				<h1 class="mb-4">売上検索結果表示</h1>
			</div>
		</div>

		<div class="row mx-2">
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
			<c:choose>
				<c:when test="${not empty error}">
					<div class="alert alert-danger text-center py-1">
						<c:out value="${error}" />
					</div>
				</c:when>
				<c:otherwise>
					<div class="alert alert-danger text-center
									py-1"
						style="visibility: hidden;">&nbsp;</div>
				</c:otherwise>
			</c:choose>
			<table class="table table-bordered table-hover">
				<thead class="table-light">
					<tr>
						<th class="col-1">操作</th>
						<th class="col-1">No.</th>
						<th class="col-1">販売日</th>
						<th class="col-1">担当</th>
						<th class="col-2">商品カテゴリー</th>
						<th class="col-3">商品名</th>
						<th class="col-1">単価</th>
						<th class="col-1">個数</th>
						<th class="col-1">小計</th>
					</tr>

				</thead>
				<tbody>
					<c:forEach var="sales" items="${salesList}" varStatus="status">
						<tr>
							<td><form action="S0021.html" method="post">
									<input type="hidden" name="sale_id" value="${sales.sale_id}">
									<button type="submit" class="btn btn-sm btn-primary">詳細</button>
								</form></td>
							<td>${sales.sale_id}</td>
							<td>${sales.sale_date}</td>
							<td>${sales.name}</td>
							<td>${sales.category_name}</td>
							<td>${sales.trade_name}</td>
							<td>${sales.unit_price}</td>
							<td>${sales.sale_number}</td>
							<td>${sales.price_all}</td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
		</div>
	</div>
	<script src="js/bootstrap.bundle.min.js"></script>
</body>
</html>