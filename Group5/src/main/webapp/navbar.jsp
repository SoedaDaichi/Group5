<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<nav class="navbar navbar-expand-lg navbar-dark bg-dark">
	<div class="container-fluid">
		<h1 class="navbar-brand ps-5 mt-1">物品売上管理システム</h1>
		<button class="navbar-toggler" type="button" data-bs-toggle="collapse"
			data-bs-target="#navbarNav" aria-controls="navbarNav"
			aria-expanded="false" aria-label="Toggle navigation">
			<span class="navbar-toggler-icon"></span>
		</button>
		<div class="collapse navbar-collapse" id="navbarNav">
			<ul class="navbar-nav">
				<li class="nav-item"><a class="nav-link" href="TodoServlet">ダッシュボード</a></li>
			</ul>

			<div class="ms-auto d-flex gap-2">
				<div class="dropdown">
					<button class="btn btn-outline-light dropdown-toggle" type="button"
						id="accountDropdown" data-bs-toggle="dropdown"
						aria-expanded="false">売上管理</button>
					<ul class="dropdown-menu dropdown-menu-end"
						aria-labelledby="accountDropdown">
						<li>
							<form action="UpdateUserServlet" method="get">
								<button type="submit" class="dropdown-item">売上登録</button>
							</form>
						</li>
						<li>
							<form action="LogoutServlet" method="get">
								<button type="submit" class="dropdown-item">売上検索</button>
							</form>
						</li>
						<li><hr class="dropdown-divider"></li>
					</ul>
				</div>
			</div>
			<!--			<div class="ms-auto d-flex gap-2">-->
			<!--				<form action="LogoutServlet" method="get">-->
			<!--					<button class="btn btn-outline-light" type="submit"-->
			<!--						onclick="return confirm('ログアウトしますか？');">ログアウト</button>-->
			<!--				</form>-->
			<!--				<form action="DeleteAccountServlet" method="post"-->
			<!--					onsubmit="return confirm('本当にアカウントを削除しますか？');">-->
			<!--					<button class="btn btn-outline-danger" type="submit">アカウント削除</button>-->
			<!--				</form>-->
			<!--			</div>-->
			<div class="ms-auto d-flex gap-2">
				<div class="dropdown">
					<button class="btn btn-outline-light dropdown-toggle" type="button"
						id="accountDropdown" data-bs-toggle="dropdown"
						aria-expanded="false">アカウント</button>
					<ul class="dropdown-menu dropdown-menu-end"
						aria-labelledby="accountDropdown">
						<li>
							<form action="UpdateUserServlet" method="get">
								<button type="submit" class="dropdown-item">アカウント登録</button>
							</form>
						</li>
						<li>
							<form action="UpdateUserServlet" method="get">
								<button type="submit" class="dropdown-item">アカウント検索</button>
							</form>
						</li>
						<li><hr class="dropdown-divider"></li>
						<li>
							<form action="LogoutServlet" method="get"
								onsubmit="return confirm('ログアウトしますか？');">
								<button type="submit" class="dropdown-item">ログアウト</button>
							</form>
						</li>
						<li>
							<form action="DeleteAccountServlet" method="post"
								onsubmit="return confirm('本当にアカウントを削除しますか？');">
								<button type="submit" class="dropdown-item text-danger">アカウント削除</button>
							</form>
						</li>
					</ul>
				</div>
			</div>

		</div>
	</div>
</nav>