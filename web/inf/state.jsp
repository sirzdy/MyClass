<%--
  Created by IntelliJ IDEA.
  User: zdy
  Date: 2017/1/15
  Time: 下午2:52
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>状态</title>
    <link rel="stylesheet" href="../css/state.css">
</head>
<body>
<jsp:include page="/page/header.jsp"/>
<div class="panel panel-default col-xs-12 col-sm-12 col-md-8 col-lg-8 col-md-offset-2 clo-lg-offset-2">
    <div class="panel-heading">
        <h3 class="panel-title">About Me </h3>
    </div>
    <div class="panel-body" class="row">
        <table class="table">
            <tr>
                <td rowspan="3" class="col-xs-2 col-sm-2 col-md-2 col-md-2">
                    <div id="avatar" align="center" style="vertical-align: middle">
                        <img src="../img/avatar.png" alt="头像" width="100" height="100">
                        <%
                            if (session.getAttribute("id").equals(request.getParameter("userid")) && session.getAttribute("id") != null) {
                                out.print("<div class=\"state_upd\" id=\"avatar_upd\" align=\"center\" data-toggle=\"modal\" data-target=\"#model_avatar\">修改</div>");
//                                style="display:none"
                            }
                        %>

                    </div>
                </td>
                <td>
                    <div>
                        ID：<span id="userid"><%=request.getParameter("userid")%></span>
                    </div>
                </td>
            </tr>

            <tr>
                <td>
                    <div id="blog">
                        <div>
                            <%
                                if (session.getAttribute("id").equals(request.getParameter("userid")) && session.getAttribute("id") != null) {
                                    out.print("<div >博客地址：\n" +
                                            "                                <span class=\"state_upd\" id=\"blog_upd\"> 修改</span>\n" +
                                            "                                <span class=\"state_cancel\" id=\"blog_cancel\"> 取消 </span>\n" +
                                            "                                <span class=\"state_save\" id=\"blog_save\"> 保存 </span>\n" +
                                            "                                <div style=\"display: inline-block\" id=\"blog_mes\"></div>\n"+
                                            "                            </div>");
                                }
                            %>
                            <div id="blog_con">这个人很懒，什么都没有留下…</div>
                        </div>
                        <%
                            if (session.getAttribute("id").equals(request.getParameter("userid")) && session.getAttribute("id") != null) {
                                out.print("<input class=\"form-control state_input\" type=\"text\" placeholder=\"博客地址\" id=\"blog_input\" style=\"display:none\">\n");
                            }
                        %>
                    </div>
                </td>
            </tr>
            <tr>
                <td>
                    <div id="signature">
                        <%
                            if (session.getAttribute("id").equals(request.getParameter("userid")) && session.getAttribute("id") != null) {
                                out.print("<div >个人签名：\n" +
                                        "                            <span class=\"state_upd\" id=\"signature_upd\"> 修改</span>\n" +
                                        "                            <span class=\"state_cancel\" id=\"signature_cancel\"> 取消 </span>\n" +
                                        "                            <span class=\"state_save\" id=\"signature_save\"> 保存 </span>\n" +
                                        "                            <div style=\"display: inline-block\" id=\"signature_mes\"></div>\n"+
                                        "                        </div>");
                            }
                        %>
                        <div id="signature_con">这个人很懒，什么都没有留下…</div>
                        <%
                            if (session.getAttribute("id").equals(request.getParameter("userid")) && session.getAttribute("id") != null) {
                                out.print("<input class=\"form-control state_input\" type=\"text\" placeholder=\"个人签名\" id=\"signature_input\" style=\"display:none\">\n");
                            }
                        %>
                    </div>
                </td>
            </tr>
        </table>
    </div>
