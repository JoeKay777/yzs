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
<title>管理员中心-关键词列表</title>
<link rel="stylesheet" href="<%=basePath%>css/userstyle.css">
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
		function del(id) {
			if (confirm("确定要删除改关键词吗？")) {
				$.ajax({
					type : 'POST',
					url : "<%=basePath%>admin/del.action",
					data : {
						keywords_id : id
					},
					dataType : "text",
					success : function(data) {
						if (data == "1") {
							alert("删除成功！");
							window.location.reload();
						} else {
							alert("删除失败！");
						}
				          
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
		
		$(document).ready(function(){
			if($("#pmmsg").val()!=""){
				alert($("#pmmsg").val());
				search(1);
				$(".loadingWrap").remove();
			}
			 $("#pm").click(function () {
				 var file = $("#pmload").val();
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
              	  $('<div class="loadingWrap"></div>').appendTo("body");
	              $("#form1").submit();
	            });
			 $("#cs").click(function () {
				 var file = $("#csload").val();
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
              	  $('<div class="loadingWrap"></div>').appendTo("body");
	              $("#form2").submit();
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
							您当前的位置： <a href="<%=basePath%>admin/index.action">管理员中心</a> <span>&gt;</span> 关键词列表
						</div>
						<div class="main_center">
							<div class="control-set"></div>
							<div class="control-search">
							<div>
								<select id="search_engines" name="search_engines" class="control-select">
									<option value="">--搜索引擎--</option>
									<option value="1" <c:if test="${search_engines eq '1' }">selected="selected"</c:if> >百度PC</option>
									<option value="2" <c:if test="${search_engines eq '2' }">selected="selected"</c:if>>百度手机</option>
									<option value="3" <c:if test="${search_engines eq '3' }">selected="selected"</c:if>>360</option>
								</select> 
								<select name="status" id="status" class="control-select">
									<option value="">---关键词状态---</option>
									<option value="1" <c:if test="${status eq '1' }">selected="selected"</c:if>>待审核</option>
									<option value="2" <c:if test="${status eq '2' }">selected="selected"</c:if>>使用中</option>
									<option value="3" <c:if test="${status eq '3' }">selected="selected"</c:if>>已停用</option>
								</select>
								<select name="user" id="user" class="control-select">
									<option  value="">---用户名---</option>
									<c:forEach items="${userList }" var="u">
										<option value="${u.username }" <c:if test="${username eq u.username }">selected="selected"</c:if>>${u.username }</option>
									</c:forEach>
								</select>
								<select name="pxStyle" id="pxStyle" class="control-select">
									<option value="">--排序方式--</option>
									<option value="1" <c:if test="${pxStyle eq '1' }">selected="selected"</c:if>>添加日期</option>
									<%-- <option value="2" <c:if test="${pxStyle eq '2' }">selected="selected"</c:if>>使用天数</option> --%>
									<option value="3" <c:if test="${pxStyle eq '3' }">selected="selected"</c:if>>最新排名</option>
									<option value="4" <c:if test="${pxStyle eq '4' }">selected="selected"</c:if>>价格</option>
								</select>
								<select name="chupai" id="chupai" class="control-select">
									<option value="">--最新排名--</option>
									<option value="1" <c:if test="${chupai eq '1' }">selected="selected"</c:if>>1-10</option>
									<option value="2" <c:if test="${chupai eq '2' }">selected="selected"</c:if>>11-30</option>
									<option value="3" <c:if test="${chupai eq '3' }">selected="selected"</c:if>>31-50</option>
									<option value="4" <c:if test="${chupai eq '4' }">selected="selected"</c:if>>51-99</option>
									<option value="5" <c:if test="${chupai eq '5' }">selected="selected"</c:if>>100+</option>
									<option value="6" <c:if test="${chupai eq '6' }">selected="selected"</c:if>>未收录</option>
									<option value="7" <c:if test="${chupai eq '7' }">selected="selected"</c:if>>未导入排名</option>
								</select>
								<select name="xinpai" id="xinpai" class="control-select">
									<option value="">--排名情况--</option>
									<option value="1" <c:if test="${xinpai eq '1' }">selected="selected"</c:if>>今日进前3</option>
									<option value="2" <c:if test="${xinpai eq '2' }">selected="selected"</c:if>>今日出前3</option>
									<option value="3" <c:if test="${xinpai eq '3' }">selected="selected"</c:if>>今日进前10</option>
									<option value="4" <c:if test="${xinpai eq '4' }">selected="selected"</c:if>>今日出前10</option>
									<option value="5" <c:if test="${xinpai eq '5' }">selected="selected"</c:if>>今日进前50</option>
									<option value="6" <c:if test="${xinpai eq '6' }">selected="selected"</c:if>>今日出前50</option>
									<option value="7" <c:if test="${xinpai eq '7' }">selected="selected"</c:if>>今日未收录</option>
									<option value="8" <c:if test="${xinpai eq '8' }">selected="selected"</c:if>>今日未导入排名</option>
									<option value="9" <c:if test="${xinpai eq '9' }">selected="selected"</c:if>>今日未更新排名</option>
								</select>
								<select name="pmstate" id="pmstate" class="control-select">
									<option value="">--排名接口--</option>
									<option value="1" <c:if test="${pmstate eq '1' }">selected="selected"</c:if>>正常</option>
									<option value="0" <c:if test="${pmstate eq '0' }">selected="selected"</c:if>>失败</option>
								</select>
								<select name="csdj" id="csdj" class="control-select">
									<option value="">--厂商对接--</option>
									<option value="1" <c:if test="${csdj eq '1' }">selected="selected"</c:if>>已处理</option>
									<option value="0" <c:if test="${csdj eq '0' }">selected="selected"</c:if>>未处理</option>
								</select>
								<select name="pmbh" id="pmbh" class="control-select">
									<option value="">--达标情况--</option>
									<option value="1" <c:if test="${pmbh eq '1' }">selected="selected"</c:if>>达标</option>
									<option value="2" <c:if test="${pmbh eq '2' }">selected="selected"</c:if>>未达标</option>
								</select>
								</div>
								<br>
								 <div align="right">
									 <input id="txtKey" class="control-text" type="text" value="${keywords }"> 
									 <input class="control-button" value="搜索" onclick="javascript:search(1);" type="button">
									 <!--  <input class="control-button" value="修改" onclick="javascript:batchUpt();" type="button"> -->
									  <input class="control-button" value="批量删除" onclick="javascript:batchDel();" type="button">
									   <input class="control-button" value="导出" onclick="javascript:exportTXT();" type="button">
								  </div>
							</div>
										<div  align="right">
											<form method="post"  action="<%=basePath%>admin/loadKeyPm.action" id="form1" enctype="multipart/form-data">
												<input name="file" id="pmload"   type="file" >
												 <input class="control-button" id="pm" value="导入排名" type="button">
												 <input type="hidden" id="pmmsg" value="${pmmsg }">
											</form>
											<form method="post"  action="<%=basePath%>admin/loadKeyCsdj.action" id="form2" enctype="multipart/form-data">
												<input name="file" id="csload"   type="file" >
												 <input class="control-button" id="cs" value="导入厂商" type="button">
											</form>
										</div>
						 	<script type="text/javascript">
							 	$(document).ready(function(e) {
									$("#boss").click(function(){
									if(this.checked){
									    $("[name=bet]").attr("checked",true);}
									else{ $("[name=bet]").attr("checked",false);}
									});
								});
						 	
								function search(currPage) {
									var pl=$("#pl_txtPage").val();
									var key = $("#txtKey").val();
									if(pl!=""){
										currPage=pl;
									}
									var gid = $("#status").val();
									var wid = $("#search_engines").val();
									var username=$("#user").val();
									var pxStyle=$("#pxStyle").val();
									var chupai=$("#chupai").val();
									var xinpai=$("#xinpai").val();
									var pmstate=$("#pmstate").val();
									var pageSize=$("#pageSize").val();
									var csdj=$("#csdj").val();
									var pmbh=$("#pmbh").val();
									var url = "<%=basePath%>admin/keylist.action?keywords=" + escape(escape(key)) + "&status="
											+ gid + "&search_engines=" + wid+"&currPage="+currPage+"&username="
											+ escape(escape(username))+"&pxStyle="+pxStyle+"&chupai="+chupai+"&xinpai="
												+xinpai+"&pmstate="+pmstate+"&pageSize="+pageSize+"&csdj="+csdj+"&pmbh="+pmbh;
									location.href = url;
								}
								
								<%-- function batchUpt(){
									//jquery获取复选框值    
						            var chk_value =[];//定义一个数组    
						            $('input[name="bet"]:checked').each(function(){//遍历每一个名字为interest的复选框，其中选中的执行函数    
							            var domain=$(this).parent().parent().children().eq(4).children().eq(0);
							            var zs=$(this).parent().parent().children().eq(6).children().eq(0);
							            if(zs.val()==null||zs.val()==""){
							            	alert("序号为"+$(this).val()+"的指数不能为空！");
							            	return false;
							            }
							            var price=$(this).parent().parent().children().eq(7).children().eq(0);
							            if(price.val()==null||price.val()==""){
							            	alert("序号为"+$(this).val()+"的单价不能为空！");
							            	return false;
							            }
							            var chupai=$(this).parent().parent().children().eq(15).children().eq(0);
							            if(chupai.val()==null||chupai.val()==""){
							            	alert("序号为"+$(this).val()+"的初始排名不能为空！");
							            	return false;
							            }
							            var xinpai=$(this).parent().parent().children().eq(16).children().eq(0);
							            var remarks=$(this).parent().parent().children().eq(18).children().eq(0);
							            var uptobj={
							            		keywords_id:$(this).val(),
							            		zs:zs.val(),
							            		domain:domain.val(),
							            		price:price.val(),
							            		chupai:chupai.val(),
							            		xinpai:xinpai.val(),
							            		remarks:remarks.val()
							            }
							            chk_value.push(uptobj);
						            });
						            if(chk_value.length!=0){
							            $.ajax({
								            type: "POST",//方法类型
								            dataType: "text",//预期服务器返回的数据类型
								            data:{
								            	uptList:JSON.stringify(chk_value)
								            },
								            url: "<%=basePath%>admin/batchUpt.action",
								            success: function (upt_msg) {
								            	alert(upt_msg);
								            	window.location.reload();
								            },
								            error : function() {
								                alert("异常！请联系客服");
								            },
								            beforeSend: function(){    
								                $('<div class="loadingWrap"></div>').appendTo("body");
								              },   
								              complete: function(){    
								                 $(".loadingWrap").remove();
								              }  
								        });
						            }else{
						            	alert("至少选中一个提交修改");
						            	return false;
						            }
								} --%>
								
								function batchDel(){
									//jquery获取复选框值    
						            var chk_value =[];//定义一个数组    
						            $('input[name="bet"]:checked').each(function(){//遍历每一个名字为interest的复选框，其中选中的执行函数    
							            var keywords=$(this).parent().parent().children().eq(3).children().eq(0);
							            var delObj={
							            		keywords_id:$(this).val(),
							            		keywords:keywords.html()
							            	
							            }
							            chk_value.push(delObj);
						            });
						            if(chk_value.length!=0){
							            $.ajax({
								            type: "POST",//方法类型
								            dataType: "text",//预期服务器返回的数据类型
								            data:{
								            	delList:JSON.stringify(chk_value)
								            },
								            url: "<%=basePath%>admin/batchDel.action",
								            success: function (del_msg) {
								            	alert(del_msg);
								            	window.location.reload();
								            },
								            error : function() {
								                alert("异常！请联系客服");
								            },
								            beforeSend: function(){    
								                $('<div class="loadingWrap"></div>').appendTo("body");
								              },   
								              complete: function(){    
								                 $(".loadingWrap").remove();
								              }  
								        });
						            }else{
						            	alert("至少选中一个提交修改");
						            	return false;
						            }
								}
								
								function xiangqing(keywords,keywords_id){
						 			var url="<%=basePath%>admin/trankeys.action?keywords="+escape(escape(keywords))+"&keywords_id="+keywords_id;
						 			location.href=url;
								}
								
								function check_open(key_id,state) {
									if(confirm("你确认开启吗?")){
										var url = "<%=basePath%>keywords/uptKeywordsStatus.action?keywords_id=" + key_id + "&status="+ state;
										$.ajax({
								            type: "POST",//方法类型
								            dataType: "json",//预期服务器返回的数据类型
								            url: url,
								            success: function (upt) {
								            	if(upt==1){
								            		alert("开启成功");
								            		search(1);
								            	}else{
								            		alert("开启失败,请联系客服");
								            	}
								               
								            },
								            error : function() {
								                alert("异常！请联系客服");
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
								
								function check_stop(key_id,state) {
									if(confirm("你确认停用吗?")){
										var url = "<%=basePath%>keywords/uptKeywordsStatus.action?keywords_id=" + key_id + "&status="+ state;
										$.ajax({
									            type: "POST",//方法类型
									            dataType: "json",//预期服务器返回的数据类型
									            url: url,
									            success: function (upt) {
									            	if(upt==1){
									            		alert("停用成功");
									            		search(1);	            		
									            	}else{
									            		alert("停用失败,请联系客服");
									            	}
									               
									            },
									            error : function() {
									                alert("异常！请联系客服");
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
								
								function exportTXT(){
									var key = $("#txtKey").val();
									var gid = $("#status").val();
									var wid = $("#search_engines").val();
									var username=$("#user").val();
									var chupai=$("#chupai").val();
									var xinpai=$("#xinpai").val();
									var pmstate=$("#pmstate").val();
									var csdj=$("#csdj").val();
									var pmbh=$("#pmbh").val();
									var url = "<%=basePath%>admin/exportKeysTXT.action?keywords=" + escape(escape(key)) + "&status="
											+ gid + "&search_engines=" + wid+"&username="
											+ escape(escape(username))+"&chupai="+chupai+"&xinpai="
											+xinpai+"&pmstate="+pmstate+"&csdj="+csdj+"&pmbh="+pmbh;
									location.href = url;
								}
								
								function uptPrice(id){
									var td=$("#price"+id);
									var txt = td.text();
									var input = $("<input type='text'  size='2' value='" + txt + "' />"); 
									td.html(input);
									//文本框失去焦点后提交内容，重新变为文本 
									input.blur(function() { 
									var newtxt = $(this).val(); 
									if(newtxt==""||newtxt==null){
										alert("价格不能为空");
										return false;
									}
									//判断文本有没有修改 
									if (newtxt != txt) { 
									//	var caid = $.trim(td.prev().text()); 
										var url ="<%=basePath%>admin/uptPrice.action";
										var data={
												keywords_id:id,
												price:newtxt
										}
										$.ajax({
								            type: "POST",//方法类型
								            url: url,
								            data:data,
								            success: function (upt) {
								            	if(upt==1){
								            		td.html(newtxt);           		
								            	}else{
								            		td.html(txt); 
								            	}
								               
								            },
								            error : function() {
								                alert("异常！请联系客服");
								            },
								            beforeSend: function(){    
							                    $('<div class="loadingWrap"></div>').appendTo("body");
							                  },   
							                  complete: function(){    
							                     $(".loadingWrap").remove();
							                  }  
								        });
										
									} 
									else 
									{ 
									td.html(newtxt); 
									} 
								}); 
								}
								function uptChupai(id){
									var td=$("#chupai"+id);
									var txt = td.text().trim();
									var input = $("<input type='text'  size='1' value='" + txt + "' />"); 
									td.html(input);
									//文本框失去焦点后提交内容，重新变为文本 
									input.blur(function() { 
									var newtxt = $(this).val(); 
									if(newtxt==""||newtxt==null){
										alert("初始排名不能为空");
										return false;
									}
									//判断文本有没有修改 
									if (newtxt != txt) { 
									//	var caid = $.trim(td.prev().text()); 
										var url ="<%=basePath%>admin/uptChupai.action";
										var data={
												keywords_id:id,
												chupai:newtxt
										}
										$.ajax({
								            type: "POST",//方法类型
								            url: url,
								            data:data,
								            success: function (upt) {
								            	if(upt==1){
								            		td.html(newtxt); 
								            	}else{
								            		td.html(txt); 
								            	}
								               
								            },
								            error : function() {
								                alert("异常！请联系客服");
								            },
								            beforeSend: function(){    
							                    $('<div class="loadingWrap"></div>').appendTo("body");
							                  },   
							                  complete: function(){    
							                     $(".loadingWrap").remove();
							                  }  
								        });
										
									} 
									else 
									{ 
									td.html(newtxt); 
									} 
								}); 
								}
								function uptXinpai(id){
									var td=$("#xinpai"+id);
									var txt = td.text().trim();
									var input = $("<input type='text'  size='1' value='" + txt + "' />"); 
									td.html(input);
									//文本框失去焦点后提交内容，重新变为文本 
									input.blur(function() { 
									var newtxt = $(this).val(); 
									//判断文本有没有修改 
									if (newtxt != txt) { 
									//	var caid = $.trim(td.prev().text()); 
										var url ="<%=basePath%>admin/uptXinpai.action";
										var data={
												keywords_id:id,
												xinpai:newtxt
										}
										$.ajax({
								            type: "POST",//方法类型
								            url: url,
								            data:data,
								            success: function (upt) {
								            	if(upt==1){
								            		td.html(newtxt);
								            	}else{
								            		td.html(txt); 
								            	}
								               
								            },
								            error : function() {
								                alert("异常！请联系客服");
								            },
								            beforeSend: function(){    
							                    $('<div class="loadingWrap"></div>').appendTo("body");
							                  },   
							                  complete: function(){    
							                     $(".loadingWrap").remove();
							                  }  
								        });
										
									} 
									else 
									{ 
									td.html(newtxt); 
									} 
								}); 
								}
								function uptRemarks(id){
									var td=$("#remarks"+id);
									var txt = td.text();
									var input = $("<input type='text'  size='3' value='" + txt + "' />"); 
									td.html(input);
									input.blur(function() { 
									var newtxt = $(this).val(); 
									if (newtxt != txt) { 
										var url ="<%=basePath%>admin/uptRemarks.action";
										var data={
												keywords_id:id,
												remarks:newtxt
										}
										$.ajax({
								            type: "POST",//方法类型
								            url: url,
								            data:data,
								            success: function (upt) {
								            	if(upt==1){
								            		td.html(newtxt);           		
								            	}else{
								            		td.html(txt); 
								            	}
								               
								            },
								            error : function() {
								                alert("异常！请联系客服");
								            },
								            beforeSend: function(){    
							                    $('<div class="loadingWrap"></div>').appendTo("body");
							                  },   
							                  complete: function(){    
							                     $(".loadingWrap").remove();
							                  }  
								        });
										
									} 
									else 
									{ 
									td.html(newtxt); 
									} 
								}); 
								}
								
								function uptKkstyle(id){
									var td=$("#kkstyle"+id);
									var txt = td.text().trim();
									var input = $("<input type='radio' name='kk' value='1' /><span>进前10</span><input type='radio' name='kk' value='2' /><span>进前3</span>"); 
									td.html(input); 
									input.change(function() { 
									var newtxt = $(this).val(); 
									if (newtxt != "") { 
										var url ="<%=basePath%>admin/uptStyle.action";
										var data={
												keywords_id:id,
												kkstyle:newtxt
										}
										$.ajax({
								            type: "POST",//方法类型
								            url: url,
								            data:data,
								            success: function (upt) {
								            	if(upt==1){
								            		if(newtxt=='1'){
														td.html("进前10");
													}
													if(newtxt=='2'){
														td.html("进前3");
													}          		
								            	}else{
								            		td.html(txt);
								            	}
								               
								            },
								            error : function() {
								                alert("异常！请联系客服");
								            },
								            beforeSend: function(){    
							                    $('<div class="loadingWrap"></div>').appendTo("body");
							                  },   
							                  complete: function(){    
							                     $(".loadingWrap").remove();
							                  }  
								        });
										
									} 
								}); 
								}
								
								function uptCsdj(id){
									var td=$("#csdj"+id);
									var txt = td.text().trim();
									var input = $("<input type='radio' name='dj' value='1' /><span>已处理</span><input type='radio' name='dj' value='0' /><span>未处理</span>"); 
									td.html(input); 
									input.change(function() { 
									var newtxt = $(this).val(); 
									if (newtxt != "") { 
										var url ="<%=basePath%>admin/uptCsdj.action";
										var data={
												keywords_id:id,
												csdj:newtxt
										}
										$.ajax({
								            type: "POST",//方法类型
								            url: url,
								            data:data,
								            success: function (upt) {
								            	if(upt==1){
								            		if(newtxt=='1'){
														td.html("<font color='green'>已处理</font>");
													}
													if(newtxt=='0'){
														td.html("<font color='red'>未处理</font>");
													}          		
								            	}else{
								            		td.html(txt);
								            	}
								               
								            },
								            error : function() {
								                alert("异常！请联系客服");
								            },
								            beforeSend: function(){    
							                    $('<div class="loadingWrap"></div>').appendTo("body");
							                  },   
							                  complete: function(){    
							                     $(".loadingWrap").remove();
							                  }  
								        });
										
									} 
								}); 
								}
								
								function uptDomain(id){
									var td=$("#domain"+id);
									var txt = td.text();
									var input = $("<input type='text'   value='" + txt + "' />"); 
									td.html(input);
									input.blur(function() { 
									var newtxt = $(this).val(); 
									if (newtxt != txt) { 
										var url ="<%=basePath%>admin/uptDomain.action";
										var data={
												keywords_id:id,
												domain:newtxt
										}
										$.ajax({
								            type: "POST",//方法类型
								            url: url,
								            data:data,
								            success: function (upt) {
								            	if(upt==1){
								            		td.html(newtxt);           		
								            	}else{
								            		td.html(txt); 
								            	}
								               
								            },
								            error : function() {
								                alert("异常！请联系客服");
								            },
								            beforeSend: function(){    
							                    $('<div class="loadingWrap"></div>').appendTo("body");
							                  },   
							                  complete: function(){    
							                     $(".loadingWrap").remove();
							                  }  
								        });
										
									} 
									else 
									{ 
									td.html(newtxt); 
									} 
								}); 
								}
								
							</script>
							<table class="table table-striped m-table" cellspacing="0"
								cellpadding="0" border="1">
								<thead>
									<tr>
										<th><input type="checkbox" id="boss"  /></th>
										<th>标识号</th>
										<th>用户名</th>
										<th>关键词</th>
										<th>类型</th>
										<th>域名</th>
										<th>指数</th>
										<th>单价</th>
										<th>扣款类型</th>
										<th>扣费</th>
										<th>状态</th>
										<th>添加时间</th>
								<!-- 		<th>指数更新时间</th> -->
										<th>排名更新时间</th>
										<th>使用天数</th>
										<th>达标天数</th>
									<!-- 	<th>初始排名</th> -->
										<th>昨日排名</th>
										<th>最新排名</th>
										<th>达标状态</th>
										<th>厂商</th>
										<th width="100px">备注</th>
										<th>操作</th>
									</tr>
								</thead>
								<c:forEach items="${rows}" var="row">
										<tr>
											<td><input type="checkbox" name="bet" value="${row.keywords_id }" /></td> 
											<td>${row.keywords_id }</td>
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
											<!--//类型：-->
											<td id="domain${row.keywords_id }" ondblclick="uptDomain(${row.keywords_id})">${row.domain_address }</td>
											<!--//域名：-->
											<c:if test="${row.search_engines eq '1' }">
												<td>${row.allIndex }</td>
											</c:if>
											<c:if test="${row.search_engines eq '2' }">
												<td>${row.mobileIndex }</td>
											</c:if>
											<c:if test="${row.search_engines eq '3' }">
												<td>${row.so360Index }</td>
											</c:if>
											<td id="price${row.keywords_id }" ondblclick="uptPrice(${row.keywords_id})">${row.price}</td>
											<td id="kkstyle${row.keywords_id }" ondblclick="uptKkstyle(${row.keywords_id})">
											<c:if test="${row.kkstyle eq '1' }">进前10</c:if>
											<c:if test="${row.kkstyle eq '2' }">进前3</c:if>
											</td>
											<td><a href="javascript:xiangqing('${row.keywords }',${row.keywords_id })">详情</a></td>
											<c:if test="${row.state eq '1' }">
											<td><font color="green">待审核</font></td>
											</c:if>
											<c:if test="${row.state eq '2' }">
											<td><font color="green">使用中</font></td>
											</c:if>
											<c:if test="${row.state eq '3' }">
											<td><font color="green">已停用</font></td>
											</c:if>
											<!--//状态：-->
											<td>${row.add_date }</td>
										<%-- 	<td>${row.zsupt_date }</td> --%>
											<td>${row.pmupt_date }</td>
											<td>${row.consume_datenum }</td>
											<td>${row.dabiao_datenum }</td>
										<%-- 	<!--//最后更新时间：-->
											<td id="chupai${row.keywords_id }" ondblclick="uptChupai(${row.keywords_id})">
											<c:if test="${row.chupai eq '9999' }">--</c:if>
											<c:if test="${row.chupai eq '10000' }">未收录</c:if>
											<c:if test="${row.chupai ne '9999' }"><c:if test="${row.chupai ne '10000' }">${row.chupai }</c:if></c:if>
											</td> --%>
											<td>
											<c:if test="${row.zrpai eq '9999' }">--</c:if>
											<c:if test="${row.zrpai eq '10000' }">未收录</c:if>
											<c:if test="${row.zrpai ne '9999' }"><c:if test="${row.zrpai ne '10000' }">${row.zrpai }</c:if></c:if>
											</td>
											<!--//初始排名：-->
											<td id="xinpai${row.keywords_id }" ondblclick="uptXinpai(${row.keywords_id})">
											<c:if test="${row.xinpai eq '9999' }">--</c:if>
											<c:if test="${row.xinpai eq '10000' }">未收录</c:if>
											<c:if test="${row.xinpai ne '9999' }"><c:if test="${row.xinpai ne '10000' }">${row.xinpai }</c:if></c:if>
											</td>
											<!--//最新排名：-->
											<c:if test="${row.pmbh eq '1' }">
											<td>达标</td>
											</c:if>
											<c:if test="${row.pmbh eq '2' }">
											<td>未达标</td>
											</c:if>
											<c:if test="${row.pmbh eq '--' }">
											<td>--</td>
											</c:if>
											<td id="csdj${row.keywords_id }" ondblclick="uptCsdj(${row.keywords_id})">
											<c:if test="${row.csdj eq '1' }"><font color="green">已处理</font></c:if>
											<c:if test="${row.csdj eq '0' }"><font color="red">未处理</font></c:if>
											</td>
											<td id="remarks${row.keywords_id }" ondblclick="uptRemarks(${row.keywords_id})">${row.remarks}</td>
											<td>
											<c:if test="${row.state eq '2' }">
											<a href="javascript:check_stop(${row.keywords_id },3);"><font color="blue">停用|</font></a>
											</c:if>
											<c:if test="${row.state eq '3' }">
											<a href="javascript:check_open(${row.keywords_id },2);"><font color="blue">启用|</font></a>
											</c:if>
											<a href="javascript:del(${row.keywords_id });">删除</a> </td>
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
						</div>
					</div>
				</div>
			</div>
		</div>



	
	<div
		style="display: none; position: fixed; left: 0px; top: 0px; width: 100%; height: 100%; cursor: move; opacity: 0; background: rgb(255, 255, 255) none repeat scroll 0% 0%;"></div>
</body>
</html>