<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="ja">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>アカウント登録確認</title>
<link rel="stylesheet" href="css/bootstrap.min.css">
</head>
<body>
	<%@ include file="navbar.jsp"%>
	<div class="container-fluid">
		<div class="col-9 offset-3">
			<h1 class="fs-1 fw-bold mx-5 mt-4">アカウントを登録してよろしいですか？</h1>
			<div class="mb-5"></div>
			<form action="S0031.html" method="post">

				<div class=row>
					<div
						class="col-2 text-end my-3 d-flex align-items-center justify-content-end">

						<h5>
							<label for="name" class="form-label">氏名 <span
								class="badge bg-secondary ms-2">必須</span></label>
						</h5>
					</div>
					<div class="col-4 my-3">
						<input type="text" name="name" class="form-control"
							value="${registerAccountsData.name}" disabled>
					</div>
				</div>

				<div class=row>
					<div
						class="col-2 text-end my-3 d-flex align-items-center justify-content-end">
						<h5>
							<label for="adless" class="form-label">メールアドレス <span
								class="badge bg-secondary ms-2">必須</span></label>
						</h5>
					</div>
					<div class="col-4 my-3">
						<input type="email" name="mail" class="form-control"
							value="${registerAccountsData.mail}" disabled>
					</div>
				</div>


				<div class=row>
					<div
						class="col-2 text-end my-3 d-flex align-items-center justify-content-end">
						<h5>
							<label for="adless" class="form-label">パスワード<span
								class="badge bg-secondary ms-2">必須</span></label>
						</h5>
					</div>
					<div class="col-4 my-3">
						<input type="password" name="pass"
							class="form-control" value="${registerAccountsData.pass}"
							disabled>
					</div>
				</div>

				<div class=row>
					<div
						class="col-2 text-end my-3 d-flex align-items-center justify-content-end">
						<h5>
							<span class>パスワード(確認)</span> <span class="badge bg-secondary">必須</span>
						</h5>
					</div>
					<div class="col-4 my-3">
						<input type="password" name="confirmPass"
							class="form-control"
							value="${registerAccountsData.confirmPass}" disabled>
					</div>
				</div>

				<div class="row">
					<div
						class="col-2 text-end my-3 d-flex align-items-center justify-content-end">
						<h5 class="mb-0">
							<span>権限</span> <span class="badge bg-secondary ms-2">必須</span>
						</h5>
					</div>
					<div class="col-10 my-3">
						<div class="form-check form-check-inline">
							<input class="form-check-input" type="radio" name="authority" value="0"
								<c:if test="${registerAccountsData.authority eq '0'}">checked</c:if>
								disabled> <label class="form-check-label"
								for="authority-none">権限なし</label>
						</div>
						<div class="form-check form-check-inline">
							<input class="form-check-input" type="radio" name="authority" value="1"
								<c:if test="${registerAccountsData.authority eq '1'}">checked</c:if>
								disabled> <label class="form-check-label"
								for="authority-sales">売上登録</label>
						</div>
						<div class="form-check form-check-inline">
							<input class="form-check-input" type="radio" name="authority" value="2"
								<c:if test="${registerAccountsData.authority eq '2'}">checked</c:if>
								disabled> <label class="form-check-label"
								for="authority-accounts">アカウント登録</label>
						</div>
						<div class="form-check form-check-inline">
							<input class="form-check-input" type="radio" name="authority" value="3"
								<c:if test="${registerAccountsData.authority eq '3'}">checked</c:if>
								disabled> <label class="form-check-label"
								for="authority-all">管理者</label>
						</div>
						<input type="hidden" name="authority"
							value="${registerAccountsData.authority}">
					</div>
				</div>

				<div class="row my-2">
					<div class="col-2 offset-2 my-3">
						<button type="submit" name = "action" value = "insert" class="btn btn-primary">OK</button>
						<button type="submit" name = "action" value = "cancel" class="btn btn-outline-secondary">キャンセル</botton>
					</div>
				</div>
			</form>
		</div>
	</div>
	<script src="js/bootstrap.bundle.min.js"></script>
</body>
</html>
