<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/WEB-INF/jsp/common/taglibs.jsp"%>

<style type="text/css">
    .btn {
        margin-right: 8px;
    }

    .angular-ui-tree-handle {
        background: #f8faff;
        border: 1px solid #dae2ea;
        color: #7c9eb2;
        padding: 6px 7px;
    }

    .angular-ui-tree-handle:hover {
        color: #438eb9;
        background: #f4f6f7;
        border-color: #dce2e8;
    }

    .angular-ui-tree-placeholder {
        background: #f0f9ff;
        border: 2px dashed #bed2db;
        -webkit-box-sizing: border-box;
        -moz-box-sizing: border-box;
        box-sizing: border-box;
    }

</style>

<div class="main-content">
	<jsp:include page="${pageContext.request.contextPath}/WEB-INF/jsp/common_ace/breadcrumbs.jsp">
	  <jsp:param value="资源管理" name="_breadcrumbs_1"/>
	</jsp:include>

	<div class="page-content">
	    
	    <div class="col-xs-12 col-sm-12 col-lg-12">
			<div class="tabbable">
				<ul class="nav nav-tabs" id="myTab">
					<c:forEach items="${sysResTypes }" var="resType" varStatus="vs">
					   <li resTypeId="${resType.id}"  class="${vs.count eq 1 ? 'active' : ''}">
						<a data-toggle="tab" href="#tab_pane_resc_${resType.id}">
							${resType.name}
						</a>
					   </li>
					</c:forEach>
				</ul>
				<div class="tab-content">
					<!-- 资源内容 -->
				   <div id="tab_pane_resc_" class="tab-pane">
				       	
				   </div>
				</div>
			</div>
		</div>
	
		<div class="col-xs-12 col-sm-12 col-lg-12 sys_list_div">
		    
		    <!-- 资讯内容 -->
		    
		</div>
    </div>
</div>
<script type="text/javascript">
    require(['jquery','cqliving_ajax','cqliving_dialog'],function($,cqliving_ajax,cqliving_dialog){
    	
    	$("#myTab li").bind("click",function(){
    		var $this= $(this);
    		$this.addClass("active").siblings().removeClass("active");
    		var resTypeId = $this.attr("resTypeId");
    		var url = "/module/security/common/find_by_resytype.html";
    		cqliving_ajax.load($(".sys_list_div"),url,{sysResTypeId:resTypeId},function(data,status){
    		});
    	});
    	
    	$("#myTab li").filter(".active").click();
    	
    });
</script>
