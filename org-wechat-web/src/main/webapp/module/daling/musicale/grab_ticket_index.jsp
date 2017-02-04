<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/common/taglib.jsp"%>
<!DOCTYPE html>
<html>
<head>
    <%@include file="/common/head_meta.jsp"%>
    <title>大宁音乐季</title>
<body>
    <div id="audio_btn" class="video_exist rotate" style="display:none;">
         <!-- <audio loop="" src="/module/daling/front/images/bgm.mp3" id="media" autoplay="autoplay" preload=""></audio>  -->
    </div>
    <div class="rob-index">
        <div class="tb-mid">
            <input type="hidden" value="${sessionScope.accId }" name="accId">
            <div class="tit"><img src="/module/daling/front/images/rob-tit01.png"></div>
            <ul class="list01">
              <c:forEach items="${tickets }" var="tic">
                 <li ticketId="${tic.id }" class="${tic.virtualNum eq 0 ? 'over' : ''}">
                    <div class="img"><img src="${tic.imageUrl}"></div>
                    <div class="info">
                        <div class="name">${tic.name }</div>
                        <div class="time"><strong>演出时间：</strong><span>${tic.duration }</span></div>
                        <div class="icon"><i></i><i></i><i></i><i></i><i></i><i></i></div>
                    </div>
                </li>
              </c:forEach>
            </ul>
            <div class="btn01"><a href="javascript:;" class="grab_btn"><img src="/module/daling/front/images/btn01.png"><span>一键抢票</span></a></div>
            <div class="btn01 btn02"><a href="javascript:;" class="rule_btn"><img src="/module/daling/front/images/btn01.png"><span>活动规则</span></a>
            <a href="javascript:;" class="my_ticket"><img src="/module/daling/front/images/btn01.png"><span>我的门票</span></a></div>
        </div>
    </div>

    <div class="pup" style="display:none;">
        <b class="close"></b>
        <div class="code">
            <div class="code-text"><img src="/module/daling/front/images/code-text.png"></div>
            <div class="code-img"><img src="/module/daling/front/images/code-img.png?v1"></div>
            <div class="btn01 btn03"><a href="javascript:;"><img src="/module/daling/front/images/btn03.png"><span>GO</span></a></div>
        </div>
    </div>
    
    <!-- 规则弹窗 -->
    <div class="pup-block rule_pup" style="display:none;">
        <div class="tb-mid">
            <div class="rule-tit"><img src="/module/daling/front/images/rule-tit.png"></div>
            <div class="text-img"><img src="/module/daling/front/images/text-img01.png"></div>
            <div class="text-say">抢票时间： <strong>9月20日</strong>开启，票空即止。</div>
            <div class="btn01 btn04"><a href="javascript:;" class="close"><img src="/module/daling/front/images/btn04.png"><span>关闭</span></a></div>
        </div>
    </div>
    
    <!-- 获奖弹窗 -->
    <div class="pup-block ticket_succ" style="display:none;">
        <div class="tb-mid">
            <div class="text-img big-pad"><img src="/module/daling/front/images/text-img02.png"></div>
            <div class="btn01 btn04"><a href="javascript:;" class="close"><img src="/module/daling/front/images/btn04.png"><span>关闭</span></a></div>
        </div>
    </div>
    <!-- 本场抽奖次数用完的 -->
    <div class="pup-block this_ticket_over" style="display:none;">
        <div class="tb-mid">
            <div class="text-img"><img src="/module/daling/front/images/text-img03.png"></div>
            <div class="text-emg"><img src="/module/daling/front/images/emg1.png"></div>
            <div class="btn01 btn04"><a href="javascript:;" class="close"><img src="/module/daling/front/images/btn04.png"><span>朕知道了</span></a></div>
        </div>
    </div>
    <!-- 未中奖弹窗 -->
    <div class="pup-block guide no_succ" style="display:none;">
        <div class="tb-mid">
            <div class="text-img"><img src="/module/daling/front/images/text-img04.png"></div>
            <div class="text-emg"><img src="/module/daling/front/images/emg2.png"></div>
            <div class="text-say">
                <div><span>抢票小贴士</span></div>
               <p>每人每天针对不同的场次各有一次抢票机会,</p>
               <p>发送给你的小伙伴能额外增加一次机会哟！</p>
            </div>
            <div class="btn01 btn04"><a href="javascript:;" class="close"><img src="/module/daling/front/images/btn04.png"><span>关闭</span></a></div>
        </div>
    </div>
    
     <!-- 未中奖弹窗 -->
    <div class="pup-block guide no_succ_no_share" style="display:none;">
        <div class="tb-mid">
            <div class="text-emg"><img src="/module/daling/front/images/emg2.png"></div>
            <div class="text-say">
                <div><span>抢票小贴士</span></div>
               <p>每人每天针对不同的场次各有一次抢票机会,</p>
               <p>发送给你的小伙伴能额外增加一次机会哟！</p>
            </div>
            <div class="btn01 btn04"><a href="javascript:;" class="close"><img src="/module/daling/front/images/btn04.png"><span>关闭</span></a></div>
        </div>
    </div>
    
    <!-- 今日抽奖次数已用完 -->
    <div class="pup-block no_chance" style="display:none;">
        <div class="tb-mid">
            <div class="text-img"><img src="/module/daling/front/images/text-img05.png"></div>
            <div class="text-emg big-pad"><img src="/module/daling/front/images/emg3.png"></div>
            <div class="btn01 btn04"><a href="javascript:;" class="close"><img src="/module/daling/front/images/btn04.png"><span>明天见</span></a></div>
        </div>
    </div>
    <!-- 今日抽奖次数已用完 -->
    <div class="pup-block guide share_chance" style="display:none;">
        <div class="tb-mid">
            <div class="text-img"><img src="/module/daling/front/images/text-img06.png"></div>
            <div class="text-emg big-pad"><img src="/module/daling/front/images/emg4.png"></div>
            <div class="btn01 btn04"><a href="javascript:;" class="close"><img src="/module/daling/front/images/btn04.png"><span>关闭</span></a></div>
        </div>
    </div>
</body>
<%@include file="/module/daling/common/include.jsp"%>
<script type="text/javascript" src="/module/daling/js/grab_ticket.js?v=v"></script>
</html>