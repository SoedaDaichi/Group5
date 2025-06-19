<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html lang="ja">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>アカウント詳細削除確認</title>
<link rel="stylesheet" href="css/bootstrap.min.css">
</head>
<body>
	<%@ include file="navbar.jsp"%>
	<div class="container-fluid">
		<div class="col-9 offset-3">
			<h1 class="fs-1 fw-bold mx-5 mt-4">アカウント詳細削除確認</h1>

			<form id="create-task-form" action="S0044.html" method="post">
				<input type="hidden" name="id" value="${account_id}" />

				<div class="row">
					<div
						class="col-2 text-end my-3 d-flex align-items-center justify-content-end">
						<h5>
							<span class>氏名</span>
						</h5>
					</div>
					<div class="col-4 my-3">
						<input type="text" id="name" name="name" class="form-control"
							value="${accounts.name}" readonly>
					</div>
				</div>

				<div class="row">
					<div
						class="col-2 text-end my-3 d-flex align-items-center justify-content-end">
						<h5>
							<span class>メールアドレス</span>
						</h5>
					</div>
					<div class="col-4 my-3">
						<input type="email" id="mail" name="mail" class="form-control"
							value="${accounts.mail}" readonly>
					</div>
				</div>

				<div class="row">
					<div
						class="col-2 text-end my-3 d-flex align-items-center justify-content-end">
						<h5>
							<span class>パスワード</span>
						</h5>
					</div>
					<div class="col-4 my-3">
						<input type="password" id="password" name="pass"
							class="form-control" value="${accounts.pass}" readonly>
					</div>
				</div>

				<div class="row">
					<div
						class="col-2 text-end my-3 d-flex align-items-center justify-content-end">
						<h5>
							<span class>パスワード(確認)</span>
						</h5>
					</div>
					<div class="col-4 my-3">
						<input type="password" id="confirm_password" name="confirm_pass"
							class="form-control" value="${accounts.pass}" readonly>
					</div>
				</div>

				<div class="row">
					<div
						class="col-2 text-end my-3 d-flex align-items-center justify-content-end">
						<h5 class="form-label">
							<span>権限</span>
						</h5>
					</div>
					<div class="col-4 my-3">
						<div class="form-check form-check-inline">
							<input class="form-check-input" type="radio" name="authority"
								id="authority-none" value="0"
								<c:if test="${accounts.authority eq 0}">checked</c:if> disabled>
							<label class="form-check-label" for="authority-none">権限なし</label>
						</div>
						<div class="form-check form-check-inline">
							<input class="form-check-input" type="radio" name="authority"
								id="authority-sales" value="1"
								<c:if test="${accounts.authority eq 1}">checked</c:if> disabled>
							<label class="form-check-label" for="authority-sales">売上登録</label>
						</div>
						<div class="form-check form-check-inline">
							<input class="form-check-input" type="radio" name="authority"
								id="authority-accounts" value="2"
								<c:if test="${accounts.authority eq 2}">checked</c:if> disabled>
							<label class="form-check-label" for="authority-accounts">アカウント登録</label>
						</div>
						<div class="form-check form-check-inline">
							<input class="form-check-input" type="radio" name="authority"
								id="authority-all" value="3"
								<c:if test="${accounts.authority eq 3}">checked</c:if> disabled>
							<label class="form-check-label" for="authority-all">管理者</label>
						</div>
						<input type="hidden" name="authority"
							value="${accounts.authority}">
					</div>
				</div>

				<div class="row my-2">
					<div class="col-2 offset-2 my-3">
						<!-- OKボタン（削除） -->
						<button type="submit" name="action" value="delete"
							class="btn btn-danger">×OK</button>

						<!-- キャンセルボタン（一覧画面に戻る） -->
						<button type="submit" name="action" value="cancel"
							class="btn btn-outline-secondary">キャンセル</button>
					</div>
				</div>
			</form>
		</div>
	</div>
	<script src="js/bootstrap.bundle.min.js"></script>
</body>
</html>
