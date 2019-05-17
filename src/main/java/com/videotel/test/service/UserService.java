package com.videotel.test.service;

import com.videotel.test.domain.AddUserRequest;
import com.videotel.test.domain.GetUserResponse;
import com.videotel.test.domain.UserDetails;
import com.videotel.test.domain.UserResponse;

public interface UserService {
	/**
	 * This is service method to add new user based on input and return message on
	 * successful addition
	 * 
	 * @param addUserRequest
	 * @return UserResponse
	 */
	public UserResponse addUser(AddUserRequest addUserRequest);

	/**
	 * This is service method to get All user information from DB
	 * 
	 * @return GetUserResponse
	 */
	public GetUserResponse retrieveAllUsers();

	/**
	 * This is service method to get user information from DB based on the input
	 * 
	 * @param id
	 * @return UserDetails
	 */
	public UserDetails getUser(long id);

	/**
	 * This is service method to update user information based on input and return
	 * message on successful updation
	 * 
	 * @param addUserRequest
	 * @param id
	 * @return UserResponse
	 */
	public UserResponse updateUser(AddUserRequest addUserRequest, long id);

	/**
	 * This is service method to delete user information based on input and return
	 * message on successful deletion
	 * 
	 * @param id
	 * @return UserResponse
	 */
	public UserResponse deleteUser(long id);

}
