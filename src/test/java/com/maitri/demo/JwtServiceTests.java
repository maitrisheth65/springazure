package com.maitri.demo;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.maitri.dao.UserDao;
import com.maitri.model.User;
import com.maitri.service.UserService;

@SpringBootTest
public class JwtServiceTests {
	@Autowired
	private UserService jwtUserDetailService;
	@Mock
	private UserDao userRepository;

	@Test
	public void addUserTest() {
		User user = new User("maitrisheth", "maitri", "sheth", "maitrisheth", null);
		// UserDto user1 = new UserDto(29,"niki","niki");
		when(userRepository.save(user)).thenReturn(user);
		assertEquals(user.getUserName(), jwtUserDetailService.registerNewUser(user).getUserName());
	}

	@Test
	public void updateUserTest() {
		User user = new User("amisheth", "ami", "sheth", "amisheth", null);
		// UserDto user1 = new UserDto(29,"niki","niki");
		// jwtUserDetailService.registerNewUser(user);

//		when(userRepository.save(user)).thenReturn(user);
		User user1 = new User();
		user1.setUserLastName("shah");
		user1.setUserFirstName("ami");
		user1.setRole(null);
		user1.setUserPassword("amishah");
		User user2 = jwtUserDetailService.updateUser("amisheth", user1);
		if (user2.getUserLastName().equals("shah")) {

		} else {
			Assertions.fail("failed");
		}
	}

}
