package com.todoapp.model.dto;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

import com.todoapp.model.entity.User;

public class UserDto extends BaseDto {

	private static final long serialVersionUID = 1L;

	private String username;
	private String password;
	private Set<TodoDto> todoList = new HashSet<>();
	
	public UserDto(String username) {
		this.setUsername(username);
	}
	
	public UserDto(User entity) {
		if (entity != null) {
			this.setUsername(entity.getUsername());
			todoList.addAll(entity.getTodoList().stream().map(TodoDto::new).collect(Collectors.toSet()));
		}
	}
	
	public UserDto() {
		
	}
	
	
	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Set<TodoDto> getTodoList() {
		return todoList;
	}

	public void setTodoList(Set<TodoDto> todoList) {
		this.todoList = todoList;
	}
	public void addTodoList(TodoDto todo) {
		this.todoList.add(todo);
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((this.getId() == null) ? 0 : this.getId().hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		UserDto other = (UserDto) obj;
		if (this.getId() == null) {
			if (other.getId() != null)
				return false;
		} else if (!this.getId().equals(other.getId()))
			return false;
		return true;
	}
}
