<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>转账页面</title>
    <style>
        form {
            height: 60px;
            line-height: 60px;
            background: #eee;
        }

        input {
            height: 28px;
        }

        input[type=submit] {
            display: inline-block;
            vertical-align: middle;
            width: 60px;
        }
    </style>
</head>
<body>
<form action="" method="post">
    <label for="payer"> 转账人：
        <input type="text" id="payer" name="payer"/>
    </label>
    <label for="payee"> 收款人：
        <input type="text" id="payee" name="payee"/>
    </label>
    <label for="money"> 金额：
        <input type="text" id="money" name="money"/>
    </label>
    <input type="submit" value="转账"/>
</form>
</body>
</html>
