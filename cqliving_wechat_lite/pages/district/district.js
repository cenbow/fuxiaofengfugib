// pages/district/district.js
var imageUtil = require('../../utils/util.js');
Page({
  data:{
    appGlobalData: getApp().globalData,
    canAddData: true,
    loadingViewDisplay: "none"
  },
  onLoad:function(options){
    // 页面初始化 options为页面跳转所带来的参数
    var imageSize = imageUtil.imageUtil()
    this.setData({  
      fullWidth: imageSize.imageWidth,  
      fullHeight: imageSize.imageHeight,

      appId: options.aid,
      columnId: options.cid
    })

    // 设置标题
    // wx.setNavigationBarTitle({
    //   title: options.tt
    // })
    // 查询数据
    this.getData(options.cid, "", "", "", true)
  },
  onReady:function(){
    // 页面渲染完成
  },
  onShow:function(){
    // 页面显示
  },
  onHide:function(){
    // 页面隐藏
  },
  onUnload:function(){
    // 页面关闭
  },
  onPullDownRefresh: function () {
    //下拉刷新
    this.getData(this.data.columnId, "", "", "", true)
  },
  // 加载更多
  onReachBottom: function() {
    var canAddData = this.data.canAddData
    if (!canAddData) {
      //前一次加载未返回，阻止重复请求
      return
    }
    this.setData({
        canAddData: false,
        loadingViewDisplay: "block"
    })

    var currColumnId = this.data.columnId
    var dataList = this.data.news
    var len = dataList.length;
    if (len) {
        var lastSortNo = dataList[len - 1].sortNo
        var lastOnlineTime = dataList[len - 1].onlineTime
        var lastId = dataList[len - 1].id
        //上拉刷新
        this.getData(currColumnId, lastSortNo, lastOnlineTime, lastId, false)
    } else {
        this.getData(currColumnId, "", "", "", true)
    }
  },

  // 新闻数据
  getData: function(currColumnId, lastSortNo, lastOnlineTime, lastId, isFirstPage) {
    var app = getApp()  
    var that = this;
    wx.request({
      url: app.globalData.serverUrl + "/wxl/news.html",
      data: {
        appId: this.data.appId,
        columnId: currColumnId,
        isCarousel: false,
        lastSortNo: lastSortNo ? lastSortNo : "", 
        lastOnlineTime: lastOnlineTime ? lastOnlineTime : "", 
        lastId: lastId ? lastId : "",
        onlyWechat: false
      },
      header: {
        'content-type': 'application/x-www-form-urlencoded'
      },
      method: "POST",
      success: function (response) {
        console.log(response.data.data.news)
        if (isFirstPage) {
          that.setData({
            news: response.data.data.news
          })  
        } else {
          var dataArrayPage = that.data.news
          if (response.data.data.news && response.data.data.news.length > 0) {
            var dataArray = response.data.data.news
            for (var i = 0; i < dataArray.length; i++) {
              dataArrayPage.push(dataArray[i]);
            }
            that.setData({
              news: dataArrayPage
            });
          }
        }
      },
      fail: function (response) {
        console.log(response)
      },
      complete: function(e) {
        wx.stopPullDownRefresh()
        var canAddData = that.data.canAddData
        that.setData({
          canAddData: true,
          loadingViewDisplay: "none"
        })  
      }
    })
  }
})