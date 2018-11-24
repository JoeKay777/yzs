(function(a,c,b){
	if(typeof module!=="undefined"&&module.exports){
		module.exports=b();}else{
		if(typeof define==="function"&&define.amd){
			define(b);}else{
			c[a]=b();}}})("Fingerprint",this,function(){
				var a=function(b){
				var c,
				d;
				c=Array.prototype.forEach;
				d=Array.prototype.map;
this.each=function(k,j,h){
	if(k===null){return;}
	if(c&&k.forEach===c){k.forEach(j,h);}else{
		if(k.length===+k.length){
		for(var g=0,e=k.length;g<e;g++){
			if(j.call(h,k[g],g,k)==={}){
				return;}}}else{
					for(var f in k){
					if(k.hasOwnProperty(f)){
					if(j.call(h,k[f],f,k)==={}){
					return;}}}}}};
this.map=function(h,g,f){
	var e=[];if(h==null){return e;}if(d&&h.map===d){
		return h.map(g,f);}this.each(h,function(k,i,j){
			e[e.length]=g.call(f,k,i,j);});
			return e;};
			if(typeof b=="object"){
			this.hasher=b.hasher;
			this.screen_resolution=b.screen_resolution;
			this.screen_orientation=b.screen_orientation;
			this.canvas=b.canvas;
			this.ie_activex=b.ie_activex;}else{
			if(typeof b=="function"){
			this.hasher=b;}}};
a.prototype={get:function(){
	var b=[];
	b.push(navigator.userAgent);
	b.push(navigator.language);
	b.push(screen.colorDepth);
	var d=this.getScreenResolution();
	if(d){b.push(d.join("x"));}b.push(new Date().getTimezoneOffset());
	b.push(this.hasSessionStorage());
	b.push(this.hasLocalStorage());
	b.push(this.hasIndexDb());
	if(document.body){
		b.push(typeof(document.body.addBehavior));}else{
			b.push(typeof undefined);}b.push(typeof(window.openDatabase));
			b.push(navigator.cpuClass);
			b.push(navigator.platform);
			b.push(navigator.doNotTrack);
			b.push(this.getPluginsString());
			var c=this.getGraphicsInfo();
			if(c){b.push(c.vendor);
				b.push(c.renderer);}if(this.isCanvasSupported()){
					b.push(this.getCanvasFingerprint());}if(this.hasher){
						return this.hasher(b.join("###"),31);}else{
							return this.murmurhash3_32_gc(b.join("###"),31);}},
	murmurhash3_32_gc:function(j,f){
		var k,
		l,
		h,
		b,
		e,
		c,
		g,
		d;
		k=j.length&3;
		l=j.length-k;
		h=f;
		e=3432918353;
		c=461845907;
		d=0;
		while(d<l){g=((j.charCodeAt(d)&255))|((j.charCodeAt(++d)&255)<<8)|((j.charCodeAt(++d)&255)<<16)|((j.charCodeAt(++d)&255)<<24);
			++d;g=((((g&65535)*e)+((((g>>>16)*e)&65535)<<16)))&4294967295;g=(g<<15)|(g>>>17);g=((((g&65535)*c)+((((g>>>16)*c)&65535)<<16)))&4294967295;
			h^=g;
			h=(h<<13)|(h>>>19);
			b=((((h&65535)*5)+((((h>>>16)*5)&65535)<<16)))&4294967295;
			h=(((b&65535)+27492)+((((b>>>16)+58964)&65535)<<16));}g=0;
			switch(k){case 3:g^=(j.charCodeAt(d+2)&255)<<16;
			case 2:g^=(j.charCodeAt(d+1)&255)<<8;
			case 1:g^=(j.charCodeAt(d)&255);
			g=(((g&65535)*e)+((((g>>>16)*e)&65535)<<16))&4294967295;
			g=(g<<15)|(g>>>17);
			g=(((g&65535)*c)+((((g>>>16)*c)&65535)<<16))&4294967295;
			h^=g;}h^=j.length;
			h^=h>>>16;
			h=(((h&65535)*2246822507)+((((h>>>16)*2246822507)&65535)<<16))&4294967295;
			h^=h>>>13;
			h=((((h&65535)*3266489909)+((((h>>>16)*3266489909)&65535)<<16)))&4294967295;
			h^=h>>>16;return h>>>0;},
hasLocalStorage:function(){
	try{return !!window.localStorage;}catch(b){
		return true;}},
hasSessionStorage:function(){
	try{return !!window.sessionStorage;}catch(b){
		return true;}},
hasIndexDb:function(){try{
	return !!window.indexedDB;}catch(b){
		return true;}},
isCanvasSupported:function(){
	try{var b=document.createElement("canvas");
	return !!(b.getContext&&b.getContext("2d"));}catch(c){
		return false;}},
isIE:function(){
	try{if(navigator.appName==="Microsoft Internet Explorer"){
		return true;}else{
		if(navigator.appName==="Netscape"&&/Trident/.test(navigator.userAgent)){
			return true;}}
		return false;}catch(b){
			return false;}},
getPluginsString:function(){
	if(this.isIE()&&this.ie_activex){
		return this.getIEPluginsString();}else{
		return this.getRegularPluginsString();}},
getRegularPluginsString:function(){
	try{return this.map(navigator.plugins,
		function(d){
			var c=this.map(d,function(e){
				return[e.type,e.suffixes].join("~");}).join(",");
				return[d.name,d.description,c].join("::");},this).join(";");}catch(b){return"";}},
getRegularPlugins:function(){
	return this.map(navigator.plugins,function(c){
		var b=this.map(c,function(d){
			return[d.type,d.suffixes].join("~");});
			return[c.name,c.description,b];},this);},
getIEPluginsString:function(){
	try{if(window.ActiveXObject){
		var c=["ShockwaveFlash.ShockwaveFlash","AcroPDF.PDF","PDF.PdfCtrl","QuickTime.QuickTime","rmocx.RealPlayer G2 Control","rmocx.RealPlayer G2 Control.1","RealPlayer.RealPlayer(tm) ActiveX Control (32-bit)","RealVideo.RealVideo(tm) ActiveX Control (32-bit)",
		"RealPlayer","SWCtl.SWCtl","WMPlayer.OCX","AgControl.AgControl","Skype.Detection"];
		return this.map(c,function(d){
			try{new ActiveXObject(d);
				return d;}catch(f){
				return null;}}).join(";");}else{
				return"";}}catch(b){
				return"";}},
getScreenResolution:function(){
	try{var b;if(this.screen_orientation){
		b=(screen.height>screen.width)?[screen.height,screen.width]:[screen.width,screen.height];}else{
			b=[screen.width,screen.height];}
		return b;}catch(c){
		return null;}},
getCanvasFingerprint:function(){
	try{var d=document.createElement("canvas");
	var c=d.getContext("2d");
	var b="http://valve.github.io";
	c.textBaseline="top";
	c.font="14px 'Arial'";
	c.textBaseline="alphabetic";
	c.fillStyle="#f60";
	c.fillRect(125,1,62,20);
	c.fillStyle="#069";
	c.fillText(b,2,15);
	c.fillStyle="rgba(102, 204, 0, 0.7)";
	c.fillText(b,4,17);
	return d.toDataURL();}catch(f){
		return"";}},
getGraphicsInfo:function(){
	try{var b=document.createElement("canvas");
	var f=b.getContext("experimental-webgl");
	var d=this.getUnmaskedInfo(f);
	return{vendor:d.vendor,renderer:d.renderer};}catch(c){
		return null;}},
getUnmaskedInfo:function(f){
	try{var c={renderer:"",vendor:""};
	var b=f.getExtension("WEBGL_debug_renderer_info");
	if(b!=null){c.renderer=f.getParameter(b.UNMASKED_RENDERER_WEBGL);
	c.vendor=f.getParameter(b.UNMASKED_VENDOR_WEBGL);}
	return c;}catch(d){
	return null;}}};
	return a;});
