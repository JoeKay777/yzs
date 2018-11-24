//Cookie.js

var sq_pi = 1;
$(document).ready(function () {
    $("#pagination_pageindex>li").each(function () {
        var index = $(this).attr("data-sp") || $(this).attr("data-pp")
                                || $(this).attr("data-p") || $(this).attr("data-np") || $(this).attr("data-ep");
        if (typeof index != "undefined")
            $(this).find("a").bind("click", function () { sq_pi = index; });
    });
});

$("#pagination_pagesize>li").each(function () {
    if (typeof $(this).attr("data-ps") != "undefined") {
        $(this).click(function () {
            $("input[name='pagesize']").val(sq_ps);
            $("#searchForm").submit();
        });
    }
});

$("#pagination_pageindex>li").each(function () {
    if (typeof $(this).attr("data-p") != "undefined"
                || typeof $(this).attr("data-sp") != "undefined"
                || typeof $(this).attr("data-pp") != "undefined"
                || typeof $(this).attr("data-np") != "undefined"
                || typeof $(this).attr("data-ep") != "undefined") {
        $(this).click(function () {
            $("input[name='currentPage']").val(sq_pi);
            $("#searchForm").submit();
        });
    }
});

function setCookie(name, value, path, expires) {
	var str = name+"="+escape(value);
	if(path=="") {
			path='/';
			str += ";path="+path;		
	}
	if(expires>0) {
			var date = new Date();
			var ms = expires*3600*1000;
			date.setTime(date.getTime()+ms);
			str += ";expires="+date.toGMTString();	
	}
	document.cookie = str;	
}

function getCookie(name) {
		var arrStr = document.cookie.split(";");
		for(var i=0; i<arrStr.length; i++) {
				var tmp = arrStr[i].replace(' ','').split("=");
				if(tmp[0]==name) return unescape(tmp[1]);	
		}
}

function delCookie(name) {
	var date = new Date();
	date.setTime(date.getTime()-100000);
	document.cookie = name +"=a; expires="+ date.toGMTString();
}
