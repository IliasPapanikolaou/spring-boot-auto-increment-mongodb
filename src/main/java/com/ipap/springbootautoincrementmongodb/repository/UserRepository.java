package com.ipap.springbootautoincrementmongodb.repository;

import com.ipap.springbootautoincrementmongodb.entity.User;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface UserRepository extends MongoRepository<User, Long> {
}
