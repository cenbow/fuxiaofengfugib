function formatTime(date) {
  var year = date.getFullYear()
  var month = date.getMonth() + 1
  var day = date.getDate()

  var hour = date.getHours()
  var minute = date.getMinutes()
  var second = date.getSeconds()


  return [year, month, day].map(formatNumber).join('/') + ' ' + [hour, minute, second].map(formatNumber).join(':')
}

function formatNumber(n) {
  n = n.toString()
  return n[1] ? n : '0' + n
}

module.exports = {
  formatTime: formatTime
}

function imageUtil() {  
  var imageSize = {};
  //获取屏幕宽高  
  wx.getSystemInfo({  
    success: function (res) {  
      var windowWidth = res.windowWidth;  
      var windowHeight = res.windowHeight;  
      imageSize.imageWidth = windowWidth;
      imageSize.imageHeight = windowHeight; 
    }  
  })  
  return imageSize;  
}  

module.exports = {  
  imageUtil: imageUtil  
}  