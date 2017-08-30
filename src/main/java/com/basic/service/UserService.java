package com.basic.service;

import java.util.List;

import com.basic.entity.User;

public interface UserService {
	
	User load(Integer id);

	User get(Integer id);

	List<User> findAll();

	void persist(User entity);

	Integer save(User entity);

	void saveOrUpdate(User entity);

	void delete(Integer id);

	void flush();
	
	List<User> get(String username);
}
