package com.videotel.test.service.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import com.videotel.test.domain.AddUserRequest;
import com.videotel.test.domain.GetUserResponse;
import com.videotel.test.domain.RestCountriesResponse;
import com.videotel.test.domain.UserDetails;
import com.videotel.test.domain.UserResponse;
import com.videotel.test.entities.User;
import com.videotel.test.exception.UserException;
import com.videotel.test.repository.UserRepository;
import com.videotel.test.service.ApiService;
import com.videotel.test.service.UserServiceImpl;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserServiceTests {
	@Mock
	private UserRepository userRepository;
	@MockBean
	private ApiService apiService;
	@InjectMocks
	private UserServiceImpl userService;

	private AddUserRequest addUserRequest;
	private long id = 1;

	@Before
	public void setup() {
		addUserRequest = new AddUserRequest();
		addUserRequest.setFirstName("Vishnu");
		addUserRequest.setLastName("Godara");
		addUserRequest.setCountry("ind");
		addUserRequest.setDateOfBirth(new Date(0));
		MockitoAnnotations.initMocks(this);
	}

	@Test
	public void createUserSuccess() throws UserException {
		User user = new User(1, "Vishnu", "Godara", new Date(0), "ind", "India");
		RestCountriesResponse body = new RestCountriesResponse();
		body.setName("India");
		ResponseEntity<RestCountriesResponse> responseEntity = new ResponseEntity<>(body, HttpStatus.ACCEPTED);
		when(apiService.getNationality("ind")).thenReturn(responseEntity);
		when(userRepository.save(user)).thenReturn(user);
		UserResponse response = userService.addUser(addUserRequest);
		assertTrue(response.isSuccess());
	}

	@Test
	public void getUserSuccess() throws Exception {
		User user = new User(1, "Vishnu", "Godara", new Date(0), "ind", "India");
		when(userRepository.findById(1)).thenReturn(user);
		UserDetails userDetails = userService.getUser(id);
		assertNotNull(userDetails);
		assertEquals(1, userDetails.getId());
	}

	@Test
	public void getAllUserSuccess() throws Exception {
		List<User> userList = new ArrayList<User>();
		User user1 = new User(1, "Vishnu", "Godara", new Date(0), "ind", "India");
		User user2 = new User(2, "Vishnu", "Godara", new Date(0), "ind", "India");
		userList.add(user1);
		userList.add(user2);
		when(userRepository.findAll()).thenReturn(userList);
		GetUserResponse getUserResonse = userService.retrieveAllUsers();
		assertNotNull(getUserResonse);
		assertEquals(2, getUserResonse.getUserDetails().size());
		assertEquals(1, getUserResonse.getUserDetails().get(0).getId());
	}

	@Test
	public void updateUserSuccess() throws UserException {
		User user = new User(1, "Vishnu", "Godara", new Date(0), "ind", "India");
		RestCountriesResponse body = new RestCountriesResponse();
		body.setName("India");
		ResponseEntity<RestCountriesResponse> responseEntity = new ResponseEntity<>(body, HttpStatus.ACCEPTED);
		when(userRepository.findById(id)).thenReturn(user);
		when(apiService.getNationality("ind")).thenReturn(responseEntity);
		when(userRepository.save(user)).thenReturn(user);
		UserResponse response = userService.updateUser(addUserRequest, id);
		assertTrue(response.isSuccess());
	}

	@Test
	public void deleteUserSuccess() throws Exception {
		doNothing().when(userRepository).deleteById(id);
		UserResponse userResponse = userService.deleteUser(id);
		assertNotNull(userResponse);
		assertTrue(userResponse.isSuccess());
	}

	// Similarly we can write more negative and positive scenarios
}