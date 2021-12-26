package com.todoapp.service.Impl;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.todoapp.model.dto.TodoDto;
import com.todoapp.model.entity.Todo;
import com.todoapp.repository.TodoRepository;
import com.todoapp.service.TodoService;

@Service
public class TodoServiceImpl implements TodoService {
	
	@Autowired
	private TodoRepository repo;

	
	@Override
	public List<TodoDto> getAllByUserId(Long id) {
		Optional<List<Todo>> opl = Optional.ofNullable(repo.findAllByUserId(id));
		if (opl.isPresent()) {
			List<TodoDto> todoDtos = opl.get().stream().map(TodoDto::new).collect(Collectors.toList());
			return todoDtos;
		}
		return null;
	}

	@Override
	public TodoDto updateTodo(TodoDto todo) {
		Optional<Todo> op = repo.findById(todo.getId());
		if (op.isPresent()) {
			Todo td = op.get();
			td.setName(todo.getName());
			return new TodoDto(repo.save(td));
		}
		return null;
	}

	@Override
	public TodoDto completedTodo(TodoDto todo) {
		Optional<Todo> op = repo.findById(todo.getId());
		if (op.isPresent()) {
			Todo td = op.get();
			td.setCompleted(todo.isCompleted());
			return new TodoDto(repo.save(td));
		}
		return null;
	}
	
	@Override
	public TodoDto deleteTodo(TodoDto todo) {
		Optional<Todo> op = repo.findById(todo.getId());
		if (op.isPresent()) {
			Todo td = op.get();
			td.setDeleted(true);
			return new TodoDto(repo.save(td));
		}
		return null;
	}

}
