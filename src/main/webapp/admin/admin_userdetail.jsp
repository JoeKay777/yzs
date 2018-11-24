<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
	pageContext.setAttribute("baseUrl", basePath);
%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="content-type" content="text/html; charset=UTF-8">
<title>管理员中心-用户信息详情</title>
<link rel="stylesheet" href="<%=basePath%>css/userstyle.css">
<script type="text/javascript" src="<%=basePath%>js/jquery-1.7.2.js"></script>
<script type="text/javascript" src="<%=basePath%>js/common.js"></script>
</head>
<body>
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
								location.href = "<%=basePath%>admin/loginOut.action";
							}
						}
					</script>
					<div class="tool_main">
						<div class="ce_bread">
							您当前的位置： <a href="<%=basePath%>admin/index.action">管理员中心</a> <span>&gt;</span>用户信息详情
						</div>
						<div class="main_center">
						<form id="form1">
							<table class="table table-c" cellspacing="0" cellpadding="0"
								border="0">
								<tbody>
									<tr>
										<td style="width: 12%;"><span class="must">*</span> 用户名：</td>
										<td style="width: 45%;">
										 <input name="username" id="txtUsername" class="control-text w-md" value="${u.username }" readonly type="text">
										</td>
									</tr>
									<tr>
										<td style="width: 12%;"><span class="must">*</span> 密码：</td>
										<td style="width: 45%;">
										 <input name="password" id="txtPassword" class="control-text w-md" value="${u.password }" readonly type="text">
										</td>
									</tr>
									<tr>
										<td><span class="must">*</span> 联系人：</td>
										<td style="width: 45%;">
										<input name="company" id="txtTruename" class="control-text w-md" value="${u.company }" readonly type="text">
										</td>
									</tr>
									<tr>
										<td><span class="must">*</span> 联系电话：</td>
										<td style="width: 45%;">
										<input name="phone" id="txtMobile" class="control-text w-md" value="${u.phone }" readonly type="text">
										</td>
									</tr>
									<tr>
										<td><span class="must">*</span> 邮箱：</td>
										<td style="width: 45%;">
										<input name="mail" id="txtEmail" class="control-text w-md" value="${u.mail }" readonly type="text">
										</td>
									</tr>
										<tr>
										<td><span class="must">*</span> 注册时间：</td>
										<td style="width: 45%;">
										<input name="register_time"  class="control-text w-md" value="${u.register_time }" readonly type="text">
										</td>
									</tr>
										<tr>
										<td><span class="must">*</span> QQ：</td>
										<td style="width: 45%;">
										<input name="qq"  class="control-text w-md" value="${u.qq }" readonly type="text">
										</td>
									</tr>
										<tr>
										<td><span class="must">*</span> 余额：</td>
										<td style="width: 45%;">
										<input name="balance" class="control-text w-md" value="${u.balance  }" readonly type="text">
										</td>
									</tr>
								</tbody>
							</table>
							</form>
						</div>
					</div>
				</div>
			</div>
		</div>

</html>