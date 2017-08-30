/**
 * 
 */
package com.basic.dao;

import java.util.List;

import com.basic.entity.Subtask;

public interface SubtaskDao extends GenericDao<Subtask,Integer>{
	Object getDisplayInfo(int operatorid, int ptaskid);
	List<Subtask> getItemSubtask(int operatorid, int caseid, int ptaskid);
}
