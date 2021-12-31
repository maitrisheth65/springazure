package com.maitri.demo;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;

import com.maitri.dao.UserDao;
import com.maitri.model.User;

@SpringBootTest
public class JwtApplicationTests {
	@Autowired
	private UserDao userRepository;

	@Test
	public void saveUserTest() {
		User expectedUser = new User();
		expectedUser.setUserName("maitrisheth");
		expectedUser.setUserFirstName("maitri");
		expectedUser.setUserLastName("sheth");
		expectedUser.setUserPassword("123");

		User user1 = userRepository.save(expectedUser);
		assertThat(user1.getUserName()).isEqualTo("maitrisheth");
	}

	@Test
	public void getUserTest() {
		User user = userRepository.findById("maitrisheth").get();
		assertThat(user.getUserName()).isEqualTo("maitrisheth");
	}

	@Test
	public void getListOfUsersTest() {
		List<User> users = userRepository.findAll();
		assertThat(users.size()).isGreaterThan(0);
	}

	@Test
	public void updateUserTest() {
		User expectedUser = new User();
		expectedUser.setUserName("maitrisheth1");
		expectedUser.setUserFirstName("maitri");
		expectedUser.setUserLastName("sheth");
		expectedUser.setUserPassword("123");
		userRepository.save(expectedUser);

		
		User userUpdated = userRepository.save(expectedUser);
		assertThat(userUpdated.getUserName()).isEqualTo("maitrisheth1");

	}

	@Test
	public void deleteUserTest() {

		User expectedUser = new User();
		expectedUser.setUserName("maitrisheth1");
		expectedUser.setUserFirstName("maitri");
		expectedUser.setUserLastName("sheth");
		expectedUser.setUserPassword("123");
		User user = userRepository.save(expectedUser);
// System.out.println(user);
// System.out.println(userRepository.findById(user.getId()).get());

		userRepository.delete(expectedUser);

// assertEquals(userRepository.findById(user.getId()).get(), null);
// Assertions.assertThat(userRepository.findById(user.getId()).get()).isEqualTo(null);
// User user= userRepository.findById(6).get();
// userRepository.delete(user);
		User user1 = null;
		Optional<User> optionalUser = userRepository.findById("maitrisheth1");
		if (optionalUser.isPresent()) {
			user1 = optionalUser.get();
			assertThat(user1).isNull();
		}

	}

}
