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

    <div class="container">
<!--        <div class="sidebar">-->

<!--            <button onclick="location.href='IchiranServlet'" class="btn btn-outline-secondary w-100 mb-3">タスク一覧</button>-->
<!--            <button onclick="location.href='todo_sakusei.jsp'" class="btn btn-primary w-100">新規作成</button>-->
<!--        </div>-->

        <div class="content">
            <h1>アカウント登録</h1>


                <form id="create-task-form" action="S0030Servlet" method="post">
            
                <div class="mt-3">
                    <label for="name" class="form-label">氏名</label>
                    <input type="text" id="name" name="name" class="form-control my-3 px-2" required>
                </div>
                <div class="mt-3">
                    <label for="adless" class="form-label">メールアドレス</label>
                    <input type="email" id="adless" name="adless" class="form-control my-3 px-2" required>
                </div>
                <div class="mt-3">
                    <label for="password" class="form-label">パスワード</label>
                    <input type="password" id="password" name="password" class="form-control my-3 px-2" placeholder="パスワード" required>
                </div>
                <div class="mt-3">
                <label for="confirm-password" class="form-label">パスワード（確認）</label>
                    <input type="password" id="confirm_password" name="confirm_password" class="form-control my-3 px-2" placeholder="パスワード（確認）" required>
                </div>
                
                
                <div class="mt-3">
                <label class="form-label">権限</label>
                <div class="form-check">
                <input class="form-check-input" type="radio" name="role" id="role-none" value="none" required>
                <label class="form-check-label" for="role-none">権限なし</label>
                </div>
                <div class="form-check">
                <input class="form-check-input" type="radio" name="role" id="role-read" value="read">
                <label class="form-check-label" for="role-read">参照</label>
               </div>
             <div class="form-check">
                <input class="form-check-input" type="radio" name="role" id="role-update" value="update">
                <label class="form-check-label" for="role-update">更新</label>
            </div>
         </div>
                
                <button type="submit" class="btn btn-primary">登録</button>
            </form>
        </div>
    </div>

</body>
</html>
    