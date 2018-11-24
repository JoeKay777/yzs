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
<title>会员中心-日消费记录</title>
<link rel="stylesheet" href="<%=basePath%>css/userstyle.css">
<script type="text/javascript" src="<%=basePath%>js/jquery-1.7.2.js"></script>
<link href="<%=basePath%>css/WdatePicker.css" rel="stylesheet" type="text/css">
<script language="javascript" type="text/javascript"
	src="<%=basePath%>js/WdatePicker.js"></script>
<link rel="stylesheet" href="<%=basePath%>css/blue.css">
<script type="text/javascript" src="<%=basePath%>js/artDialog.js"></script>
<script type="text/javascript" src="<%=basePath%>js/iframeTools.js"></script>
<script type="text/javascript" src="<%=basePath%>js/data.js"></script>
</head>
<body>
	<form id="form1">

		<script type="text/javascript">
				
		</script>
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
						function search(currPage) {
							var pl=document.getElementById("pl_txtPage").value; 
							var date = document.getElementById("txtDate").value;
							if(date.length!=8&&date.length!=4&&date.length!=6&&date.length!=0){
								alert("按日查询格式如：'20180101';按月查询格式如：'201801';按日查询格式如：'2018';");
								return false;
							}
							if(pl!=""){
								currPage=pl;
							}
							var pageSize=document.getElementById("pageSize").value;
							var url = "<%=basePath%>money/tranSerialList.action?date=" + date+ "&currPage="+currPage+"&pageSize="+pageSize;
							location.href = url;
						}
					</script>
					<div class="tool_main">
						<div class="ce_bread">
							您当前的位置： <a href="<%=basePath %>user/index.action">会员中心</a> <span>&gt;</span> 日消费记录
						</div>

						<div class="main_center">
							<div class="control-search">
								<input name="txtDate" id="txtDate" class="control-text"
									onclick="fPopCalendar(event,this,this)" type="text" value="${date }">
								<input class="control-button" value="搜索"
									onclick="javascript:search(1);" type="button">
							</div>
							<table class="table table-striped m-table" cellspacing="0"
								cellpadding="0" border="0">
								<thead>
									<tr>
										<th style="width: 40px;">标识号</th>
										<th>日期</th>
										<th>消费</th>
										<th>余额</th>
									</tr>
								</thead>
								<c:forEach items="${rows }" var="row">
										<tr>
											<td>${row.transerial_id }</td>
											<td>${row.date }</td>
											<td style="color: Red; font-weight: bold;">${row.account }</td>
											<td style="color: blue; font-weight: bold;">${row.balance }</td>
										</tr>
								</c:forEach>
							</table>
							<div style="height: 10px;" align="right"><font color="red">总额：${sum_account }元</font></div>
								<!--//页脚：-->
									<div class="pageList">
										<strong
											style="margin-right: 15px; font-size: 14px; font-weight: 700;">总数<font
											color="red">${count }</font>
										</strong> 
											<a class="previousBtn tBtn"
											href="javascript:search(${currPage-1} );">上页</a> 
											<a class="cur" href="javascript:search(${currPage })">${currPage }</a> <a
											class="nextBtn tBtn" href="javascript:search(${currPage+1 });">下页</a> 
											<span class="mr10"> 
											<input name="pl$txtPage" id="pl_txtPage" style="width: 30px;"
											class="isTxtBig w-50" type="text" value=""> 页
											</span> 
											<a id="pl_btnPage" class="de"
											href="javascript:search(${currPage })">Go</a>
											<select name="pageSize" id="pageSize">
												<option  value="10" <c:if test="${pageSize eq '10' }">selected="selected"</c:if>>10</option>
												<option  value="20" <c:if test="${pageSize eq '20' }">selected="selected"</c:if>>20</option>
												<option  value="50" <c:if test="${pageSize eq '50' }">selected="selected"</c:if>>50</option>
											</select>
									</div>

						</div>
					</div>
				</div>
			</div>
		</div>
	</form>


	<div
		style="display: none; position: fixed; left: 0px; top: 0px; width: 100%; height: 100%; cursor: move; opacity: 0; background: rgb(255, 255, 255) none repeat scroll 0% 0%;"></div>
</body>
</html>