/**
 * 
 */
package com.wentao.cloud_note.dao;

import com.wentao.cloud_note.entity.User;

/**
 * 
 * @author wentao
 */
public interface UserDao {
	User findUserByName(String name);
	
	int addUser(User user);

	User findUserById(String id);

	void changePwdById(User user);

}
