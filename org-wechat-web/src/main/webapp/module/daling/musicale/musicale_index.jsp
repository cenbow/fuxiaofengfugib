<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/common/taglib.jsp"%>
<!DOCTYPE html>
<html>
<head>
    <%@include file="/common/head_meta.jsp"%>
    <title>大宁音乐季</title>
<body>
    <div id="audio_btn" class="video_exist rotate" style="display:none;">
         <audio loop="" src="/module/daling/front/images/bgm.mp3" id="media"></audio>
    </div>
    <div class="loding" style="display:block;"><img src="/module/daling/front/images/loading.png" alt=""></div>
    <canvas class="mylegend" style="display:none; " width="300" height="300"></canvas>
    <div class="door" style="display:;">
        <div class="c-img">
            <img src="/module/daling/front/images/door-bg.jpg">
            <div class="btn01"><a href=""><img src="/module/daling/front/images/btn01.png"><span>屋顶约会</span></a></div>
            <div class="open">
                <!-- <span><img src="/module/daling/front/images/door01.png"></span> -->
                <span class="l"><img src="/module/daling/front/images/door01l.png"></span>
                <span class="r"><img src="/module/daling/front/images/door01r.png"></span>
            </div>
        </div>
    </div>
    <div class="house-bak">
        <div class="c-img">
            <img src="/module/daling/front/images/house-bak.jpg">
        </div>
    </div>
    <div class="house" style="display:;">
        <div class="c-img">
            <img src="/module/daling/front/images/house-bg.jpg">
            <div class="cloud cloud1"><img src="/module/daling/front/images/house-cloud01.png"></div>
            <div class="cloud cloud2"><img src="/module/daling/front/images/house-cloud02.png"></div>
            <div class="cloud cloud3"><img src="/module/daling/front/images/house-cloud03.png"></div>
            <div class="cloud cloud4"><img src="/module/daling/front/images/house-cloud04.png"></div>
            <div class="cloud cloud5"><img src="/module/daling/front/images/house-cloud05.png"></div>
            <div class="light">
                <img src="/module/daling/front/images/house-light1.png">
                <span class="light1"></span>
            </div>
            <div class="audio"><img src="/module/daling/front/images/house-audio.png">
            <img src="/module/daling/front/images/house-audios.png"></div>
            <div class="people peo1"><img src="/module/daling/front/images/house-p1.png"></div>
            <div class="people peo2"><img src="/module/daling/front/images/house-p2.png"></div>
            <div class="people peo3"><img src="/module/daling/front/images/house-p3.png"></div>
            <div class="people peo4"><img src="/module/daling/front/images/house-p4.png"></div>
        </div>
    </div>
    <div class="fullpage">
        <div class="sheet-top"><img src="/module/daling/front/images/sheet-tp1.png"></div>
        <div class="sheet-bottom">
            <img src="/module/daling/front/images/sheet-bt1.png">
            <span><img src="/module/daling/front/images/sheet-bt2.png"></span>
        </div>
        <div class="hand-tip"><img src="/module/daling/front/images/hand-tip.png"></div>
        <div id="fullpage">
            <div class="section">
                <div class="sheet" style="display:;">
                    <ul>
                        <li>
                            <p><img src="/module/daling/front/images/sheet01.png"></p>
                            <p><img src="/module/daling/front/images/sheet01s.png"></p>
                        </li>
                        <li>
                            <p><img src="/module/daling/front/images/sheet02.png"></p>
                            <p><img src="/module/daling/front/images/sheet02s.png"></p>
                        </li>
                        <li>
                            <p><img src="/module/daling/front/images/sheet03.png"></p>
                            <p><img src="/module/daling/front/images/sheet03s.png"></p>
                        </li>
                    </ul>
                </div>
            </div>
            <div class="section">
                <div class="zhiyin" style="display:;">
                    <div class="c-img">
                        <img src="/module/daling/front/images/zhiyin-bg.jpg">
                    </div>
                    <div class="zhiyin-list">
                        <div>
                            <img src="/module/daling/front/images/zhiyin-idx.png">
                            <ul>
                                <li><img src="/module/daling/front/images/zhiyin-01.png"></li>
                                <li><img src="/module/daling/front/images/zhiyin-02.png"></li>
                                <li><img src="/module/daling/front/images/zhiyin-03.png"></li>
                            </ul>
                        </div>
                        <div class="btn01"><a href="/musicale/${sessionScope.accId}/ticket_jsp.html">
                        <img src="/module/daling/front/images/btn01.png" class="grab_btn">
                        <span class="grab_btn">我要抢票</span></a></div>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <!-- 分享 -->
    <div class="no-data">弹窗提示!</div>
</body>

<%@include file="/module/daling/common/include.jsp"%>
<script src="/module/daling/front/js/soya2d.custom.min.js"></script>
<script src="/module/daling/front/js/libs/vendors/jquery.easings.min.js"></script>
<script src="/module/daling/front/js/libs/vendors/iscroll.min.js"></script>
<link href="/module/daling/front/js/libs/jquery.fullpage.min.css" rel="stylesheet">
<script src="/module/daling/front/js/libs/jquery.fullpage.min.js"></script>
<script src="/module/daling/front/js/common.js"></script>
<script type="text/javascript">
  $(function(){
	  $(".grab_btn").bind("touchend",function(){
		  window.location.href=$(this).parent().attr("href");
	  });
  });

</script>
</html>