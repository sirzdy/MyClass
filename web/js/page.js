/**
 * Created by zdy on 2016/12/26 0026.
 */
var su=new Object;
su.suid=0;
su.supwd=0;
su.suenpwd_1=0;
su.suenpwd_2=0;
/*注册框*/
$("#suid").on('blur',function(){
    var val=$(this).val();
    var reg=/^201[12]24302[0-4][0-9]$/;
    if(!reg.test(val)){
        $("#alertid").removeClass("hidden");
        su.suid=0;
    }else{
        $("#alertid").addClass("hidden");
        su.suid=1;
    }
})

$("#supwd").on('blur',function(){
    var val=$(this).val();
    var reg=/^[\x21-\x7E]{6,}$/;
    if(!reg.test(val)){
        $("#alertpwd").removeClass("hidden");
        su.supwd=0;
    }else{
        $("#alertpwd").addClass("hidden");
        su.supwd=1;
    }
})

$("#suenpwd").on('blur',function(){
    var val=$(this).val();
    var reg=/^[\x21-\x7E]{6,}$/;
    if(!reg.test(val)){
        $("#alertenpwd").removeClass("hidden");
        su.suenpwd_1=0;
    }else{
        $("#alertenpwd").addClass("hidden");
        su.suenpwd_1=1;
    }
    var oval=$("#supwd").val();
    //console.log(val+" "+oval);
    if(val!=oval){
        $("#alertenpwdsame").removeClass("hidden");
        su.suenpwd_2=0;
    }else{
        $("#alertenpwdsame").addClass("hidden");
        su.suenpwd_2=1;
    }
    $("#supwd").on('blur',function(){
        if($("#supwd").val()!=$("#suenpwd").val()){
           /* console.log($(this).parent().parent().children()[0]);
            $($(this).parent().parent().children()[2]).find("[role='alert']").removeClass("hidden");*/
            $("#alertenpwdsame").removeClass("hidden");
            su.suenpwd_2=0;
        }else{
            $("#alertenpwdsame").addClass("hidden");
            su.suenpwd_2=1;
        }
    })
})
$("#subtn").on('click',function(){
    if($("#suid").val()==""||$("#supwd").val()==""||$("#suenpwd").val()==""){
        //alert("请填写所有字段！");
        $("#sumes").html("请填写所有字段！");
        return;
    }
    if(su.suid+su.supwd+su.suenpwd_1+su.suenpwd_2!=4){
        //alert("请检查字段内容！");
        $("#sumes").html("请检查字段内容！");
        return;
    }
    var pwd="myclass"+$("#supwd").val();
    var pwd_sha1=hex_sha1(pwd);
    var pwd_md5=hex_md5(pwd_sha1);
    var pwd_base64=new Base64().encode(pwd_md5);
    $.post("/MyClass/BaseServlet",{"signtype":"signup","id":$("#suid").val(),"pwd":pwd_base64},function(data){
        if(data=="exist"){
            $("#sumes").html("<div style='color: red;'>用户已经存在!</div>");
        }
        if(data=="fail"){
            $("#sumes").html("<div style='color: red;'>注册失败!</div>");
        }
        if(data=="success"){
            $("#sumes").html("<div style='color: #999999;'>注册成功!</div>");
            location.assign("../index.jsp");
            //alert("注册成功并登录！");
        }
    })
})

/*登录框*/
$("#sibtn").on('click',function(){
    if($("#siid").val()==""||$("#sipwd").val()==""){
        alert("请填写所有字段！");
        return;
    }
    var pwd="myclass"+$("#sipwd").val();
    var pwd_sha1=hex_sha1(pwd);
    var pwd_md5=hex_md5(pwd_sha1);
    var pwd_base64=new Base64().encode(pwd_md5);
    $.post("/MyClass/BaseServlet",{"signtype":"signin","id":$("#siid").val(),"pwd":pwd_base64},function(data){
        console.log(data);
        if(data=="fail"){
            $("#simes").html("<div style='color: red;'>登录失败!</div>");
        }
        if(data=="success"){
            $("#simes").html("<div style='color: #999999;'>登录成功!</div>");
            location.assign("../index.jsp");
            //alert("登录成功！");
        }
    })
})

$("#signout").on("click",function () {
    $.post("/MyClass/BaseServlet",{"signtype":"signout"},function(data){
        location.assign("../index.jsp");
    })
})
if($("#current_avatar").attr("alt")!=""&&$("#current_avatar").attr("alt")!=null){
    $.post("/MyClass/GetStateServlet",{"userid":$("#current_avatar").attr("alt")},function (data) {
        //console.log(data);
        if (data=="nodata"){
            return;
        }
        var obj=JSON.parse(data);
       // console.log(obj);
        if (obj.avatar!=""){
            $("#current_avatar").attr("src",obj.avatar);
        }
    });
}