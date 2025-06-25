<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html lang="ja">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>アカウント詳細編集確認</title>
<link rel="stylesheet" href="css/bootstrap.min.css">
</head>
<body>
	<%@ include file="navbar.jsp"%>
	<div class="container-fluid">
		<div class="col-9 offset-3">
			<h1 class="fs-1 fw-bold mx-5 mt-4">アカウント詳細編集確認</h1>
			<form id="create-task-form" action="S0043.html" method="post">
				<input type="hidden" name="accountId" value="${accountId}">
				<div class=row>
					<div
						class="col-2 text-end my-3 d-flex align-items-center justify-content-end">
						<h5>
							<span>氏名</span> <span class="badge bg-secondary">必須</span>
						</h5>
					</div>
					<div class="col-4 my-3">
						<input type="text" name="name" class="form-control"
							value="${accountsData.name}" disabled>
					</div>
				</div>
				<div class=row>
					<div
						class="col-2 text-end my-3 d-flex align-items-center justify-content-end">
						<h5>
							<span>メールアドレス</span> <span class="badge bg-secondary">必須</span>
						</h5>
					</div>
					<div class="col-4 my-3">
						<input type="email" name="mail" class="form-control"
							value="${accountsData.mail}" disabled>
					</div>
				</div>

				<div class=row>
					<div
						class="col-2 text-end my-3 d-flex align-items-center justify-content-end">
						<h5>
							<span>パスワード</span> <span class="badge bg-secondary">必須</span>
						</h5>
					</div>
					<div class="col-4 my-3">
						<input type="password" name="pass" class="form-control"
							value="${accountsData.pass}" disabled>
					</div>
				</div>

				<div class=row>
					<div
						class="col-2 text-end my-3 d-flex align-items-center justify-content-end">
						<h5>
							<span>パスワード(確認)</span> <span class="badge bg-secondary">必須</span>
						</h5>
					</div>
					<div class="col-4 my-3">
						<input type="password" name="confirmPass" class="form-control"
							value="${accountsData.confirmPass}" disabled>
					</div>
				</div>

				<div class="row">
					<div
						class="col-2 text-end my-3 d-flex align-items-center justify-content-end">
						<h5 class="mb-0">
							<span>権限</span> <span class="badge bg-secondary ms-2">必須</span>
						</h5>
					</div>
					<div class="col-4 my-3">
						<div class="form-check form-check-inline">
							<input class="form-check-input" type="radio" name="authority"
								value="0"
								<c:if test="${accountsData.authority eq '0'}">checked</c:if>
								disabled> <label class="form-check-label"
								for="authority-none">権限なし</label>
						</div>
						<div class="form-check form-check-inline">
							<input class="form-check-input" type="radio" name="authority"
								value="1"
								<c:if test="${accountsData.authority eq '1'}">checked</c:if>
								disabled> <label class="form-check-label"
								for="authority-sales">売上登録</label>
						</div>
						<div class="form-check form-check-inline">
							<input class="form-check-input" type="radio" name="authority"
								value="2"
								<c:if test="${accountsData.authority eq '2'}">checked</c:if>
								disabled> <label class="form-check-label"
								for="authority-accounts">アカウント登録</label>
						</div>
						<div class="form-check form-check-inline">
							<input class="form-check-input" type="radio" name="authority"
								value="3"
								<c:if test="${accountsData.authority eq '3'}">checked</c:if>
								disabled> <label class="form-check-label"
								for="authority-all">管理者</label>
						</div>
						<input type="hidden" name="authority"
							value="${accountsData.authority}">
					</div>
				</div>
				<div class="row my-2">
					<div class="col-2 offset-2 my-3">
						<button type="submit" class="btn btn-primary">✓OK</button>
						<!--						<button type="button" class="btn btn-outline-secondary">キャンセル</button>-->
						<button type="button" class="btn btn-outline-secondary"
							onclick="history.back()">キャンセル</button>
					</div>
				</div>
			</form>
		</div>
	</div>
	<script src="js/bootstrap.bundle.min.js"></script>
</body>
</html>
