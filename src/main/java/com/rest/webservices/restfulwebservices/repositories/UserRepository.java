package com.rest.webservices.restfulwebservices.repositories;

import com.rest.webservices.restfulwebservices.beans.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User,Integer> {

}
