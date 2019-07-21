<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2019/7/20
  Time: 9:46
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Title</title>

    <!--
        SpringMVC 处理静态资源：
        1.为什么会有这样的问题：
        优雅的REST 风格的资源URL不希望带.html 或者.do 等后缀
        若将DispatcherServlet 请求映射配置为/,
        则Spring MVC 将捕获WEB容器的所有请求，包括静态资源的请求，SpringMVC 会将他们当成一个普通请求处理，
        因找不到对应处理器将导致错误。
        2.解决：在SpringMVC 的配置文件中配置   <%-- <mvc:default-servlet-handler/>--%>

       <%-- <mvc:annotation-driven></mvc:annotation-driven>--%>
    -->
    <script type="application/javascript" src="/js/jquery.min.js"></script>
    <script type="application/javascript">
        $(function () {
            $(".delete").click(function () {
                var  href =$(this).attr("href");
                $("form").attr("action",href).submit();
                return false;
            });
        })
    </script>
</head>
<body>

    <form action="" method="post">
        <input type="hidden" name="_method" value="DELETE"/>
    </form>
    <c:if test="${empty requestScope.employees}">
        没有任何员工信息
    </c:if>
    <c:if test="${!empty requestScope.employees}">
        <table border="1" cellspacing="0" cellpadding="10">
            <tr>
                <th>ID</th>
                <th>LastName</th>
                <th>Email</th>
                <th>Gender</th>
                <th>Department</th>
                <th>Edit</th>
                <th>Delete</th>
            </tr>
            <c:forEach items="${requestScope.employees}" var="emp">
                <tr>
                    <td>${emp.id}</td>
                    <td>${emp.lastName}</td>
                    <td>${emp.email}</td>
                    <td>${emp.gender ==0 ? 'Female':'Male'}</td>
                    <td>${emp.department.departmentName}</td>
                    <td><a href="/emp/${emp.id}">Edit</a> </td>
                    <td><a href="/emp/${emp.id}" class="delete">Delete</a> </td>
                </tr>
            </c:forEach>
        </table>
    </c:if>
    <br>
    <a href="/emp">Add New Employee</a>
</body>
</html>
