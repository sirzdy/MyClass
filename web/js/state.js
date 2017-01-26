/**
 * Created by zdy on 2017/1/17.
 */
var userid=$("#userid").html();
//console.log(userid);
function getAttr(){
    $.post("../GetStateServlet",{"userid":userid},function (data) {
        //console.log(data);
        if (data=="nodata"){
            return;
        }
        var obj=JSON.parse(data);
        //console.log(obj);
        if (obj.avatar!=""){
            $("#avatar img").attr("src",obj.avatar);
        }
        if (obj.blog!=""){
            $("#blog_con").html("<a href=\""+obj.blog+"\">"+obj.blog+"</a>");
        }else{
            $("#blog_con").html("这个人很懒，什么都没有留下…")
        }
        if (obj.signature!=""){
            $("#signature_con").html(obj.signature);
        }else{
            $("#signature_con").html("这个人很懒，什么都没有留下…");
        }
        if (obj.statement!=""){
            $("#statement_con").html(obj.statement);
        }else{
            $("#statement_con").html("这个人很懒，什么都没有留下…");
        }

    });
}
getAttr();
$("#avatar").on("click",function () {
    $("#avatar_upd").show();
});
$("#signature_upd,#blog_upd").on("click",function () {
    var content=$(this).attr("id");
    var con;
    if(/(.*)_(.*)/.test(content)){
        con=RegExp.$1;
        //console.log(con);
    }
    $("#"+con+"_mes").html("");
    $("#"+con+"_upd").hide();
    $("#"+con+"_con").hide();
    $("#"+con+"_input").show();
    $("#"+con+"_save").show();
    $("#"+con+"_cancel").show();
});
$("#signature_cancel,#blog_cancel").on("click",function () {
    var content=$(this).attr("id");
    var con;
    if(/(.*)_(.*)/.test(content)){
        con=RegExp.$1;
        //console.log(con);
    }
    $("#"+con+"_mes").html("");
    $("#"+con+"_save").hide();
    $("#"+con+"_cancel").hide();
    $("#"+con+"_input").hide();
    $("#"+con+"_con").show();
    $("#"+con+"_upd").show();
})
$("#signature_save,#blog_save").on("click",function () {
    var content=$(this).attr("id");
    var con;
    if(/(.*)_(.*)/.test(content)){
        con=RegExp.$1;
        //console.log(con);
    }
    if (con=="blog"&&$("#blog_input").val()!=""){
        var reg=/^((http:\/\/)|(https:\/\/))[a-zA-Z0-9]+(\.[a-zA-Z0-9]+)+(\/.*)*$/
        if (reg.test($("#blog_input").val())){
            $("#blog_mes").html("<div style='color: #999999;'>格式正确!</div>");
        }else {
            $("#blog_mes").html("<div style='color: #FFC125;'>格式错误!</div>");
            return;
        }
    }
    $.post("../StateServlet",{"type":con,"attr":$("#"+con+"_input").val()},function (data) {
        if(data=="signout"){
            $("#"+con+"_mes").html("<div style='color: red;'>未登录!</div>");
        }
        if(data=="fail"){
            $("#"+con+"_mes").html("<div style='color: red;'>修改失败!</div>");
        }
        if(data=="success"){
            $("#"+con+"_mes").html("<div style='color: #999999;'>修改成功!</div>");
            getAttr();
        }
    });
    $("#"+con+"_save").hide();
    $("#"+con+"_cancel").hide();
    $("#"+con+"_input").hide();
    $("#"+con+"_con").show();
    $("#"+con+"_upd").show();
    var content=$("#"+con+"_input").val();

});

$("#blog_input").on("input",function () {
    var reg=/^((http:\/\/)|(https:\/\/))[a-zA-Z0-9]+(\.[a-zA-Z0-9]+)+(\/.*)*$/
    if (reg.test($(this).val())){
        $("#blog_mes").html("<div style='color: #999999;'>格式正确!</div>");
    }else {
        $("#blog_mes").html("<div style='color: #FFC125;'>格式错误!</div>");
    }
    if($(this).val()==""){
        $("#blog_mes").html("");
    }
});
$("#statement_upd").on("click",function () {
    var str=$("#statement_textarea").val().replace(/\n/g,"</p><p>");
    if (str!=""){
        str="<p>"+str+"</p>";
    }
    $.post("../StateServlet",{"type":"statement","attr":str},function (data) {
        if(data=="signout"){

        }
        if(data=="fail"){

        }
        if(data=="success"){
            getAttr();
        }
    });
})
