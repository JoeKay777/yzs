<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path +"/";
	pageContext.setAttribute("baseUrl", basePath);
%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="content-type" content="text/html; charset=UTF-8">
<title>管理员中心-修改密码</title>
<style type="text/css">
.input {
	border-color: #FF0000;
	border-style: solid;
}
</style>
<link rel="stylesheet" href="<%=basePath%>css/userstyle.css">
<script type="text/javascript" src="<%=basePath%>js/jquery-1.7.2.js"></script>
<script type="text/javascript" src="<%=basePath%>js/common.js"></script>
<script type="text/javascript" src="<%=basePath%>js/autoMessage.js"></script>
</head>
<body>


	<form  id="form1">
		<div class="mNav">
			<div class="container">
				<ul class="nav_list clearfix">
					<li><span
						style="color: #ffffff; font-size: 16px; padding-left: 10px; padding-top: 10px; line-height: 40px;">
						<a href="<%=basePath%>admin/index.action">管理员中心</a>
							&nbsp;&nbsp; 欢迎管理员：<span id="top_lblName">${sessionScope.admin.admin_name}</span>
					</span></li>
				</ul>
			</div>
		</div>
		<div class="mMain">
			<div class="container">
				<div class="manage clearfix">
						<div class="side_menu fl">
						<div class="menu-list">
							<h3>关键词管理</h3>
							<ul class="">
								<li><a href="<%=basePath%>admin/keyprice.action" >关键词报价</a></li>
								<li><a href="<%=basePath%>admin/keyadd.action" >添加关键词</a></li>
								<li><a href="<%=basePath%>admin/keydr.action" >关键词导入</a></li>
								<li><a href="<%=basePath%>admin/keylist.action">关键词列表</a></li>
								<li><a href="<%=basePath%>admin/keychecklist.action" >待审核关键字</a></li>
							</ul>
						</div>
						<div class="menu-list">
							<h3>统计数据</h3>
							<ul class="">
								<li><a href="<%=basePath%>admin/transeriallist.action" >扣费记录</a></li>
								<li><a href="<%=basePath%>admin/trandetaillist.action" >每日扣费详情</a></li>
								<li><a href="<%=basePath%>admin/trankeys.action" >关键字扣费记录</a></li>
							</ul>
						</div>
						<div class="menu-list">
							<h3>财务管理</h3>
							<ul class="">
								<li><a href="<%=basePath%>admin/rechangeadd.action" >充值录入</a></li>
								<li><a href="<%=basePath%>admin/rechangelist.action" >充值记录</a></li>
							</ul>
						</div>
						<div class="menu-list">
							<h3>客户管理</h3>
							<ul class="">
								<li><a href="<%=basePath%>user/userListShow.action" >客户列表</a></li>
							</ul>
						</div>
						<div class="menu-list">
							<h3>管理员管理</h3>
							<ul class="">
								<li><a href="<%=basePath%>admin/list.action">管理员列表</a></li>
								<li><a href="<%=basePath%>admin/upt.action">信息详情</a></li>
								<li><a href="<%=basePath%>admin/add.action" >管理员添加</a></li>
								<li><a href="<%=basePath%>admin/adminUptPwd.action" >密码修改</a></li>
								<li><a href="javascript:;" onclick="javascript:LoginOut();">退
										出</a></li>
							</ul>
						</div>
					</div>
					<script type="text/javascript">
function LoginOut() {
    if (confirm("您确定要退出吗？")) {
        location.href="<%=basePath%>admin/loginOut.action";
    }
}
</script>
					<div class="tool_main">
						<div class="ce_bread">
							您当前的位置： <a href="<%=basePath%>admin/index.action">管理员中心</a> <span>&gt;</span> 修改密码
						</div>
						<div class="well">请妥善保管好您的密码，如果忘记密码请找军哥找回！</div>
						<div class="main_center">
							<table class="table table-c" align="center" cellspacing="0"
								cellpadding="0" border="0">
								<tbody>
									<tr>
										<td style="width: 12%;"><span class="must">*</span> 旧密码：
										</td>
										<td style="width: 45%;" ><input type="text"
											id="txtOldPassword" class="control-text w-md">
										</td>
										<td><span class="control-info control-info-red"
											id="tipOldPassword"></span></td>
									</tr>
									<tr>
										<td><span class="must">*</span> 新密码：</td>
										<td style="width: 45%;"><input type="password"
											id="txtPassword" class="control-text w-md"
											style="width: 200px"></td>
										<td><span class="control-info control-info-red"
											id="tipPassword"></span></td>
									</tr>


									<tr>
										<td align="left"><span class="must">*</span> 确认新密码：</td>
										<td style="width: 45%;"><input
											type="password" id="txtConfirmPassword"
											class="control-text w-md"
											style="width: 200px" /></td>
										<td><span class="control-info control-info-red"
											id="tipConfirmPassword"></span></td>
									</tr>


									<tr>
										<td>&nbsp;</td>
										<td colspan="3" align="center"><input
											class="control-button btn-tj" type="button" value="提交"
											onClick="check_submit();"></td>
									</tr>
								</tbody>
							</table>
						</div>

					</div>
				</div>
			</div>
		</div>
	</form>
<script type="text/javascript">
$(document).ready(function(){
      $("#txtOldPassword").bind("blur",function(){check_null("txtOldPassword","tipOldPassword","请输入原密码",false);});
      $("#txtPassword").bind("blur",function(){check_null("txtPassword","tipPassword","请输入新密码",false);});
      $("#txtConfirmPassword").bind("blur",function(){check_null("txtConfirmPassword","tipConfirmPassword","重复新密码不能为空",false);});     
});
    
function check_submit()
{
    var ret = true;
    
    if (!check_null("txtOldPassword","tipOldPassword","请输入原密码",true)) { return false;}
    else if (!check_null("txtPassword","tipPassword","请输入新密码",true)) { return false;}
    else if ($.trim($("#txtPassword").val()).length < 6) {
                $("#tipPassword").html("请输入6位以上的密码！");
                return false;
    }
    else if (!check_null("txtConfirmPassword","tipConfirmPassword","重复新密码不能为空",true)) { return false;}
    else if ($("#txtPassword").val() != $("#txtConfirmPassword").val())
    {
        $("#tipConfirmPassword").html("重复密码不一致");
        return  false;
    }
    $.ajax({
        type: "POST",//方法类型
        url: "<%=basePath%>admin/uptPwd.action" ,//url
        dataType:"text",
        data: {
        	oldPwd:$.trim($("#txtOldPassword").val()),
        	newPwd:$.trim($("#txtPassword").val())
        },
        success: function (data) {
           alert(data);
        },
        error : function() {
            alert("异常！");
        }
    });
    return true;
}

</script>
</body>
</html>