package com.example.cruddemo.service;

import java.util.List;
import java.util.Optional;

import org.apache.catalina.User;
import org.springframework.stereotype.Service;

import com.example.cruddemo.model.Users;
import com.example.cruddemo.repository.UserRepositoy;

@Service
public class UserServiceimpl implements UserService {

	UserRepositoy repositoy;

	public UserServiceimpl(UserRepositoy repositoy) {
		this.repositoy = repositoy;

	}

	@Override
	public Users createUser(Users users) {
		Users user = repositoy.findById(users.getId()).orElse(null);
		if (user != null) {
			return null;
		}

		return repositoy.insert(users);
	}

	@Override
	public List<Users> getAllUsers() {
		return repositoy.findAll();

	}

	@Override
	public Users getUserById(String id) {
		return repositoy.findById(id).orElse(null);
	}

	@Override
	public Users updateById(String id, Users user) {
		Users existingUser = repositoy.findById(id).orElse(null);
		if (existingUser == null) {
			return null;
		}
		existingUser.setFirstName(user.getFirstName());
		existingUser.setLastName(user.getLastName());
		existingUser.setEmail(user.getEmail());
		existingUser.setPhoneNumber(user.getPhoneNumber());
		repositoy.save(existingUser);
		return existingUser;
	}

	@Override
	public Users deleteById(String id) {
		Users deletedUser = repositoy.findById(id).orElse(null);
		if (deletedUser == null)
			return null;
		repositoy.deleteById(id);
		return deletedUser;

	}

}
