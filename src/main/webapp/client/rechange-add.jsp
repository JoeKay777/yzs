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
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>会员中心-充值</title>
<link rel="stylesheet" href="<%=basePath%>css/userstyle.css">
<script type="text/javascript" src="<%=basePath%>js/jquery-1.7.2.js"></script>
</head>
<body>
	<form method="post" action="###" id="form1">
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
							您当前的位置： <a href="<%=basePath%>user/index.action">会员中心</a> <span>&gt;</span> 充值
						</div>
	                    <c:forEach items="${adminList }" var="admin">
								充值联系QQ：<a  href="tencent://message/?uin=${admin.admin_qq }&Site=qq&Menu=yes"> <img
									src="<%=basePath%>img/pa.jpg" alt="点击这里给我发消息" title="点击这里给我发消息" border="0"></a>
									<br/>
	                    </c:forEach>
							<!--
                    <span style="font-weight:bold; font-size:14px;">支付宝账户</span>
                    <br />
                    <span style="font-weight:bold; font-size:14px; color:Red;">taozeng226@vip.sina.com</span>
                    <br />
                    *涛
                    -->
							<div style="height: 10px;"></div>
						<div class="well">
							充值成功后，我们的客服会及时给您添加充值信息，请耐心等待^_^<br>
							如有问题，请致电客服╰(￣▽￣)╮
						</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</form>


	<script type="text/javascript">
		function next() {
			var money = $.trim($("#txtMoney").val());
			if (money == "") {
				alert("请输入充值金额!");
				return false;
			}
			return true;
		}
	</script>
</body>
</html>