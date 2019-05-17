package com.videotel.test.controller.test;

import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.when;

import java.sql.Date;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import com.videotel.test.controller.UserDetailsController;
import com.videotel.test.domain.AddUserRequest;
import com.videotel.test.domain.UserDetails;
import com.videotel.test.domain.UserResponse;
import com.videotel.test.exception.UserNotFoundException;
import com.videotel.test.service.UserService;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserDetailsControllerTests {
	@Autowired
	private UserDetailsController controller;
	@MockBean
	private UserService userService;
	private AddUserRequest addUserRequest;
	private UserResponse userResponse;
	private UserDetails userDetails;
	private int id;

	@Before
	public void setup() {
		addUserRequest = new AddUserRequest();
		addUserRequest.setFirstName("Vishnu");
		addUserRequest.setLastName("Godara");
		addUserRequest.setCountry("ind");
		addUserRequest.setDateOfBirth(new Date(0));

		userResponse = new UserResponse();
		userResponse.setSuccess(true);

		userDetails = new UserDetails();
		userDetails.setFirstName("Vishnu");
		userDetails.setNationality("India");
		id = 1;
	}

	@Test
	public void createnewUserSuccess() {
		when(userService.addUser(addUserRequest)).thenReturn(userResponse);
		ResponseEntity<UserResponse> response = controller.createUser(addUserRequest);
		assertTrue(response.getBody().isSuccess());
	}

	@Test
	public void getUserSuccess() {
		when(userService.getUser(id)).thenReturn(userDetails);
		ResponseEntity<UserDetails> response = controller.retrieveUser(1);
		assertTrue(response.getBody().getNationality().equals("India"));
	}

	@Test(expected = UserNotFoundException.class)
	public void getUserNotSuccess() {
		when(userService.getUser(id)).thenThrow(new UserNotFoundException(false, "NOT Found"));
		controller.retrieveUser(id);
	}

	@Test(expected = UserNotFoundException.class)
	public void updateUserNotSuccess() {
		when(userService.updateUser(addUserRequest, id)).thenThrow(new UserNotFoundException(false, "NOT Found"));
		controller.updateUserDetails(addUserRequest, id);
	}

	@Test
	public void deleteUserSuccess() {
		when(userService.deleteUser(id)).thenReturn(userResponse);
		ResponseEntity<UserResponse> response = controller.deleteUser(id);
		assertTrue(response.getBody().isSuccess());
	}

	@Test(expected = UserNotFoundException.class)
	public void deleteUserNotSuccess() {
		when(userService.deleteUser(id)).thenThrow(new UserNotFoundException(false, "NOT Found"));
		controller.deleteUser(id);
	}
}
