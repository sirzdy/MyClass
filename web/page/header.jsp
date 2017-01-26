<%--
  Created by IntelliJ IDEA.
  User: zdy
  Date: 2017/1/14
  Time: 上午11:44
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<head>
    <meta charset="UTF-8">
    <title>Header</title>
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no">
    <link rel="stylesheet" href="../css/font-awesome.min.css">
    <link rel="stylesheet" href="../css/bootstrap.min.css">
    <link rel="stylesheet" href="../css/mystyle.css">
    <!-- HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries -->
    <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
    <!--[if lt IE 9]>
    <script src="http://cdn.bootcss.com/html5shiv/3.7.2/html5shiv.min.js"></script>
    <script src="http://cdn.bootcss.com/respond.js/1.4.2/respond.min.js"></script>
    <![endif]-->
</head>

<body>
<nav class="navbar navbar-default" role="navigation">
    <div class="container-fluid">
        <!-- Brand and toggle get grouped for better mobile display -->
        <div class="navbar-header">
            <button type="button" class="navbar-toggle collapsed" data-toggle="collapse"
                    data-target="#bs-example-navbar-collapse-1">
                <span class="sr-only">Toggle navigation</span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
            </button>
            <a class="navbar-brand" href="../">Contacts</a>
            <%--<li>当前在线用户数：${userNumber}</li>--%>
            <%--<%
                if(session.getServletContext().getAttribute("signinUserNumber")!=null&&!session.getServletContext().getAttribute("signinUserNumber").equals("")){
            %>
                当前登录用户数：${signinUserNumber}
            <%
            }%>--%>
        </div>
        <!-- Collect the nav links, forms, and other content for toggling -->

        <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
            <ul class="nav navbar-nav">
                <%
                    if (session.getAttribute("id")!=null){
                        out.println("<li><a href=\"../inf/state.jsp?userid="+session.getAttribute("id")+"\">个人主页</a></li>\n"+
                                "<li><a href=\"../inf/all.jsp\">看资料</a></li>\n"+
                                "<li><a href=\"../page/chat.jsp\">聊天室</a></li>\n");
                    }
                %>
            </ul>
            <ul class="nav navbar-nav navbar-right">
                <li><a>当前在线用户数 ${userNumber}<span class="sr-only">${userNumber}</span></a></li>
                <%
                    if (session.getAttribute("id")==null){
                        out.println("<li><div id=\"signbtn\">\n" +
                                "                    <button type=\"button\" class=\"btn btn-default navbar-btn\" data-toggle=\"modal\" data-target=\"#signup\">注册\n" +
                                "                    </button>\n" +
                                "                    <button type=\"button\" class=\"btn btn-default navbar-btn\" data-toggle=\"modal\" data-target=\"#signin\">登录\n" +
                                "                    </button>\n" +
                                "                </div></li>");
                    }else{
                        out.println(" <li id=\"dropdown\" class=\"dropdown\">\n" +
                                "                    <a href=\"#\" class=\"dropdown-toggle\" data-toggle=\"dropdown\">\n" +
                                "                        <img id=\"current_avatar\" src=\"../img/avatar.png\" alt="+session.getAttribute("id")+" class=\"img-rounded\" width=\"25\"height=\"25\"/>\n" +
                                "                        <span class=\"caret\"></span>\n" +
                                "                    </a>\n" +
                                "                    <ul class=\"dropdown-menu\" role=\"menu\">\n" +
                                "                        <li><a href=\"../inf/state.jsp?userid="+session.getAttribute("id")+"\" id=\"state\">【<span id=\"id\">");
                        out.println(session.getAttribute("id")+"\n");
                        out.println("</span>】</a></li>\n" +
                                "                        <li><a href=\"../inf/inf.jsp\" id=\"myinf\">我的资料</a></li>\n" +
                                "                        <li><a href=\"mailto:boxuerixin@gmail.com?subject=班级系统意见反馈\">意见反馈</a></li>\n" +
                                "                        <li class=\"divider\"></li>\n" +
                                "                        <li><a href=\"#\" id=\"signout\">退出登录</a></li>\n" +
                                "                    </ul>\n" +
                                "                </li>");
                    }
                %>
            </ul>
        </div>
        <!-- /.navbar-collapse -->
    </div>
    <!-- /.container-fluid -->
