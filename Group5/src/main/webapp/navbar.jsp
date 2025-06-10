<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<nav class="navbar navbar-expand-lg navbar-dark bg-dark mb-4">
	<div class="container-fluid">
		<h1 class="navbar-brand ps-5 mt-1">物品売上管理システム</h1>
		<button class="navbar-toggler" type="button" data-bs-toggle="collapse"
			data-bs-target="#navbarNav" aria-controls="navbarNav"
			aria-expanded="false" aria-label="Toggle navigation">
			<span class="navbar-toggler-icon"></span>
		</button>
		<div class="collapse navbar-collapse" id="navbarNav">
			<ul class="navbar-nav align-items-center gap-2 ps-5">
				<li class="nav-item"><a class="nav-link" href="C002Servlet">ダッシュボード</a>
				</li>
				<li class="nav-item dropdown">
					<button class="btn dropdown-toggle nav-link ps-5" type="button"
						id="salesDropdown" data-bs-toggle="dropdown" aria-expanded="false">売上管理</button>
					<ul class="dropdown-menu dropdown-menu-lg-end"
						aria-labelledby="salesDropdown">
						<li>
							<form action="S0010Servlet" method="get">
								<button type="submit" class="dropdown-item">売上登録</button>
							</form>
						</li>
						<li><hr class="dropdown-divider"></li>
						<li>
							<form action="S0020Servlet" method="get">
								<button type="submit" class="dropdown-item">売上検索</button>
							</form>
						</li>
					</ul>
				</li>
				<li class="nav-item dropdown">
					<button class="btn dropdown-toggle nav-link ps-5" type="button"
						id="accountDropdown" data-bs-toggle="dropdown"
						aria-expanded="false">アカウント</button>
					<ul class="dropdown-menu dropdown-menu-lg-end"
						aria-labelledby="accountDropdown">
						<li>
							<form action="S0030Servlet" method="get">
								<button type="submit" class="dropdown-item">アカウント登録</button>
							</form>
						</li>
						<li><hr class="dropdown-divider"></li>
						<li>
							<form action="S0040Servlet" method="get">
								<button type="submit" class="dropdown-item">アカウント検索</button>
							</form>
						</li>
					</ul>
				</li>
			</ul>
			<ul class="navbar-nav ms-auto">
				<li class="nav-item">
					<form action="LogoutServlet" method="get"
						onsubmit="return confirm('ログアウトしますか？');">
						<button type="submit" class="btn btn-outline-danger">ログアウト</button>
					</form>
				</li>
			</ul>
		</div>
	</div>
</nav>
