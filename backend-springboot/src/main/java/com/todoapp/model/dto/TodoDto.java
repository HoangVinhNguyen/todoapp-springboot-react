package com.todoapp.model.dto;

import com.todoapp.model.entity.Todo;

public class TodoDto extends BaseDto {

	private static final long serialVersionUID = 1L;

	private String name;
	private boolean completed;
	private UserDto user;
	
	
	public TodoDto(Todo entity) {
		if (entity != null) {
			this.setId(entity.getId());
			this.setName(entity.getName());
			this.setCompleted(entity.isCompleted());
		}
	}
	public TodoDto(String name, boolean completed) {
		this.name = name;
		this.completed = completed;
	}
	public TodoDto(Long id, String name, boolean completed) {
		this.setId(id);
		this.name = name;
		this.completed = completed;
	}
	public TodoDto() {
	}
	
	public UserDto getUser() {
		return user;
	}
	public void setUser(UserDto user) {
		this.user = user;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public boolean isCompleted() {
		return completed;
	}
	public void setCompleted(boolean completed) {
		this.completed = completed;
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
		TodoDto other = (TodoDto) obj;
		if (this.getId() == null) {
			if (other.getId() != null)
				return false;
		} else if (!this.getId().equals(other.getId()))
			return false;
		return true;
	}
}
