define(['bootstrap','cqliving_ajax','cqliving_dialog'],function($,cqliving_ajax,cqliving_dialog){
	
	
	
	var url = "common/new_find_sys_resc.html";
	cqliving_ajax.ajaxOperate(url,"",{},function(data,status){
		
		ajaxCallBack(data,status);
		
	});
	
	
	function ajaxCallBack(data,status){
		
		if(data.length<=0)return;
		
		$("#myTab li").each(function(i,n){
			var $this = $(n);
			var resTypeId = $this.attr("resTypeId");
			getTree(data[resTypeId],$this);
			selectResc();
		});
	}
	
	function getTree(rescs,$this){
		
		if(!rescs || rescs.length<=0)return;
		
		for(var i=0,m=rescs.length;i<m;i++){
			var resc = rescs[i];
			var sub = resc.subResource;
			getHtml(resc,$this);
			if(sub && sub.length>=1){//是父级
				getTree(sub,$this);
			}
		}
	}
	
	function getHtml(resc,$this){
		
		var pid = resc.parentId;
		var tab_contentId = $this.find("a[data-toggle]").attr("href");
		var $tab_pane = $(tab_contentId);
		var $pInput = $tab_pane.find(":input[value="+pid+"]");
		var $p = $pInput.closest(".widget-box");
		if($p.length>=1){
			appendChild($p,resc);
		}else if(pid == 0){
			var $box = getBoxHeaderHtml(resc);
			if($this.hasClass("active")){
				$tab_pane.addClass("active");
			}
			$tab_pane.append($box);
		}
	}
	
	//获取widget-box模板
	function getBoxHeaderHtml(resc){
		
		var html = "";
		if(!resc)return html;
		var tmpHtml = $("#parent_tmp").html();
		 var   $newTmpHtml = $(tmpHtml).clone(true);
		 var $headerInput = $newTmpHtml.find(".widget-header .smaller :input").eq(0);
		 $headerInput.val(resc.id);
		 $headerInput.next().text(resc.title);
		 return $newTmpHtml;
	}
	
	//获取多选框模板
	function getCheckBoxHtml(resc){
		
		var html = "";
		if(!resc)return html;
		var $temp = $("#checkBoxTmp").html();
		var $newHtml = $($temp).clone(true);
		$newHtml.find(":input").val(resc.id);
		$newHtml.find(":input").next().text(resc.title);
		return $newHtml;
	}
	
	function getBoxBody(){
		var $content = $($("#child_temp").html());
		var $newContent = $content.clone(true);
		
		return $newContent;
	}
	
	//加载widget-box模板内容
	function appendChild($p,resc){
		//debugger;
		var $boxBody = $p.children(".widget-body");
		var sub = resc.subResource;
		if(sub && sub.length>=1){//如果有子元素，则为父级就添加widget-box
			append$widgetBox($p,getBoxHeaderHtml(resc));
			return;
		}
		if($boxBody.length>=1){
			append$boxMain($boxBody.children(".widget-main"),getCheckBoxHtml(resc));
		}else{
			var $content = getBoxBody();
			$content.children(".widget-main").append(getCheckBoxHtml(resc));
			$p.children(".widget-header").after($content);
		}
	}
	
   function append$boxMain($boxMain,html){
		var $label = $boxMain.children("label");
		if($label.length>=1){
			$label.last().after(html);
		}else{
			var $childBox = $boxMain.children(".widget-box");
			if($childBox.length>=1){
				$childBox.first().before(html);
			}else{
				$boxMain.append(html);
			}
			
		}
   }
   
   function append$widgetBox($box,html){
	   
	   var $body = $box.children(".widget-body");
	 
	   if($body.length>=1){
		   $body.children(".widget-main").append(html);
	   }else{
		   var $content = getBoxBody();
		   $box.find(".widget-header").after($content);
		   $box.children(".widget-body").children(".widget-main").append(html);
	   }
   }
   
   $(".tab-content").on("click",":input[type=checkbox][defaultName=resIds]",function(){
	   
	   var $this = $(this);
	   var isChecked = $this.is(":checked");
	   var $boxHeader = $this.closest(".widget-header");
	   var $P = $this.closest(".widget-box");
	   if($boxHeader.length>=1 && !isChecked){//父级
		   disCheckChildren($P);
	   }else if(isChecked){//子集
		   checkedAllParents($P);
	   }
   });
   
   
   $(".tab-content").on("click",":input[type=checkbox][name=checked_all]",function(){
	   
	   var $this = $(this);
	   var isChecked = $this.is(":checked");
	   var $box = $this.closest(".widget-box");
	   if(isChecked){
		   checkAllChildren($box);
	   }else{
		   disCheckChildren($box);
	   }
   });
   
   function checkedAllParents($pBox){
	   
       if($pBox && $pBox.length>=1){
    	  $pBox.children(".widget-header").find(":input[type=checkbox][defaultName=resIds]").prop("checked",true);
	   }
	   
   }
   
   function disCheckChildren($pBox){
	   
	   if($pBox && $pBox.length>=1){
		   
		   $pBox.find(":input[type=checkbox]").prop("checked",false);
		   
	   }
	   
   }
   
   function checkAllChildren($box){
	   
     if($box && $box.length>=1){
		   
    	 $box.find(":input[type=checkbox]").prop("checked",true);
		   
	   }
	   
   }
   
   function selectResc(){
	   
	   if(roleRescs && typeof roleRescs === 'string'){
		    roleRescs = eval("("+roleRescs+")");
	   }
	   
	   if(roleRescs && roleRescs.length>=1){
		   $(".tab-content :input[type=checkbox]").each(function(i,n){
			   var $n = $(n);
			   for(var j=0,k=roleRescs.length;j<k;j++){
				   var recs = roleRescs[j];
				   if($n.val() == recs.id){
					   $n.prop("checked",true);
				   }
			   }
		   });
	   }
	   
   }
});