// pages/districts/districts.js
var app = getApp()
Page({
    data: {
        canAddData: true,
        loadingViewDisplay: "none",
        dataList: []
    },
    onLoad: function (options) {
        // 页面初始化 options为页面跳转所带来的参数
        this.getData()
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
    onPullDownRefresh: function () {
        //下拉刷新
        this.getData()
    },
    onReachBottom: function () {
        var canAddData = this.data.canAddData
        if (!canAddData) {
            //前一次加载未返回，阻止重复请求
            return
        }
        this.setData({
            canAddData: false,
            loadingViewDisplay: "block"
        })

        var len = this.data.dataList.length;
        if (len) {
            var lastSortNo = this.data.dataList[len - 1].sortNo;
            var lastAppId = this.data.dataList[len - 1].appId;
            var lastId = this.data.dataList[len - 1].id;
            //上拉刷新
            this.getData(lastSortNo, lastAppId, lastId, true)
        } else {
            this.getData()
        }
    },
    getData: function (lastSortNo, lastAppId, lastId, isadd) {
        var url = app.globalData.serverUrl + "/wxl/getApps.html";
        var that = this;
        wx.request({
            //url: 'http://127.0.0.1/wxl/getApps.html',
            url: url,
            data: {
                appId: app.globalData.appId,
                lastSortNo: lastSortNo ? lastSortNo : '',
                lastAppId: lastAppId ? lastAppId : '',
                lastId: lastId ? lastId : ''
            },
            method: "POST",
            dataType: "json",
            header: {
                'content-type': 'application/x-www-form-urlencoded'
            },
            success: function (data) {
                if (isadd) {
                    var dataArrayPage = that.data.dataList;
                    if (data.data.data.dataList && data.data.data.dataList.length > 0) {
                        var dataArray = data.data.data.dataList
                        for (var i = 0; i < dataArray.length; i++) {
                            dataArrayPage.push(dataArray[i]);
                        }
                        that.setData({
                            dataList: dataArrayPage
                        });
                    }
                } else {
                    that.setData({
                        dataList: data.data.data.dataList
                    });
                }
            },
            fail: function (err) {
                console.log("调用失败");
                console.log(err);
            },
            complete: function (e) {
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