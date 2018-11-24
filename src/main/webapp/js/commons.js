// JavaScript Document

// common js

var ele = '<div style="clear:both; height:0; font-size: 1px; line-height: 0px;"></div>';
$(".ym-form").append($(ele));

/**
 * @param array:args
 */
function $i(id) {
	return document.getElementById(id);	
}

function $t(tagName) {
	return document.getElementsByTagName(tagName);
}

function $n(name) {
	return document.getElementsByName(name);	
}

var tipsBox = window.top.$("#mode_tips_v2");
var dialog = window.top.$("#dialog");
var tips = window.top.$("#dialog_tips");
var content = window.top.$("#dialog_content");

function submitForm(args) {
	if(arguments.length==0) return false;
	$.ajax({
	    type: args.method,
	    url: args.url,
	    data: args.data,
	    timeout: args.timeout,
	    beforeSend: function () {
	        tipsBox.empty().append("<span class='gtl_ico_clear'></span><img src='/statics/admin/images/loading.gif' />正在提交数据。。。  <span class='gtl_end'></span>");
	        tipsBox.parent("#q_Msgbox").show();
	    },
	    error: function (XMLHttpRequest, textStatus, errorThrown) {
	        if (textStatus == 'timeout') {
	            tipsBox.empty().append("<span class='gtl_ico_fail'></span>服务器忙，请稍后再试。。。  <span class='gtl_end'></span>");
	            setTimeout(function () { tipsBox.parent("#q_Msgbox").hide(); }, 700);
	        }
	    },
	    success: function (msg) {
	        if (msg == "0") {
	            tipsBox.empty().append("<span class='gtl_ico_hits'></span>数据更新完成。。。  <span class='gtl_end'></span>");
	        } else if (msg == "1") {
	            tipsBox.empty().html("<span class='gtl_ico_succ'></span>数据保存成功。。。  <span class='gtl_end'></span>");
	        } else {
	            tipsBox.empty().append("<span class='gtl_ico_succ'></span>" + msg + "。。。  <span class='gtl_end'></span>");
	        }
	        setTimeout(function () {
	            tipsBox.parent("#q_Msgbox").hide();
	            //	window.top.$("#mask").hide();
	            if (args.redirect != null) {
	                location.href = args.redirect;
	            }
	            if (args.dialog != null) {
	                window.top.$("#mask").css("zIndex", 1);
	                dialog.show();
	                tips.text(args.dialog.tips);
	                content.html(args.dialog.content);
	            }
	        }, 700);
	    }
	});	
}

//chkAll
function chkAll(obj1) {
	var obj = $t("input");
	for(var i=0; i<obj.length; i++) {
		if(obj[i].type=="checkbox" && typeof obj[i].name!='undefined') {
		//	if(obj[i]==obj1) continue;
			if(obj1.checked==true) {
				obj[i].checked=true;
			}else {
				obj[i].checked=false;	
			}
			//obj[i].checked = obj[i].checked==false ? true : false; 	
		}
	}
}

function isDouble(obj) {
    reg = /^\d+(\.\d+)?$/;
    if (!reg.test(obj)) {
        return false;
    } else {
        return true;
    }
}

//邮箱验证
function is_email(str) {
    if (str.length != 0) {
        if (str.charAt(0) == "." || str.charAt(0) == "@" || str.indexOf('@', 0) == -1 || str.indexOf('.', 0) == -1 ||
                str.lastIndexOf("@") == str.length - 1 || str.lastIndexOf(".") == str.length - 1) {
            return false;
        }
    }
    return true;
}



function checkphone(phone) {
    //验证电话号码手机号码，包含153，159号段   
    if (phone != "") {
        var p1 = /^(13[0-9]|14[0-9]|15[0-9]|18[0-9])\d{8}$/;
        var me = false;
        if (p1.test(phone)) me = true;
        if (!me) {
            
            return false;
        }
    }
    return true;
}

