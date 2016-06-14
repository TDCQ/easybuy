<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <title>后台管理 - 易买网</title>
    <link type="text/css" rel="stylesheet" href="../css/style.css" />
    <script type="text/javascript" src="../scripts/function-manage.js"></script>
    <style>
        table {
            width: 100%;
        }
        tr>th, tr>td{
            border: solid 1px #dfc9b2;
        }
        dic.list {
            padding: 20px;
        }
        div.list > ul {
            width: 300px;
            margin: 0 auto;
        }

        div.list > ul > li {
            list-style-type: none;
            display: inline-block;
        }
    </style>
</head>
<body>

<div id="main">
    <div class="main">
        <h2>用户管理</h2>
        <div class="manage">
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
                    <td>${user.name !""}</td>
                    <td>${user.password !""}</td>
                    <td>${user.email !""}</td>
                    <td>${user.mobile !""}</td>
                    <td>${user.address !""}</td>
                    <td>
                        <a href="/manage/modify.html?id=${user.id}">修改</a>
                        <a href="/manage/delete.html?id=${user.id}">删除</a>
                    </td>
                </tr>
            </#list>
            </table>
        </div>

        <div class="pager">
            <ul class="clearfix">
            <#list pages as page>
                <li>
                    <#if pageIndex == page >
                    ${page + 1}
                    <#else>
                        <a href="/manage/getUsers.html?pageIndex=${page+1}&pageSize=${pageSize}">${page + 1}</a>
                    </#if>
                </li>
            </#list>
            </ul>
        </div>
    </div>
    <div class="clear"></div>
</div>
<div id="footer">
    Copyright &copy; 2012 旋风科技有限公司 All Rights Reserved. 蜀ICP证0280001号
</div>
</body>
</html>
