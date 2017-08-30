package com.basic.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "task")
public class Task implements Serializable {
	private static final long serialVersionUID = 9180192917824358498L;
//	@Id
//	@Column(name = "id")
//	private Integer id;
	
	@Id
	@Column
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer taskid;
	@Column
	private Integer ptaskid;
	@Column
	private Integer pmid;
	@Column
	private Integer operatorid;
	@Column
	private String name;
	@Column
	private String softver;
	@Column
	private String hardver;

//	public Integer getId() {
//		return id;
//	}
//
//	public void setId(Integer id) {
//		this.id = id;
//	}

	public Integer getTaskid() {
		return taskid;
	}

	public void setTaskid(Integer taskid) {
		this.taskid = taskid;
	}

	public Integer getPtaskid() {
		return ptaskid;
	}

	public Integer getPmid() {
		return pmid;
	}

	public void setPmid(Integer pmid) {
		this.pmid = pmid;
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

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSoftver() {
		return softver;
	}

	public void setSoftver(String softver) {
		this.softver = softver;
	}

	public String getHardver() {
		return hardver;
	}

	public void setHardver(String hardver) {
		this.hardver = hardver;
	}

}
