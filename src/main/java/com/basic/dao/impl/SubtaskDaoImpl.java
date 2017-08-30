package com.basic.dao.impl;

import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.basic.dao.SubtaskDao;
import com.basic.entity.Subtask;

@Repository("subtaskDao")
@Transactional
public class SubtaskDaoImpl implements SubtaskDao {
	private static final String SUBTASK_DIS = 
			"SELECT t1.*,t.* FROM subtask t "+
			"left JOIN "+
			"(select tb.name as pname,ta.* from testcase ta "+
			"		left join testcase tb on ta.pid=tb.id) t1 "+
			"on t.caseid = t1.id "+
			"where t.operatorid=:operatorid and t.ptaskid=:ptaskid ORDER BY caseid";
	private static final String SUBTASK_ITEM = "SELECT * FROM subtask t WHERE t.operatorid= :operatorid and t.caseid= :caseid and t.ptaskid= :ptaskid";
	
	@Autowired
	private SessionFactory sessionFactory;
	
	private Session getCurrentSession(){
		return this.sessionFactory.getCurrentSession();
	}
	
	public Subtask load(Integer id) {
		// TODO Auto-generated method stub
		return (Subtask)this.getCurrentSession().load(Subtask.class, id);
	}

	public Subtask get(Integer id) {
		// TODO Auto-generated method stub
		return (Subtask)this.getCurrentSession().load(Subtask.class, id);
	}

	public List<Subtask> findAll() {
		// TODO Auto-generated method stub
		List<Subtask> tasks = this.getCurrentSession().createQuery("from Subtask").setCacheable(true).list();
		return tasks;
	}

	public void persist(Subtask entity) {
		// TODO Auto-generated method stub
		this.getCurrentSession().persist(entity);
	}

	public Integer save(Subtask entity) {
		// TODO Auto-generated method stub
		return (Integer)this.getCurrentSession().save(entity);
	}

	public void saveOrUpdate(Subtask entity) {
		// TODO Auto-generated method stub
		this.getCurrentSession().saveOrUpdate(entity);
	}

	public void delete(Integer id) {
		// TODO Auto-generated method stub
		Subtask entity = this.load(id);
		this.getCurrentSession().delete(entity);
	}

	public void flush() {
		// TODO Auto-generated method stub
		this.getCurrentSession().flush();
	}

	public Object getDisplayInfo(int operatorid, int ptaskid) {
		// TODO Auto-generated method stub
		Object obj = this.getCurrentSession().createNativeQuery(SUBTASK_DIS)
				.setParameter("operatorid", operatorid)
				.setParameter("ptaskid", ptaskid)
				.getResultList();
		return obj;
	}

	public List<Subtask> getItemSubtask(int operatorid, int caseid, int ptaskid) {
		// TODO Auto-generated method stub
		return (List<Subtask>)this.getCurrentSession().createNativeQuery(SUBTASK_ITEM, Subtask.class)
			.setParameter("operatorid", operatorid)
			.setParameter("caseid", caseid)
			.setParameter("ptaskid", ptaskid)
			.getResultList();
		
//		return null;
	}

}
