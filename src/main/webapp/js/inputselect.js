 function inputselect(){  
			    //定义要传递的url和值  
			    var url=jQuery("#input_url").val();  
			    var val=jQuery("#dlUser").val().trim();  
			    if(val!=""){   //当文本内容不为空时进行异步检索  
			    	jQuery.post(url,{username:val},function(data,status){  
			        if(status=="success"){ //当接收服务器端数据成功时  
			        	var tipHtml="";        //拼接html标签  
			            if(data==null||data==""){
			            	jQuery("#tip").hide();
			            }else{
			            	jQuery("#tip").show();
			            }
			            jQuery.each(data,function(i,dom){
			          		 tipHtml+="<option style='height:20px;'>"+dom.username+"</option>";   
			          	 });
			            //获得输入姓名文本框的宽度  
			            var width=parseInt(jQuery("#dlUser").width());  
			            //设置ul宽度和文本框的宽度相等  
			            jQuery("#tip").html(tipHtml).width(width);  
			            jQuery("#tip").css("position","relative").css("top",5).css("left",10).css("list-style-type","none");  
			         /*   jQuery("#tip option").click(function(){  
			                jQuery("#tip").hide();  
			                jQuery("#dlUser").val(jQuery(this).text());  
			            });    */
			        }  
			       });  
			    }else{
			    	jQuery("#tip").hide();
			    } 
		  }