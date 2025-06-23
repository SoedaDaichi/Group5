<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
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
				<li class="nav-item"><a class="nav-link" href="C002.html">ダッシュボード</a>
				</li>
				<li class="nav-item dropdown">
					<button class="btn dropdown-toggle nav-link ps-5" type="button"
						id="salesDropdown" data-bs-toggle="dropdown" aria-expanded="false">売上管理</button>
					<ul class="dropdown-menu dropdown-menu-lg-end"
						aria-labelledby="salesDropdown">
						<c:if
							test="${sessionScope.loginAccount.authority == 1 || sessionScope.loginAccount.authority == 3}">
							<li>
								<form action="S0010.html" method="get">
									<button type="submit" class="dropdown-item">売上登録</button>
								</form>
							</li>
							<li><hr class="dropdown-divider"></li>
						</c:if>
						<li>
							<form action="S0020.html" method="get">
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
						<c:if
							test="${sessionScope.loginAccount.authority == 2 || sessionScope.loginAccount.authority == 3}">
							<li>
								<form action="S0030.html" method="get">
									<button type="submit" class="dropdown-item">アカウント登録</button>
								</form>
							</li>
							<li><hr class="dropdown-divider"></li>
						</c:if>
						<li>
							<form action="S0040.html" method="get">
								<button type="submit" class="dropdown-item">アカウント検索</button>
							</form>
						</li>
					</ul>
				</li>
			</ul>
			<ul class="navbar-nav ms-auto pe-4">
				<li class="nav-item dropdown">
					<button class="btn dropdown-toggle nav-link ps-5" type="button"
						id="logoutDropdown" data-bs-toggle="dropdown"
						aria-expanded="false">
						<c:out value="${sessionScope.loginAccount
						.name}" />
					</button>
					<ul class="dropdown-menu dropdown-menu-end"
						aria-labelledby="logoutDropdown">

						<form action="Logout.html" method="post"
							onsubmit="return confirm('ログアウトしますか？');">
							<button type="submit" class="dropdown-item">ログアウト</button>
						</form>
				</li>

				</li>
			</ul>
			</ul>
		</div>
	</div>
</nav>