</div>
<div class="panel panel-default col-xs-12 col-sm-12 col-md-8 col-lg-8 col-md-offset-2 clo-lg-offset-2">
    <div class="panel-heading">
        <h3 class="panel-title">I want to say… </h3>
    </div>
    <div class="panel-body">
        <div>
            <blockquote id="statement_con">
                <p>
                    这个人很懒，什么都没有留下…
                </p>
            </blockquote>
        </div>
        <%
            if (session.getAttribute("id").equals(request.getParameter("userid")) && session.getAttribute("id") != null) {
                out.print("<div class=\"input-group\">\n" +
                        "            <textarea class=\"form-control\" rows=\"3\" placeholder=\"我想说…\" id=\"statement_textarea\"></textarea>\n" +
                        "            <div class=\"input-group-addon\" id=\"statement_upd\" style=\"cursor: hand;\">更新</div>\n" +
                        "        </div>");
            }
        %>
    </div>
</div>

<div class="modal fade" tabindex="-1" id="model_avatar">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal">
                    <span aria-hidden="true">&times;</span>
                    <span class="sr-only">Close</span></button>
                <h4 class="modal-title">修改头像</h4>
            </div>
            <div class="modal-body" id="avatar_area" align="center">
                <div id="avatar_content" style="width: 100%;">
                    <%--文件输入框--%>
                    <input type="file" id="avatar_file" style="display: none"  accept="image/*">
                    <div id="result" style="display: none"></div>
                    <div class="form-group">
                        <button class="btn-default form-control" onclick="$('#avatar_file').click()">选择文件</button>
                    </div>
                    <%--画布--%>
                    <canvas id="avatar_canvas" style="border: 1px solid #999;width: 100%;display: none;"></canvas>
                    <%--按钮组--%>
                    <div class="form-group" id="base_tools" style="display: none;">
                        <div class="btn-group">
                            <button type="button" class="btn btn-default" id="avatar_reset" data-toggle="tooltip" data-placement="left"  title="重置">
                                <i class="fa fa-refresh " aria-hidden="true"></i>
                            </button>
                            <button type="button" class="btn btn-default" id="avatar_spin_anticlockwise">
                                <i class="fa fa-reply " aria-hidden="true"></i>
                            </button>
                        </div>
                        <button style="display: inline;" align="center">
                            <input type="range" value="25" min="1" max="100" style="width: 70px;vertical-align: middle;"
                                   id="avatar_zoom">
                        </button>
                        <div class="btn-group ">
                            <button type="button" class="btn btn-default" id="avatar_spin_clockwise">
                                <i class="fa fa-share" aria-hidden="true"></i>
                            </button>
                            <button type="button" class="btn btn-default" data-toggle="tooltip" data-placement="right" title="More" id="avatar_more">
                                <i class="fa fa-plus-square " aria-hidden="true"></i>
                            </button>
                        </div>
                    </div>
                    <div class="form-group" id="more_tools" style="display: none">
                        <button type="button" class="btn btn-default" id="avatar_zoomout">
                            <i class="fa fa-compress " aria-hidden="true"></i>
                        </button>
                        <div class="btn-group">
                            <button type="button" class="btn btn-default" id="avatar_left">
                                <i class="fa fa-arrow-left" aria-hidden="true"></i>
                            </button>
                            <button type="button" class="btn btn-default" id="avatar_up">
                                <i class="fa fa-arrow-up" aria-hidden="true"></i>
                            </button>
                            <button type="button" class="btn btn-default" id="avatar_down">
                                <i class="fa fa-arrow-down" aria-hidden="true"></i>
                            </button>
                            <button type="button" class="btn btn-default" id="avatar_right">
                                <i class="fa fa-arrow-right" aria-hidden="true"></i>
                            </button>
                        </div>
                        <button type="button" class="btn btn-default" id="avatar_zoomin">
                            <i class="fa fa-expand" aria-hidden="true"></i>
                        </button>
                    </div>
                </div>
                <div class="modal-footer">
                    <div style="display: inline-block" id="avames"></div>
                    <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
                    <button type="button" class="btn btn-primary" id="avatar_save">保存</button>
                </div>
            </div>
        </div><!-- /.modal-content -->
    </div><!-- /.modal-dialog -->
</div><!-- /.modal -->
<script src="../js/state.js"></script>
<script src="../js/avatar.js"></script>
</body>
</html>
