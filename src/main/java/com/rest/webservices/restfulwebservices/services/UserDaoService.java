package com.rest.webservices.restfulwebservices.services;

import com.rest.webservices.restfulwebservices.beans.User;
import com.rest.webservices.restfulwebservices.exception.UserNotFoundException;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
public class UserDaoService {

    private static List<User> users = new ArrayList<>();
    private static int usersCount = 3;

    static {
        users.add(new User(1,"Adam",new Date()));
        users.add(new User(2,"Abhishek",new Date()));
        users.add(new User(3,"Anand",new Date()));
    }

    public List<User> findAll(){
        return users;
    }

    public User findOne(int id){
        for(User user:users){
            if(user.getId()==id){
                return user;
            }
        }
        throw new UserNotFoundException("id - "+id);
    }

    public User save(User user){
        if(user.getId()==null){
            user.setId(++usersCount);
        }
        users.add(user);
        return user;
    }

    public User deleteUserById(int id){
        Iterator<User> iterator = users.iterator();
        while(iterator.hasNext()){
            User user = iterator.next();;
            if(user.getId() !=null && user.getId()==id){
                iterator.remove();
                return user;
            }
        }
        throw new UserNotFoundException("id - "+id);
    }

}
