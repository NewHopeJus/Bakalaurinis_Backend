package com.example.bakalaurinis.repository;

import com.example.bakalaurinis.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 *  The repository is responsible for interacting with the database
 *  and performing CRUD (Create, Read, Update, Delete)
 *  operations on your User entities.
 *
 *
 * */
@Repository
public interface UserRepository extends JpaRepository<User, Long> {

}
