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
<title>管理员中心-关键字扣费详情</title>
<link rel="stylesheet" href="<%=basePath%>css/userstyle.css">
<script type="text/javascript" src="<%=basePath%>js/jquery-1.7.2.js"></script>
<link href="<%=basePath%>css/WdatePicker.css" rel="stylesheet" type="text/css">
<link rel="stylesheet" href="<%=basePath%>css/blue.css">
<script type="text/javascript" src="<%=basePath%>js/data.js"></script>
</head>
<body>



	<script type="text/javascript">
</script>
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
						function search(currPage) {
							var pl=document.getElementById("pl_txtPage").value; 
							var beginDate = document.getElementById("beginDate").value;
							var endDate = document.getElementById("endDate").value;
							var dabiao = document.getElementById("dabiao").value;
							var keywords=document.getElementById("keywords").value;
							var username=document.getElementById("user").value;
							if(pl!=""){
								currPage=pl;
							}
							var pageSize=document.getElementById("pageSize").value;
							var url = "<%=basePath%>admin/trankeys.action?beginDate=" + beginDate+ 
									"&currPage="+currPage+"&endDate="+endDate+"&keywords="
									+escape(escape(keywords))+"&dabiao="+dabiao+"&username="+escape(escape(username))+"&pageSize="+pageSize;
							location.href = url;
						}
				</script>
				<div class="tool_main">
					<div class="ce_bread">
						您当前的位置： <a href="<%=basePath%>admin/index.action">管理员中心</a> <span>&gt;</span> 关键字扣费详情
					</div>
					<div class="main_center">
						<form  id="form1">
							<div class="control-search">
								<select name="user" id="user" class="control-select">
									<option  value="">---用户名---</option>
									<c:forEach items="${userList }" var="u">
										<option value="${u.username }" <c:if test="${username eq u.username }">selected="selected"</c:if>>${u.username }</option>
									</c:forEach>
								</select>
								<span>关键字：</span> 
								<input name="keywords" id="keywords" class="control-text" type="text" value="${keywords }">
								<span>初始日期：</span> 
								<input name="beginDate" id="beginDate" class="control-text"
									onclick="fPopCalendar(event,this,this)" type="text" value="${beginDate }">
								<span>结束日期：</span> 
								<input name="endDate" id="endDate" class="control-text"
									onclick="fPopCalendar(event,this,this)" type="text" value="${endDate }">
								<select name="dabiao" id="dabiao" class="control-select">
									<option value="">---排名情况---</option>
									<option value="1" <c:if test="${dabiao eq '1' }">selected="selected"</c:if>>达标</option>
									<option value="2" <c:if test="${dabiao eq '2' }">selected="selected"</c:if>>未达标</option>
								</select>
								<input class="control-button" value="搜索"
									onclick="javascript:search(1);" type="button">
							</div>
							<table class="table table-striped m-table" cellspacing="0" cellpadding="0" border="0">
								<thead>
									<tr>
										<th style="width: 28px;">序号</th>
										<th>用户名</th>
										<th>关键词</th>
										<th>类型</th>
										<th>域名</th>
										<th>最新排名</th>
										<th>交易时间</th>
										<th>消费</th>
										<th>排名情况</th>
										<!--<th>查看</th>-->
									</tr>
								</thead>
								<c:forEach items="${rows}" var="row">
										<tr>
											<td>${row.tran_id }</td>
											<td>${row.username }</td>
											<c:if test="${row.search_engines eq '1' }">
											<td><a href="https://www.baidu.com/s?wd=${row.keywords }" target="_blank">${row.keywords }</a></td>
											<td>百度PC</td>
											</c:if>
											<c:if test="${row.search_engines eq '2' }">
											<td><a href="https://m.baidu.com/s?word=${row.keywords }" target="_blank">${row.keywords }</a></td>
											<td>百度手机</td>
											</c:if>
											<c:if test="${row.search_engines eq '3' }">
											<td><a href="https://www.so.com/s?ie=utf-8&shb=1&src=360sou_newhome&q=${row.keywords }" target="_blank">${row.keywords }</a></td>
											<td>360PC</td>
											</c:if>
											<td>${row.domain }</td>
											<!-- 域名 -->
											<td>
											<font color="red">
											<c:if test="${row.xinpai eq '9999' }">--</c:if>
											<c:if test="${row.xinpai eq '10000' }">未收录</c:if>
											<c:if test="${row.xinpai ne '9999' }"><c:if test="${row.xinpai ne '10000' }">${row.xinpai }</c:if></c:if>
											</font>
											</td>
											<td>${row.tran_time }</td>
											<td><span style="color: Red">${row.tran_money}</span></td>
											<c:if test="${row.dabiao eq '1' }">
												<td>达标</td>
											</c:if>
											<c:if test="${row.dabiao eq '2' }">
												<td>未达标</td>
											</c:if>
											<!-- 消耗积分 -->
											<!--<td><a href="javascript:view(546);">查看</a></td> -->
										</tr>
								</c:forEach>
							</table>
							<div style="height: 10px;" align="right"><font color="red">总额：${sum_account }元</font></div>
							<div style="height: 10px;" ></div>
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
									</form>
					</div>
				</div>
			</div>
		</div>
	</div>

	<div
		style="display: none; position: fixed; left: 0px; top: 0px; width: 100%; height: 100%; cursor: move; opacity: 0; background: rgb(255, 255, 255) none repeat scroll 0% 0%;"></div>
</body>
</html>