package com.example.demo;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class TodoController {
	
	@Autowired
	ToDoService todoService;

	@RequestMapping(value="/home", method = RequestMethod.GET)
	public String method0(){
		return "todo.html";
	}
	
	@RequestMapping(value="/gettodo", method = RequestMethod.GET)
	@ResponseBody
	public List<ToDoModel> gettodo(){
		return todoService.getAll();
	}
	
	@RequestMapping(value="/addtodo", method = RequestMethod.POST)
	@ResponseBody
	public List<ToDoModel> addtodo(@RequestBody ToDoModel todo, @RequestHeader HttpHeaders headers){
		ToDoModel todoModel = new ToDoModel();
		todoModel.setTodoDetail(todo.getTodoDetail());
		todoModel.setStatus(todo.getStatus());
		todoService.create(todoModel);
		return todoService.getAll();
	}
	
	@RequestMapping(value="/removeTodo", method = RequestMethod.DELETE)
	@ResponseBody
	public List<ToDoModel> removeTodo(@RequestParam int slno){
		System.out.println(" remove todo  "+slno);
		todoService.remove(slno);
		return todoService.getAll();
	}
}
