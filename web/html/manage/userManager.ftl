<html>
<head>
    <title>用户管理</title>
    <style>
        tr, th, td{
            border: solid 1px black;
        }
        ul > li {
            display: inline;
        }
    </style>
</head>
<body>
<h1>用户列表</h1>
<table>
    <tr>
        <th>用户名</th>
        <th>密码</th>
        <th>邮箱</th>
        <th>电话</th>
        <th>地址</th>
        <th>操作</th>
    </tr>
    <#list userList as user>
        <tr>
            <td>${user.name }</td>
            <td>${user.password }</td>
            <td>${user.email }</td>
            <td>${user.mobile }</td>
            <td>${user.address }</td>
            <td>
                <a href="/manage/user-modify.html?id=${user.id}">修改</a>
                <a href="/manage/user-delete.html?id=${user.id}">删除</a>
            </td>
        </tr>
    </#list>
</table>

<ul>
    <#list pages as page>
    <li>
        <#if pageIndex == page >
            ${page + 1}
        <#else>
            <a href="/manage/getUsers.html?pageIndex=${page+1}&pageSize=${pageSize}">${page + 1}</a>
        </#if>
        <#--<a href="/userManager.html?pageIndex=${page}&pageSize=${pageSize}">${page + 1}</a>-->
        <#--<#sep>&nbsp;&nbsp;</sep>-->
    </li>
    </#list>
</ul>
</body>
</html>