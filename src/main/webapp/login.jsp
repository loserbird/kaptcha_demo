<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
<form action="${pageContext.request.contextPath}/validate2">
验证码：<input type="text" name="validateCode"><img alt="" src="${pageContext.request.contextPath}/generate">
<input type="submit" value="确定"/>
</form>

<form action="${pageContext.request.contextPath}/validate" method="post">    
    <img src="kaptcha.jpg" id="kaptchaImage" /> <input type="text"    
        name="validateCode" value="" /> <input type="submit" name="submit"    
        value="submit" />    
</form>    
</body>
</html>