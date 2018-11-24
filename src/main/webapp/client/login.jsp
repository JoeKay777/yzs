<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
pageContext.setAttribute("baseUrl",basePath); 
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<title>5188seo快排系统</title>
		<link rel="stylesheet" type="text/css" href="<%=basePath%>css/filestyle.css"/>
		<style>
			body{
				background-image:url(<%=basePath%>/img/1.jpg);
			}
		</style>
		<script type="text/javascript" src="<%=basePath%>js/jquery-1.7.2.js"></script>
	</head>
	<body leftmargin="0" onload="changeImg()" >
		<div id="main_bar">
    <div style="background-color:transparent;color:#fff;">
        <form id="ff" >
          <div align="center"><h1>--欢迎登陆--</h1></div>	
            <ul class="reg-box">
                <li>
                    <label for="">账&nbsp;&nbsp;号</label>
                    <input type="text" name="username" placeholder="请输入账号" id="box_name" class="txt" value="" onfocus="this.value=''" onblur="if(this.value=='')this.value='请输入账号'"/>
                    <span class="error error5"></span>
                </li>
                <li>
                    <label for="">密&nbsp;&nbsp;码</label>
                    <input type="password" name="password" placeholder="请输入密码" id="box_pass" class="txt" value="password" onfocus="this.value=''" onblur="if(this.value=='')this.value='password'"/>
                    <span class="error error6"></span>
                </li>
                <li>
                    <label for="">验证码</label>
                    <input type="text" id="vcode" placeholder="验证码" value="验证码" onfocus="this.value=''" onblur="if(this.value=='')this.value='验证码'"/>
                    <span id="code" title="看不清，换一张"></span>                                  
                </li>
            </ul>
            <div class="sub">
                <input type="button" id="login" onclick="javascript:return check();" value="登录"/>             
            </div>
            <div class="common">
            	<a href="<%=basePath%>client/register.jsp">注册</a>
            </div>
        </form>
    </div>
</div>
</body>
<script type="text/javascript">
		var code;//声明一个变量用于存储生成的验证码
		document.getElementById("code").onclick=changeImg;
		function changeImg(){
			//alert("换图片");
			var arrays=new Array(
				'1','2','3','4','5','6','7','8','9','0',
				'a','b','c','d','e','f','g','h','i','j',
				'k','l','m','n','o','p','q','r','s','t',
				'u','v','w','x','y','z',
				'A','B','C','D','E','F','G','H','I','J',
				'K','L','M','N','O','P','Q','R','S','T',
				'U','V','W','X','Y','Z'				
			);
			code='';//重新初始化验证码
			//alert(arrays.length);
			//随机从数组中获取四个元素组成验证码
			for(var i=0;i<4;i++){
			//随机获取一个数组的下标
				var r=parseInt(Math.random()*arrays.length);
				code+=arrays[r];
				//alert(arrays[r]);
			}
			//alert(code);
			document.getElementById('code').innerHTML=code;//将验证码写入指定区域
		}		
		
		//效验验证码(表单被提交时触发)
		function check(){
			var box_name=document.getElementById('box_name').value;
			if(box_name==null||box_name==""){
				alert("请输入账号");
				return false;
			}
			var box_pass=document.getElementById('box_pass').value;
			if(box_pass==null||box_pass==""){
				alert("请输入密码");
				return false;
			}
			//获取用户输入的验证码
			var input_code=document.getElementById('vcode').value;
			//alert(input_code+"----"+code);
			if(input_code.toLowerCase()==code.toLowerCase())
			{
				//验证码正确(表单提交)
				 $.ajax({
		            type: "POST",//方法类型
		            dataType: "text",//预期服务器返回的数据类型
		            url: "<%=basePath%>user/login.action" ,//url
		            data: {username: $.trim($("#box_name").val()),password:$.trim($("#box_pass").val()) },
		            success: function (msg) {
		            	if(msg!="1"){
		            		alert(msg);
		            	}else{
		            		window.location.href="<%=basePath%>user/index.action";
		            	}
		            },
		            error : function() {
		                alert("异常！");
		            }
		        });
				return true;
			}
			alert("请输入正确的验证码!");
			//验证码不正确,表单不允许提交
			return false;
		}
	</script>	

</html>
