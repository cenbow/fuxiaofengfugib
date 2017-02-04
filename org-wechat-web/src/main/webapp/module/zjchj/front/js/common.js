$(function(){
    if ($(window.screen.width<900)) {//不需要使用 
        var docElement = document.documentElement,
        metaElement = document.querySelector('meta[name="viewport"]'),
        styleElement = document.createElement("style"),
        version,
        screenScale = 1,
        originScreenW = 750,
        screenW = window.screen.width,
        screenH = window.screen.height;
        screenScale = parseInt(screenW) / originScreenW;
        if (/Android (\d+\.\d+)/.test(navigator.userAgent)) {
            version = parseFloat(RegExp.$1);
            if (version > 2.3) {
                metaElement.setAttribute("content", "width="+originScreenW+",minimum-scale=" + screenScale + ",maximum-scale=" + screenScale + ",target-densitydpi=device-dpi")
            } else {
                metaElement.setAttribute("content", "width="+originScreenW+",target-densitydpi=device-dpi")
            }
        } else {
            metaElement.setAttribute("content", "width="+originScreenW+",user-scalable=no,target-densitydpi=device-dpi")
        }
    }
})