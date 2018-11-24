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
<title>管理员中心-管理员信息详情及修改</title>
<link rel="stylesheet" href="<%=basePath%>css/userstyle.css">
<script type="text/javascript" src="<%=basePath%>js/jquery-1.7.2.js"></script>
<script type="text/javascript" src="<%=basePath%>js/data.js"></script>
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
							您当前的位置： <a href="<%=basePath%>admin/index.action">管理员中心</a> <span>&gt;</span>管理员详情及修改
						</div>
						<div class="main_center">
						<form id="form1">
							<table class="table table-c" cellspacing="0" cellpadding="0"
								border="0">
								<tbody>
									<tr>
										<td style="width: 12%;"><span class="must">*</span> 用户名：</td>
										<td style="width: 45%;">
										 <input name="admin_name" id="txtUsername" class="control-text w-md" value="${a.admin_name }" readonly type="text">
										</td>
										<td><span class="control-info control-info-red"
											id="tipUsername"></span></td>
									</tr>
									<tr>
										<td style="width: 12%;"><span class="must">*</span> 密码：</td>
										<td style="width: 45%;">
										 <input name="admin_pass" id="txtPassword" class="control-text w-md" value="${a.admin_pass }" readonly type="text">
										</td>
										<td><span class="control-info control-info-red"
											id="tipPassword"></span></td>
									</tr>
									<tr>
										<td><span class="must">*</span> 联系人：</td>
										<td style="width: 45%;">
										<input name="admin_truename" id="txtTruename" class="control-text w-md" value="${a.admin_truename }" type="text">
										</td>
										<td>
											<span class="control-info control-info-red"
											id="tipTruename"></span>
										</td>
									</tr>
									<tr>
										<td><span class="must">*</span> 联系电话：</td>
										<td style="width: 45%;">
										<input name="admin_tel" id="txtMobile" class="control-text w-md" value="${a.admin_tel }" type="text">
										</td>
										<td>
											<span class="control-info control-info-red"
											id="tipMobile"></span>
										</td>
									</tr>
									<tr>
										<td><span class="must">*</span> 邮箱：</td>
										<td style="width: 45%;">
										<input name="admin_mail" id="txtEmail" class="control-text w-md" value="${a.admin_mail }" type="text">
										<input name="admin_id" type="hidden" value="${a.admin_id }">
										</td>
										<td>
											<span class="control-info control-info-red"
											id="tipEmail"></span>
										</td>
									</tr>
									<tr>
										<td><span class="must">*</span> QQ：</td>
										<td style="width: 45%;">
										<input name="admin_qq" id="txtQQ" class="control-text w-md" value="${a.admin_qq }" type="text">
										</td>
										<td>
											<span class="control-info control-info-red"
											id="tipQQ"></span>
										</td>
									</tr>
									<tr>
										<td>&nbsp;</td>
										<td><input name="btnSave" value="修 改"
											onclick="javascript:return check_submit();" id="btnSave"
											class="control-button btn-tj" type="button">  
									</tr>
								</tbody>
							</table>
							</form>
						</div>
					</div>
				</div>
			</div>
		</div>

	


	<script type="text/javascript">
	
			function check_submit()
			{
			    if (!check_null("txtTruename","tipTruename","请输入联系人",true)) { return false;}
			    var reg = /^(13[0-9]|14[579]|15[0-3,5-9]|16[6]|17[0135678]|18[0-9]|19[89])\d{8}$/;
			        if (!reg.test(jQuery("#txtMobile").val())) {
			        	jQuery("#tipMobile").html("请输入正确的手机号码");
			            return false;
			        }
			        else
			        {
			        	jQuery("#tipMobile").html("");
			        }
			        var reg = /^\S+\@{1}(\S)+\.{1}\w+$/;
			        if (!reg.test(jQuery("#txtEmail").val())) {

			        	jQuery("#tipEmail").html("请填写正确格式的邮箱");
			            return false;
			        } 
			        else
			        {
			        	jQuery("#tipEmail").html("");
			        }
			        if (!check_null("txtQQ","tipQQ","请输入QQ",true)) { return false;}
			        jQuery.ajax({
			            type: "POST",//方法类型
			            url: "<%=basePath%>admin/update.action" ,//url
			            dataType:"text",
			            data: jQuery("#form1").serialize(),
			            success: function (data) {
								alert(data);
								window.location.reload();
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