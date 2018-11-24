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
<head>
<meta http-equiv="content-type" content="text/html; charset=UTF-8">
<title>会员中心</title>
<link rel="stylesheet" href="<%=basePath %>css/userstyle.css">
<script type="text/javascript" src="<%=basePath %>js/common.js"></script>
<script type="text/javascript" src="<%=basePath%>js/jquery-1.7.2.js"></script>
</head>

<body>
		<div class="mNav">
			<div class="container">
				<ul class="nav_list clearfix">
					<li><span
						style="color: #ffffff; font-size: 16px; padding-left: 10px; padding-top: 10px; line-height: 40px;">
						<a href="<%=basePath%>user/index.action">首页</a>
							&nbsp;&nbsp; 当前用户：<span id="top_lblName">${sessionScope.user.username}</span>
							&nbsp;&nbsp; 余额：<span id="top_lblName">${sessionScope.user.balance }</span>
							&nbsp;&nbsp; 消息：<span id="top_lblName"  >${sessionScope.user.user_msg }</span>
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
					<script type="text/javascript" src="<%=basePath %>js/echarts.js"></script>
					<script type="text/javascript">
						function LoginOut() {
							if (confirm("您确定要退出吗？")) {
								location.href = "<%=basePath%>user/loginOut.action";
							}
						}
						  //指定图标的配置和数据
						$(document).ready(function(){
							  var dabiaoArray=jQuery("#dabiaoArray").val().replace("]","").replace("[","");
							  var dateArray=jQuery("#dateArray").val().replace("]","").replace("[","");
							  var arrDate = [];
							  arrDate=dateArray.split(",");
							  var arrDabiaoNum=[];
							  arrDabiaoNum=dabiaoArray.split(",");
							  var option = {
							            title:{
							                text:'进十日达标情况',
							                left: 'center'
							            },
							            tooltip:{},
							            legend:{
							                data:['用户来源']
							            },
							            xAxis:{
							                data:arrDate
							            
							            },
							            yAxis:{
									
							            },
							            series:[{
							                name:'达标数',
							                type:'line',
							                data:arrDabiaoNum
							            }]

							        };
							        var myChart = echarts.init(document.getElementById('chartmain'));
							        myChart.setOption(option);
							
						});
				      
				        //使用制定的配置项和数据显示图表
					</script>
					<div class="tool_main">
						<div class="ce_bread">
							您当前的位置： <a href="<%=basePath%>user/index.action">会员中心</a>
						</div>
						<div class="well">
							欢迎您：${sessionScope.user.username}。有疑问请联系：QQ：<a  href="tencent://message/?uin=${admin.admin_qq }&Site=qq&Menu=yes"> <img
								src="<%=basePath%>img/pa.jpg" alt="点击这里给我发消息" title="点击这里给我发消息" border="0"></a>
						</div>
						<div class="main_center">
							<table>
								<tbody>
									<tr>
										<td style="width: 150px;"><strong>关键字总数:</strong> <span
											style="color: Green;">${keyCount }</span></td>
										<td style="width: 150px;"><strong>待审核关键字:</strong> <span
											style="color: Green;">${checkCount }</span></td>
										<td style="width: 150px;"><strong>昨日达标数:</strong> <span
											style="color: red;">${dabiaoCount }</span></td>
										<td style="width: 150px;"><strong>昨日扣费金额:</strong> <span
											style="color: red;">${yesterdayAccount }元</span></td>
										<td style="width: 150px;"><strong>今日预计消费:</strong> <span
											style="color: red;">${yjxf }元</span></td>
										<td style="width: 150px;"><strong>余额:</strong> <span
											style="color: red;">${userBalance }元</span></td>
										<c:if test="${tsmsg!=null }">
										<td style="width: 150px;"><strong>消息:</strong> <span
											style="color: red;">${tsmsg }</span></td>
										</c:if>
									</tr>
								</tbody>
							</table>
						</div>
						<div>
							<input type="hidden" id="dateArray" value="${dateArray }">
							<input type="hidden" id="dabiaoArray" value="${dabiaoArray }">
						</div>
						<br>
						<c:if test="${u.rechange_status eq '1' }">
						<div id="chartmain" style="width:80%; height: 500px;"></div>
						</c:if>
						<c:if test="${u.rechange_status eq '0' }">
							<div class="well">
								您还未充值，请联系客服进行充值。
							</div>
						</c:if>
						<c:if test="${u.rechange_status eq '2' }">
							<div class="well">
								您的余额不足，请联系客服进行充值。
							</div>
						</c:if>
						
					</div>
				</div>
			</div>
		</div>
</body>
</html>