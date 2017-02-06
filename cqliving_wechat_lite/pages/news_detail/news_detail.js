// pages/news_detail/news_detail.js
var WxParse = require('../../utils/wxParse/wxParse.js');
var imageUtil = require('../../utils/util.js');
Page({
  data:{
    appId: 1,
    url: '/wxl/nd.html',
    source: '',
    pubDate: '',
    viewCount: '',
    resourceList: null,
    tmpContents: null,
    shareTitle: '',//分享标题
    synopsis: '',//分享摘要
    newId: null,//当前新闻id
    pageTotalImages: []//存放页面上所有的图片
  },
  onLoad:function(options){
    // 页面初始化 options为页面跳转所带来的参数
    if(!options.id){
      console.log('参数有误');
    }
    this.setData({
      newsId: options.id
    });
    var that = this,
        _app = getApp(),
        url = _app.globalData.serverUrl + this.data.url;
    wx.request({
      url: url,
      data: {
        appId: _app.globalData.appId,
        id: options.id
      },
      method: 'POST', // OPTIONS, GET, HEAD, POST, PUT, DELETE, TRACE, CONNECT
      header: {'content-type': 'application/x-www-form-urlencoded'}, // 设置请求的 header
      success: function(res){
        if(res.data.code >= 0){
            var rs = res.data.data;
            var article = that.replaceHtml(rs.content),
                title = rs.title,
                contextType = rs.contextType;
            //设置标题
            // wx.setNavigationBarTitle({
            //   title: _app.globalData.navigationBarTitleText
            // });
            WxParse.wxParse('title', 'html', title, that,5);//标题
            that.setData({
              source: rs.source,
              pubDate: rs.pubDate,
              viewCount: rs.viewCount,
              contextType: contextType,
              synopsis: rs.synopsis,
              shareTitle: title
            });

            var tmpContents = [];
            if(contextType == 5 || contextType == 4){//视频||音频
              that.setData({
                resourceList: rs.appResource
              });
            }else if(contextType == 2){//处理原创新闻
                var  list = rs.appResource;
                var rsList = [];
                for(var i = 0; i < list.length; i++){
                    var ttype = list[i].type;
                    if(ttype == 0){//普通文本
                        WxParse.wxParse('transData', 'html', that.replaceHtml(list[i].description), that,5);
                        tmpContents.push({id: list[i].id, data: that.data.transData});
                        that.getWxParseImagesToData(that.data.transData);
                    }else if(ttype == 6){
                      that.getImagesToData([list[i]]);
                    }
                }
                that.setData({
                  resourceList: rs.appResource
                });
            }else if(contextType == 1){//多图
              that.getImagesToData(rs.appResource);
              that.setData({
                resourceList: rs.appResource
              });
            }else{
              WxParse.wxParse('article', 'html', article, that,5);//图文内容
              that.getWxParseImagesToData(that.data.article);
            }
            that.setData({
              tmpContents: tmpContents
            });
        }
      },
      fail: function() {
        // fail
      },
      complete: function() {
        // complete
      }
    })
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
  getImagesToData: function(list){
    var that = this,
        images = that.data.pageTotalImages;
        if(typeof that.data.pageTotalImages != 'object'){
            images = [];
        }
    if(list && list.length > 0){
        for(var i = 0; i < list.length; i++){
            if(list[i].fileUrl != null && list[i].fileUrl != ''){
                images.push(list[i].fileUrl);
            }
        }
        that.setData({
          pageTotalImages: images
        });
    }
  },
  getWxParseImagesToData: function(data){
    var that = this,
        list = data.imageUrls,
        images = that.data.pageTotalImages;
    if(typeof that.data.pageTotalImages != 'object'){
        images = [];
    }
    if(list && list.length > 0){
        for(var i = 0; i < list.length; i++){
          if(list[i] != null && list[i] != ''){
              images.push(list[i]);
          }
        }
        that.setData({
          pageTotalImages: images
        });
    }
  },
  onShareAppMessage: function () {
    var that = this;
    return {
      title: that.data.shareTitle,
      desc: that.data.synopsis,
      path: '/pages/news_detail/news_detail?id=' + that.data.newsId
    }
  },
  replaceHtml: function(html){//<strong>标签两边的空格，防止wxParse工具把空格解析成一行
    html = html.replace(/\s*<strong>\s*/g, '<strong>');
    return html;
  }
})