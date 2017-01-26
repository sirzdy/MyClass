<%--
  Created by IntelliJ IDEA.
  User: zdy
  Date: 2017/1/9 0009
  Time: 10:32
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>信息</title>
    <link rel="stylesheet" href="../css/inf.css">
</head>
<body>
<jsp:include page="../page/header.jsp"/>
<div class="panel panel-default col-xs-12 col-sm-12 col-md-8 col-lg-8 col-md-offset-2 clo-lg-offset-2">
    <div class="panel-heading">
        <h3 class="panel-title"><strong>个人信息 </strong><%=session.getAttribute("id")%>
        </h3>
    </div>
    <div class="panel-body" id="inf-panel">
        <form role="form">
            <div class="row">
                <div class="col-xs-12 col-sm-6 col-md-6 col-lg-6">
                    <div class="form-group">
                        <div class="input-group">
                            <span class="input-group-addon">姓名</span>
                            <input type="text" class="form-control" name="name" id="name" placeholder="请输入中文姓名"/>
                        </div>
                    </div>
                </div>
                <div class="col-xs-12 col-sm-6 col-md-6 col-lg-6">
                    <div class="form-group">
                        <div class="input-group" class="has-success">
                            <span class="input-group-addon">生日</span>
                            <input type="date" class="form-control" name="birthday" id="birthday"/>
                        </div>
                    </div>
                </div>
            </div>
            <div class="form-group">
                <div class="input-group">
                    <span class="input-group-addon">邮箱</span>
                    <input type="email" class="form-control" name="email" id="email" placeholder="请输入电子邮箱"/>
                </div>
            </div>
            <div class="form-group">
                <div class="input-group">
                    <span class="input-group-addon">手机</span>
                    <input type="text" class="form-control" name="tel" id="tel" placeholder="请输入手机号"/>
                </div>
            </div>
            <div class="form-group">
                <div class="input-group">
                    <span class="input-group-addon">QQ</span>
                    <input type="text" class="form-control" name="qq" id="qq" placeholder="请输入QQ号/手机号"/>
                </div>
            </div>
            <div class="form-group">
                <div class="input-group">
                    <span class="input-group-addon">微信</span>
                    <input type="text" class="form-control" name="wechat" id="wechat" placeholder="请输入微信号/手机号/QQ号"/>
                </div>
            </div>
            <div class="form-group">
                <div class="row">
                    <div class="col-xs-12 col-sm-4 col-md-4 col-lg-4">
                        <div class="input-group">
                            <span class="input-group-addon">国家</span>
                            <select class="form-control" name="country" id="country">
                            </select>
                        </div>
                    </div>
                    <div class="col-xs-12  col-sm-4 col-md-4 col-lg-4 ">
                        <div class="input-group">
                            <span class="input-group-addon">省份</span>
                            <select class="form-control" name="province" id="province">
                            </select>
                        </div>
                    </div>
                    <div class="col-xs-12 col-sm-4 col-md-4 col-lg-4 ">
                        <div class="input-group">
                            <span class="input-group-addon">城市</span>
                            <select class="form-control" name="city" id="city">
                            </select>
                        </div>
                    </div>
                </div>
            </div>
            <div class="form-group">
                <div class="row">
                    <div class="col-xs-12 col-sm-6 col-md-6 col-lg-6">
                        <div class="input-group">
                            <span class="input-group-addon">学校</span>
                            <input type="text" class="form-control" name="university" id="university"
                                   placeholder="请输入在读院校"/>
                        </div>
                    </div>
                    <div class="col-xs-12 col-sm-6 col-md-6 col-lg-6">
                        <div class="input-group">
                            <span class="input-group-addon">院系</span>
                            <input type="text" class="form-control" name="academy" id="academy"
                                   placeholder="请输入在读院校院系"/>
                        </div>
                    </div>
                </div>
            </div>
            <div class="form-group">
                <div class="row">
                    <div class="col-xs-12 col-sm-6 col-md-6 col-lg-6">
                        <div class="input-group">
                            <span class="input-group-addon">单位</span>
                            <input type="text" class="form-control" name="company" id="company" placeholder="请输入就职单位"/>
                        </div>
                    </div>
                    <div class="col-xs-12 col-sm-6 col-md-6 col-lg-6">
                        <div class="input-group">
                            <span class="input-group-addon">职位</span>
                            <input type="text" class="form-control" name="occupation" id="occupation"
                                   placeholder="请输入职位"/>
                        </div>
                    </div>
                </div>
            </div>
        </form>
    </div>
    <div class="panel-footer">
        <button type="button" class="btn btn-default show-inf" id="updinf">修改</button>
        <button type="button" class="btn btn-default upd-inf" id="caninf" style="display: none">取消</button>
        <button type="button" class="btn btn-default upd-inf" id="saveinf" style="display: none">保存</button>
        <div style="display: inline-block" id="infmes"></div>
    </div>
</div>

<script src="../js/addr.js"></script>
<script src="../js/inf.js"></script>
</body>
</html>
