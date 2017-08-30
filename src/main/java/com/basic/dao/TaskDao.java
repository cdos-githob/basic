/**
 * 
 */
package com.basic.dao;

import java.util.List;

import com.basic.entity.Task;
import com.basic.entity.User;

public interface TaskDao extends GenericDao<Task,Integer>{
	public List<Task> findAll(User user);
}
