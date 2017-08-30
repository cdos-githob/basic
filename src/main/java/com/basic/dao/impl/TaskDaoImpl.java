package com.basic.dao.impl;

import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.basic.dao.TaskDao;
import com.basic.entity.Subtask;
import com.basic.entity.Task;
import com.basic.entity.Testcase;
import com.basic.entity.User;

@Repository("taskDao")
@Transactional
public class TaskDaoImpl implements TaskDao {

	private static final String TASK_OPERATOR = "select * from task t where t.operatorid = :operatorid";
	private static final String TASK_ROOT = "select * from task t where t.pmid = :pmid";

	@Autowired
	private SessionFactory sessionFactory;

	private Session getCurrentSession() {
		return this.sessionFactory.getCurrentSession();
	}

	public Task load(Integer id) {
		// TODO Auto-generated method stub
		return (Task) this.getCurrentSession().load(Task.class, id);
	}

	public Task get(Integer id) {
		// TODO Auto-generated method stub
		return (Task) this.getCurrentSession().load(Task.class, id);
	}

	public List<Task> findAll() {
		// TODO Auto-generated method stub
		List<Task> tasks = this.getCurrentSession().createQuery("from Task").setCacheable(true).list();
		return tasks;
	}

	public void persist(Task entity) {
		// TODO Auto-generated method stub
		this.getCurrentSession().persist(entity);
	}

	public Integer save(Task entity) {
		// TODO Auto-generated method stub
		return (Integer) this.getCurrentSession().save(entity);
	}

	public void saveOrUpdate(Task entity) {
		// TODO Auto-generated method stub
		this.getCurrentSession().saveOrUpdate(entity);
	}

	public void delete(Integer id) {
		// TODO Auto-generated method stub
		Task entity = this.load(id);
		this.getCurrentSession().delete(entity);
	}

	public void flush() {
		// TODO Auto-generated method stub
		this.getCurrentSession().flush();
	}

	public List<Task> findAll(User user) {
		// TODO Auto-generated method stub
		switch (user.getRole()) {
			case User.ROLE_ROOT:
				return (List<Task>)this.getCurrentSession().createNativeQuery(TASK_ROOT, Task.class)
						.setParameter("pmid", user.getId())
						.getResultList();
			case User.ROLE_OPERATOR:
				return (List<Task>)this.getCurrentSession().createNativeQuery(TASK_OPERATOR, Task.class)
						.setParameter("operatorid", user.getId())
						.getResultList();
			default:
				return null;
		}
	}

}
