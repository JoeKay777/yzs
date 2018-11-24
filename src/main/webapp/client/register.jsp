<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
pageContext.setAttribute("baseUrl",basePath); 
%>
<!DOCTYPE>
<html ><head>
<meta http-equiv="content-type" content="text/html; charset=UTF-8">
<title>会员注册</title>
<link rel="stylesheet" href="<%=basePath %>css/commons.css">
<script type="text/javascript" src="<%=basePath %>js/jquery-1.7.2.js"></script>

</head>
<body>
<form id="form1"> 
    <div class="mLogin">
    <div class="container">
        <div class="login-box">
            <h2 class="title">会员注册</h2>
            <ul class="login-list">
                <li>
                    <span class="must">*</span> 用户名&nbsp;&nbsp;&nbsp;
                    <input name="username" id="txtAccount" class="mUser btn-group" placeholder="请输入4-15位的字母+数字组合" type="text">
                    <span class="must" id="tipAccount"></span>
                </li>
                <li><span class="must">*</span> 密&nbsp;码&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                    <input name="password" id="txtPassword" class="mPassword btn-group" placeholder="请输入6位以上的密码" type="password">
                    <span class="must" id="tipPassword"></span>
                </li>
                <li><span class="must">*</span> 确认密码
                    <input name="txtCheckPassword" id="txtCheckPassword" class="agPassword btn-group" placeholder="确认密码" type="password">
                    <span class="must" id="tipCheckPassword"></span>
                </li>
                 <li><span class="must">*</span> 联系人&nbsp;&nbsp;&nbsp;
                    <input name="company" id="txtTruename" class="agPassword btn-group" type="text">
                    <span class="must" id="tipTruename"></span>
                </li>
                  <li><span class="must">*</span> Email&nbsp;&nbsp;&nbsp;&nbsp;
                    <input name="mail" id="txtEmail" class="agPassword btn-group" placeholder="" type="text">
                    <span class="must" id="tipEmail"></span>
                </li>
                 <li><span class="must">*</span> 手机号码
                    <input name="phone" id="txtMobile" class="agPassword btn-group" placeholder="" type="text">
                    <span class="must" id="tipMobile"></span>
                </li>
                	
                   <li><span class="must"></span> &nbsp;&nbsp;联系QQ&nbsp;&nbsp;
                    <input name="qq" id="txtQQ" class="agPassword btn-group" placeholder="" type="text">
                </li>
                <li>
                    <input name="btnReg" value="注 册" onclick="javascript:return check_reg();" id="btnReg" class="mSubmit" type="button">               
                </li>
            </ul>
            <div class="zc-wj">
                已有账号？立即<a href="<%=basePath %>client/login.jsp"><font color="blue"> 前往登陆</font> </a>
            </div>
        </div>
    </div>
</div> 
</form>
<script type="text/javascript">
function check_null(eleName,tipName,msg, flag)
{
    var value = $.trim($("#" + eleName).val());
    if (value == "")
    {
        $("#" + tipName).html(msg);
        if (flag)
        {
            location.hash = "#" + eleName;
        }
        return false;
    }
    else
    {
        $("#" + tipName).html("");
        return true;
    }
}

function check_reg()
{
    var ret = true;
    if (!check_null("txtAccount","tipAccount","请输入用户名",true)) { return false;}
    var reg = new RegExp('^[a-zA-Z]{1}[a-zA-Z0-9]{3,14}$');
            if (!reg.test($("#txtAccount").val())) {
                $("#tipAccount").html("请输入4-15位的字母+数字组合");
                return false;
            }
            else
            {
                 $("#tipAccount").html("");
            }
    if (!check_null("txtPassword","tipPassword","请输入密码",true)) { return false;}
    if ($.trim($("#txtPassword").val()).length < 6) {
 
                $("#tipPassword").html("请输入6位以上的密码！");
                return false;
            }
            else
            {
                 $("#tipPassword").html("");
            }
    
    if ($("#txtPassword").val() != $("#txtCheckPassword").val()) {

        $("#tipCheckPassword").html("确认密码不一致！");
        return false;
    }
    else
    {
        $("#tipCheckPassword").html("");
    }
    if (!check_null("txtTruename","tipTruename","请输入联系人",true)) { return false;}
    var reg = /^\S+\@{1}(\S)+\.{1}\w+$/;
        if (!reg.test($("#txtEmail").val())) {

            $("#tipEmail").html("请填写正确格式的邮箱");
            return false;
        } 
        else
        {
            $("#tipEmail").html("");
        }
        
        var reg = /^(13[0-9]|14[579]|15[0-3,5-9]|16[6]|17[0135678]|18[0-9]|19[89])\d{8}$/;
        if (!reg.test($("#txtMobile").val())) {
            $("#tipMobile").html("请输入正确的手机号码");
            return false;
        }
        else
        {
            $("#tipMobile").html("");
        }
        $.ajax({
            type: "POST",//方法类型
            url: "<%=basePath%>user/register.action" ,//url
            data: $("#form1").serialize(),
            dataType: "text",
            success: function (data) {
                alert(data);
                window.location.href="<%=basePath %>client/login.jsp"
            }
        });
    return true;
}
</script>
</body>
</html>