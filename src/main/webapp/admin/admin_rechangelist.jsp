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
<title>管理员中心-充值记录</title>
<link rel="stylesheet" href="<%=basePath%>css/userstyle.css">
<script type="text/javascript" src="<%=basePath%>js/jquery-1.7.2.js"></script>
<script type="text/javascript" src="<%=basePath%>js/data.js"></script>
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
						function search(currPage) {
							var pl=document.getElementById("pl_txtPage").value; 
							if(pl!=""){
								currPage=pl;
							}
							var payment_style=document.getElementById("payment_style").value;
							var username=document.getElementById("user").value;
							var date=document.getElementById("txtDate").value;
							var pageSize=document.getElementById("pageSize").value;
							var url = "<%=basePath%>admin/rechangelist.action?currPage="+currPage
									+"&payment_style="+payment_style+"&username="+ escape(escape(username))+"&date="+date+"&pageSize="+pageSize;
							location.href = url;
						}
					</script>
					<div class="tool_main">
						<div class="ce_bread">
							您当前的位置： <a href="<%=basePath%>admin/index.action">管理员中心</a> <span>&gt;</span> 充值记录
						</div>
						<div class="main_center">
						<form  id="form1">
							<div class="control-search">
								<select id="payment_style" name="payment_style" class="control-select">
									<option value="">--支付方式--</option>
									<option value="1" <c:if test="${payment_style eq '1' }">selected="selected"</c:if> >微信</option>
									<option value="2" <c:if test="${payment_style eq '2' }">selected="selected"</c:if>>支付宝</option>
									<option value="3" <c:if test="${payment_style eq '3' }">selected="selected"</c:if>>网银</option>
								</select> 
								<select name="user" id="user" class="control-select">
									<option  value="">---用户名---</option>
									<c:forEach items="${userList }" var="u">
										<option value="${u.username }" <c:if test="${username eq u.username }">selected="selected"</c:if>>${u.username }</option>
									</c:forEach>
								</select>
								<input name="txtDate" id="txtDate" class="control-text"
									onclick="fPopCalendar(event,this,this)" type="text" value="${date }">
								 <input class="control-button" value="搜索" onclick="javascript:search(1);" type="button">
								  
							</div>
							<table class="table table-striped m-table" cellspacing="0"
								cellpadding="0" border="0">
								<thead>
									<tr>
										<th style="width: 28px;">序号</th>
										<th>用户</th>
										<th>支付方式</th>
										<th>金额</th>
										<!--<th>兑换积分</th>-->
										<th>时间</th>
										<th>备注</th>
									</tr>
								</thead>
								<c:forEach items="${rows }" var="row">
										<tr>
											<td>${row.rechange_id }</td>
											<td>${row.username }</td>
											<c:if test="${row.payment_style eq '1' }">
												<th>微信</th>
											</c:if>
											<c:if test="${row.payment_style eq '2' }">
												<th>支付宝</th>
											</c:if>
											<c:if test="${row.payment_style eq '3' }">
												<th>网银</th>
											</c:if>
											<td style="color: Red; font-weight: bold;">${row.amount }</td>
											<!--<td style="color:Red; font-weight:bold;">80000</td>-->
											<td>${row.add_time }</td>
											<td>${row.re_describe }</td>
										</tr>
								</c:forEach>
							</table>
							<div style="height: 10px;" align="right"><font color="red">总额：${sum_amount }元</font></div>
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
									</form>
						</div>
					</div>
				</div>
			</div>
		</div>
</body>
</html>