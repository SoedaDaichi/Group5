<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<title>Login</title>
<link rel="stylesheet" href="css/bootstrap.min.css" />
</head>
<body>
	<form action="LoginServlet" method="post">
		<br>
		<div class="row my-4">
			<div class="col-sm-4 offset-sm-4">
				<div class="row my-3 px-3">メールアドレス</div>
				<div class="row my-3 px-2">
					<input type="text" class="form-control" placeholder="メールアドレス"
						name="name" required />
				</div>
				<div class="row my-3 px-3">パスワード</div>
				<div class="row my-3 px-2">
					<input type="password" class="form-control" placeholder="パスワード"
						name="pass" required />
				</div>
				<div class="row my-3 px-2 "
					<div class="d-grid gap-2 d-md-flex justify-content-md-end">
							<button type="submit" class="btn btn-primary btn-lg">ログイン</button>
						</div>
					<div class="form-check my-4">
						<input class="form-check-input" type="checkbox" id="keepLoggedIn">
						<label class="form-check-label" for="keepLoggedIn">
							ログイン状態を維持する </label>
					</div></div>
			</div>
		</div>
	</form>
	<div class="row">
		<p>
			<a href="#" trget="blank">パスワードをお忘れの方</a>
		</p>
		<!-- blankは選択したら別タグが開く -->
	</div>
	<div class="row">
		<p>
			<a href="NewUser.jsp" trget="blank">新規登録（無料）</a>
		</p>
	</div>
	</div>
	</div>
</body>
</html>

