/**
 * 
 */
package com.basic.dao;

import java.util.List;

import com.basic.entity.User;

public interface UserDao extends GenericDao<User,Integer>{
	List<User> get(String username);
}
