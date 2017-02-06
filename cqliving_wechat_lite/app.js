//app.js
App({
  data:{
  },
  onLaunch: function () {
    //调用API从本地缓存中获取数据
    var logs = wx.getStorageSync('logs') || []
    logs.unshift(Date.now())
    wx.setStorageSync('logs', logs)
  },
  getUserInfo:function(cb){
    var that = this
    if(this.globalData.userInfo){
      typeof cb == "function" && cb(this.globalData.userInfo)
    }else{
      //调用登录接口
      wx.login({
        success: function () {
          wx.getUserInfo({
            success: function (res) {
              that.globalData.userInfo = res.userInfo
              typeof cb == "function" && cb(that.globalData.userInfo)
            }
          })
        }
      })
    }
  },
  globalData:{
    // 接口服务器地址
    // serverUrl: "http://172.30.141.137:1014", //本机
    // serverUrl: "http://222.180.239.90:38088",  //测试环境
    // serverUrl: "http://api.cqliving.com:81", // 预发布地址
    serverUrl: "https://api.cqliving.com", // 正式地址
    //serverUrl: "https://apitest.cqliving.com", // 测试环境

    // 轮播图高度
    carouselHeight: 20*9/16, 
    // 单图图片高度
    singleImgHeight: 20*0.92*0.3*3/4, 
    // 单图文本块高度
    singleContentHeight: 20*0.92*9/40, 
    // 大图图片高度
    bigImgHeight: 20*0.92*3/7,
    // 多图图片高度
    multipleImgHeight: 20*0.92*0.3*3/4, 
    // 视频图片高度
    videoImgHeight: 20*9/16,

    // 当前小程序的 APPID（重庆）
    appId: 1
  }
})