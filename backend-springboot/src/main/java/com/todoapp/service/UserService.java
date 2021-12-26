package com.todoapp.service;

import com.todoapp.model.dto.TodoDto;
import com.todoapp.model.dto.UserDto;
import com.todoapp.model.entity.User;

public interface UserService {

	User getUserByUsername(String username);
	UserDto getUserById(Long id);
	UserDto addTodo(Long id, TodoDto todo);
	UserDto updateTodo(Long id, TodoDto todo);
}