(function(){
	var R=function(t){
		try{
			var i=document.createElement("script");
			i.type="text/javascript";
			i.charset="utf8";
			i.async=true;
			i.src=t;
			var m=document.getElementsByTagName("head")[0];
			m.appendChild(i);}catch(p){
				console.log("dd error:",p);}};
				if(!window._dp){return;}
				var H=window._dp.https_fallback_domains||[];
				var L=window._dp.src;
				if(window._dp.src_back){
					R(window._dp.src_back);}else{
						if(L&&L!="null"&&L!="undefined"){
							var O=new Date().getTime(),
							A="sid"+O;
				try{window.source_domain=L.substring(0,L.search("/"));}catch(S){}
				var D=document.getElementsByTagName("script"),
				J="http://"+L,
				c=true;
				for(var Q in D){if(!D[Q].src){continue;}
				var G=D[Q].src,
				P=G.indexOf("#");
				if(P!==-1){G=G.substr(0,P);}if(G==J){
					c=D[Q].async;break;}}
				var q=false;
				for(var Q=0;Q<=H.length;Q++){
					if(L.indexOf(H[Q])!=-1){q=true;break;}}if(q){
						if(L.indexOf("https://")<0){
							L=L.indexOf("http://")==0?L.replace("http://","https://"):"https://"+L;}}else{
							L+=(L.search("[?]")!=-1?"&":"?")+"08022728"+O;}
							if(L.indexOf("https://")<0&&L.indexOf("http://")<0){L="http://"+L;}
							if(c){R(L);}else{
								document.writeln('<script id="'+A+'" src="'+L+'"><\/script>');}}}
var g=function(){
	var i="";
	if(top&&top.location){
		try{i=top.location.href;}catch(p){
			var m=(parent!==window);
			if(m){i=document.referrer;}else{
				i=document.location.href;}}}
	return i;};
	var B=function(i){
	var e=i.indexOf("?",0);
	if(e>=0){return i.substr(0,e);}else{
		return i;}};
	var b="";
	try{if(top&&top.location){
		b=top.location.href;}}catch(S){}
	var F=document.location.href||"";
	var V=document.referrer||"";
	var s=window._dp.disp_domain,
	E=window._dp.scheme,
	f=window._dp.strategy_id,
	k=window._dp.media_id,
	u=window._dp.src,
	K=window._dp.ref,
	C=window._dp.flow_type,
	l=window._dp.tid,
	f=window._dp.strategy_id,
	a=window._dp.width,
	d=window._dp.height;
	var v=g();
	if(v!==K){K=v;}
	var n=window.screen.width+"x"+window.screen.height;
	var x=window._dp?window._dp.rid:"";
	var o="";
	if(window._page_id){
		o=window._page_id;}else{
			if(window._dp&&window._dp.page_id){
				o=window._dp.page_id;window._page_id=o;}}
	var r=function(m){
		var i="";
		for(var e in m){
			if(m[e]!==null&&m[e]!==undefined&&m[e]!==""){i+=(i?"&":"")+e+"="+m[e];}}return i;};
		var j=function(i){
			var e=document.cookie.match(new RegExp("(^| )"+i+"=([^;]*)(;|$)"));
			if(e!=null){
				return unescape(e[2]);}
			return null;};
var y=function(){
	try{return"localStorage" in window&&window.localStorage!==null;}catch(i){
		return false;}};
var I=function(i,e){
	var m=!+"\v1";
	if(e=="backgroundPosition"){
	if(m){return i.currentStyle.backgroundPositionX+" "+i.currentStyle.backgroundPositionY;}}
	if(i.currentStyle){
		return i.currentStyle[e];}else{
			return document.defaultView.getComputedStyle(i,null)[e];}};
var N=function(m){
	if(m.indexOf("rem")>-1){m=m.replace("rem","");}
	var i=document.getElementsByTagName("html")[0];
	var e;
	if(i.style&&i.style.fontSize){e=i.style.fontSize;}else{
		e=I(i,"font-size");}if(e){
		e=e.replace("px","");
		return m*e;}
		return null;};
var z=function(){
	var t=document.getElementsByTagName("*");
	if(t){
		var m=t.length;
		for(var p=0;p<m;p++){
		if(t[p].tagName!=="SCRIPT"&&t[p].tagName!=="HTML"&&t[p].tagName!=="HEAD"&&t[p].tagName!=="META"&&t[p].tagName!=="LINK"&&t[p].tagName!=="TITLE"&&t[p].tagName!=="BODY"){
			if(t[p].id=="_dk_kl_btn"){continue;}
			var X;
			if(t[p].style&&t[p].style.top){
			X=t[p].style.top;}else{
			X=I(t[p],"top");
			if(X){
			X=X.replace("px","");
			}}if(X&&X.indexOf("rem")>-1){
			X=N(X);}if(X&&X.indexOf("px")>-1){
			X=X.replace("px","");}
			var e;
			if(t[p].style&&t[p].style.position){
				e=t[p].style.position;}else{
				e=I(t[p],"position");}
				var W;
				if(t[p].style&&t[p].style.display){
				W=t[p].style.display;
				}else{
				W=I(t[p],"display");}
				var Y={top:X,display:W,position:e};
				if(W&&W!=="none"&&X&&e&&(e==="fixed")){
					console.log(Y);return false;}}}}
	return true;};
	var M={
		i:f,
		m:k,
		f:C,
		w:a,
		h:d,
		td:l,
		r:encodeURIComponent(B(K)),
		t:encodeURIComponent(B(b)),
		rf:encodeURIComponent(B(V)),
		p:encodeURIComponent(B(F)),
		ws:n,
		pd:o,
		rd:x,
		v:"2.0"};
		var w=y()?window.localStorage.__uid:j("__uid");
		try{var U=new Fingerprint();
			var T=U.get();
			M.fi=T;}catch(S){}if(w){M.u=w;}if(u){
			M.s=encodeURIComponent(u);}if(!z()){
				if(M.nf){
					M.nf=M.nf+",t";}else{
					M.nf="t";}}if(!window._bm_id){
						var h=E+"://"+s+"/bm?"+r(M);window._bm_id=o;R(h);}})();