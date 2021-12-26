package com.todoapp.user;

import static org.assertj.core.api.Assertions.assertThat;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;

import com.todoapp.common.SystemConstant;
import com.todoapp.model.entity.Todo;
import com.todoapp.model.entity.User;
import com.todoapp.repository.UserRepository;

@DataJpaTest(showSql = false)
@AutoConfigureTestDatabase(replace = Replace.NONE)
@Rollback(false)
public class UserRepositoryTest {

	@Autowired
	private UserRepository repo;
	
	
	@Test
	public void createUserWithOneRole() {
		LocalDateTime createdDate = LocalDateTime.now();
		String pass = "$2a$12$lBqU0yNuKrJindFuRJRfAesjMmwNYki/.Dr6MiCTDqoBb.4wn4f8u";
		//String passEnd = passwordEnd.encode("12345678");
		User user = new User("vinh", pass, SystemConstant.USER_ROLE);
		user.setEnabled(true);
		User savedUser = repo.save(user);
		assertThat(savedUser.getId()).isGreaterThan(0);
	}
	
	@Test
	public void createTodo() {
		User user = repo.findUserById(1L);
		Todo todo = new Todo("Learn Redux", false);
		Set<Todo> todos = new HashSet<>();
		todo.setUser(user);
		todos.add(todo);
		user.setTodoList(todos);
		repo.save(user);
	}
}
