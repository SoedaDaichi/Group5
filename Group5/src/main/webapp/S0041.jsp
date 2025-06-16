<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="ja">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>アカウント検索結果表示</title>
<link rel="stylesheet" href="css/bootstrap.min.css">
</head>
<body>
	<%@ include file="navbar.jsp"%>

		<div class="container mt-5">
<!--	<div class="container-fluid mx-5">-->
<!--		<div class="col-10 offset-3">-->
			<h1 class="fs-1 fw-bold mx-5 mt-4">アカウント検索結果表示</h1>


			<table class="table table-bordered table-hover">
				<thead class="table-light">
					<tr>
						<th>操作</th>
						<th>No.</th>
						<th>氏名</th>
						<th>メールアドレス</th>
						<th>権限</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach var="account" items="${accountList}" varStatus="status">
						<tr>
							<td><a href="S0042.html?id=${account.account_id}"
								class="btn btn-sm btn-primary">編集</a> <a
								href="S0044.html?id=${account.account_id}"
								class="btn btn-sm btn-danger">削除</a></td>
							<td>${account.account_id}</td>
							<td>${account.name}</td>
							<td>${account.mail}</td>
							<td><c:choose>
									<c:when test="${account.authority == '0'}">権限なし</c:when>
									<c:when test="${account.authority == '1'}">売上登録</c:when>
									<c:when test="${account.authority == '2'}">アカウント登録</c:when>
									<c:when test="${account.authority == '3'}">管理者</c:when>
								</c:choose></td>
						</tr>
					</c:forEach>
				</tbody>
			</table>


		</div>
	</div>
	<script src="js/bootstrap.bundle.min.js"></script>
</body>
</html>
