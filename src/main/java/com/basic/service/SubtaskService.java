package com.basic.service;

import java.util.List;

import com.basic.entity.Subtask;

public interface SubtaskService {
	
	Subtask load(Integer id);

	Subtask get(Integer id);

	List<Subtask> findAll();

	void persist(Subtask entity);

	Integer save(Subtask entity);

	void saveOrUpdate(Subtask entity);

	void delete(Integer id);

	void flush();
	
	Object getDisplayInfo(int operatorid, int ptaskid);
	
	List<Subtask> getItemSubtask(int operatorid, int caseid, int ptaskid);
}
