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
<title>会员中心-基本资料</title>
<link rel="stylesheet" href="<%=basePath%>css/userstyle.css">
<script type="text/javascript" src="<%=basePath%>js/jquery-1.7.2.js"></script>
<script type="text/javascript" src="<%=basePath%>js/common.js"></script>
</head>
<body>
	<form method="post"  action="<%=basePath%>user/userUpdate.action" id="form1">
		<div class="mNav">
			<div class="container">
				<ul class="nav_list clearfix">
					<li><span
						style="color: #ffffff; font-size: 16px; padding-left: 10px; padding-top: 10px; line-height: 40px;">
						<a href="<%=basePath%>user/index.action">首页</a>
							&nbsp;&nbsp; 当前用户：<span id="top_lblName">${sessionScope.user.username}</span>
							&nbsp;&nbsp; 余额：<span id="top_lblName">${u.balance }</span>
							&nbsp;&nbsp; 消息：<span id="top_lblName"  >${u.user_msg }</span>
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
								<li><a href="<%=basePath%>user/keyPrice.action?user_id=${sessionScope.user.user_id}" >关键词报价</a></li>
								<li><a href="<%=basePath%>user/keyAdd.action?user_id=${sessionScope.user.user_id}" id="leftmenu_A1">新增关键词</a></li>
								<li><a href="<%=basePath%>user/keyDr.action?user_id=${sessionScope.user.user_id}" id="leftmenu_A2">关键词导入</a></li>
								<li><a href="<%=basePath%>keywords/keywordsListShow.action" id="leftmenu_menukeys">关键词列表</a></li>
							</ul>
						</div>
						<div class="menu-list">
							<h3>统计数据</h3>
							<ul class="">
								<li><a href="<%=basePath%>money/tranDetailList.action" id="leftmenu_menukeyday">每日扣费详情</a></li>
								<li><a href="<%=basePath%>money/tranSerialList.action" id="leftmenu_menuscoreday">消费记录</a></li>
								<li><a href="<%=basePath%>money/tranKeys.action" >关键字扣费详情</a></li>
							</ul>
						</div>
						<div class="menu-list">
							<h3>财务管理</h3>
							<ul class="">
								<li><a href="<%=basePath%>user/rechangeAdd.action?user_id=${sessionScope.user.user_id}" id="leftmenu_menupay">充值</a></li>
								<li><a href="<%=basePath%>money/rechangeList.action" id="leftmenu_menupaylist">充值记录</a></li>
							</ul>
						</div>
						<div class="menu-list">
							<h3>账户管理</h3>
							<ul class="">
								<li><a href="<%=basePath%>user/userDetail.action?user_id=${sessionScope.user.user_id}" id="leftmenu_menuprofile">基本资料</a></li>
								<li><a href="<%=basePath%>user/userUptPwd.action?user_id=${sessionScope.user.user_id}" id="leftmenu_menupass">修改密码</a></li>
								<li><a href="javascript:;" onclick="javascript:LoginOut();">退
										出</a></li>
							</ul>
						</div>
					</div>
					<script type="text/javascript">
						function LoginOut() {
							if (confirm("您确定要退出吗？")) {
								location.href = "<%=basePath%>user/loginOut.action";
							}
						}
					</script>
					<div class="tool_main">
						<div class="ce_bread">
							您当前的位置： <a href="<%=basePath %>user/index.action">会员中心</a> <span>&gt;</span> 基本资料
						</div>
						<div class="well">请填写真实的资料，以便我们提供更好的服务。</div>
						<div class="main_center">
							<table class="table table-c" cellspacing="0" cellpadding="0"
								border="0">
								<tbody>
									<tr>
										<td style="width: 12%;"><span class="must">*</span> 联系人：</td>
										<td style="width: 45%;"><input name="txtTruename"
											id="txtTruename" class="control-text w-md" value="${u.company }"
											type="text" readonly></td>
										<td><span class="control-info control-info-red"
											id="tipTruename"></span></td>
									</tr>
									<tr>
										<td><span class="must">*</span> EMAIL：</td>
										<td style="width: 45%;"><input name="txtEmail"
											id="txtEmail" class="control-text w-md" value="${u.mail }"
											type="text"></td>
										<td><span class="control-info control-info-red"
											id="tipEmail"></span></td>
									</tr>
									<tr>
										<td><span class="must">*</span> 手机：</td>
										<td style="width: 45%;"><input name="txtMobile"
											id="txtMobile" class="control-text w-md" value="${u.phone }"
											type="text"></td>
										<td><span class="control-info control-info-red"
											id="tipMobile"></span></td>
									</tr>
									<tr>
										<td><span class="must">&nbsp;&nbsp;</span>QQ：</td>
										<td style="width: 45%;"><input name="txtQQ" id="txtQQ"
											class="control-text w-md" value="${u.qq }" type="text"></td>
										<td></td>
									</tr>
									<tr>
										<td>&nbsp;</td>
										<td><input name="btnSave" value="提 交"
											onclick="javascript:return check_submit();" id="btnSave"
											class="control-button btn-tj" type="submit">
											<input type="hidden" value="${u.user_id }" name="user_id"/>
											<input type="hidden" value="${msg }" id="msg"/>
										</td>
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
		$(document).ready(function() {
			$("#txtTruename").bind("blur", function() {
				check_null("txtTruename", "tipTruename", "请输入联系人姓名", false);
			});
			$("#txtMobile").bind("blur", function() {
				check_null("txtMobile", "tipMobile", "请输入手机号码", false);
			});
			$("#txtEmail").bind("blur", function() {
				check_null("txtEmail", "tipEmail", "请输入Email", false);
			});
			if($("#msg").val()!=""){
				alert($("#msg").val());
			}
		});

		function check_submit() {
			var ret = true;

			if (!check_null("txtTruename", "tipTruename", "请输入联系人姓名", true)) {
				ret = false;
			}
			if (!check_null("txtMobile", "tipMobile", "请输入手机号码", true)) {
				ret = false;
			} else {
				var reg = /^(13[0-9]|14[579]|15[0-3,5-9]|16[6]|17[0135678]|18[0-9]|19[89])\d{8}$/;
				
				if (!reg.test($("#txtMobile").val())) {
					$("#tipMobile").html("请输入正确的手机号码");
					ret = false;
				}
			}
			if (!check_null("txtEmail", "tipEmail", "请输入Email", true)) {
				ret = false;
			} else {
				var reg = /^\S+\@{1}(\S)+\.{1}\w+$/;
				if (!reg.test($("#txtEmail").val())) {

					$("#tipEmail").html("请填写正确格式的邮箱");
					return false;
				}
			}
			return ret;
		}
	</script>
</body>
</html>