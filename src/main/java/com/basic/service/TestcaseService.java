package com.basic.service;

import java.util.List;

import com.basic.entity.Testcase;

public interface TestcaseService {
	
	Testcase load(Integer id);

	Testcase get(Integer id);

	List<Testcase> findAll();

	void persist(Testcase entity);

	Integer save(Testcase entity);

	void saveOrUpdate(Testcase entity);

	void delete(Integer id);

	void flush();
}
