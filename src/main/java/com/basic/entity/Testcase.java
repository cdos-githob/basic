package com.basic.entity;

import java.io.Serializable;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="testcase")
public class Testcase implements Serializable{

	private static final long serialVersionUID = -3151576481243312151L;

	@Id
	@Column(name="id")
	private Integer id;
	@Column(name="pid")
	private Integer pid;
	@Column(name="name")
	private String name;
	@Column(name="casenum")
	private Integer casenum;
	@Column(name="step")
	private String step;
	@Column(name="result")
	private String result;
	@Column(name="comment")
	private String comment;
	@Column(name="times")
	private Integer times;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getPid() {
		return pid;
	}
	public void setPid(Integer pid) {
		this.pid = pid;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Integer getCasenum() {
		return casenum;
	}
	public void setCasenum(Integer casenum) {
		this.casenum = casenum;
	}
	public String getStep() {
		return step;
	}
	public void setStep(String step) {
		this.step = step;
	}
	public String getResult() {
		return result;
	}
	public void setResult(String result) {
		this.result = result;
	}
	public String getComment() {
		return comment;
	}
	public void setComment(String comment) {
		this.comment = comment;
	}
	public Integer getTimes() {
		return times;
	}
	public void setTimes(Integer times) {
		this.times = times;
	}
	
}
