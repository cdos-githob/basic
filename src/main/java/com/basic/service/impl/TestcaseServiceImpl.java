package com.basic.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.basic.dao.TestcaseDao;
import com.basic.entity.Testcase;
import com.basic.service.TestcaseService;

@Service("testcaseService")
public class TestcaseServiceImpl implements TestcaseService{
	@Autowired
	private TestcaseDao testcaseDao;

	public Testcase load(Integer id) {
		// TODO Auto-generated method stub
		return testcaseDao.load(id);
	}

	public Testcase get(Integer id) {
		// TODO Auto-generated method stub
		return testcaseDao.load(id);
	}

	public List<Testcase> findAll() {
		// TODO Auto-generated method stub
		return testcaseDao.findAll();
	}

	public void persist(Testcase entity) {
		// TODO Auto-generated method stub
		testcaseDao.save(entity);
	}

	public Integer save(Testcase entity) {
		// TODO Auto-generated method stub
		return testcaseDao.save(entity);
	}

	public void saveOrUpdate(Testcase entity) {
		// TODO Auto-generated method stub
		testcaseDao.saveOrUpdate(entity);
	}

	public void delete(Integer id) {
		// TODO Auto-generated method stub
		testcaseDao.delete(id);
	}

	public void flush() {
		// TODO Auto-generated method stub
		testcaseDao.flush();
	}
	
}
