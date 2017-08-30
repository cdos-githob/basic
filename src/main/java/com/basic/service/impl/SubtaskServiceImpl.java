package com.basic.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.basic.dao.SubtaskDao;
import com.basic.entity.Subtask;
import com.basic.service.SubtaskService;

@Service("subtaskService")
public class SubtaskServiceImpl implements SubtaskService{
	@Autowired
	private SubtaskDao subtaskDao;

	public Subtask load(Integer id) {
		// TODO Auto-generated method stub
		return subtaskDao.load(id);
	}

	public Subtask get(Integer id) {
		// TODO Auto-generated method stub
		return subtaskDao.load(id);
	}

	public List<Subtask> findAll() {
		// TODO Auto-generated method stub
		return subtaskDao.findAll();
	}

	public void persist(Subtask entity) {
		// TODO Auto-generated method stub
		subtaskDao.save(entity);
	}

	public Integer save(Subtask entity) {
		// TODO Auto-generated method stub
		return subtaskDao.save(entity);
	}

	public void saveOrUpdate(Subtask entity) {
		// TODO Auto-generated method stub
		subtaskDao.saveOrUpdate(entity);
	}

	public void delete(Integer id) {
		// TODO Auto-generated method stub
		subtaskDao.delete(id);
	}

	public void flush() {
		// TODO Auto-generated method stub
		subtaskDao.flush();
	}

	public Object getDisplayInfo(int operatorid, int ptaskid) {
		// TODO Auto-generated method stub
		return subtaskDao.getDisplayInfo(operatorid, ptaskid);
	}

	public List<Subtask> getItemSubtask(int operatorid, int caseid, int ptaskid) {
		// TODO Auto-generated method stub
		return subtaskDao.getItemSubtask(operatorid, caseid, ptaskid);
	}
	
}
