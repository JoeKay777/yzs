<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path +"/";
	pageContext.setAttribute("baseUrl", basePath);
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>管理员中心-管理员列表</title>
<link rel="stylesheet" href="<%=basePath%>css/userstyle.css">
<script type="text/javascript" src="<%=basePath%>js/jquery-1.7.2.js"></script>
<script type="text/javascript" src="<%=basePath%>js/autoMessage.js"></script>
	<link rel="stylesheet" href="<%=basePath%>css/blue.css">
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
							您当前的位置： <a href="<%=basePath%>admin/index.action">管理员中心</a> <span>&gt;</span> 管理员列表
						</div>
						<div class="main_center">
							<div class="control-set"></div>

							<form method="post"  id="form1">
							<table class="table table-striped m-table" cellspacing="0"
								cellpadding="0" border="1">
								<thead>
									<tr>
										<th style="width: 28px;">序号</th>
										<th>用户名</th>
										<th>使用人</th>
										<th>联系电话</th>
										<th>添加时间</th>
										<th width="100">操作</th>
								</thead>
								<c:forEach items="${rows}" var="row">
										<tr>
											<td>${row.admin_id }</td>
											<td>${row.admin_name }</td>
											<td>${row.admin_truename }</td>
											<td>${row.admin_tel }</td>
											<td>${row.admin_adddate }</td>
											<td>
											<a href="javascript:admin_detail(${row.admin_id });"><font color="blue">详情</font></a>	
											| <a href="javascript:admin_del(${row.admin_id });">删除</a> </td>
										</tr>
								</c:forEach>
								
							</table>
							<div style="height: 10px;"></div>
								<center>
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
									</div>
								</center>
							<!--//页底：-->
								</form>
						</div>
					</div>
				</div>
			</div>
		</div>



	<script type="text/javascript">

	function search(currPage) {
		var pl=document.getElementById("pl_txtPage").value; 
		if(pl!=""){
			currPage=pl;
		}
		var url = "<%=basePath%>admin/list.action?currPage="+currPage;
		location.href = url;
	}
		function admin_detail(admin_id) {
			var url = "<%=basePath%>admin/detail.action?admin_id=" + admin_id ;
			location.href = url;
		}
		function admin_del(id) {
			if (confirm("你确定删除？")) {
				$.ajax({
					type : 'POST',
					dataType:'text',
					url : "<%=basePath%>admin/del.action",
					data : {
						admin_id : id
					},
					success : function(data) {
							alert(data);
							window.location.reload();
					},
					error : function() {
			                alert("异常！");
			        }
				});
			}

		}
	</script>
	<div
		style="display: none; position: fixed; left: 0px; top: 0px; width: 100%; height: 100%; cursor: move; opacity: 0; background: rgb(255, 255, 255) none repeat scroll 0% 0%;"></div>
</body>
</html>