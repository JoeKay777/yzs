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
<title>会员中心-关键词管理</title>
<link rel="stylesheet" href="<%=basePath%>css/userstyle.css">
<script type="text/javascript" src="<%=basePath%>js/jquery-1.7.2.js"></script>
	<link rel="stylesheet" href="<%=basePath%>css/blue.css">
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

					<script type="text/javascript">
						function LoginOut() {
							if (confirm("您确定要退出吗？")) {
								location.href = "<%=basePath%>user/loginOut.action";
							}
						}
					</script>
					<div class="tool_main">
						<div class="ce_bread">
							您当前的位置： <a href="<%=basePath%>user/index.action">会员中心</a> <span>&gt;</span> 关键词列表
						</div>
						<div class="main_center">
							<div class="control-set"></div>

					<form method="post"  id="form1">
							<div class="control-search">
								<select id="search_engines" name="search_engines" class="control-select">
									<option value="">--搜索引擎--</option>
									<option value="1" <c:if test="${search_engines eq '1' }">selected="selected"</c:if> >百度PC</option>
									<option value="2" <c:if test="${search_engines eq '2' }">selected="selected"</c:if>>百度手机</option>
									<option value="3" <c:if test="${search_engines eq '3' }">selected="selected"</c:if>>360</option>
								</select> 
								<select name="status" id="status" class="control-select">
									<option value="">--关键词状态--</option>
									<option value="1" <c:if test="${status eq '1' }">selected="selected"</c:if>>待审核</option>
									<option value="2" <c:if test="${status eq '2' }">selected="selected"</c:if>>使用中</option>
									<option value="3" <c:if test="${status eq '3' }">selected="selected"</c:if>>已停用</option>
								</select>
								<select name="pxStyle" id="pxStyle" class="control-select">
									<option value="">--排序方式--</option>
									<option value="1" <c:if test="${pxStyle eq '1' }">selected="selected"</c:if>>添加日期</option>
					<%-- 				<option value="2" <c:if test="${pxStyle eq '2' }">selected="selected"</c:if>>使用天数</option> --%>
									<option value="3" <c:if test="${pxStyle eq '3' }">selected="selected"</c:if>>最新排名</option>
								</select>
								<select name="pmbh" id="pmbh" class="control-select">
									<option value="">--是否达标--</option>
									<option value="1" <c:if test="${pmbh eq '1' }">selected="selected"</c:if>>达标</option>
									<option value="2" <c:if test="${pmbh eq '2' }">selected="selected"</c:if>>未达标</option>
								</select>
								  <input id="txtKey" class="control-text" type="text" value="${keywords }"> 
								 <input class="control-button" value="搜索" onclick="javascript:search(1);" type="button">
							</div>
						 	<script type="text/javascript">
						 	
								function search(currPage) {
									var pl=$("#pl_txtPage").val();
									var key = $("#txtKey").val();
									if(pl!=""){
										currPage=pl;
									}
									var gid = $("#status").val();
									var wid = $("#search_engines").val();
									var pxStyle=$("#pxStyle").val();
									var pmbh=$("#pmbh").val();
									var pageSize=$("#pageSize").val();
									var url = "<%=basePath%>keywords/keywordsListShow.action?keywords=" + escape(escape(key)) + "&status="
											+ gid + "&search_engines=" + wid+"&currPage="+currPage+"&pxStyle="+pxStyle+"&pmbh="+pmbh+"&pageSize="+pageSize;
									location.href = url;
								}
							</script>
							<table class="table table-striped m-table" cellspacing="0"
								cellpadding="0" border="1">
								<thead>
									<tr>
										<th style="width: 40px;">标识号</th>
										<th>关键词</th>
										<th>类型</th>
										<th>域名</th>
										<th>指数</th>
										<th>单价</th>
										<th>扣费</th>
										<th>状态</th>
										<th>添加时间</th>
										<th>排名更新时间</th>
										<th>使用天数</th>
										<th>达标天数</th>
									<!-- 	<th>初始排名</th> -->
										<th>昨日排名</th>
										<th>最新排名</th>
										<th>达标状态</th>
										<!-- <th>操作</th> -->
									</tr>
								</thead>
								<c:forEach items="${rows}" var="row">
										<tr>
											<td>${row.keywords_id }</td>
											<!--序号：-->
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
											<td>${row.domain_address }</td>
											<c:if test="${row.search_engines eq '1' }">
												<td>${row.allIndex }</td>
											</c:if>
											<c:if test="${row.search_engines eq '2' }">
												<td>${row.mobileIndex }</td>
											</c:if>
											<c:if test="${row.search_engines eq '3' }">
												<td>${row.so360Index }</td>
											</c:if>
											<td><span style="color: red">${row.price }</span></td>
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
											<td>${row.add_date }</td>
											<td>${row.pmupt_date }</td>
											<td>${row.consume_datenum }</td>
											<td>${row.dabiao_datenum }</td>
											<%-- <td>
											<c:if test="${row.chupai eq '9999' }">--</c:if>
											<c:if test="${row.chupai eq '10000' }">未收录</c:if>
											<c:if test="${row.chupai ne '9999' }"><c:if test="${row.chupai ne '10000' }">${row.chupai }</c:if></c:if>
											</td> --%>
											<td>
											<c:if test="${row.zrpai eq '9999' }">--</c:if>
											<c:if test="${row.zrpai eq '10000' }">未收录</c:if>
											<c:if test="${row.zrpai ne '9999' }"><c:if test="${row.zrpai ne '10000' }">${row.zrpai }</c:if></c:if>
											</td>
											<td>
											<font color="red">
											<c:if test="${row.xinpai eq '9999' }">--</c:if>
											<c:if test="${row.xinpai eq '10000' }">未收录</c:if>
											<c:if test="${row.xinpai ne '9999' }"><c:if test="${row.xinpai ne '10000' }">${row.xinpai }</c:if></c:if>
											</font>
											</td>
											<c:if test="${row.pmbh eq '1' }">
											<td>达标</td>
											</c:if>
											<c:if test="${row.pmbh eq '2' }">
											<td>未达标</td>
											</c:if>
											<c:if test="${row.pmbh eq '--' }">
											<td>--</td>
											</c:if>
										<%-- 	<!--变化：-->
											<td>
											<c:if test="${row.state eq '2' }">
											<a href="javascript:check_stop(${row.keywords_id },3);"><font color="blue">停用|</font></a>
											</c:if>
											<input type="hidden" id="hidden1" value="${currPage }"/>
											<c:if test="${row.state eq '3' }">
											<a href="javascript:check_open(${row.keywords_id },2);"><font color="blue">启用|</font></a>
											</c:if>
											 <a href="javascript:del(${row.keywords_id });">删除</a></td> --%>
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
							<!-- <div class="well">
								您当前的用户等级为<font color="blue">普通会员</font>,关键词最大管理数量为<font
									color="red">###</font>条，还可以添加<font color="red">####</font>条
							</div> -->
								</form>
						</div>
					</div>
				</div>
			</div>
		</div>



	<script type="text/javascript">
		/* $(function() {
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
 */
 function xiangqing(keywords,keywords_id){
		var url="<%=basePath%>money/tranKeys.action?keywords="+escape(escape(keywords))+"&keywords_id="+keywords_id;
		location.href=url;
	}
		function check_open(key_id,state) {
			confirm("你确认开启吗?");
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
	            		alert("开启失败,请联系客服");
	            	}
	            },
	            error : function() {
	                alert("异常！请联系客服");
	            }
	        });
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


	/* 	function view(id) {
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
 */
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

	/* 	function open_num(id) {
			art.dialog.open('keys_set.aspx?id=' + id, {
				title : '关键词点击数设置',
				width : "750px",
				height : "298px",
				lock : true
			});
		} */
	</script>
	<div
		style="display: none; position: fixed; left: 0px; top: 0px; width: 100%; height: 100%; cursor: move; opacity: 0; background: rgb(255, 255, 255) none repeat scroll 0% 0%;"></div>
</body>
</html>