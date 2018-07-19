<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<script type="text/javascript" src="jquery-1.9.1.min.js"></script>
<script src="http://res.wx.qq.com/open/js/jweixin-1.0.0.js"></script>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="width=device-width,user-scalable=0">
<script type="text/javascript">
	$(function() {
		var curUrl = window.location.href || "";
		var tmpCurl = curUrl.replace(/&/g, "___");
		var addRecUrl = "/wx/jssdk?currentUrl=" + tmpCurl + "&t="
				+ new Date().getTime();
		
		$.ajax({
			type : "POST",
			url : addRecUrl,
			data : null,
			contenttype : "application/x-www-form-urlencoded;charset=utf-8",
			dataType : "json",
			success : callbackConfig,
			timeout : 60000,
		});
	})

	function callbackConfig(response) {
		wx.config({
			debug : true, // 开启调试模式,调用的所有api的返回值会在客户端alert出来，若要查看传入的参数，可以在pc端打开，参数信息会通过log打出，仅在pc端时才会打印。
			appId :'wx4057676eb20bc7c8', // 必填，公众号的唯一标识
			timestamp : response.timestamp, // 必填，生成签名的时间戳
			nonceStr : response.nonceStr, // 必填，生成签名的随机串
			signature : response.signature,// 必填，签名，见附录1
			jsApiList : [ // 必填，需要使用的JS接口列表，所有JS接口列表见附录2
			'wx.onMenuShareTimeline', 'wx.onMenuShareAppMessage' ]
		});

		wx.ready(function() {
			wx.onMenuShareTimeline({
				title : 'test分享到朋友圈', // 分享标题
				link : 'http://movie.douban.com', // 分享链接
				imgUrl : 'http://pic6.huitu.com/res/20130116/84481_20130116142820494200_1.jpg', // 分享图标
				success : function() {
					// 用户确认分享后执行的回调函数
				},
				cancel : function() {
					// 用户取消分享后执行的回调函数
				}
			});
			wx.onMenuShareAppMessage({
				title : '私人分享', // 分享标题
				desc : 'weixinAutho', // 分享描述
				link : 'http://music.163.com', // 分享链接
				imgUrl : 'http://pic6.huitu.com/res/20130116/84481_20130116142820494200_1.jpg', // 分享图标
				success : function() {
					// 用户确认分享后执行的回调函数
				},
				cancel : function() {
					// 用户取消分享后执行的回调函数
				}
			});
		});

		wx.error(function(res) {
			alert('系统或网络发生错误.请稍候再试!');
		});

	}
</script>
<title>啊啊啊测试</title>
</head>
<body>分享测试
</body>
</html>