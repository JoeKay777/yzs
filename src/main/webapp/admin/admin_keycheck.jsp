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
<title>管理员中心-关键词审核</title>
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
							您当前的位置： <a href="<%=basePath%>admin/index.action">管理员中心</a> <span>&gt;</span> 关键词审核
						</div>
						<div class="main_center">
							<div class="control-set"></div>

					<form method="post"  id="form1">
							<input type="hidden" id="hidden_flag" value=""/>	
							<input type="hidden" id="hidden_qy" value="${qycount }"/>
							<div class="control-search">
								<select id="search_engines" name="search_engines" class="control-select">
									<option value="">--搜索引擎--</option>
									<option value="1" <c:if test="${search_engines eq '1' }">selected="selected"</c:if> >百度PC</option>
									<option value="2" <c:if test="${search_engines eq '2' }">selected="selected"</c:if>>百度手机</option>
									<option value="3" <c:if test="${search_engines eq '3' }">selected="selected"</c:if>>360</option>
								</select> 
								<select name="user" id="user" class="control-select">
									<option  value="">---用户名---</option>
									<c:forEach items="${userList }" var="u">
										<option value="${u.username }" <c:if test="${username eq u.username }">selected="selected"</c:if>>${u.username }</option>
									</c:forEach>
								</select>
								 <input class="control-button" value="搜索" onclick="javascript:search(1);" type="button">
								  <input class="control-button" value="一键启用" onclick="javascript:qiyong(1);" type="button">
								   <input class="control-button" value="导出excel" onclick="javascript:keyExcel();" type="button">
							</div>
						 	<script type="text/javascript">
						 		$(function(){
						 			var hidden_qy=$("#hidden_qy").val();
						 			if(hidden_qy!=""){
						 	            $(".loadingWrap").remove();
						 				alert("启用关键字"+hidden_qy+"条");
						 			}
						 		});
						 		function qiyong(currPage){
						 			if(confirm("你确认开启吗？？？操作前请先下载报表！！！")){
						 				if($("#hidden_flag").val()=="1"){
								 			var pl=$("#pl_txtPage").val();
											if(pl!=""){
												currPage=pl;
											}
											var wid = $("#search_engines").val();
											var username=$("#user").val();
											var pageSize=$("#pageSize").val();
											var url = "<%=basePath%>admin/keychecklist.action?search_engines="
													+ wid+"&currPage="+currPage+"&username="+ escape(escape(username))+"&qyFlag=1&pageSize="+pageSize;
											window.location.href = url;
								            $('<div class="loadingWrap"></div>').appendTo("body");
						 				}else{
						 					alert("请先下载报表！");
						 				}
						 			}
						 		}
								function search(currPage) {
									var pl=$("#pl_txtPage").val();
									if(pl!=""){
										currPage=pl;
									}
									var wid = $("#search_engines").val();
									var username=$("#user").val();
									var pageSize=$("#pageSize").val();
									var url = "<%=basePath%>admin/keychecklist.action?search_engines="
										+ wid+"&currPage="+currPage+"&username="+ escape(escape(username))+"&pageSize="+pageSize;
									location.href = url;
								}
								function keyExcel(){
										var url="<%=basePath%>keywords/exportCheckKeys.action";
										window.location.href=url;
										$("#hidden_flag").val(1);
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
										<th style="width: 28px;">序号</th>
										<th>用户名</th>
										<th>关键词</th>
										<th>类型</th>
										<th>域名</th>
										<th>指数</th>
										<th>单价</th>
										<th>扣款类型</th>
										<th>状态</th>
										<th>添加时间</th>
										<th>操作</th>
									</tr>
								</thead>
								<c:forEach items="${rows}" var="row">
										<tr>
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
											<!--//指数：-->
											<td><span style="color: red">${row.price }</span></td>
											<td id="kkstyle${row.keywords_id }" ondblclick="uptKkstyle(${row.keywords_id})">
											<c:if test="${row.kkstyle eq '1' }">进前10</c:if>
											<c:if test="${row.kkstyle eq '2' }">进前3</c:if>
											</td>
											<!--//单价：-->
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
											<td>
											<c:if test="${row.state eq '2' }">
											<a href="javascript:check_stop(${row.keywords_id },2);"><font color="blue">停用|</font></a>
											</c:if>
											<c:if test="${row.state eq '3' }">
											<a href="javascript:check_open(${row.keywords_id },1);"><font color="blue">启用|</font></a>
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
											<a class="cur" href="javascript:search(${currPage })">${currPage }</a> 
											<a class="nextBtn tBtn" href="javascript:search(${currPage+1 });">下页</a> 
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
		$(function() {
			$("#select").click(function() {
				if ($(this).prop("checked")) {
					$("input:checkbox").each(function() {
						this.checked = true;
					});
				} else {
					$("input:checkbox").each(function() {
						this.checked = false;
					});

				}
			});
		});

		function check_open(key_id,state) {
			confirm("你确认开启吗?");
			if($("#hidden_flag").val()=="1"){
					var url = "<%=basePath%>keywords/uptKeywordsStatus.action?keywords_id=" + key_id + "&status="+ state;
					location.href = url;
					$.ajax({
			            type: "POST",//方法类型
			            dataType: "json",//预期服务器返回的数据类型
			            url: url,
			            success: function (upt) {
			            	if(upt==1){
			            		alert("开启成功");
			            		window.location.reload();
			            	}else{
			            		alert("开启失败");
			            	}
			            },
			            error : function() {
			                alert("异常！");
			            }
			        });
				}else{
					alert("请先下载报表！");
				}
		}

		function check_stop(key_id,state) {
			confirm("你确认停用吗?");
			var url = "<%=basePath%>keywords/uptKeywordsStatus.action?keywords_id=" + key_id + "&status="+ state;
			$.ajax({
		            type: "POST",//方法类型
		            dataType: "json",//预期服务器返回的数据类型
		            url: url,
		            success: function (upt) {
		            	if(upt==1){
		            		alert("停用成功");
		            		window.location.reload();		            		
		            	}else{
		            		alert("停用失败,请联系客服");
		            	}
		            },
		            error : function() {
		                alert("异常！请联系客服");
		            }
		        });
		}

		function check_del() {
			if (check_select() == false) {
				alert("请至少选择一项要删除的关键词!");
				return false;
			}
			return confirm("该操作将删除操作关键词的所有相关数据，您确认删除吗 ？");
		}

		function check_select() {
			var isSelect = false;
			$("input:checkbox").each(function() {
				if (this.checked == true && this.id != "select") {
					isSelect = true;
				}
			});
			return isSelect;
		}

		function view(id) {
			art.dialog.open('keys_view.aspx?id=' + id, {
				title : '关键词详情',
				width : "750px",
				height : "278px",
				lock : true
			});
		}

		function ranking(id) {
			art.dialog.open('ranking_view.aspx?id=' + id, {
				title : '排名详情',
				width : "750px",
				height : "500px",
				lock : true
			});
		}

		function del(id) {
			if (confirm("确定要删除改关键词吗？")) {
				$.ajax({
					type : 'POST',
					url : "<%=basePath%>keywords/del.action",
					data : {
						keywords_id : id
					},
					success : function(data) {
						if (data == "1") {
							window.location.reload();
						} else {
							alert("删除失败！");
						}
					},
					dataType : "text"
				});
			}

		}

		function open_num(id) {
			art.dialog.open('keys_set.aspx?id=' + id, {
				title : '关键词点击数设置',
				width : "750px",
				height : "298px",
				lock : true
			});
		}
	</script>
	<div
		style="display: none; position: fixed; left: 0px; top: 0px; width: 100%; height: 100%; cursor: move; opacity: 0; background: rgb(255, 255, 255) none repeat scroll 0% 0%;"></div>
</body>
</html>