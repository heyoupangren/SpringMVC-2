<%@ page import="java.util.Map" %>
<%@ page import="java.util.HashMap" %><%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2019/7/20
  Time: 10:43
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Title</title>
</head>
<body>


    <form action="testConversionServiceConverter" method="post">
        <%--lastname-email-gender-department.id 例如:GG-gg@163.com-0-105 --%>
        Employee: <input type="text" name="employee"><br>
        <input type="submit" value="Submit">
    </form>
    <br>

    <!--
        1.WHY 使用form标签？
        可以更快速的开发表单页面，而且可以更方便的进行表单回显
        2.注意：
        可以通过ModelAttribute 属性指定绑定的模型属性，
        若没有指定该属性，则默认从request 域对象中读取command 的表单bean
        如果该属性值有不存在，则会发生错误
    -->
    <form:form action="${pageContext.request.contextPath}/emp" method="post" modelAttribute="employee">
        <!--path属性对应html表单标签的name属性-->
        
        <c:if test="${employee.id==null}">
            LastName: <form:input path="lastName"/>
        </c:if>
        
        <c:if test="${employee.id!=null}">
            <form:hidden path="id"/>
            <input type="hidden" name="_method" value="PUT">

            <%--对于 _method 不能使用form:hidden 标签，因为modelAttribute 对应的bean中 没有 _method 这个属性--%>
            <%--<form:hidden path="_method" value="PUT">--%>
        </c:if>
      
        <br>
        Email: <form:input path="email"/>
        <br>
        <%
            Map<String,String> genders = new HashMap();
            genders.put("1","Male");
            genders.put("0","Female");
            request.setAttribute("genders",genders);
        %>
        Gender:
        <br>
        <form:radiobuttons path="gender" items="${genders}" delimiter="<br>"/>
        <br>
        Department:<form:select path="department.id"
                                items="${departments}" itemValue="id" itemLabel="departmentName"></form:select>
        <br>
        <%--1.数据类型转换
            2.数据类型格式化
            3.数据校验
            1).如何校验？注解
            ①.使用JSR 303验证标准
            ②.加入hibernate validator 验证框架
            ③.在SpringMVC 配置文件中添加    <mvc:annotation-driven />
            ④.需要在bean的属性上添加对应的注解
            ⑤.在目标方法bean类型的前面添加@Valid 注解
            2).验证出错转向哪个页面？
            3).错误消息？如何显示，如何把错误消息进行国际化
        --%>
        Birth：<form:input path="birth"/>
        <br>
        Salary：<form:input path="salary"/>
        <br>
        <input type="submit" value="Submit">
    </form:form>
</body>
</html>
