package com.todoapp.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.todoapp.model.entity.Todo;

@Repository
public interface TodoRepository extends JpaRepository<Todo, Long>{

	@Query("SELECT td FROM Todo td WHERE td.isDeleted = FALSE AND td.user.id = :id")
	public List<Todo> findAllByUserId(@Param("id") Long id);
}
