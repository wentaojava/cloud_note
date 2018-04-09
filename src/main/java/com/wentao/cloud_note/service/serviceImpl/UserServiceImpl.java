/**
 * 
 */
package com.wentao.cloud_note.service.serviceImpl;

import java.util.UUID;

import javax.annotation.Resource;

import com.wentao.cloud_note.utils.JsonForResult;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.wentao.cloud_note.dao.UserDao;
import com.wentao.cloud_note.entity.User;
import com.wentao.cloud_note.exceptions.PasswordException;
import com.wentao.cloud_note.exceptions.UserNameException;
import com.wentao.cloud_note.exceptions.UserNotFoundException;
import com.wentao.cloud_note.service.UserService;

/**
 * 业务实现层
 * @author wentao
 */
@Service("userService")
public class UserServiceImpl implements UserService{
    /**
	 * 在配置文件里设置密码加密时的盐
	 *
	 * */
	@Value("#{jdbc.salt}")
	private String salt;
	
	@Resource
	private UserDao userDao;

	/**
	*
	* 用户登录
	*
	 * @param name
	* @return
	* @author wentao
	* @time 2018年04月04日
	*/
	public User login(String name, String password) throws UserNotFoundException, PasswordException {
		if(name==null || "".equals(name.trim())) {
			throw new UserNotFoundException("请输入用户名");
		}
		if(password==null || "".equals(password.trim())) {
			throw new PasswordException("请输入密码");
		}
		User user=userDao.findUserByName(name.trim());
		if(user==null) {
			throw new UserNotFoundException("用户名不存在");
		}
		String pwd=DigestUtils.md5Hex(salt+password.trim());
		//System.out.println(pwd);
		if(pwd.trim().equals(user.getPassword())) {
			return user;
		}
		throw new PasswordException("密码错误");
	}

	/**
	*
	* 用户注册
	*
	 * @param
	* @return
	* @author wentao
	* @time 2018年04月04日
	*/
	public User regist(String name, String password, String nick, String confirm)
			throws UserNameException, PasswordException {
		if(name==null || "".equals(name.trim())) {
			throw new UserNameException("用户名不能为空");
		}
		User one=userDao.findUserByName(name);
		if(one!=null) {
			throw new UserNameException("用户名已存在");
		}
		if(nick==null || "".equals(nick.trim())) {
			nick=name;
		}
		if(password==null || "".equals(password.trim())) {
			throw new PasswordException("密码不能为空");
		}
		if(confirm==null || "".equals(confirm.trim())) {
			throw new PasswordException("确认密码不能为空");
		}
		if(!password.equals(confirm)) {
			throw new PasswordException("两次密码输入不相同");
		}
		String id=UUID.randomUUID().toString();
	    password=DigestUtils.md5Hex(salt+password.trim());
	    String token=null;
		User user=new User(id,name,password,nick,token);
		int result=userDao.addUser(user);
		if(result!=1) {
			throw new RuntimeException("添加失败");
		}
		return user;
	}

	/**
	 *
	 * 根据id获取用户原密码
	 *
	 * @param id
	 * @return
	 * @author wentao
	 * @time 2018年04月09日
	 */
	public Boolean findUserById(String id,String oldPwd) throws PasswordException {
		User user;
		if(id==null || "".equals(id)){
			throw new PasswordException("用户Id不能为空");
		}
		if(oldPwd==null || "".equals(oldPwd)){
			throw new PasswordException("原密码不能为空");
		}
		try{
			user=userDao.findUserById(id);
			//System.out.println("findUserById");
		}catch (RuntimeException e){
			throw new RuntimeException("查询失败");
		}
		if(user==null){
			throw new PasswordException("查询不到用户信息");
		}
		String old=DigestUtils.md5Hex(salt+oldPwd);
		if(!old.equals(user.getPassword())){
			throw new PasswordException("原始密码错误");
		}else{
			return true;
		}
	}

	/**
	 *
	 *根据id修改用户密码
	 *
	 * @param id
	 * @param finalPwd
	 * @param newPwd
	 * @throws PasswordException
	 * @return
	 * @author wentao
	 * @time 2018年04月09日
	 */
	public Boolean changePwdById(String id, String newPwd, String finalPwd) throws PasswordException {
		if(id==null || "".equals(id)){
			throw new PasswordException("用户Id不能为空");
		}
		if(newPwd==null || "".equals(newPwd)){
			throw new PasswordException("新密码不能为空");
		}
		if(finalPwd==null || "".equals(finalPwd)){
			throw new PasswordException("确认密码不能为空");
		}
		if(!newPwd.equals(finalPwd)){
			throw new PasswordException("两次密码输入的不一致");
		}
		User user=new User();
		user.setId(id);
		user.setPassword(DigestUtils.md5Hex(salt+newPwd));
		try{
			userDao.changePwdById(user);
			return true;
		}catch (RuntimeException e){
			throw new RuntimeException("更新失败");
			//
		}
		//return false;
	}

}
