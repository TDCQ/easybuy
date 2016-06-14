<html>
<head>
    <title>用户管理</title>
</head>
<body>
<h1>Welcome ${user}!</h1>
<#list userList as user>
    <td>${user.name}</td>
    <td>${user.password}</td>
</#list>

<#list pages as page>
    <li>
        <a href="/UserManager.html?pageIndex=${pageIndex}&pageSize=${pageSize}">${pageIndex}</a>
        <#sep>&nbsp;&nbsp;</sep>
    </li>
</#list>
</body>
</html>