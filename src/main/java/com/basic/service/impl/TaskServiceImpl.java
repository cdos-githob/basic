package com.basic.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.basic.dao.TaskDao;
import com.basic.entity.Task;
import com.basic.entity.User;
import com.basic.service.TaskService;

@Service("taskService")
public class TaskServiceImpl implements TaskService{
	@Autowired
	private TaskDao taskDao;

	public Task load(Integer id) {
		// TODO Auto-generated method stub
		return taskDao.load(id);
	}

	public Task get(Integer id) {
		// TODO Auto-generated method stub
		return taskDao.load(id);
	}

	public List<Task> findAll() {
		// TODO Auto-generated method stub
		return taskDao.findAll();
	}

	public void persist(Task entity) {
		// TODO Auto-generated method stub
		taskDao.save(entity);
	}

	public Integer save(Task entity) {
		// TODO Auto-generated method stub
		return taskDao.save(entity);
	}

	public void saveOrUpdate(Task entity) {
		// TODO Auto-generated method stub
		taskDao.saveOrUpdate(entity);
	}

	public void delete(Integer id) {
		// TODO Auto-generated method stub
		taskDao.delete(id);
	}

	public void flush() {
		// TODO Auto-generated method stub
		taskDao.flush();
	}

	public List<Task> findAll(User user) {
		// TODO Auto-generated method stub
		return taskDao.findAll(user);
	}
	
}
