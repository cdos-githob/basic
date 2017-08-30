package com.basic.entity;

import java.io.Serializable;
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "subtask")
public class Subtask implements Serializable, Cloneable {
	private static final long serialVersionUID = 4973147192396712750L;
	
//	@Id
//	@Column(name = "id")
//	private Integer id;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column
	private Integer taskid;
	@Column
	private Integer ptaskid;
	@Column
	private Integer operatorid;
	@Column
	private Integer caseid;
	@Column
	private Integer testnum;
	@Column
	private Integer ispass;
	@Column
	private Integer pcid;
	@Column
	private String comm;
	@Column
	private Timestamp time;
	
	public Subtask clone(){
		Subtask st = null;
		try{
			st = (Subtask)super.clone();
		}catch(Exception e){
			e.printStackTrace();
		}
		return st;
	}

	public Integer getTaskid() {
		return taskid;
	}

	public void setTaskid(Integer taskid) {
		this.taskid = taskid;
	}

	public Integer getPtaskid() {
		return ptaskid;
	}

	public void setPtaskid(Integer ptaskid) {
		this.ptaskid = ptaskid;
	}

	public Integer getOperatorid() {
		return operatorid;
	}

	public void setOperatorid(Integer operatorid) {
		this.operatorid = operatorid;
	}

	public Integer getCaseid() {
		return caseid;
	}

	public void setCaseid(Integer caseid) {
		this.caseid = caseid;
	}

	public Integer getTestnum() {
		return testnum;
	}

	public void setTestnum(Integer testnum) {
		this.testnum = testnum;
	}

	public Integer getIspass() {
		return ispass;
	}

	public void setIspass(Integer ispass) {
		this.ispass = ispass;
	}

	public Integer getPcid() {
		return pcid;
	}

	public void setPcid(Integer pcid) {
		this.pcid = pcid;
	}

	public String getComm() {
		return comm;
	}

	public void setComm(String comm) {
		this.comm = comm;
	}

	public Timestamp getTime() {
		return time;
	}

	public void setTime(Timestamp time) {
		this.time = time;
	}
	
}
