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
<title>会员中心-新增关键词</title>
<link rel="stylesheet" href="<%=basePath%>css/userstyle.css">
	<script type="text/javascript" src="<%=basePath%>js/common.js"></script>
	<script type="text/javascript" src="<%=basePath%>js/jquery-1.7.2.js"></script>
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
					                var rechange_status=$("#hidden_re").val();
					                if(rechange_status=="1"){
					                	$('<div class="loadingWrap"></div>').appendTo("body");
					                	$("#form1").submit();     
					                }else if(rechange_status=="2"){
					                	alert("老铁，您的余额已不足，请联系客服充值后再来~~");
					                }else if(rechange_status=="0"){
					                	alert("老铁，您还未充值，请联系客服充值后再来~~");
					                }
					               
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
						<a href="<%=basePath%>user/index.action">首页</a>
							&nbsp;&nbsp; 当前用户：<span id="top_lblName">${sessionScope.user.username}</span>
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
							关键词导入
						</div>
						<div class="main_center">
						<form method="post"  action="<%=basePath%>keywords/uploadKeyFile.action" id="form1" enctype="multipart/form-data">
							<table class="table table-c">
								<tbody>
									<tr>
										<td style="width: 12%;"><span class="must">*</span>
											选择本地excel表：</td>
										<td style="width: 45%;"><input name="file"
											id="fileUpload1" type="file" value="${file }"></td>
										<td><a href="<%=basePath%>keywords/downModel.action">下载excel模版</a></td>
									</tr>
									<tr>
										<td>&nbsp;</td>
										<td><input name="btnSave" value="导 入" id="btnSave"
											type="button">&nbsp;&nbsp;
											<input type="hidden" value="${u.rechange_status }" id="hidden_re">
											<input type="hidden" value="${msg}" id="hidden_msg">
											</td>
									</tr>
									<tr>
										<td>&nbsp;</td>
										<td><font color="red">${msg }</font></td>
									</tr>
								</tbody>
							</table>
							</form>
						</div>
						<div class="well">
							<font color="red">请先下载模版，按照模版的格式添加关键字，避免操作不当而引起重复的操作。</font><br>
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