</nav>
<div class="modal fade bs-example-modal-sm" id="signup" tabindex="-1" role="dialog" aria-labelledby="mySmallModalLabel"
     aria-hidden="true">
    <div class="modal-dialog modal-sm">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">&times;</span><span
                        class="sr-only">Close</span></button>
                <h4 class="modal-title">注册</h4>
            </div>
            <div class="modal-body">
                <form role="form">
                    <div class="form-group">
                        <label style="display: block;">学号</label>
                        <input type="text" class="form-control" id="suid" placeholder="请输入学号">
                        <div class="alert alert-warning alert-dismissible hidden" role="alert" id="alertid">
                            <strong>请输入正确的学号!</strong>
                        </div>
                    </div>
                    <div class="form-group">
                        <label>密码</label>
                        <input type="password" class="form-control" id="supwd" placeholder="请输入密码">
                        <div class="alert alert-warning alert-dismissible hidden" role="alert" id="alertpwd">
                            <strong>请输入六位（含）以上的密码!</strong>
                        </div>
                    </div>
                    <div class="form-group">
                        <label>确认密码</label>
                        <input type="password" class="form-control" id="suenpwd" placeholder="请确认密码">
                        <div class="alert alert-warning alert-dismissible hidden" role="alert" id="alertenpwd">
                            <strong>请输入六位（含）以上的密码!</strong>
                        </div>
                        <div class="alert alert-warning alert-dismissible hidden" role="alert" id="alertenpwdsame">
                            <strong>两次输入的密码不一致!</strong>
                        </div>
                    </div>
                </form>
            </div>
            <div class="modal-footer">
                <div style="display: inline-block" id="sumes"></div>
                <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
                <button type="button" class="btn btn-primary" id="subtn">确定</button>
            </div>
        </div>
        <!-- /.modal-content -->
    </div>
    <!-- /.modal-dialog -->
</div>
<!-- /.modal -->
<div class="modal fade bs-example-modal-sm" id="signin" tabindex="-1" role="dialog" aria-labelledby="mySmallModalLabel"
     aria-hidden="true">
    <div class="modal-dialog modal-sm">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">&times;</span><span
                        class="sr-only">Close</span></button>
                <h4 class="modal-title">登录</h4>
            </div>
            <div class="modal-body">
                <form role="form">
                    <div class="form-group">
                        <label>学号</label>
                        <input type="text" class="form-control" id="siid" placeholder="请输入学号">
                        <div class="alert alert-warning alert-dismissible hidden" role="alert">
                            <strong>请输入正确的学号!</strong>
                        </div>
                    </div>
                    <div class="form-group">
                        <label>密码</label>
                        <input type="password" class="form-control" id="sipwd" placeholder="请输入密码">
                        <div class="alert alert-warning alert-dismissible hidden" role="alert">
                            <strong>请输入正确的密码!</strong>
                        </div>
                    </div>
                </form>
            </div>
            <div class="modal-footer">
                <div style="display: inline-block" id="simes"></div>
                <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
                <button type="button" class="btn btn-primary" id="sibtn">确定</button>
            </div>
        </div>
        <!-- /.modal-content -->
    </div>
    <!-- /.modal-dialog -->
</div>
<!-- /.modal -->



<script src="../js/jquery.min.js"></script>
<script src="../js/bootstrap.min.js"></script>
<script src="../js/sha1.js"></script>
<script src="../js/md5.js"></script>
<script src="../js/base64.js"></script>
<script src="../js/page.js"></script>
</body>

</html>
