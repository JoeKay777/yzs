<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page isELIgnored="false"%>
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
<title>管理员中心-关键词报价</title>
<link rel="stylesheet" href="<%=basePath%>css/userstyle.css">
<script type="text/javascript" src="<%=basePath%>js/jquery-1.7.2.js"></script>
<script type="text/javascript" src="<%=basePath%>js/common.js"></script>
<script type="text/javascript" src="<%=basePath%>js/loading.js"></script>
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

<script type="text/javascript">
function check_submit()
{
var keys=$.trim($("#txtKey").val());
    if(keys!=null&&keys!=''){
    	   $.ajax({
 　　            type:"post",
 　　            url:"<%=basePath%>keywords/keysPrice.action",
           data:{keyArray:keys},
 　　            dataType:'json',//服务器返回的数据类型 可选XML ,Json jsonp script html text等
 　　            success:function(data){
 　　           	 $("#baojia tbody").empty();
 　　           	 $.each(data,function(i,dom){
 　　           		 $("#baojia tbody").append('<tr><td>'+dom.keyword+'</td><td>'+dom.baiduPcPrice+'元</td><td>'+dom.baiduMoPrice+'元</td><td>'+dom.so360Price+'元</td></tr>');
 　　           	 });
 			} ,
           error : function() {
               alert("异常！");
           },
           beforeSend: function(){    
             $('<div class="loadingWrap"></div>').appendTo("body");
           },   
           complete: function(){    
              $(".loadingWrap").remove();
           }  

 　　        })
    }else{
    	alert("请输入要查询的关键字");
    	return false;
    }
}
function LoginOut() {
    if (confirm("您确定要退出吗？")) {
        location.href="<%=basePath%>admin/loginOut.action";
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
				<div class="tool_main">
					<div class="ce_bread">
						您当前的位置： <a href="<%=basePath%>admin/index.action">会员中心</a> <span>&gt;</span>
						新增关键词
					</div>
					<div class="main_center">
						<form id="form1">
							<div align="center">
								<input name="txtKey" id="txtKey" class="control-text w-md"
									type="text">&nbsp; <input value="查    询"
									class="control-button btn-tj"
									onclick="javascript:check_submit();" type="button" />
							</div>
							<br />
							<table class="table table-striped m-table" cellspacing="0"
								cellpadding="0" border="1" id="baojia">
								<thead>
									<tr>
										<th>关键字</th>
										<th>百度PC报价</th>
										<th>百度Mobile报价</th>
										<th>360PC报价</th>
									</tr>
								</thead>
								<tbody>

								</tbody>
							</table>
						</form>
					</div>
					<div class="well">
						<span>查询关键词报价，最多可一次性查10个词，关键词用英文逗号隔开。</span>
					</div>
				</div>
			</div>
		</div>
	</div>


</body>
</html>