$(function () {
  $('#last_password').blur(checkUserOldPwdById);
    $('#new_password').blur(checkNewPassword);
    $('#final_password').blur(checkFinalPassword);
    $('#changePassword').click(changePassword);
    $('#back').click(back);

});

//根据cookie里的id查询用户原密码是否正确
function checkUserOldPwdById() {
    var userId=getCookie("userId");
    //console.log(userId);
    var userOldPwd=$('#last_password').val();
    var data={"id":userId,"oldPwd":userOldPwd};
    $.ajax({
        url:'user/checkOldPwd.do',
        type:'post',
        data:data,
        dataType:'json',
        success:function (result) {
            if(result.state==0){
                $('#last_password').next().find('span').empty();
                //return true;
            }
            if(result.state==2){
                var msg=result.message;
                //console.log(msg);
                $('#last_password').next().show().find('span').html(msg);
                //return false;
            }
        },
        error:function (e) {
            alert("通信失败");
        }
    });
};

//检查输入的原密码
function checkPassword() {
    var pwd=$('#last_password').val().trim();
    var rule=/^\w{3,10}$/;
    if(rule.test(pwd)){
        $('#last_password').next().find('span').empty();
        //console.log("123");
        return true;
    }
    $('#last_password').next().show().find('span').html("3~10个字符");
    return false;

};

//检查输入的密码
function checkNewPassword() {
    var pwd=$('#new_password').val().trim();
    var rule=/^\w{3,10}$/;
    if(rule.test(pwd)){
        $('#new_password').next().find('span').empty();
        //console.log("123");
        return true;
    }
    $('#new_password').next().show().find('span').html("3~10个字符");
    return false;

};
//检查确认密码的输入
function checkFinalPassword() {
    var pwd=$('#new_password').val().trim();
    var finalPwd=$('#final_password').val().trim();
    var rule=/^\w{3,10}$/;
    if(rule.test(finalPwd)){
        if(pwd==finalPwd){
            $('#final_password').next().find('span').empty();
            return true;
        }
        $('#final_password').next().show().find('span').html("两次密码输入不一致");
        return false;
    }
    $('#final_password').next().show().find('span').html("3~10个字符");
    return false;
};

//修改密码
function changePassword() {
    console.log("123");
    var userId=getCookie("userId");
    var newPwd=$('#new_password').val().trim();
    var finalPwd=$('#final_password').val().trim();
    var check=checkPassword()+checkNewPassword()+checkFinalPassword();
    if(check!=3){
        return;
    }
    var data={"id":userId,"newPwd":newPwd,"finalPwd":finalPwd};
    $.ajax({
        url:'user/changePwd.do',
        type:'post',
        data:data,
        dataType:'json',
        success:function (result) {
            console.log("234");
            if(result.state==0){
                delCookie("userId");
                location.href="log_in.html";
            }else{
                $('#new_password').next().show().find('span').html(result.message);
            }
        },
        error:function (e) {
            alert("通讯失败");
        }

    });
}

//关闭
function back() {
    location.href='../../edit.html';
}