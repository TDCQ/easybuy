<!doctype html>
<head>
  <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
  <title>后台管理 - 易买网</title>
  <link type="text/css" rel="stylesheet" href="../css/style.css"/>
  <script type="text/javascript" src="../scripts/function-manage.js"></script>
  <style>
    table {
      width: 100%;
    }

    td > label {
      float: right;
      margin-right: 1em;
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
    <h2>更新${user.name}的个人信息</h2>
    <div class="manage">
      <form action="modify.html" method="post">
        <table>
          <tr style="display: none">
            <td><label for="id">id</label></td>
            <td><input type="text" id="id" name="id" value="${user.id!""}"></td>
          </tr>
          <tr>
            <td><label for="name">姓名</label></td>
            <td><input type="text" id="name" name="name" value="${user.name!""}"></td>
          </tr>
          <tr>
            <td><label for="password">密码</label></td>
            <td><input type="text" id="password" name="password" value="${user.password!""}"></td>
          </tr>
          <tr>
            <td><label for="gender">性别</label></td>
            <td>
              <select id="gender" name="gender">
              <#list genders as gender>
                <#if gender.getGender() = user.gender.getGender() >
                  <option value="${gender.getGender() }" selected>${gender.getGender()}</option>
                <#else>
                  <option value="${gender.getGender()}">${gender.getGender()}</option>
                </#if>
              </#list>
              </select>
            </td>
          </tr>
          <tr>
            <td><label for="birthday">生日</label></td>
            <td><input type="text" id="birthday" name="birthday" placeholder="yyyy-MM-dd" value="${user.birthday?string["yyyy-MM-dd"] !""}" ></td>
          </tr>
          <tr>
            <td><label for="identityCode">身份证号</label></td>
            <td><input type="text" id="identityCode" name="identityCode" value="${user.identityCode!""}"></td>
          </tr>
          <tr>
            <td><label for="email">邮箱</label></td>
            <td><input type="text" id="email" name="email" value="${user.email!""}"></td>
          </tr>
          <tr>
            <td><label for="mobile">手机</label></td>
            <td><input type="text" id="mobile" name="mobile" value="${user.mobile!""}"></td>
          </tr>
          <tr>
            <td><label for="address">地址</label></td>
            <td><input type="text" id="address" name="address" value="${user.address!""}"></td>
          </tr>
          <tr>
            <td><label for="status">角色</label></td>
            <td>
              <select id="status" name="status">
                <#list userStatus as status>
                  <#if status.getRole() == user.status.getRole() >
                    <option value="${status.getRole()}" selected>${status.getRole()}</option>
                  <#else>
                    <option value="${status.getRole()}">${status.getRole()}</option>
                  </#if>
                </#list>
              </select>
            </td>
          </tr>
          <tr>
            <td><label></label></td>
            <td><input type="submit"/></td>
          </tr>
        </table>
      </form>
    </div>

  </div>
  <div class="clear"></div>
</div>
<div id="footer">
  Copyright &copy; 2012 旋风科技有限公司 All Rights Reserved. 蜀ICP证0280001号
</div>
</body>
</html>
<#--<#list user?keys as prop>-->
  <#--<#if prop="id">-->
  <#--<tr style="display:none">-->
    <#--<td><label for="${prop}">${prop}</label></td>-->
    <#--<td><input id="${prop}" value="${user[prop]!""}" name="${prop}"/></td>-->
  <#--</tr>-->
  <#--<#elseif prop="gender">-->
  <#--<tr style="display:none">-->
    <#--<td><label for="${prop}">${prop}</label></td>-->
    <#--<td><select name="${prop}">-->
      <#--<option value="男">男</option>-->
      <#--<option value="女">女</option>-->
    <#--</select></td>-->
  <#--</tr>-->
  <#--<#else>-->
  <#--<tr>-->
    <#--<td><label for="${prop}">${prop}</label></td>-->
    <#--<td><input id="${prop}" value="${user[prop]!""}" name="${prop}"/></td>-->
  <#--</tr>-->

  <#--</#if>-->
<#--</#list>-->