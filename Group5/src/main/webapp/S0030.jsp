<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="ja">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>アカウント登録</title>
<link rel="stylesheet" href="css/bootstrap.min.css">
</head>
<body>
	<%@ include file="navbar.jsp"%>
	<!--	<div class="container">-->

	<!--		<div class="content">-->

	<div class="container-fluid">
		<div class="col-9 offset-3">
			<h1 class="fs-1 fw-bold mx-5 mt-4">アカウント登録</h1>


			<form action="S0030.html" method="post">
				<div class=row>
					<div class="col-offset-2 col-6">
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
					</div>
				</div>
				<div class=row>
					<div
						class="col-2 text-end my-3 d-flex align-items-center justify-content-end">
						<h5>
							<label for="name" class="form-label">氏名 <span
								class="badge bg-secondary ms-2">必須</span></label>
						</h5>
					</div>
					<div class="col-4 my-3">
						<input type="text" id="name" value="${registerAccountsform.name}"
							name="name"
							class="form-control ${not empty error ? ' is-invalid' : ''}"
							placeholder="氏名">
						<c:choose>
							<c:when test="${not empty errorQueue.name}">
								<div class="text-danger small">
									<c:out value="${errorQueue.name}" />
								</div>
							</c:when>
							<c:otherwise>
								<div class="small" style="visibility: hidden;">&nbsp;</div>
							</c:otherwise>
						</c:choose>
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
						<input type="email" id="adless"
							value="${registerAccountsform.mail}" name="mail"
							class="form-control ${not empty error ? ' is-invalid' : ''}"
							placeholder="メールアドレス">
						<c:choose>
							<c:when test="${not empty errorQueue.mail}">
								<div class="text-danger small">
									<c:out value="${errorQueue.mail}" />
								</div>
							</c:when>
							<c:otherwise>
								<div class="small" style="visibility: hidden;">&nbsp;</div>
							</c:otherwise>
						</c:choose>
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
							class="form-control ${not empty error ? ' is-invalid' : ''}"
							placeholder="パスワード">
						<c:choose>
							<c:when test="${not empty errorQueue.pass}">
								<div class="text-danger small">
									<c:out value="${errorQueue.pass}" />
								</div>
							</c:when>
							<c:otherwise>
								<div class="small" style="visibility: hidden;">&nbsp;</div>
							</c:otherwise>
						</c:choose>
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
							class="form-control ${not empty error ? ' is-invalid' : ''}"
							placeholder="パスワード（確認）">
						<c:choose>
							<c:when test="${not empty errorQueue.confirmPass}">
								<div class="text-danger small">
									<c:out value="${errorQueue.confirmPass}" />
								</div>
							</c:when>
							<c:otherwise>
								<div class="small" style="visibility: hidden;">&nbsp;</div>
							</c:otherwise>
						</c:choose>
					</div>
				</div>

				<div class="row">
					<div
						class="col-2 text-end my-3 d-flex align-items-center justify-content-end">
						<h5 class="form-label">
							権限 <span class="badge bg-secondary ms-2">必須</span>
						</h5>
					</div>

					<div class="col-4 my-3">
						<div class="form-check form-check form-check-inline">
							<input class="form-check-input" type="radio" name="authority"
								id="authority-none" value="0"
								${registerAccountsForm.authority == '0' ? 'checked' : ''}
								checked> <label class="form-check-label"
								for="authority-none">権限なし</label>
						</div>
						<div class="form-check form-check form-check-inline">
							<input class="form-check-input" type="radio" name="authority"
								id="authority-sales" value="1"
								${registerAccountsForm.authority == '1' ? 'checked' : ''}>
							<label class="form-check-label" for="authority-sales">売上登録</label>
						</div>
						<div class="form-check form-check form-check-inline">
							<input class="form-check-input" type="radio" name="authority"
								id="authority-accounts" value="2"
								${registerAccountsForm.authority == '2' ? 'checked' : ''}>
							<label class="form-check-label" for="authority-accounts">アカウント登録</label>
						</div>
						<div class="form-check form-check form-check-inline">
							<input class="form-check-input" type="radio" name="authority"
								id="authority-all" value="3"
								${registerAccountsForm.authority == '3' ? 'checked' : ''}>
							<label class="form-check-label" for="authority-all">管理者</label>
						</div>
					</div>
				</div>

				<div class="row my-2">
					<div class="col-2 offset-2 my-3">
						<button type="submit" class="btn btn-primary">✓登録</button>

					</div>
				</div>
			</form>

		</div>
		<script src="js/bootstrap.bundle.min.js"></script>
</body>
</html>
