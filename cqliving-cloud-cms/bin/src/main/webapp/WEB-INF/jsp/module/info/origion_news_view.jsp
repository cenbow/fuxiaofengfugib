<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ include file="/WEB-INF/jsp/common/taglibs.jsp"%>

<c:forEach items="${informationContents }" var="inforContent">
	<div class="col-xs-10 widget-container-span padding150">
		<div style="opacity: 1;" class="widget-box collapsed form-group col-sm-7">
			<div class="widget-header">
				<h5>${contentAllTypes[inforContent.type] }&nbsp;&nbsp;&nbsp;&nbsp; ${inforContent.title }</h5>
				<div class="widget-toolbar">
					<!-- <a href="#" data-action="collapse"><i class="icon-chevron-down"></i></a> -->
					<a class="btn-sm" infocontentid="${inforContent.id}" href="javascript:;"  data_url="add_${contentAllTypesI18N[inforContent.type]}.html"  origion_type="${contentAllTypesI18N[inforContent.type]}_element">编辑</a>
					<a class="btn-sm infocontent_move_up" href="javascript:;">上移</a> 
					<a class="btn-sm infocontent_move_down" href="javascript:;">下移</a> 
					<a href="javascript:;" class="origion_remove" info_content_type="${inforContent.type }"><i class="icon-remove"></i>删除</a>
				</div>
			</div>
		</div>
	</div>
</c:forEach>

<script type="text/javascript">

  require(['jquery','cqliving_ajax','cqliving_dialog'],function($,cqliving_ajax,cqliving_dialog){
	  
	  $(".infocontent_move_up").bind("click",function(){
		  var $this = $(this);
		  var $thisContainer = $this.closest(".widget-container-span");
		  $thisContainer.prev().before($thisContainer);
		  sort();
	  });
	  
      $(".infocontent_move_down").bind("click",function(){
    	  var $this = $(this);
		  var $thisContainer = $this.closest(".widget-container-span");
		  $thisContainer.next().after($thisContainer);
		  sort();
	  });
	  
      //排序
      function sort(){
    	  var sorts = [];
    	  var contentIds = [];
    	 $("#form1 a[origion_type]").each(function(i,n){
    		 contentIds.push($(n).attr("infocontentid"));
    		 sorts.push(i+1);
    	 });
    	 var url = "common/info_content_sort.html"
         var informationId = $("#form1 input[name=informationId]").val();
    	 var param = {
    			 sorts:sorts,
    			 contentIds:contentIds,
    			 informationId:informationId
    	 };
    	 cqliving_ajax.ajaxOperate(url,"#form1",param,function(){
    		 
    	 });
      }
      
      $("#form1 .origion_remove").bind("click",function(){
    	  
	    	  var $this = $(this);
	    	  //原创新闻得至少留一个内容
	    	  var $containSpan = $this.closest(".widget-container-span");
	    	  if($containSpan.siblings().length<=1){
	    		  cqliving_dialog.error("原创新闻内容得至少有一个新闻内容");
	    		  return ;
	    	  }
	    	  cqliving_dialog.confirm("警告","确认要删除吗？删除后不可回复",function(){
	    		  var infocontentid = $this.parent().find("a").eq(0).attr("infocontentid");
	        	  var url = "common/delete_origion_news.html";
	        	  var informationId = $("#form1 input[name=informationId]").val();
	        	  var params = {infocontentId:infocontentid,
	        			        informationId:informationId,
	        			        isViewWechat : isViewWechat($this.closest(".widget-container-span").siblings()),
	        			        appId:appId ? appId : $("select[name=appId]").val()
	        			        };
	        	  cqliving_ajax.ajaxOperate(url,"#form1",params,function(data,status){
	        		  $this.closest(".widget-container-span").remove();
	        	  });
	    	  });
      });
      
      //是否推荐到微信小程序
      function isViewWechat(contentObjs){
    	      if(!contentObjs || contentObjs.length<=0){
    	    	       return 0;
    	      }
    	      var isViewWechat = 1;
    	      $.each(contentObjs,function(i,n){
    	    	     var $this = $(n);
    	    	     var info_content_type = $this.find("#form1 a[info_content_type]").attr("info_content_type");
    	    	     info_content_type = parseInt(info_content_type,10);
    	    	     if(3== info_content_type || 4== info_content_type || 5 == info_content_type){
    	    	    	     isViewWechat=0;
    	    	    	     return false;
    	    	     }
    	      });
    	      return isViewWechat;
      }
  });
</script>
