package com.rest.webservices.restfulwebservices.repositories;

import com.rest.webservices.restfulwebservices.beans.Post;
import com.rest.webservices.restfulwebservices.beans.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PostRepository extends JpaRepository<Post,Integer> {

}
