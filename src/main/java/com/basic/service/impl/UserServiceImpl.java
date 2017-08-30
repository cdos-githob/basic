package com.basic.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.basic.dao.UserDao;
import com.basic.entity.User;
import com.basic.service.UserService;

@Service("userService")
public class UserServiceImpl implements UserService{
	@Autowired
	private UserDao userDao;

	public User load(Integer id) {
		// TODO Auto-generated method stub
		return userDao.load(id);
	}

	public User get(Integer id) {
		// TODO Auto-generated method stub
		return userDao.load(id);
	}

	public List<User> findAll() {
		// TODO Auto-generated method stub
		return userDao.findAll();
	}

	public void persist(User entity) {
		// TODO Auto-generated method stub
		userDao.save(entity);
	}

	public Integer save(User entity) {
		// TODO Auto-generated method stub
		return userDao.save(entity);
	}

	public void saveOrUpdate(User entity) {
		// TODO Auto-generated method stub
		userDao.saveOrUpdate(entity);
	}

	public void delete(Integer id) {
		// TODO Auto-generated method stub
		userDao.delete(id);
	}

	public void flush() {
		// TODO Auto-generated method stub
		userDao.flush();
	}
	
	public List<User> get(String username){
		return userDao.get(username);
	}
}