/*


*判别身份证号码是否合法,入口参数为身份证号码


*返回 true:false


*Junsan Jin 20050902


*/
function checkId(varInput) {
    if (varInput == null || varInput.trim() == "") {
        return false;
    }


    varInput = varInput.trim();
    if (varInput.length != 18 && varInput.length != 15) {
        
        return false;
    }
    var ret = convertID(varInput);
    //     alert(ret);
    if (ret == false) {
       
        return false;
    }
    else if (varInput.length == 18 && varInput != ret) {
        
        return false;
    }
    else {
        //返回值可以自动升级18位身份证号
        //return ret;
        // alert("正确");
        return true;
    }
}
/*
*15身份证号码升18位,入口参数0为15身份证号码,返回值为18位身份证号码
*如果证号错误则返回false
*Junsan Jin 20050902
*/
function convertID(varInput) {


    if (varInput == null || varInput.trim() == "") {
        return false;
    }
    var strOldID = new String(varInput.trim());
    var strNewID = "";
    if (strOldID.length == 15) {


        for (i = 0; i < 15; i++) {


            //15位的身份证号必须全部由数字组成，否则，视为非法


            if (checkZInt(strOldID.substring(i, 1))) {
                return false;
            }
        }
        //取得身份证中的年月日
        var year = "19" + strOldID.substr(6, 2);
        //   alert(year);
        var month = strOldID.substr(8, 2);
        //    alert(month);
        var day = strOldID.substr(10, 2);
        //     alert(day);
        //校验日期是否正确
        if (checkDate(year, month, day)) {
            return false;
        }
        strNewID = strOldID.substring(0, 6) + "19" + strOldID.substring(6, 15);
    }
    else if (strOldID.length == 18) {
        for (i = 0; i < 17; i++) {
            //15位的身份证号必须全部由数字组成，否则，视为非法
            if (checkZInt(strOldID.substring(i, 1))) {
                return false;
            }
        }
        if (strOldID.substring(17, 18).toUpperCase != "X" && checkZInt(strOldID.substring(17, 18))) {
            return false;
        }

        //取得身份证中的年月日
        var year = "19" + strOldID.substr(6, 4);
        var month = strOldID.substr(10, 2);
        var day = strOldID.substr(12, 2);
        //校验日期是否正确
        if (checkDate(year, month, day)) {
            return false;
        }
        strNewID = strOldID.substring(0, 17);
    }
    else if (strOldID.length == 17) {
        for (i = 0; i < 17; i++) {
            //15位的身份证号必须全部由数字组成，否则，视为非法
            if (checkZInt(strOldID.substring(i, 1))) {
                return false;
            }
        }
        //取得身份证中的年月日
        var year = "19" + strOldID.substr(6, 4);
        var month = strOldID.substr(10, 2);
        var day = strOldID.substr(12, 2);
        //校验日期是否正确
        if (checkDate(year, month, day)) {
            return false;
        }
        strNewID = strOldID;
    }
    return strNewID = strNewID + createCK(strNewID);


}
/*


*根据17位的身份证号得到最后一位校验码


*strID：身份证号前17位


*只返回


*/
function createCK(strID) {
    var s = 0;
    var WI = new Array(7, 9, 10, 5, 8, 4, 2, 1, 6, 3, 7, 9, 10, 5, 8, 4, 2, 1);
    var AI = "10X98765432";
    for (i = 0; i < 17; i++) {
        j = strID.substr(i, 1) * WI[i];
        s = s + j;
    }
    s = s % 11;
    return AI.substr(s, 1);
}


/*


*主要提供对日期的精确校验，验证日期是否合法


*非法返回true,合法返回false


*Junsan Jin 20050902 


*参数说明：


*year：年


*month：月


*day：日


*/
function checkDate(year, month, day) {
    var flag = false;
    var time = new Date(year, month - 1, day);
    //    alert(time);
    var e_year = time.getFullYear();
    //    alert(e_year);
    var e_month = time.getMonth() + 1;
    //    alert(e_month);
    var e_day = time.getDate();
    //    alert(e_day);
    if (year != e_year || month != e_month || day != e_day) {
        flag = true;
    }
    return flag;
}


/*


*检查输入的串是否在0到9之间的字符组成


*不是则返回true，如果是则返回false


*Junsan Jin 20050902


*/
function checkZInt(str) {
    var reg = /^\d+$/;
    if (arr = str.match(reg)) {
        //全部是数字
        return false;
    }
    else {
        //含有其他字符
        return false;
    }
}
/*


*字符串去掉左右空格的方法


*Junsan Jin 20050902


*/
String.prototype.trim = function () {
    return this.replace(/(^\s*)|(\s*$)/g, "");
}
/*


*字符串去掉左空格的方法


*Junsan Jin 20050902


*/
String.prototype.ltrim = function () {
    return this.replace(/(^\s*)/g, "");
}


/*


*字符串去掉右空格的方法


*Junsan Jin 20050902


*/
String.prototype.rtrim = function () {
    return this.replace(/(\s*$)/g, "");


}
