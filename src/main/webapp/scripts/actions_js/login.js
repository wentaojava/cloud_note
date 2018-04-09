//console.log("hello");

$(function(){
	$('#login').click(loginAction);
	$('#count').blur(checkName);
	$('#password').blur(checkPwd);
    $('#regist_button').click(registUser);
    $('#regist_username').blur(checkRegistName);
    $('#regist_password').blur(checkRegistPassword);
    $('#final_password').blur(checkFinalPassword);
});
function checkName(){
	//var value=this.value;
	//var id=this.id;
	var value=$('#count').val();
	var rule=/^\w{4,10}$/;
		if(!rule.test(value)){
			$('#nameError').html('4~10个字符');
			return false;
		}
		$('#nameError').empty();
		return true;
}
function checkPwd(){
	var value=$('#password').val();
	var rule=/^\w{3,10}$/;
		if(!rule.test(value)){
			$('#passwordError').html('3~10个字符');
			return false;
		}
		$('#passwordError').empty();
		return true;
}

//登录按钮事件
function loginAction(){
	//获取输入的用户名与密码
	var name=$('#count').val();
	var password=$('#password').val();
	var check=checkName()+checkPwd();
	if(check!=2){
		return;
	}
	var data={"name":name,"password":password};
	$.ajax({
		url:'user/login.do',
		data:data,
		type:'post',
		dataType:'json',
		success:function(result){
			//console.log(result);
			if(result.state==0){
				//登录成功!
				var user = result.data;
				//console.log(user);
				//跳转到 edit.html
                addCookie("userId",user.id);
                console.log(getCookie("userId"));
				location.href='../../edit.html';
			}
			if(result.state==1){
				var msg = result.message;
				$('#count').next().html(msg);
			}
			if(result.state==2){
				var msg = result.message;
				$('#password').next().html(msg);
			}
		},
		error:function(e){
			alert("通信失败");
		}
	});
}

//检查注册时的输入的用户名
function checkRegistName() {
    var name=$('#regist_username').val().trim();
    var rule=/^\w{4,10}$/;
    if(rule.test(name)){
        $('#regist_username').next().find('span').empty();
        return true;
    }
    $('#regist_username').next().show().find('span').html("4~10个字符");
    return false;
}

//检查注册时输入的密码
function checkRegistPassword() {
    var pwd=$('#regist_password').val().trim();
    var rule=/^\w{3,10}$/;
    if(rule.test(pwd)){
        $('#regist_password').next().find('span').empty();
        console.log("123");
        return true;
    }
    $('#regist_password').next().show().find('span').html("3~10个字符");
    return false;
    
}
//检查确认密码的输入
function checkFinalPassword() {
    var pwd=$('#regist_password').val().trim();
    var finalPwd=$('#final_password').val().trim();
    var rule=/^\w{3,10}$/;
    if(rule.test(finalPwd)){
        if(pwd==finalPwd){
            $('#regist_password').next().find('span').empty();
            return true;
        }
        $('#final_password').next().show().find('span').html("两次密码输入不一致");
        return false;
    }
    $('#final_password').next().show().find('span').html("3~10个字符");
    return false;
}
//注册按钮事件
function registUser() {
    //console.log("hello");
    var name=$('#regist_username').val().trim();
    var pwd=$('#regist_password').val().trim();
    var finalPwd=$('#final_password').val().trim();
    var nick=$('#nickname').val().trim();
    var check=checkRegistName()+checkRegistPassword()+checkFinalPassword();
    if(check!=3){
        return false;
    }
    var data={"name":name,"password":pwd,"confirm":finalPwd,"nick":nick};
    $.ajax({
       url:'user/regist.do',
        type:'post',
        dataType:'json',
        data:data,
        success:function (result) {
           if(result.state==0){
               $('#regist_username').val("");
               $('#regist_password').val("");
               $('#nickname').val("");
               $('#final_password').val("");
               //返回登录界面
               $('#back').click();
               $('#count').val(result.data.name);
               $('#password').focus();
           }
            if(result.state==1){
                $('#regist_username').next().show().find('span').html(result.message);
            }
            if(result.state==2){
                $('#regist_password').next().show().find('span').html(result.message);
            }
        },
        error:function (e) {
            alert("通讯失败");
        }
    });


}