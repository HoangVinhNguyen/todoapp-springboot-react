package com.todoapp.service.Impl;

import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.todoapp.model.dto.TodoDto;
import com.todoapp.model.dto.UserDto;
import com.todoapp.model.entity.Todo;
import com.todoapp.model.entity.User;
import com.todoapp.repository.UserRepository;
import com.todoapp.service.UserService;

@Service
@Transactional
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepository repo;
	
	@Override
	public User getUserByUsername(String username) {
		Optional<User> op = Optional.ofNullable(repo.findUserByUsername(username));
		return op.orElseGet(null);
	}

	@Override
	public UserDto getUserById(Long id) {
		Optional<User> op = Optional.ofNullable(repo.findUserById(id));
		if (op.isPresent()) {
			return new UserDto(op.get());
		}
		return null;
	}

	@Override
	public UserDto addTodo(Long id, TodoDto todo) {
		Optional<User> op = Optional.ofNullable(repo.findUserById(id));
		if (op.isPresent()) {
			User user = op.get();
			Todo td = new Todo(todo);
			td.setUser(user);
			user.addTodoList(td);
			user = repo.save(user);
			return new UserDto(user);
		}
		return null;
	}
	@Override
	public UserDto updateTodo(Long id, TodoDto todo) {
		Optional<User> op = Optional.ofNullable(repo.findUserById(id));
		if (op.isPresent()) {
			User user = op.get();
			Todo td = new Todo(todo);
			td.setUser(user);
			user.addTodoList(td);
			user = repo.save(user);
			return new UserDto(user);
		}
		return null;
	}

}
