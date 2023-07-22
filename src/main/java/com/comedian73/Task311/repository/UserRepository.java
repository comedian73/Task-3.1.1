package com.comedian73.Task311.repository;

import com.comedian73.Task311.model.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, Long> {
}
