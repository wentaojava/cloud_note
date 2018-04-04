/**
 * 
 */
package com.wentao.cloud_note.service;

import com.wentao.cloud_note.entity.User;
import com.wentao.cloud_note.exceptions.PasswordException;
import com.wentao.cloud_note.exceptions.UserNameException;
import com.wentao.cloud_note.exceptions.UserNotFoundException;

/**
 * 业务层接口
 * @author wentao
 */
public interface UserService {
	/**
	 * 用户登录
	 **/
	User login(String name,String password) throws UserNotFoundException,PasswordException;
	/**
	 * 用户注册
	 **/
	User regist(String name,String password,String nick,String confirm) throws UserNameException,PasswordException;

}
