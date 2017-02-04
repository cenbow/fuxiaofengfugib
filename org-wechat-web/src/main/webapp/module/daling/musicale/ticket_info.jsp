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
    <div class="rob-index ticket-img">
        <div class="tb-mid">
            <div class="tit"><img src="/module/daling/front/images/ticket-tit.png"></div>
            <div class="ticket">
                <div class="name"><img src="/module/daling/front/images/ticket-name.png"><span>2016大宁音乐季·${musicaleTicket.name}专场</span></div>
                <div class="ticket-m">
                    <div class="ticket-text">
                        <dl>
                            <dt>时间:</dt>
                            <dd>${fn:replace(musicaleTicket.duration,'<br>','')}</dd>
                        </dl>
                        <dl>
                            <dt>地点:</dt>
                            <dd>中央广场</dd>
                        </dl>
                        <dl>
                            <dt>规则:</dt>
                            <dd class="block">
                                <p>1.本券仅作为规定场次演出内场券使用，当日有效，不得用作其他用途；</p>
                                <p>2.门票转发、截图无效。一人一张，凭票入场；</p>
                                <p>3.本券不得兑换现金，不找零；</p>
                                <p>4.演出开场5分钟后停止检票，不得进入内场区；</p>
                                <p>5.进入内场区不得携带食物和饮料(瓶装饮料除外)</p>
                                <p>6.主办方可在法律允许范围内对规则做适当调整。</p>
                            </dd>
                        </dl>
                    </div>
                    <div class="ticket-code">
                        <div class="img"><img src="/module/daling/front/images/ticket-0${musicaleTicket.id}.png"></div>
                        <div class="code"><img src="${qrcodePath}"></div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</body>
<%@include file="/module/daling/common/include.jsp"%>
</html>