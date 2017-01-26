<%--
  Created by IntelliJ IDEA.
  User: zdy
  Date: 2017/1/23
  Time: 上午11:05
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<jsp:include page="../page/header.jsp"/>
<div class="panel panel-default col-xs-12 col-sm-12 col-md-10 col-lg-10 col-md-offset-1 clo-lg-offset-1">
    <div class="panel-heading">
        <h3 class="panel-title"><strong>信息 </strong>
        </h3>
    </div>
    <div class="panel-body table-responsive">
        <table class="table table-hover table-striped" id="allinf">
            <tr>
                <th>学号</th>
                <th>姓名</th>
                <th>邮箱</th>
                <th>电话</th>
                <th>QQ</th>
                <th>微信</th>
                <th>生日</th>
                <th>地址</th>
                <th>高校</th>
                <th>工作</th>
            </tr>
        </table>
    </div>
    <div class="panel-footer">
        悬浮查看状态，单击跳转到ta的主页。
    </div>
</div>
<div id="information"></div>
<%--<div class="panel-heading">
            <span class="panel-title"><strong>状态 </strong></span>
            <i class="fa fa-close fa-2x" style="float: right;"></i>

        </div>--%>
<script src="../js/all.js"></script>
</body>
</html>
