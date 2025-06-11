<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
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
	<div class="container">

		<div class="content">
			<h1>アカウント登録</h1>


			<form id="create-task-form" action="S0030Servlet" method="post">
				<c:if test="${not empty error}">
					<div class="alert alert-danger text-center py-2">
						<c:out value="${error}" />
					</div>
				</c:if>
				<div class="mt-3">
					<label for="name" class="form-label">氏名 <span
						class="badge bg-secondary">必須</span>
					</label> <input type="text" required="required" id="name" name="name"
						class="form-control ${not empty error ? ' is-invalid' : ''} my-3 px-2"
						placeholder="氏名" required>
				</div>

				<div class="mt-3">
					<label for="adless" class="form-label">メールアドレス <span
						class="badge bg-secondary">必須</span>
					</label> <input type="email" required="required" id="adless" name="mail"
						class="form-control ${not empty error ? ' is-invalid' : ''} my-3 px-2"
						placeholder="メールアドレス" required>
				</div>

				<div class="mt-3">
					<label for="password" class="form-label">パスワード <span
						class="badge bg-secondary">必須</span>
					</label> <input type="password" required="required" id="password"
						name="pass"
						class="form-control ${not empty error ? ' is-invalid' : ''} my-3 px-2"
						placeholder="パスワード" required>
				</div>

				<div class="mt-3">
					<label for="confirm-password" class="form-label">パスワード（確認）
						<span class="badge bg-secondary">必須</span>
					</label> <input type="password" required="required" id="confirm_password"
						name="confirm_pass"
						class="form-control ${not empty error ? ' is-invalid' : ''} my-3 px-2"
						placeholder="パスワード（確認）" required>
				</div>


				<div class="mt-3">
					<label class="form-label">権限 <span
						class="badge bg-secondary">必須</span>
					</label>
					<div class="form-check">
						<input class="form-check-input" type="radio" name="role"
							id="role-none" value="0" required> <label
							class="form-check-label" for="role-none">権限なし</label>
					</div>
					<div class="form-check">
						<input class="form-check-input" type="radio" name="role"
							id="role-read" value="1"> <label class="form-check-label"
							for="role-read">売上登録</label>
					</div>
					<div class="form-check">
						<input class="form-check-input" type="radio" name="role"
							id="role-update" value="2"> <label
							class="form-check-label" for="role-update">アカウント登録</label>
					</div>
				</div>

				<button type="submit" class="btn btn-primary">登録</button>
			</form>
		</div>
	</div>
	<script src="js/bootstrap.bundle.min.js"></script>
</body>
</html>
