/**
 * Created by zdy on 2017/1/14.
 */
$("#inf-panel input.form-control,#inf-panel select.form-control").on("input",function(){
    var reg=new Object();
    reg.name=/^[\u4E00-\u9FA5]{2,20}$/;
    reg.email=/^([a-zA-Z0-9_-])+@([a-zA-Z0-9_-])+((\.[a-zA-Z0-9_-]{2,3}){1,2})$/;
    reg.tel=/^\s*\+?\s*(\(\s*\d+\s*\)|\d+)(\s*-?\s*(\(\s*\d+\s*\)|\s*\d+\s*))*\s*$/;
    reg.qq=/^[1-9][0-9]{4,9}$/;
    reg.wechat=/(^[a-zA-Z]{1}[-_a-zA-Z0-9]{5,19})$|(^[1-9][0-9]{4,9}$)|(^\s*\+?\s*(\(\s*\d+\s*\)|\d+)(\s*-?\s*(\(\s*\d+\s*\)|\s*\d+\s*))*\s*$)/;
    reg.birthday=/^199[0-9]-(0[1-9]|1[0-2])-(0[1-9])|((1|2)[1-9])|(3[0-1])$/
    reg.university=/^..+$/;
    reg.academy=/^..+$/;
    reg.company=/^..+$/;
    reg.occupation=/^..+$/;
    reg.country=/^.*$/;
    reg.province=/^.*$/;
    reg.city=/^.*$/;
    var val=$(this).val();
    if(val==null||val==""){
        $(this).parent().removeClass("has-warning");
        $(this).parent().removeClass("has-success");
    }
    else if((reg[this.id].test(val))&&(val.length<=50)){
        $(this).parent().removeClass("has-warning");
        $(this).parent().addClass("has-success");
    }else{
        $(this).parent().removeClass("has-success");
        $(this).parent().addClass("has-warning");
    }
})
$("#saveinf").on("click",function () {
    if($("#inf-panel .has-warning").length!=0){
        $("#infmes").html("<div style='color: red;'>存在不合法的内容!</div>");
        return;
    }
    $.post("../InfoServlet",{"name":$("#name").val(),"email":$("#email").val(),"tel":$("#tel").val(),"qq":$("#qq").val(),"wechat":$("#wechat").val(),"birthday":$("#birthday").val(),"country":$("#country").val(),"province":$("#province").val(),"city":$("#city").val(),"university":$("#university").val(),"academy":$("#academy").val(),"company":$("#company").val(),"occupation":$("#occupation").val()},function(data){
        //console.log(data);
        if(data=="signout"){
            $("#infmes").html("<div style='color: red;'>未登录!</div>");
        }
        if(data=="fail"){
            $("#infmes").html("<div style='color: red;'>修改失败!</div>");
        }
        if(data=="success"){
            $("#infmes").html("<div style='color: #999999;'>修改成功!</div>");
        }
    });
    $("#inf-panel input.form-control,#inf-panel select.form-control").parent().removeClass("has-success");
    $("#inf-panel input.form-control,#inf-panel select.form-control").parent().removeClass("has-warning");
})
$("#updinf").on("click",function () {
    $(".show-inf").hide();
    $(".upd-inf").show();
    $("#inf-panel select").removeAttr("disabled");
    $("#inf-panel .form-control").removeAttr("readonly");
})
$("#caninf").on("click",function () {
    $(".show-inf").show();
    $(".upd-inf").hide();
    $("#inf-panel .form-control").attr("readonly","readonly");
    $("#inf-panel select").attr("disabled","disabled");
    $.post("../GetInfoServlet",function (data) {
        if (data=="nodata"){
            $("#infmes").html("<div style='color: #999999;'>尚未填写信息，请完善!</div>");
            $("#updinf").click();
        }else if (data=="signout"){
            $("#infmes").html("<div style='color: red;'>尚未登录!</div>");
        }else{
            //$("#infmes").html("<div style='color: #999999;'>获取信息成功!</div>");
            console.log(data);
            var obj=JSON.parse(data);
            console.log(obj)
            $("#name").val(obj.name);
            $("#email").val(obj.email);
            $("#tel").val(obj.tel);
            $("#qq").val(obj.qq);
            $("#wechat").val(obj.wechat);
            $("#birthday").val(obj.birthday);
            $("#country").val(obj.country)&&$("#country").change();
            $("#province").val(obj.province)&&$("#province").change();
            $("#city").val(obj.city);
            $("#university").val(obj.university);
            $("#academy").val(obj.academy);
            $("#company").val(obj.company);
            $("#occupation").val(obj.occupation);
            $("#infmes").html("");
        }
    });
    $("#inf-panel input.form-control,#inf-panel select.form-control").parent().removeClass("has-success");
    $("#inf-panel input.form-control,#inf-panel select.form-control").parent().removeClass("has-warning");
})
$("#caninf").click();
$("#birthday").attr("min","1990-01-01");
$("#birthday").attr("max",new Date().getFullYear()+"-"+new Date().getMonth()+1+"-"+new Date().getDate());
