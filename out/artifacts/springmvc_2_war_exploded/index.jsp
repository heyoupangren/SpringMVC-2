<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2019/7/20
  Time: 9:36
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8;" pageEncoding="UTF-8" language="java" %>
<html>
  <head>
    <title>index</title>
    <script type="application/javascript" src="/js/jquery.min.js"></script>
    <script type="application/javascript">
      $(function () {
          $("#testJson").click(function () {
             var url =this.href;
             var args = {};
            $.post(url,args,function (data) {
                for(var i=0;i<data.length;i++){
                    var id = data[i].id;
                    var lastName = data[i].lastName;

                    alert(id + " : "+lastName);
                }
            });
            return false;
          });
      })
    </script>
  </head>
  <body>
  <a href="/emps">List All Employees</a>

  <br><br>

  <a href="/testJson" id="testJson">Test Json</a>

  <br><br>

  <form action="/testHttpMessageConverter" method="post" enctype="multipart/form-data">
    File: <input type="file" name="file"/>
    Desc: <input type="text" name="desc"/>
    <input type="submit" value="Submit">
  </form>
<br><br>

  <a href="/testResponseEntity">Test ResponseEntity</a>

  <br><br>

  <!--
    关于国际化：
    1.在页面上能够根据浏览器语言的设置的情况对文本(不是内容)，时间，数值进行本地化处理
    2.可以在bean 中获取国际化资源文件Locale 对应的消息
    3.可以通过超链接切换Locale ,而不再依赖于浏览器的语言设置情况

    解决：1.使用JSTL的fmt标签
    2.在bean中注入ResourceBundleMessageSource 的示例，使用其对应的getMessage 方法即可
    3.配置LocalResolver 和 LocaleChangeInterceptor
   -->

  <br><br>

  <a href="/i18n">I18N Page</a>
  <br><br>

  <!--文件上传功能-->
  <form action="/testFileUpload" method="post" enctype="multipart/form-data">
    File: <input type="file" name="file"/>
    Desc: <input type="text" name="desc"/>
    <input type="submit" value="Submit">
  </form>
  <br><br>

  <a href="/testExceptionHandlerExceptionResolver?i=1">Test ExceptionHandlerExceptionResolver</a>
  <br><br>
  <a href="/testResponseStatusExceptionResolver?i=1">Test ResponseStatusExceptionResolver</a>
  <br><br>
  <a href="/testDefaultHandlerExceptionResolver">Test DefaultHandlerExceptionResolver</a>
  <br><br>

  <a href="/testSimpleMappingExceptionResolver?i=2">Test SimpleMappingExceptionResolver</a>
  <br><br>
  </body>
</html>
