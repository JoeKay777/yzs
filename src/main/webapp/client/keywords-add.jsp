<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page isELIgnored="false"%>
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
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>会员中心-新增关键词</title>
<link rel="stylesheet" href="<%=basePath%>css/userstyle.css">
<script type="text/javascript" src="<%=basePath%>js/jquery-1.7.2.js"></script>
<script type="text/javascript" src="<%=basePath%>js/common.js"></script>
<link rel="stylesheet" href="<%=basePath%>css/blue.css">
<style>
.loadingWrap {
	position: fixed;
	top: 0;
	left: 0;
	width: 100%;
	height: 100%;
	z-index: 300;
	background-image: url(<%=basePath%>/img/loading.gif);
	background-repeat: no-repeat;
	background-position: center center;
	background-color: #000;
	background-color: rgba(0, 0, 0, 0.5);
	filter: alpha(opacity = 50);
}
</style>
<script type="text/javascript">
var arrKey = new Array();
function check_submit()
{
    var ret = true;
    if (!check_null("dlWeb","tipWeb","请选择类型",true)) { return;}
    else if (!check_null("txtAddress","tipAddress","请输入域名",true)) { return;}
    else if (!check_null("txtKeys","tipKeys","请输入关键词",true)) { return;}
    
    var patrn=/^[0-9a-zA-Z]+[0-9a-zA-Z\.-]*\.[a-zA-Z]{2,4}$/;
    var address = $.trim($("#txtAddress").val());
    
    if (!confirm("您确定提交吗？"))
    {
        return;
    }
    var strKeys = $.trim($("#txtKeys").val());
    var rechange_status=$("#hidden_re").val();
    if(rechange_status=="1"){
    	window.setTimeout(key_post(strKeys),1000);     
    }else if(rechange_status=="2"){
    	alert("老铁，您的余额已不足，请联系客服充值后再来~~");
    }else if(rechange_status=="0"){
    	alert("老铁，您还未充值，请联系客服充值后再来~~");
    }
}


function key_post(key)
{
    var address = $.trim($("#txtAddress").val());
    var webid = $.trim($("#dlWeb").val());
    var posturl = "<%=basePath%>keywords/loadKeywords.action";
    var data={
    		address:address,
    		webid:webid,
    		keyArray:key
    }
        $.ajax({
　　            type:"post",
　　            url:posturl,
　　            data:data,
　　            traditional: true,  //传数组进后台需要设置该属性
　　            dataType:'text',//服务器返回的数据类型 可选XML ,Json jsonp script html text等
　　            success:function(message){
　　             		alert(message);
			},
	           beforeSend: function(){    
	               $('<div class="loadingWrap"></div>').appendTo("body");
	             },   
	             complete: function(){    
	                $(".loadingWrap").remove();
	             }  
　　        })
}


function LoginOut() {
    if (confirm("您确定要退出吗？")) {
        location.href="<%=basePath%>user/loginOut.action";
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
						<a href="<%=basePath%>user/index.action">首页</a> &nbsp;&nbsp;
						当前用户：<span id="top_lblName">${sessionScope.user.username}</span>
						&nbsp;&nbsp; 余额：<span id="top_lblName">${u.balance }</span>
						&nbsp;&nbsp; 消息：<span id="top_lblName">${u.user_msg }</span>
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
				<div class="tool_main">
					<div class="ce_bread">
						您当前的位置： <a href="<%=basePath%>user/index.action">会员中心</a> <span>&gt;</span>
						新增关键词
					</div>
					<div class="main_center">
						<form method="post" id="form1">
							<table class="table table-c" cellspacing="0" cellpadding="0"
								border="0">
								<tbody>
									<tr>
										<td style="width: 12%;"><span class="must">*</span> 类型：</td>
										<td style="width: 45%;"><select name="dlWeb" id="dlWeb"
											class="control-select">
												<option value="" selected="selected">-请选择搜索引擎-</option>
												<option value="1">百度PC</option>
												<option value="2">百度移动</option>
												<option value="3">360PC</option>

										</select></td>
										<td><span class="control-info control-info-red"
											id="tipWeb"></span></td>
									</tr>
									<tr>
										<td style="width: 12%;"><span class="must">*</span> 域名：</td>
										<td style="width: 45%;"><input name="txtAddress"
											id="txtAddress" class="control-text w-md" type="text">
										</td>
										<td><span class="control-info control-info-red"
											id="tipAddress"></span></td>
									</tr>
									<tr>
										<td colspan="2"><span>域名可以是主域名（如：www.baidu.com），也可以是2级域名（如：www.zhidao.baidu.com）</span></td>
									</tr>
									<tr>
										<td><span class="must">*</span> 关键词：</td>
										<td style="width: 45%;"><textarea name="txtKeys"
												rows="10" cols="40" id="txtKeys"></textarea></td>
										<td><span class="control-info control-info-red"
											id="tipKeys"></span></td>
									</tr>
									<tr>
										<td colspan="2"><span>查询关键词百度指数，可一次性查10个词，关键词用英文逗号隔开。</span></td>
									</tr>
									<tr>
										<td>&nbsp;</td>
										<td><input value="提 交" class="control-button btn-tj"
											onclick="javascript:check_submit();" type="button"> <input
											type="hidden" value="${u.rechange_status }"
											id="hidden_re"></td>
									</tr>
								</tbody>
							</table>
						</form>
					</div>
					<div class="well">
						请确保您所提交的关键词排名在百度前5页(前3页效果更好)，以免造成不必要的资源浪费。<br>
						为了防止用户注册小号恶意提交，用户必须至少预充值100元才能提交关键词。<br>
						若余额不足以扣款任一达标的关键字，将停止排名操作。本平台承诺<font color="red">无效退款</font>，请用户放心使用。
					</div>
				</div>
			</div>
		</div>
	</div>


</body>
</html>