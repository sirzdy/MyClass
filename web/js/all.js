/**
 * Created by zdy on 2017/1/24.
 */
$.get("../GetAllServlet",function (data) {
    //console.log(data);
    $("#allinf").append(data);

    $("#allinf tr").hover(function (e) {
        var userid=$(this).children("td:first").html();
        //console.log(userid);
        //console.log(e.pageX);
        //console.log(e.pageY);
        var xpos=e.pageX+10;
        var ypos=e.pageY+40;
        if(userid==null||userid==""){
            return;
        }
        var strVar="";
        strVar += "<div style=\"position:fixed;width:400px;height: 90px;display: none\" id=\"show_area\">";
        strVar += "    <div class=\"panel panel-info\">";
        strVar += "        <div class=\"panel-body table-responsive\" id=\"inf-panel\">";
        strVar += "            <table class=\" table table-condensed\">";
        strVar += "                <tr >";
        strVar += "                    <td align=\"center\" rowspan=\"2\" width=\"60\"><img src=\"../img/avatar.png\" id=\"info_avatar\" style=\"vertical-align: center\" alt=\"Avatar\" width=\"60\" height=\"60\"\/><\/td>";
        strVar += "                    <td class=\"active\" style=\"height: 25px;\" id=\"info_blog\"><\/td>";
        strVar += "                <\/tr>";
        strVar += "                <tr >";
        strVar += "                    <td class=\"info\" id=\"info_signature\" style=\"height: 30px;\"><\/td>";
        strVar += "                <\/tr>";
        strVar += "            <\/table>";
        strVar += "        <\/div>";
        strVar += "    <\/div>";
        strVar += "<\/div>";
        $("#information").append(strVar);
        $("#show_area").css("left",xpos+"px");
        $("#show_area").css("top",ypos+"px");

        $.post("../GetStateServlet",{"userid":userid},function (data) {
            //console.log("获取数据");
            if (data=="nodata"){
                //console.log("kong");
                return;
            }
            var obj=JSON.parse(data);
            //console.log(obj);
            if (obj.avatar!=""){
                $("#info_avatar").attr("src",obj.avatar);
            }
            if (obj.blog!=""){
                $("#info_blog").html("<a href=\""+obj.blog+"\">"+obj.blog+"</a>");
            }else{
                $("#info_blog").html("这个人很懒，什么都没有留下…")
            }
            if (obj.signature!=""){
                $("#info_signature").html(obj.signature);
            }else{
                $("#info_signature").html("这个人很懒，什么都没有留下…");
            }
            $("#show_area").fadeIn();
        });
    },function () {
        $("#show_area").fadeOut();
        $("#show_area").remove();
    });
    $("#allinf tr").on("click",function () {
        var userid=$(this).children("td:first").html();
        //console.log(userid);
        window.location.href="../inf/state.jsp?userid="+userid;
    });
})
