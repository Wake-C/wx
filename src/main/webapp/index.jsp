<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="width=device-width,user-scalable=0">
<title>微信授权测试</title>
</head>
<body>
	欢迎访问 this is test
	<br /> openId:${userInfo.openId}
	<br /> 用户名:${userInfo.nickname}
	<br /> 来自:${userInfo.country }${userInfo.province}${userInfo.city }
	
	微信支付二维码
	<img alt="" src="${dataUrl }">
</body>
</html>