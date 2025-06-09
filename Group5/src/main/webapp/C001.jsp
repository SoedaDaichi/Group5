<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1" />
    <title>Login</title>
    <link rel="stylesheet" href="css/bootstrap.min.css" />
</head>
<body>
    <div class="container-fluid">
        <div class="row p-3 mb-2 bg-danger-subtle text-emphasis-danger bg-opacity-75">
            <div class="col fs-2 fw-bold text-center">
                物品売上管理システム
            </div>
        </div>
    </div>
 
    <form action="C001Servlet" method="post">
        <br>
        <div class="row my-4">
            <div class="col-sm-4 offset-sm-4">
                <div class="row my-3 px-3">メールアドレス</div>
                <div class="row my-3 px-2">
                    <input type="text" class="form-control" placeholder="メールアドレス" name="mail" required />
                </div>
                <div class="row my-3 px-3">パスワード</div>
                <div class="row my-3 px-2">
                    <input type="password" class="form-control" placeholder="パスワード" name="pass" required />
                </div>
                <div class="row my-3 px-2">
                    <div class="d-grid gap-2 d-md-flex justify-content-md-end">
                        <button type="submit" class="btn btn-primary btn-lg">ログイン</button>
                    </div>
                </div>
                <div class="form-check my-4">
                    <input class="form-check-input" type="checkbox" id="keepLoggedIn">
                    <label class="form-check-label" for="keepLoggedIn">
                        ログイン状態を維持する
                    </label>
                </div>
            </div>
        </div>
    </form>
 
<!--    <div class="container-fluid">-->
<!--    	<div class="col text-center">-->
<!--        <div class="row">-->
<!--            <p>-->
<!--                <a href="#">パスワードをお忘れの方はこちら</a>-->
<!--            </p>-->
<!--        </div>-->
<!--        <div class="row">-->
<!--            <p>-->
<!--                <a href="#">新規登録（無料）</a>-->
<!--            </p>-->
<!--        </div>-->
<!--    </div>-->
</body>
</html>