package com.example.cruddemo;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.CoreMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.internal.matchers.Any;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import com.example.cruddemo.model.Address;
import com.example.cruddemo.model.Users;
import com.example.cruddemo.repository.UserRepositoy;
import java.lang.Exception;
import com.example.cruddemo.service.UserServiceimpl;

// Class For Testing Service Layer Of User
@ExtendWith(MockitoExtension.class)
public class UserServiceTest {
	
	@Mock
	private UserRepositoy repositoy;
	
	@InjectMocks
	private UserServiceimpl service;
	
	Users user;
	
	@BeforeEach
	public void setUp()
{
		user= new Users();
		user.setU_fn("Krishna");
		user.setU_ln("joshi");
		user.setU_em("krishna@gmail.com");
		user.setU_mn("5");
}
	@Test
	@DisplayName("Test Save User  ")
	public void whenSaveUser_thenReturn_savedUser()
	{
		when(repositoy.save(Mockito.any(Users.class))).thenReturn(user);
		Users user1 =service.createUser(user);
		Assertions.assertNotNull(user1);
		Assertions.assertEquals(user.getU_mn(), user1.getU_mn());
	}
	
	@Test
	public void testFindUserById()
	{
		when(repositoy.findById(Mockito.any(String.class))).thenReturn(Optional.of(user));
		Users existingUser = service.getUserById(user.getU_mn());
		
		Assertions.assertNotNull(existingUser);
		Assertions.assertEquals(user, existingUser);
	}
	
	@Test
	public void testGetAllUsers()
	{
		List<Users> users= new ArrayList<>();
		users.add(user);
		when(repositoy.findAll()).thenReturn(users);
		List <Users> allUsers = service.getAllUsers();
		Assertions.assertNotNull(allUsers);
		Assertions.assertEquals(1,allUsers.size())
		;
	}
	
	@Test
	public void testDeleteUserById()
	{
		when(repositoy.findById(Mockito.any(String.class))).thenReturn(Optional.of(user));
		doNothing().when(repositoy).deleteById(Mockito.anyString());
		Users deletedUser=service.deleteById(user.getU_mn());
		Assertions.assertEquals(user, deletedUser);
//		Assertions.assertEquals(user.getU_mn(), deletedUser.getU_mn());
	
	}
	@Test
	public void testUpdateUserById()
	{
		when(repositoy.findById(Mockito.anyString())).thenReturn(Optional.of(user));
		when(repositoy.save(Mockito.any(Users.class))).thenReturn(user);
		user.setU_fn("Krish");
		Users updatedUser = service.updateById(user.getU_mn(), user);
		Assertions.assertNotNull(updatedUser);
		Assertions.assertEquals("Krish",updatedUser.getU_fn());
		
	}
	

}
