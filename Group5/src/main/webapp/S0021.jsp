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
		<div class = "row">
			<div class = "col-10 offset-3">
				<h1 class="mb-4">売上検索結果表示</h1>
			</div>
		</div>
		
		<div class="row mx-2">
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
					</tr>
				</thead>
				<tbody>
					<c:forEach var="account" items="${salesList}" varStatus="status">
						<tr>
							<td><a href="EditServlet?id=${sales.sale_id}"
								class="btn btn-sm btn-primary">詳細</a> 
							</td>
							<td>${status.index + 1}</td>
							<td>${sales.}</td>
							<td>${sales.}</td>
							<td>${sales.}</td>
							<td>${sales.}</td>
							<td>${sales.}</td>
							<td>${sales.}</td>
							<td>${sales.}</td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
		</div>
	</div>
</body>
</html>