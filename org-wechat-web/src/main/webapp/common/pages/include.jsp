<meta name="viewport" content="width=device-width,initial-scale=1.0,minimum-scale=1.0,maximum-scale=1.0,user-scalable=no" />
<script type="text/javascript" src="/common/scripts/lib/require.js"></script>
<script type="text/javascript">
(function() {
	require.config({
		baseUrl: "/common/scripts/lib",
		paths: {
			jquery: 'jquery-1.11.3.min',
			wxShare: 'http://res.wx.qq.com/open/js/jweixin-1.0.0'
		}
	});
})();
var uris = window.location.href.split('/');
var host = uris[0] + "//" + uris[2];

/** 分享 */
function initShare(options) {
	var wx = options.wxObj;
	if (wx) {
		wx.config(options.config);
		wx.ready(function() {
			wx.onMenuShareAppMessage({
				title : options.title,
				desc : options.desc,
				link : options.link,
				imgUrl : options.imgUrl,
				trigger : function(res) {},
				success : function(res) {
					if (options.success) {
						try {
							options.success.call(this, arguments);
						} catch (e) {
							console.log(e)
						}
					}
				},
				cancel : function(res) {},
				fail : function(res) {}
			});
			
			wx.onMenuShareTimeline({
				title : options.title,
				link : options.link,
				imgUrl : options.imgUrl,
				trigger : function(res) {},
				success : function(res) {
					if (options.success) {
						try {
							options.success.call(this, arguments);
						} catch (e) {
							console.log(e)
						}
					}
				},
				cancel : function(res) {},
				fail : function(res) {}
			});
			wx.error(function(res) {});
		});
	}
	else {
		console.log('weixin javascript object is null')
	}
}
</script>
