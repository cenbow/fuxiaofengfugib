<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/common/taglib.jsp"%>
<!DOCTYPE html>
<html>
<head>
    <%@include file="/common/head_meta.jsp"%>
    <title>大宁音乐季</title>
<body>
    <input type="hidden" name="accId" value="${sessionScope.accId}">
    <input type="hidden" name="voteStep" value="2"/>
    <div class="list-index">
        <div class="list-bg">
            <div class="list-rule"><img src="/module/daling/front/images/rule-btn.png" alt="" class="rule_img"></div>
            <img src="/module/daling/front/images/list-bg_1.jpg" alt="">
        </div>
        <ul>
          <c:forEach items="${contestant }" var="peter">
             <li code="${peter.code }" name="${peter.name}">
                <div class="list-box">
                    <div class="img"><img src="${peter.imageUrl}" alt=""><span>${peter.baseInfo}</span></div>
                    <div class="name"><strong>${peter.name}</strong><span>票数：<em>${peter.voteNum}</em></span></div>
                    <div class="btn"><a href="javascript:;" class="vote_btn"><span>投TA一票</span></a></div>
                </div>
            </li>
          </c:forEach>
        </ul>
    </div>
    <!-- 活动规则弹窗 -->
    <div class="pup-rule" style="display:none;">
        <div class="tit"><img src="/module/daling/front/images/pup-rule-tit.png" alt=""></div>
        <ul>
            <li><b>1</b>关注大宁国际官方微信<strong>(LifeHub_DANING)</strong>后即可为喜欢的选手投票；</li>
            <li><b>2</b>每人限投一票，投票票数记为大众评审分数，占决赛现场总分1/4；</li>
            <li><b>3</b>参赛者如有恶意刷票行为,一经发现,活动主办方有权取消该参赛者的参赛资格和获奖资格；</li>
            <li><b>4</b>本活动最终解释权归活动主办方所有。</li>
        </ul>
        <div class="time">
            <span>投票时间</span>
            <strong>10月15日</strong>
        </div>
        <div class="btn"><a href="javascript:;" class="close_btn">退出</a></div>
    </div>
    <!-- 二维码 -->
    <div class="pup-lsit" style="display:none;">
        <b class="close"></b>
        <div class="list-code">
            <img src="/module/daling/front/images/code-img.png" alt="">
            <span>关注大宁微信公众号，<br>为您喜欢的选手投上一票吧~</span>
        </div>
    </div>
    <!-- 分享 -->
    <div class="pup-lsit share_vote" style="display:none;">
        <b class="close close_btn"></b>
        <div class="text">
            <p>分享给你的朋友们，</p>
            <p>可额外增加一次投票机会哦</p>
        </div>
        <div class="btn"><a href="javascript:;" class="close_btn">知道了</a></div>
    </div>
    <!-- 投票次数超过 弹窗  -->
    <div class="pup-lsit today_no_chance" style="display:none;">
        <b class="close close_btn"></b>
        <div class="text">
            <p>每人只能投一票哦~</p>
            <p>发送给朋友们可以让更多为你支持的选手投票！</p>
        </div>
        <div class="btn"><a href="javascript:;" class="close_btn">知道了</a></div>
    </div>
    <!-- 投票确认 弹窗  -->
    <div class="pup-lsit vote_pup" style="display:none;">
        <b class="close close_btn"></b>
        <div class="text">
            <p>您确认要为<em>90号</em>选手<em>张二喜</em>投票吗？</p>
            <p>每人只有一次投票机会哦~</p>
            <input type="hidden" name="code" value="">
        </div>
        <div class="btn two"><a href="javascript:;">是的</a><a href="javascript:;" class="close_btn">放弃</a></div>
    </div>
</body>
<%@include file="/module/daling/common/include.jsp"%>
<link href="/module/daling/front/css/fn_toupiao.css" rel="stylesheet">
<script type="text/javascript" src="/module/daling/js/vote.js?v=v1"></script>
</html>