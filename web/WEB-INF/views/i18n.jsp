<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2019/7/21
  Time: 10:34
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8"  pageEncoding="UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<head>
    <title>Title</title>
    <meta charset="UTF-8">
</head>
<body>
<fmt:message key="i18n.user"></fmt:message>
<br><br>

<a href="/i18n2">I18N2 Page</a>

<br><br>

<a href="/i18n?locale=zh_CN">中文</a>

<br><br>

<a href="/i18n?locale=en_US">英文</a>
</body>
</html>
