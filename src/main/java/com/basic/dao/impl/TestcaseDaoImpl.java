package com.basic.dao.impl;

import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.basic.dao.TestcaseDao;
import com.basic.entity.Testcase;

@Repository("testcaseDao")
@Transactional
public class TestcaseDaoImpl implements TestcaseDao {
	
	@Autowired
	private SessionFactory sessionFactory;
	
	private Session getCurrentSession(){
		return this.sessionFactory.getCurrentSession();
	}
	
	public Testcase load(Integer id) {
		// TODO Auto-generated method stub
		return (Testcase)this.getCurrentSession().load(Testcase.class, id);
	}

	public Testcase get(Integer id) {
		// TODO Auto-generated method stub
		return (Testcase)this.getCurrentSession().load(Testcase.class, id);
	}

	public List<Testcase> findAll() {
		// TODO Auto-generated method stub
		List<Testcase> cases = this.getCurrentSession().createQuery("from Testcase").setCacheable(true).list();
		return cases;
	}

	public void persist(Testcase entity) {
		// TODO Auto-generated method stub
		this.getCurrentSession().persist(entity);
	}

	public Integer save(Testcase entity) {
		// TODO Auto-generated method stub
		return (Integer)this.getCurrentSession().save(entity);
	}

	public void saveOrUpdate(Testcase entity) {
		// TODO Auto-generated method stub
		this.getCurrentSession().saveOrUpdate(entity);
	}

	public void delete(Integer id) {
		// TODO Auto-generated method stub
		Testcase entity = this.load(id);
		this.getCurrentSession().delete(entity);
	}

	public void flush() {
		// TODO Auto-generated method stub
		this.getCurrentSession().flush();
	}

}
