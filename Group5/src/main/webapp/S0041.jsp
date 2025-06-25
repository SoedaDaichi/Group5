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

		<h1 class="fs-1 fw-bold mx-5 mt-4">アカウント検索結果表示</h1>

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
					<c:if
						test="${sessionScope.loginAccount.authority == 2 || sessionScope.loginAccount.authority == 3}">
						<th>操作</th>
					</c:if>
					<th>No.</th>
					<th>氏名</th>
					<th>メールアドレス</th>
					<th>権限</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach var="accounts" items="${accountsList}" varStatus="status">
					<tr>
						<c:if
							test="${sessionScope.loginAccount.authority == 2 || sessionScope.loginAccount.authority == 3}">
							<td>
								<form action="S0041.html" method="post">
									<input type="hidden" name="accountId"
										value="${accounts.accountId}">
									<button type="submit" name="action" value="edit"
										class="btn btn-sm btn-primary">編集</button>
									<button type="submit" name="action" value="delete"
										class="btn btn-sm btn-danger">削除</button>
								</form>
							</td>
						</c:if>
						<td>${accounts.accountId}</td>
						<td>${accounts.name}</td>
						<td>${accounts.mail}</td>
						<td><c:choose>
								<c:when test="${accounts.authority == '0'}">権限なし</c:when>
								<c:when test="${accounts.authority == '1'}">売上登録</c:when>
								<c:when test="${accounts.authority == '2'}">アカウント登録</c:when>
								<c:when test="${accounts.authority == '3'}">管理者</c:when>
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
