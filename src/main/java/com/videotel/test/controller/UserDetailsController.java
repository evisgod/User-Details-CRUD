package com.videotel.test.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.videotel.test.domain.AddUserRequest;
import com.videotel.test.domain.GetUserResponse;
import com.videotel.test.domain.UserDetails;
import com.videotel.test.domain.UserResponse;
import com.videotel.test.service.UserService;
import com.videotel.test.validation.UserInputValidation;

/**
 * This is controller layer where REST APIs are defined.
 * 
 */
@RestController
@RequestMapping("/v1/users")
public class UserDetailsController {
	@Autowired
	UserService userService;
	@Autowired
	UserInputValidation userInputValidation;

	/**
	 * This method will call the Service layer - addUser method to create a user in
	 * DB.
	 * 
	 * @param addUserRequest
	 * @return
	 */
	@PostMapping
	public ResponseEntity<UserResponse> createUser(@RequestBody AddUserRequest addUserRequest) {
		userInputValidation.validCountryCode(addUserRequest.getCountry());
		UserResponse response = userService.addUser(addUserRequest);
		return new ResponseEntity<>(response, HttpStatus.CREATED);
	}

	/**
	 * This method will call the Service layer - getUser method to get a user from
	 * DB
	 * 
	 * @param id
	 * @return
	 */
	@GetMapping("/{id}")
	public ResponseEntity<UserDetails> retrieveUser(@PathVariable long id) {
		UserDetails response = userService.getUser(id);
		return new ResponseEntity<>(response, HttpStatus.OK);
	}

	/**
	 * This method will call the Service layer - retrieveAllUsers method to get all
	 * user from DB
	 * 
	 * @param id
	 * @return
	 */
	@GetMapping
	public ResponseEntity<GetUserResponse> retrieveAllUser() {
		GetUserResponse response = userService.retrieveAllUsers();
		return new ResponseEntity<>(response, HttpStatus.OK);
	}

	/**
	 * This method will call the Service layer - updateUser method to update user
	 * details in DB
	 * 
	 * @param addUserRequest
	 * @param id
	 * @return
	 */
	@PutMapping("/{id}")
	public ResponseEntity<UserResponse> updateUserDetails(@RequestBody AddUserRequest addUserRequest,
			@PathVariable long id) {
		userInputValidation.validCountryCode(addUserRequest.getCountry());
		UserResponse response = userService.updateUser(addUserRequest, id);
		return new ResponseEntity<>(response, HttpStatus.OK);
	}

	/**
	 * This method will call the Service layer - deleteUser method to deleter user
	 * from DB
	 * 
	 * @param id
	 * @return
	 */
	@DeleteMapping("/{id}")
	public ResponseEntity<UserResponse> deleteUser(@PathVariable long id) {
		UserResponse response = userService.deleteUser(id);
		return new ResponseEntity<>(response, HttpStatus.OK);
	}
}
