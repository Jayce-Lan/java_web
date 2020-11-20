<%--
  Created by IntelliJ IDEA.
  User: lanla
  Date: 2020-11-20
  Time: 14:57
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>修改学生信息</title>
</head>
<body>
    <form action="/student" method="post">
        学号：<input type="text" name="id" value="${student.id}" readonly><br/>
        姓名：<input type="text" name="name" value="${student.name}"><br/>
        成绩：<input type="text" name="score" value="${student.score}"><br/>
        <input type="hidden" name="method" value="update">
        <input type="submit" value="确认修改">
    </form>
</body>
</html>
