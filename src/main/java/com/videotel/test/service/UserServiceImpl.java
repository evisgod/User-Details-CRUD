package com.videotel.test.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import com.videotel.test.domain.AddUserRequest;
import com.videotel.test.domain.GetUserResponse;
import com.videotel.test.domain.RestCountriesResponse;
import com.videotel.test.domain.UserDetails;
import com.videotel.test.domain.UserResponse;
import com.videotel.test.entities.User;
import com.videotel.test.exception.UserException;
import com.videotel.test.exception.UserNotFoundException;
import com.videotel.test.repository.UserRepository;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	UserRepository userRepository;
	@Autowired
	ApiService apiService;

	@Override
	public UserResponse addUser(AddUserRequest addUserRequest) {
		UserResponse userResponse = new UserResponse();
		User user = new User();
		user.setFirstName(addUserRequest.getFirstName());
		user.setLastName(addUserRequest.getLastName());
		user.setCountry(addUserRequest.getCountry());
		user.setDateOfBirth(addUserRequest.getDateOfBirth());
		ResponseEntity<RestCountriesResponse> nationality = apiService.getNationality(addUserRequest.getCountry());
		try {
			user.setNationality(nationality.getBody().getName());
			userRepository.save(user);
			userResponse.setSuccess(true);
			userResponse.setMsg("User created successfully");
		} catch (Exception e) {
			throw new UserException(false, "User creation failed. Please check your input details.");
		}
		return userResponse;
	}

	@Override
	public GetUserResponse retrieveAllUsers() {
		GetUserResponse getUserResponse = new GetUserResponse();
		List<User> userList = userRepository.findAll();
		if (CollectionUtils.isEmpty(userList)) {
			throw new UserNotFoundException(false, "User Details not found. Please add a user.");
		}
		List<UserDetails> usersDetails = userList.stream()
				.map(user -> new UserDetails(user.getId(), user.getFirstName(), user.getLastName(),
						user.getDateOfBirth(), user.getCountry(), user.getNationality()))
				.collect(Collectors.toList());
		getUserResponse.setUserDetails(usersDetails);
		return getUserResponse;
	}

	@Override
	public UserDetails getUser(long id) {
		User user = userRepository.findById(id);
		if (StringUtils.isEmpty(user)) {
			throw new UserNotFoundException(false, "User not found for id-" + id);
		}
		return new UserDetails(user.getId(), user.getFirstName(), user.getLastName(), user.getDateOfBirth(),
				user.getCountry(), user.getNationality());
	}

	@Override
	public UserResponse updateUser(AddUserRequest addUserRequest, long id) {
		UserResponse userResponse = new UserResponse();
		User user = userRepository.findById(id);
		if (StringUtils.isEmpty(user)) {
			throw new UserException(false, "User not found for id-" + id);
		}
		user.setFirstName(addUserRequest.getFirstName());
		user.setLastName(addUserRequest.getLastName());
		user.setDateOfBirth(addUserRequest.getDateOfBirth());
		user.setCountry(addUserRequest.getCountry());
		ResponseEntity<RestCountriesResponse> nationality = apiService.getNationality(addUserRequest.getCountry());

		try {
			user.setNationality(nationality.getBody().getName());
			userRepository.save(user);
			userResponse.setSuccess(true);
			userResponse.setMsg("User updated successfully");
		} catch (Exception e) {
			throw new UserException(false, "User updation failed. Please check input details.");
		}
		return userResponse;
	}

	@Override
	public UserResponse deleteUser(long id) {
		UserResponse userResponse = new UserResponse();
		try {
			userRepository.deleteById(id);
			userResponse.setSuccess(true);
			userResponse.setMsg("User deleted successfully");
		} catch (Exception e) {
			throw new UserException(false, "User deletion failed. Please check input details.");
		}
		return userResponse;
	}
}