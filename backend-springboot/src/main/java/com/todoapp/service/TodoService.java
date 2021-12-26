package com.todoapp.service;

import java.util.List;

import com.todoapp.model.dto.TodoDto;

public interface TodoService {

	List<TodoDto> getAllByUserId(Long id);
	TodoDto updateTodo(TodoDto todo);
	TodoDto completedTodo(TodoDto todo);
	TodoDto deleteTodo(TodoDto todo);
}
