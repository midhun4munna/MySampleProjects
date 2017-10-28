package com.example.demo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "todo")
public class ToDoModel {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int slno;
	@Column(name = "todoDetail")
	private String todoDetail;
	@Column(name = "status")
	private String status;

	public String getTodoDetail() {
		return this.todoDetail;
	}

	public void setTodoDetail(String todoDetail) {
		this.todoDetail = todoDetail;
	}

	public int getSlno() {
		return this.slno;
	}

	public void setSlno(int slno) {
		this.slno = slno;
	}

	public String getStatus() {
		return this.status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
}
