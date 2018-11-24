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
<title>管理员中心-充值</title>
<link rel="stylesheet" href="<%=basePath%>css/userstyle.css">
<script type="text/javascript" src="<%=basePath %>js/jquery-1.7.2.js"></script>
<script type="text/javascript" src="<%=basePath%>js/common.js"></script>
<script type="text/javascript" src="<%=basePath%>js/data.js"></script>
<script type="text/javascript" src="<%=basePath%>js/inputselect.js"></script>
	<link rel="stylesheet" href="<%=basePath %>css/blue.css">
		<script type="text/javascript">
		jQuery(document).ready(function() {
			jQuery("#dlUser").bind("blur", function() {
				check_null("dlUser", "tipUser", "请选择用户", false);
			});
			jQuery("#dlPay").bind("blur", function() {
				check_null("dlPay", "tipPay", "请选择支付方式", false);
			});
			jQuery("#txtAmount").bind("blur", function() {
				check_null("txtAmount", "tipAmount", "请输入金额", false);
			});
			jQuery("#txtDate").bind("blur", function() {
				check_null("txtDate", "tipDate", "请添加日期", false);
			});
		});

		function check_submit() {
			var ret = true;

			if (!check_null("dlUser", "tipUser", "请选择用户", true)) {
				return false;
			}
			if (!check_null("dlPay", "tipPay", "请选择支付方式", true)) {
				return false;
			}
			if (!check_null("txtAmount", "tipAmount", "请输入金额", true)) {
				return false;
			} 
			if (!check_null("txtDate", "tipDate", "请添加日期", true)) {
				return false;
			} 
			submit();
			return true;
		}
		function submit()
		{
			if(confirm("你确定添加支付信息？请确认再次确认信息，一旦提交无法更改！！！")){
				 var dlUser = jQuery.trim(jQuery("#dlUser").val());
				    var dlPay = jQuery.trim(jQuery("#dlPay").val());
				    var txtAmount = jQuery.trim(jQuery("#txtAmount").val());
				    var txtDate = jQuery.trim(jQuery("#txtDate").val());
				    var txtDescribe = jQuery.trim(jQuery("#txtDescribe").val());
				    var posturl = "<%=basePath%>money/saveRechange.action";
				    var data={
				    		username:dlUser,
				    		amount:txtAmount,
				    		payment_style:dlPay,
				    		describe:txtDescribe,
				    		add_time:txtDate
				    }
				    jQuery.ajax({
				　　            type:"post",
				　　            url:posturl,
				　　            data:data,
				　　            dataType:'text',//服务器返回的数据类型 可选XML ,Json jsonp script html text等
				　　            success:function(msg){
				　　             		if(msg=="1"){
				　　             			alert("添加成功");
				　　             		}else if(msg=="0"){
				　　             			alert("添加失败");
				　　             		}
							}
				　　        })
			}
		   
		}
		 
	</script>
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
							您当前的位置： <a href="<%=basePath%>admin/index.action">管理员中心</a> <span>&gt;</span>充值录入
						</div>
						<div class="main_center">
						<form id="form1">
							<table class="table table-c" cellspacing="0" cellpadding="0"
								border="0">
								<tbody>
									<tr>
										<td style="width: 12%;"><span class="must">*</span> 客户名：</td>
										<td style="width: 45%;">
											<input id="dlUser" name="username" list="tip" class="control-select" placeholder="请输入用户名"  value="" oninput="inputselect()" type="text"><br/>
											<input type="hidden" id="input_url"  value="<%=basePath%>user/getUsername.action">
											<datalist id="tip" >
											</datalist> 
										</td>
										<td><span class="control-info control-info-red"
											id="tipUser"></span></td>
									</tr>
									<tr>
										<td style="width: 12%;"><span class="must">*</span> 支付方式：</td>
										<td style="width: 45%;"><select name="payment_style" id="dlPay"
											class="control-select">
												<option value="" selected="selected">-请选择支付方式-</option>
												<option value="1">微信</option>
												<option value="2">支付宝</option>
												<option value="3">网银</option>
										</select></td>
										<td><span class="control-info control-info-red"
											id="tipPay"></span></td>
									</tr>
									<tr>
										<td style="width: 12%;"><span class="must">*</span> 充值金额：</td>
										<td style="width: 45%;"><input name="amount"
											id="txtAmount" class="control-text w-md"
											value="" type="text"></td>
										<td><span class="control-info control-info-red"
											id="tipAmount"></span></td>
									</tr>
									<tr>
									
										<td style="width: 12%;"><span class="must">*</span> 充值时间：</td>
										<td style="width: 45%;"><input name="add_time" id="txtDate" class="control-text w-md"
									onclick="fPopCalendar(event,this,this)" type="text" value=""></td>
										<td><span class="control-info control-info-red"
											id="tipDate"></span></td>
									</tr>
									<tr>
										<td style="width: 12%;"><span class="must">*</span> 描述：</td>
										<td style="width: 45%;">
										<textarea name="describe"
												rows="10" cols="40" id="txtDescribe" class="control-text"></textarea>
										</td>
										<td>
											<span class="control-info control-info-red"
											id="tipDescribe"></span>
										</td>
									</tr>
									<tr>
										<td>&nbsp;</td>
										<td><input name="btnSave" value="提 交"
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

</body>
</html>