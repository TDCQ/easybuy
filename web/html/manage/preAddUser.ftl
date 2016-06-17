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
    <h2>添加用户</h2>
    <div class="manage">
      <form action="/manage/addUser.html" method="post">
        <table>
          <tr>
            <td><label for="name">姓名</label></td>
            <td><input type="text" id="name" name="name" value=""}"></td>
          </tr>
          <tr>
            <td><label for="password">密码</label></td>
            <td><input type="text" id="password" name="password" value=""></td>
          </tr>
          <tr>
            <td><label for="gender">性别</label></td>
            <td>
              <select id="gender" name="gender">
              <#list genders as gender>
                  <option value="${gender.getGender()}">${gender.getGender()}</option>
              </#list>
              </select>
            </td>
          </tr>
          <tr>
            <td><label for="birthday">生日</label></td>
            <td><input type="text" id="birthday" name="birthday" placeholder="yyyy-MM-dd" value=""}" ></td>
          </tr>
          <tr>
            <td><label for="identityCode">身份证号</label></td>
            <td><input type="text" id="identityCode" name="identityCode" value=""></td>
          </tr>
          <tr>
            <td><label for="email">邮箱</label></td>
            <td><input type="text" id="email" name="email" value=""></td>
          </tr>
          <tr>
            <td><label for="mobile">手机</label></td>
            <td><input type="text" id="mobile" name="mobile" value=""></td>
          </tr>
          <tr>
            <td><label for="address">地址</label></td>
            <td><input type="text" id="address" name="address" value=""}"></td>
          </tr>
          <tr>
            <td><label for="status">角色</label></td>
            <td>
              <select id="status" name="status">
              <#list userStatus as status>
                <option value="${status.getRole()}">${status.getRole()}</option>
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