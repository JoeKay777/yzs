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
<title>管理员中心-用户列表</title>
<link rel="stylesheet" href="<%=basePath%>css/userstyle.css">
<script type="text/javascript" src="<%=basePath%>js/jquery-1.7.2.js"></script>
<script type="text/javascript" src="<%=basePath%>js/autoMessage.js"></script>
	<link rel="stylesheet" href="<%=basePath%>css/blue.css">
		<style>  
.loadingWrap{  
    position:fixed;  
    top:0;  
    left:0;  
    width:100%;  
    height:100%;  
    z-index:300;  
    background-image:url(<%=basePath%>/img/loading.gif);  
    background-repeat:no-repeat;  
    background-position:center center;  
    background-color:#000;  
    background-color:rgba(0,0,0,0.5);  
    filter:alpha(opacity=50);  
}  
</style>
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
							您当前的位置： <a href="<%=basePath%>admin/index.action">管理员中心</a> <span>&gt;</span> 用户列表
						</div>
						<div class="main_center">
							<div class="control-set"></div>

					<form method="post"  id="form1">
							<div class="control-search">
								用户名： <input id="username" class="control-text" type="text" value="${username }"> 
								<select name="user_status" id="user_status" class="control-select">
									<option value="">---用户状态---</option>
									<option value="1" <c:if test="${user_status eq '1' }">selected="selected"</c:if>>正常</option>
									<option value="0" <c:if test="${user_status eq '0' }">selected="selected"</c:if>>已停用</option>
								</select>
								<select name="rechange_status" id="rechange_status" class="control-select">
									<option value="">---充值状态---</option>
									<option value="0" <c:if test="${rechange_status eq '0' }">selected="selected"</c:if>>未充值</option>
									<option value="1" <c:if test="${rechange_status eq '1' }">selected="selected"</c:if>>余额充足</option>
									<option value="2" <c:if test="${rechange_status eq '2' }">selected="selected"</c:if>>余额不足</option>
								</select>
								 <input class="control-button" value="搜索" onclick="javascript:search(1);" type="button">
							</div>
						 	<script type="text/javascript">
						 	
								function search(currPage) {
									var pl=document.getElementById("pl_txtPage").value; 
									if(pl!=""){
										currPage=pl;
									}
									var user_status=document.getElementById("user_status").value; 
									var username=document.getElementById("username").value; 
									var rechange_status=document.getElementById("rechange_status").value; 
									var pageSize=document.getElementById("pageSize").value; 
									var url = "<%=basePath%>user/userListShow.action?user_status="+ user_status + 
											"&username="+ escape(escape(username))+"&currPage="+currPage+"&rechange_status="+rechange_status+"&pageSize="+pageSize;
									location.href = url;
								}
							</script>
							<table class="table table-striped m-table" cellspacing="0"
								cellpadding="0" border="1">
								<thead>
									<tr>
										<th style="width: 28px;">序号</th>
										<th>用户名</th>
										<th>联系人</th>
										<th>联系电话</th>
										<th>余额</th>
										<th>用户状态</th>
										<th>充值状态</th>
										<th>操作</th>
								</thead>
								<c:forEach items="${rows}" var="row">
										<tr>
											<td>${row.user_id }</td>
											<td>${row.username }</td>
											<td>${row.company }</td>
											<td>${row.phone }</td>
											<td><span style="color: red">${row.balance }</span></td>
											<c:if test="${row.user_status eq '1' }">
											<td><font color="green">使用中</font></td>
											</c:if>
											<c:if test="${row.user_status eq '0' }">
											<td><font color="green">已停用</font></td>
											</c:if>
											<c:if test="${row.rechange_status eq '0' }">
											<td><font color="red">未充值</font></td>
											</c:if>
											<c:if test="${row.rechange_status eq '1' }">
											<td><font color="green">余额充足</font></td>
											</c:if>
											<c:if test="${row.rechange_status eq '2' }">
											<td><font color="red">余额不足</font></td>
											</c:if>
											<td>
											<a href="javascript:user_detail(${row.user_id });"><font color="blue">详情</font></a>	
											<c:if test="${row.user_status eq '1' }">
											|<a href="javascript:user_stop('${row.username }');"><font color="blue">停用</font></a>
											</c:if>
											<c:if test="${row.user_status eq '0' }">
											|<a href="javascript:user_open('${row.username }');"><font color="blue">启用</font></a>
											</c:if>
											| <a href="javascript:user_del('${row.username }');">删除</a> </td>
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
											<select name="pageSize" id="pageSize">
												<option  value="10" <c:if test="${pageSize eq '10' }">selected="selected"</c:if>>10</option>
												<option  value="20" <c:if test="${pageSize eq '20' }">selected="selected"</c:if>>20</option>
												<option  value="50" <c:if test="${pageSize eq '50' }">selected="selected"</c:if>>50</option>
											</select>
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

		function user_detail(user_id) {
			var url = "<%=basePath%>admin/userdetail.action?user_id=" + user_id ;
			location.href = url;
		}
		function user_del(username) {
			if (confirm("你确定删除该用户以及与此用户相关的所有数据？")) {
				$.ajax({
					type : 'POST',
					dataType:'text',
					url : "<%=basePath%>admin/userdel.action",
					data : {
						username : username
					},
					success : function(data) {
						alert(data);
						window.location.reload();
					},
					
					error : function() {
			                alert("异常！");
			        },
			           beforeSend: function(){    
			               $('<div class="loadingWrap"></div>').appendTo("body");
			             },   
			             complete: function(){    
			                $(".loadingWrap").remove();
			             }  
				});
			}

		}
		function user_stop(username) {
			if (confirm("你确定停用该用户吗？")) {
				$.ajax({
					type : 'POST',
					dataType:'text',
					url : "<%=basePath%>admin/userstop.action",
					data : {
						username : username
					},
					success : function(data) {
						alert(data);
						window.location.reload();
					},
					error : function() {
			                alert("异常！");
			        },
			           beforeSend: function(){    
			               $('<div class="loadingWrap"></div>').appendTo("body");
			             },   
			             complete: function(){    
			                $(".loadingWrap").remove();
			             }  
				});
			}

		}
		function user_open(username) {
			if (confirm("你确定启用该用户吗？")) {
				$.ajax({
					type : 'POST',
					dataType:'text',
					url : "<%=basePath%>admin/useropen.action",
					data : {
						username : username
					},
					success : function(data) {
						alert(data);
						window.location.reload();
					},
					error : function() {
			                alert("异常！");
			        },
			           beforeSend: function(){    
			               $('<div class="loadingWrap"></div>').appendTo("body");
			             },   
			             complete: function(){    
			                $(".loadingWrap").remove();
			             }  
				});
			}

		}
	</script>
	<div
		style="display: none; position: fixed; left: 0px; top: 0px; width: 100%; height: 100%; cursor: move; opacity: 0; background: rgb(255, 255, 255) none repeat scroll 0% 0%;"></div>
</body>
</html>