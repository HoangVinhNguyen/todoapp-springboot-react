package com.todoapp.model.entity;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.todoapp.model.dto.UserDto;

@Entity
@Table(name = "user")
public class User extends BaseEntity {

	private static final long serialVersionUID = 1L;

	@Column(length = 200, nullable = false)
	private String username;

	@Column(length = 64, nullable = false)
	private String password;
	
	@Column(nullable = false)
	private boolean enabled;
	
	@Column(nullable = false)
	private String role;
	
	@OneToMany(mappedBy="user", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JsonIgnore
    private Set<Todo> todoList = new HashSet<>();

	public User() {

	}

	public User(String username, String password, String role) {
		this.setUsername(username);
		this.setPassword(password);
		this.setRole(role);
	}
	
	public User(UserDto dto) {
		this.setUsername(dto.getUsername());
		this.setPassword(dto.getPassword());
		todoList.addAll(dto.getTodoList().stream().map(Todo::new).collect(Collectors.toSet()));
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

	public boolean isEnabled() {
		return enabled;
	}

	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public Set<Todo> getTodoList() {
		return todoList;
	}

	public void setTodoList(Set<Todo> todoList) {
		this.todoList = todoList;
	}
	
	public void addTodoList(Todo todo) {
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
		User other = (User) obj;
		if (this.getId() == null) {
			if (other.getId() != null)
				return false;
		} else if (!this.getId().equals(other.getId()))
			return false;
		return true;
	}
}
