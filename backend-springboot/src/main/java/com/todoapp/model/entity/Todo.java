package com.todoapp.model.entity;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.todoapp.model.dto.TodoDto;

@Entity
@Table(name="todo")
public class Todo extends BaseEntity {

	private static final long serialVersionUID = 1L;

	@Column(length=200, nullable=false)
	private String name;
	
	@Column(nullable=false)
	private boolean completed;
	
	@ManyToOne(cascade = CascadeType.ALL)
	@JsonIgnore
    @JoinColumn(name="user_id", nullable=false)
    private User user;
	
	public Todo() {
	}
	
	public Todo(TodoDto dto) {
		if(dto != null) {
			this.setId(dto.getId());
			this.setName(dto.getName());
			this.setCompleted(dto.isCompleted());
		}
	}
	
	public Todo(String name, boolean completed) {
		this.name = name;
		this.completed = completed;
	}
	
	public User getUser() {
		return user;
	}

	public void setUser(User user) {
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
		Todo other = (Todo) obj;
		if (this.getId() == null) {
			if (other.getId() != null)
				return false;
		} else if (!this.getId().equals(other.getId()))
			return false;
		return true;
	}
}
