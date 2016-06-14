<!doctype html>
<html lang="zh-cn">
<head>
    <meta charset="UTF-8">
    <title>更新用户信息</title>
    <style>
        li {list-style-type: none;}

        label {
            display: inline-block;
            width: 150px;
            text-align: right;
        }
        input[type="submit"] {
            margin-top: 20px;
        }
    </style>
</head>
<body>
    <h2>更新${user.name}的个人信息</h2>
    <form action="/userManager/motify.html" method="post">
        <#--<#list userKey as key>-->
            <#--<li><label for="${key}" >${key}</label>-->
            <#--<input id="${key}" value="" name="${key}" />-->
            <#--</li>-->
        <#--</#list>-->
        <#list user?keys as prop>
            <#if prop="id">
                <li style="display:none"><label for="${prop}" >${prop}</label>
                    <input id="${prop}" value="${user[prop]!""}" name="${prop}" />
                </li>
            <#else>
                <li><label for="${prop}" >${prop}</label>
                    <input id="${prop}" value="${user[prop]!""}" name="${prop}" />
                </li>
            </#if>

        </#list>
        <li><label></label><input type="submit" /></li>
    </form>
</body>
</html>