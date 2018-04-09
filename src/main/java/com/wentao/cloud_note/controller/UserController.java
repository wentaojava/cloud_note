/**
 * 
 */
package com.wentao.cloud_note.controller;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.wentao.cloud_note.entity.User;
import com.wentao.cloud_note.exceptions.PasswordException;
import com.wentao.cloud_note.service.UserService;
import com.wentao.cloud_note.utils.JsonForResult;

/**
 * 控制层
 * @author wentao
 */
@Controller
@RequestMapping("/user")
public class UserController {
	@Resource
	private UserService userService;
	
	@ExceptionHandler(Exception.class)
	@ResponseBody
	public Object exceptionHandler(Exception e) {
		return new JsonForResult(e);
	}

	@RequestMapping("/login.do")
	@ResponseBody
	public Object login(String name,String password) {
		User user=userService.login(name, password);
		return new JsonForResult(user);
	}
	
	@RequestMapping("/regist.do")
	@ResponseBody
	public Object regist(String name, String password, String nick, String confirm) {
		User user=userService.regist(name, password, nick, confirm);
		return new JsonForResult(user);
	}

	@RequestMapping("/checkOldPwd.do")
	@ResponseBody
	public Object findUserById(String id,String oldPwd){
		Boolean result=userService.findUserById(id,oldPwd);
		return new JsonForResult(result);
	}

	@RequestMapping("/changePwd.do")
	@ResponseBody
	public Object changePwdById(String id,String newPwd,String finalPwd){
		Boolean result=userService.changePwdById(id,newPwd,finalPwd);
		return new JsonForResult(result);
	}
	
	

}
