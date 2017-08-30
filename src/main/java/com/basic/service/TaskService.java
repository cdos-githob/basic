package com.basic.service;

import java.util.List;

import com.basic.entity.Task;
import com.basic.entity.User;

public interface TaskService {
	
	Task load(Integer id);

	Task get(Integer id);

	List<Task> findAll();

	void persist(Task entity);

	Integer save(Task entity);

	void saveOrUpdate(Task entity);

	void delete(Integer id);

	void flush();
	
	List<Task> findAll(User user);
}
