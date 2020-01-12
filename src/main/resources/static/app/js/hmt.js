$(function(){
	let openid = sessionStorage.getItem("openid");
	if (openid == null){
		let url = location.href;
		if (url.indexOf("?") !== -1){
			let codeStr = url.split("&")[0];
			if (codeStr.indexOf("=") !== -1){
				let code = codeStr.split("=")[1];
				$.ajax({
					type:"post",
					url:"http://i0vlgub..mofasuidao.cn/code",
					data:{"code":code},
					dataType:"json",
					async:false,
					success:function (data) {
						sessionStorage.setItem("openid", data.data.openid);
					}
				})
			}
		}
	}
	//计算内容上下padding

	reContPadding({main:"#main",header:"#header",footer:"#footer"});
	function reContPadding(o){
		var main = o.main || "#main",
			header = o.header || null,
			footer = o.footer || null;
		var cont_pt = $(header).outerHeight(true),
			cont_pb = $(footer).outerHeight(true);
		$(main).css({paddingTop:cont_pt,paddingBottom:cont_pb});
	}
});

//折叠展开列表内容
$(document).ready(function(){
  mui('#slider').on('tap', '.open-btn', function (e) {
	  $(".nav-con").fadeToggle("fast");
	  $(".open-btn span").toggleClass('rotate'); 	
	  if($(".open-btn span").hasClass('rotate')){
		 $("#slider").on("touchmove.ddd",function(e){
			// console.log(e)
			  e.stopPropagation();
		});
	  }else{
		  console.log(1)
		 $("#slider").off("touchmove.ddd");  
	  }
  });
});