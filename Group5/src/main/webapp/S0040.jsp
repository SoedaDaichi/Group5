<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="ja">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>アカウント検索</title>
<link rel="stylesheet" href="css/bootstrap.min.css">
</head>
<body>
	<%@ include file="navbar.jsp"%>
	<div class="container">

		<div class="content">
			<h1>アカウント検索条件入力</h1>


			<form id="create-task-form" action="S0040Servlet" method="post">

				<div class="mt-3">
					<label for="name" class="form-label">氏名
					<span class="badge bg-secondary">部分一致</span>
					</label> 
					<input type="text" required="required"
						id="name" name="name" class="form-control my-3 px-2" placeholder="氏名" required>
				</div>
				
				<div class="mt-3">
					<label for="adless" class="form-label">メールアドレス
					</label> 
					<input
						type="email" required="required" id="adless" name="mail"
						class="form-control my-3 px-2" placeholder="メールアドレス" required>
				</div>
				

				<div class="mt-3">
					<label class="form-label">権限
					</label>
					<div class="form-check">
						<input class="form-check-input" type="radio" name="role"
							id="role-none" value="none" required> <label
							class="form-check-label" for="role-none">権限なし</label>
					</div>
					<div class="form-check">
						<input class="form-check-input" type="radio" name="role"
							id="role-read" value="read"> <label
							class="form-check-label" for="role-read">売上登録</label>
					</div>
					<div class="form-check">
						<input class="form-check-input" type="radio" name="role"
							id="role-update" value="update"> <label
							class="form-check-label" for="role-update">アカウント登録</label>
					</div>
				</div>

				<button type="submit" class="btn btn-primary">検索</button>
				<button type="submit" class="btn btn-primary">クリア</button>
			</form>
		</div>
	</div>

</body>
</html>
