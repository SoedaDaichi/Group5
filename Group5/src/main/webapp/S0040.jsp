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
					<label for="name" class="form-label">氏名 <span
						class="badge bg-secondary">部分一致</span>
					</label> <input type="text" id="name" name="name"
						class="form-control my-3 px-2" placeholder="氏名">
				</div>

				<div class="mt-3">
					<label for="adless" class="form-label">メールアドレス </label> <input
						type="email" id="adless" name="mail"
						class="form-control my-3 px-2" placeholder="メールアドレス">
				</div>


				<div class="mt-3">
					<label class="custom-control-label">権限 </label>
					<div class="custom-control custom-checkbox">
						<input class="custom-control-input" type="checkbox" name="role0"
							id="role-none" value="0"> <label
							class="custom-control-label" for="role-none">権限なし</label>
					</div>
					<div class="custom-control custom-checkbox">
						<input class="custom-control-input" type="checkbox" name="role1"
							id="role-sales" value="1"> <label
							class="custom-control-label" for="role-read">売上登録</label>
					</div>
					<div class="custom-control custom-checkbox">
						<input class="custom-control-input" type="checkbox" name="role10"
							id="role-account" value="10"> <label
							class="custom-control-label" for="role-read">アカウント登録</label>
					</div>
				</div>

				<button type="submit" class="btn btn-primary">検索</button>
				<button type="submit" class="btn btn-primary">クリア</button>
			</form>
		</div>
	</div>

</body>
</html>
