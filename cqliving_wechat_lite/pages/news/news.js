// pages/news/index.js
var imageUtil = require('../../utils/util.js');
Page({
  data: {
    appGlobalData: getApp().globalData,
    currColumnId: 851,
    canAddData: true,
    loadingViewDisplay: "none",
    swiper: {
      indicatorDots: false,
      autoplay: true,
      interval: 5000,
      duration: 1000
    },
    dataNewsList:[],//栏目对应的新闻集合
    dataCarouselList: []//栏目对应的轮播图集合
  },
  onLoad: function (options) {
    // 页面初始化 options为页面跳转所带来的参数
    var that = this,
        imageSize = imageUtil.imageUtil()
    that.setData({  
      fullWidth: imageSize.imageWidth,  
      fullHeight: imageSize.imageHeight  
    });
    that.getTabNav();//获取导航数据
    that.getData("", "", "", true);//加载数据

    // this.getData(this.data.currColumnId, true, "", "", "", true)
  },
  onReady: function () {
    // 页面渲染完成
  },
  onShow: function () {
    // 页面显示
  },
  onHide: function () {
    // 页面隐藏
  },
  onUnload: function () {
    // 页面关闭
  },
  getTabNav: function(){//获取导航数据
    var that = this,
    tabNav = {},
    imgServer = 'http://images.cqliving.com/wx/xiaochengxu/';
    tabNav = {
      tabNum: 6,
      items: [
          {idx: 0, text: "热.头条", columnId: 851, isCarousel: true, src: imgServer + 'img_1.png', select: imgServer + 'img_10.png'},
          {idx: 1, text: "愉.生活", columnId: 854, isCarousel: true, src: imgServer + 'img_2.png', select: imgServer + 'img_20.png'},
          {idx: 2, text: "云.视听", columnId: 855, isCarousel: false, src: imgServer + 'img_3.png', select: imgServer + 'img_30.png'},
          {idx: 3, text: "政.时政", columnId: 856, isCarousel: false, src: imgServer + 'img_4.png', select: imgServer + 'img_40.png'},
          {idx: 4, text: "社.社会", columnId: 857, isCarousel: false, src: imgServer + 'img_5.png', select: imgServer + 'img_50.png'},
          {idx: 5, text: "娱.娱体", columnId: 858, isCarousel: false, src: imgServer + 'img_6.png', select: imgServer + 'img_60.png'}
    ]}
    that.setData({//初始化数据哦
      tabNav: tabNav,
      currColumnId: 851,//当前栏目的id（默认851）
      currentTab: 0,//当前栏目的序号（默认851的序号）
      isCarousel: true//是否有轮播（默认851有轮播）
    });
  },
  getData: function(lastSortNo, lastOnlineTime, lastId, isFirstPage){//加载页面数据
      var that = this,
          columnId = that.data.currColumnId,
          appId = that.data.appGlobalData.appId,
          serverUtl = that.data.appGlobalData.serverUrl;
      wx.request({
          url: serverUtl + "/wxl/news.html",
          data: {
            appId: appId,
            columnId: columnId,
            isCarousel: that.data.isCarousel,
            lastSortNo: lastSortNo ? lastSortNo : "", 
            lastOnlineTime: lastOnlineTime ? lastOnlineTime : "", 
            lastId: lastId ? lastId : "",
            onlyWechat: true
          },
          header: {'content-type': 'application/x-www-form-urlencoded'},
          method: "POST",
          success: function (response) {
              var rsData = response.data.data,
                  dataNewsList = that.data.dataNewsList,//所有新闻集合
                  dataCarouselList = that.data.dataCarouselList,//所有栏目的轮播图集合
                  carouselsList = rsData.carousels;

              if(isFirstPage){
                  dataNewsList[that.data.currentTab] = rsData.news;
                  if(that.data.isCarousel){
                    dataCarouselList[that.data.currentTab] = {len: carouselsList.length, data: carouselsList};
                  }else{
                      dataCarouselList[that.data.currentTab] = [];
                  }
                  that.setData({
                      dataCarouselList: dataCarouselList
                    });
              }else{
                  var dataArray = rsData.news
                  for (var i = 0; i < dataArray.length; i++) {
                      dataNewsList[that.data.currentTab].push(dataArray[i]);
                  }
              }
              that.setData({
                dataNewsList: dataNewsList
              });
              
          },
          complete: function(e) {
            wx.stopPullDownRefresh();
            var canAddData = that.data.canAddData;
            that.setData({
              canAddData: true,
              loadingViewDisplay: "none"
            });
          }
      });
  },
  onReachBottom: function(){//上拉加载
      var that = this,
          canAddData = that.data.canAddData;
      if (!canAddData) {
        //前一次加载未返回，阻止重复请求
        return ;
      }
      that.setData({
        canAddData: false,
        loadingViewDisplay: "block"
      });
      var dataNewsList = that.data.dataNewsList;
      var dataNews = that.data.dataNewsList[that.data.currentTab];
      var len = dataNews.length;
      if(len){
          var lastSortNo = dataNews[len - 1].sortNo;
          var lastOnlineTime = dataNews[len - 1].onlineTime;
          var lastId = dataNews[len - 1].id;
          that.getData(lastSortNo, lastOnlineTime, lastId, false);
      }else{
          that.getData("", "", "", true);
      }
  },
  newsActive: function(e){//切换栏目
      var that = this,
          columnId = e.target.dataset.colId,
          currentTab = e.target.dataset.idx,
          isCarousel = e.target.dataset.carousel;
          
      if(that.data.currColumnId != columnId){
          that.setData({
              currColumnId: columnId,
              currentTab: currentTab,
              isCarousel: isCarousel
          });
          if (!that.data.dataNewsList[that.data.currentTab]) {
            //无数据时，加载数据
            that.getData("", "", "", true);
          }
      }
  },
  onPullDownRefresh: function(){//下拉刷新
      this.getData("", "", "", true);
  },

  // 转到新闻详情
  newsDetail: function(e) {
    wx.navigateTo({
      url: "../news_detail/news_detail?id=" + e.currentTarget.dataset.id
    })
  } 
})