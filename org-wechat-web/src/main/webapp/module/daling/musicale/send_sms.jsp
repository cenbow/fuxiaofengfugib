<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/common/taglib.jsp"%>
<!DOCTYPE html>
<html>
<head>
    <%@include file="/common/head_meta.jsp"%>
    <title>大宁音乐季</title>
<body>
    <div id="audio_btn" class="video_exist rotate" style="display:;">
         <audio loop="" src="/module/daling/front/images/bgm.mp3" id="media" autoplay="autoplay" preload=""></audio> 
    </div>
    <div class="rule"></div>
    <div class="rob-index rob-btimg">
        <div class="tb-mid">
            <div class="tit"><img src="/module/daling/front/images/rob-tit03.png"></div>
            <div class="frm">
                <div class="frm-tit"><img src="/module/daling/front/images/frm-tit.png"></div>
                <form id="form">
	                <input type="hidden" value="${sessionScope.accId }" name="accId">
	                <dl class="name">
	                    <dt></dt>
	                    <dd><input type="text" placeholder="请输入您的姓名" name="name"></dd>
	                </dl>
	                <dl class="phone">
	                    <dt></dt>
	                    <dd><input type="text" placeholder="请输入手机号" name="phone"></dd>
	                </dl>
	                <dl class="check">
	                    <dt></dt>
	                    <dd><input type="text" placeholder="请输入验证码" name="smsCode"><span class="get_sms_btn">获取验证码</span></dd>
	                </dl>
                </form>
            </div>
            <div class="btn01"><a href="javascript:;" class="grab_btn"><img src="/module/daling/front/images/btn01.png"><span>我要抢票</span></a></div>
        </div>
    </div>

    <!-- 规则弹窗 -->
    <!-- <div class="pup-block">
        <div class="tb-mid">
            <div class="rule-tit"><img src="/module/daling/front/images/rule-tit.png"></div>
            <div class="text-img"><img src="/module/daling/front/images/text-img01.png"></div>
            <div class="text-say">抢票时间： <strong>9月20日</strong>开启，票空即止。</div>
            <div class="btn01 btn04"><a href="javascript:;" class="close_rule_btn"><img src="/module/daling/front/images/btn04.png"><span>关闭</span></a></div>
        </div>
    </div> -->
</body>
<%@include file="/module/daling/common/include.jsp"%>
<script type="text/javascript" src="/module/daling/js/send_sms.js"></script>
</html>