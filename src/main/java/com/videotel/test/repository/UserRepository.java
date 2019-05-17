package com.videotel.test.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.videotel.test.entities.User;

/**
 * This is the SpringData JPA repository to perform CRUD operations.
 *
 */
@Repository
public interface UserRepository extends CrudRepository<User, Long> {

	User findById(long id);

	@Override
	List<User> findAll();

}