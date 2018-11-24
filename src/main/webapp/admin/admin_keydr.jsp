<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
pageContext.setAttribute("baseUrl",basePath); 
%>
<!DOCTYPE html>
<html>
<head >
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>管理员-关键词导入</title>
<link rel="stylesheet" href="<%=basePath%>css/userstyle.css">
	<script type="text/javascript" src="<%=basePath%>js/common.js"></script>
	<script type="text/javascript" src="<%=basePath%>js/jquery-1.7.2.js"></script>
	<script type="text/javascript" src="<%=basePath%>js/inputselect.js"></script>
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
						function LoginOut() {
							if (confirm("您确定要退出吗？")) {
								location.href = "<%=basePath%>user/loginOut.action";
							}
						}
						$(document).ready(function(){
							if($("#msg").val()!=""){
								$(".loadingWrap").remove();
							}
							 $("#btnSave").click(function () {
					                var file = $("#fileUpload1").val();
					                if (file == "") {
					                    alert("请选择要上传的文件");
					                    return false
					                } else {
					                    //检验文件类型是否正确
					                    var exec = (/[.]/.exec(file)) ? /[^.]+$/.exec(file.toLowerCase()) : '';
					                    if (exec != "xlsx"&& exec!="xls") {
					                        alert("文件格式不对，请上传Excel文件!");
					                        return false;
					                    }
					                }
					                var username=$("#dlUser").val();
					                if(username==""){
					                	alert("请选择用户名");
					                	return false;
					                }
					                	$('<div class="loadingWrap"></div>').appendTo("body");
					                	$("#form1").submit();     
					               
					                return true;
					            });
						})
	</script>
</head>
<body>
	
		<div class="mNav">
			<div class="container">
				<ul class="nav_list clearfix">
					<li><span
						style="color: #ffffff; font-size: 16px; padding-left: 10px; padding-top: 10px; line-height: 40px;">
						<a href="<%=basePath%>admin/index.action">管理员中心</a>
							&nbsp;&nbsp;欢迎管理员：<span id="top_lblName">${sessionScope.admin.admin_name}</span>
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
							您当前的位置： <a href="<%=basePath%>admin/index.action">管理员中心</a> <span>&gt;</span>
							关键词导入
						</div>
						<div class="main_center">
						<form method="post"  action="<%=basePath%>admin/uploadKeyFile.action" id="form1" enctype="multipart/form-data">
							<table class="table table-c">
									<tr>
										<td align="right"><span class="must">*</span>
											选择本地excel表：
											<input name="file"
											id="fileUpload1" type="file" value="${file }">
										</td>
										<td><span class="must">*</span>
										<%-- 	<select name="username" id="dlUser"
												class="control-select">
													<option value="" selected="selected">-请选择客户名-</option>
													<c:forEach items="${userList }" var="u">
														<option value="${u.username }">${u.username }</option>
													</c:forEach>
											</select> --%>
											<input id="dlUser" name="username" list="tip" class="control-select" placeholder="请输入用户名"  value="" oninput="inputselect()" type="text"><br/>
											<input type="hidden" id="input_url"  value="<%=basePath%>user/getUsername.action">
											<datalist id="tip" >
											</datalist>                  

										</td>
										<td><a href="<%=basePath%>keywords/downModel.action">下载excel模版</a></td>
									</tr>
									<tr>
										<td>
											<input class="control-button" value="导入" id="btnSave" type="button">
											<input type="hidden" value="${msg}" id="hidden_msg">
										</td>
										<td><font color="red">${msg }</font></td>
									</tr>
							</table>
							</form>
						</div>
						<div class="well">
							<font color="red">请先下载模版，按照模版的格式添加关键字，避免操作不当而引起重复的操作。</font><br>
							<font color="red">导入前请先确认该用户已经充值！</font>
						</div>
					</div>
				</div>
			</div>
		</div>




</body>
</html>