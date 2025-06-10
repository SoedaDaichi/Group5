<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
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
	<div class="container">

		<div class="content">
			<h1>アカウント詳細削除確認</h1>

			<%
				String role = request.getParameter("role");
			%>

			<form id="create-task-form" action="S0044Servlet" method="post">

				<div class="mt-3">
					<label for="name" class="form-label">氏名 <span
						class="required-badge">必須</span>
					</label> <input type="text" id="name" name="name"
						class="form-control my-3 px-2"
						value="<%=request.getParameter("name")%>" readonly>
				</div>

				<div class="mt-3">
					<label for="adless" class="form-label">メールアドレス <span
						class="required-badge">必須</span>
					</label> <input type="email" id="adless" name="mail"
						class="form-control my-3 px-2"
						value="<%=request.getParameter("mail")%>" readonly>
				</div>

				<div class="mt-3">
					<label for="password" class="form-label">パスワード <span
						class="required-badge">必須</span>
					</label> <input type="password" id="password" name="pass"
						class="form-control my-3 px-2"
						value="<%=request.getParameter("pass")%>" readonly>
				</div>

				<div class="mt-3">
					<label for="confirm-password" class="form-label">パスワード（確認）
						<span class="required-badge">必須</span>
					</label> <input type="password" id="confirm_password" name="confirm_pass"
						class="form-control my-3 px-2"
						value="<%=request.getParameter("confirm_pass")%>" readonly>
				</div>

				<div class="mt-3">
					<label class="form-label">権限</label>
					<div class="form-check">
						<input class="form-check-input" type="radio" name="role"
							id="role-none" value="none"
							<%= "none".equals(role) ? "checked" : "" %> disabled> <label
							class="form-check-label" for="role-none">権限なし</label>
					</div>
					<div class="form-check">
						<input class="form-check-input" type="radio" name="role"
							id="role-read" value="read"
							<%= "read".equals(role) ? "checked" : "" %> disabled> <label
							class="form-check-label" for="role-read">売上登録</label>
					</div>
					<div class="form-check">
						<input class="form-check-input" type="radio" name="role"
							id="role-update" value="update"
							<%= "update".equals(role) ? "checked" : "" %> disabled> <label
							class="form-check-label" for="role-update">アカウント登録</label>
					</div>
					<input type="hidden" name="role" value="<%=role%>">
				</div>

				<button type="submit" class="btn btn-primary">OK</button>
				<button type="submit" class="btn btn-primary">キャンセル</button>
			</form>
		</div>
	</div>

</body>
</html>
