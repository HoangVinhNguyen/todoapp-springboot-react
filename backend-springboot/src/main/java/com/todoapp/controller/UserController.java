package com.todoapp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.todoapp.model.dto.TodoDto;
import com.todoapp.model.request.TodoRequest;
import com.todoapp.service.TodoService;
import com.todoapp.service.UserService;

@RestController
@RequestMapping("/api/user")
public class UserController {
	
	@Autowired
	private UserService service;

	@Autowired
	private TodoService todoService;

	@GetMapping("/todo/all/{id}")
	public ResponseEntity<?> getTodo(@PathVariable("id") Long id) {
		return ResponseEntity.ok(todoService.getAllByUserId(id));
	}
	@PostMapping("/todo")
	public ResponseEntity<?> addTodo(@RequestBody TodoRequest todoReq) {
		return ResponseEntity.ok(service.addTodo(todoReq.getUserId(), new TodoDto(todoReq.getName(), todoReq.isCompleted())));
	}
	@PostMapping("/todo/update")
	public ResponseEntity<?> updateTodo(@RequestBody TodoRequest todoReq) {
		return ResponseEntity.ok(todoService.updateTodo(new TodoDto(todoReq.getId(), todoReq.getName(), todoReq.isCompleted())));
	}
	@PostMapping("/todo/completed")
	public ResponseEntity<?> completedTodo(@RequestBody TodoRequest todoReq) {
		return ResponseEntity.ok(todoService.completedTodo(new TodoDto(todoReq.getId(), todoReq.getName(), todoReq.isCompleted())));
	}
	@PostMapping("/todo/delete")
	public ResponseEntity<?> deleteTodo(@RequestBody TodoRequest todoReq) {
		return ResponseEntity.ok(todoService.deleteTodo(new TodoDto(todoReq.getId(), todoReq.getName(), todoReq.isCompleted())));
	}
}
