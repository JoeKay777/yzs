<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path+"/";
	pageContext.setAttribute("baseUrl", basePath);
%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>管理员中心-添加关键词</title>
<link rel="stylesheet" href="<%=basePath %>css/userstyle.css">
	<script type="text/javascript" src="<%=basePath %>js/jquery-1.7.2.js"></script>
	<script type="text/javascript" src="<%=basePath %>js/common.js"></script>
	<script type="text/javascript" src="<%=basePath%>js/inputselect.js"></script>
	<link rel="stylesheet" href="<%=basePath %>css/blue.css">
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
var arrKey = new Array();
function check_submit()
{
    var ret = true;
    if (!check_null("dlUser","tipUser","请选择客户",true)) { return;}
    else if (!check_null("dlWeb","tipWeb","请选择类型",true)) { return;}
    else if (!check_null("txtAddress","tipAddress","请输入域名",true)) { return;}
    else if (!check_null("txtKeys","tipKeys","请输入关键词",true)) { return;}
    
    var patrn=/^[0-9a-zA-Z]+[0-9a-zA-Z\.-]*\.[a-zA-Z]{2,4}$/;
    var address = $.trim($("#txtAddress").val());
    
    if (!confirm("您确定提交吗？"))
    {
        return;
    }
    var strKeys = $.trim($("#txtKeys").val());
    window.setTimeout(key_post(strKeys),1000);     
}


function key_post(key)
{
	var username=$.trim($("#dlUser").val());
    var address = $.trim($("#txtAddress").val());
    var webid = $.trim($("#dlWeb").val());
    var posturl = "<%=basePath%>keywords/loadKeywords.action";
    var data={
    		username:username,
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
							您当前的位置： <a href="<%=basePath%>admin/index.action">管理员中心</a> <span>&gt;</span>添加关键词
						</div>
						<div class="main_center">
							<form  id="form1">
							<table class="table table-c" cellspacing="0" cellpadding="0"
								border="0">
								<tbody>
									<tr>
										<td style="width: 12%;"><span class="must">*</span> 客户名：</td>
										<td style="width: 45%;">
											<input id="dlUser" name="username" list="tip" class="control-select" placeholder="请输入用户名"  value="" oninput="inputselect()" type="text"><br/>
											<input type="hidden" id="input_url"  value="<%=basePath%>user/getUsername.action">
											<datalist id="tip" ></datalist>   
										</td>
										<td><span class="control-info control-info-red"
											id="tipUser"></span></td>
									</tr>
									<tr>
										<td style="width: 12%;"><span class="must">*</span> 类型：</td>
										<td style="width: 45%;">
										<select name="dlWeb" id="dlWeb"
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
										<td style="width: 45%;">
										<input name="txtAddress" id="txtAddress" class="control-text w-md" type="text">
										</td>
										<td><span class="control-info control-info-red"
											id="tipAddress"></span></td>
									</tr>
									<tr>
										<td colspan="2"><span>域名可以是主域名（如：www.baidu.com），也可以是2级域名（如：www.zhidao.baidu.com）</span></td>
									</tr>
									<tr>
										<td><span class="must">*</span> 关键词：</td>
										<td style="width: 45%;">
										<textarea name="txtKeys"
												rows="10" cols="40" id="txtKeys"></textarea>
										</td>
										<td>
											<span class="control-info control-info-red"
											id="tipKeys"></span>
										</td>
									</tr>
									<tr>
										<td colspan="2"><span>查询关键词百度指数，可一次性查10个词，关键词用英文逗号隔开。</span></td>
									</tr>
									<tr>
										<td>&nbsp;</td>
										<td><input value="提 交" class="control-button btn-tj"
											onclick="javascript:check_submit();" type="button"></td>
									</tr>
								</tbody>
							</table>
						</form>
						</div>
						<div class="well">
							<font color="red">导入前请先确认该用户已经充值！</font>
						</div>
					</div>
				</div>
			</div>
		</div>


</body>
</html>