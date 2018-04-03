//console.log("hello");

$(function(){
	$('#login').click(loginAction);
	$('#count').blur(checkName);
	$('#password').blur(checkPwd);
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
			console.log(result);
			if(result.state==0){
				//登录成功!
				var user = result.data;
				console.log(user);
				//跳转到 edit.html
				location.href='edit.html';
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