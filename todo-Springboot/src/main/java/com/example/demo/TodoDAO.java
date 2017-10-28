package com.example.demo;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

@Repository
public class TodoDAO {

	@PersistenceContext
	private EntityManager entityManager;

	public void create(ToDoModel apiRequest) {
		entityManager.persist(apiRequest);
	}

	public void update(ToDoModel apiRequest) {
		entityManager.merge(apiRequest);
	}

	public ToDoModel getToDoModelById(int id) {
		return entityManager.find(ToDoModel.class, id);
	}

	public void delete(int id) {
		ToDoModel todomodel = getToDoModelById(id);
		if (todomodel != null) {
			entityManager.remove(todomodel);
		}
	}
	
	public List<ToDoModel> getAll() {
		 List<ToDoModel> toDoList = (List<ToDoModel>)entityManager.createQuery( "SELECT t FROM ToDoModel t").getResultList();
		 return toDoList;
	}

}
