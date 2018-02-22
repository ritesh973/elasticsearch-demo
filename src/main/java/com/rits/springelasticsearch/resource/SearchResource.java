package com.rits.springelasticsearch.resource;

import com.rits.springelasticsearch.model.Users;
import com.rits.springelasticsearch.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/rest/search")
public class SearchResource {

    @Autowired
    UserRepository userRepository;


    @GetMapping(value = "/name/{text}")
    public List<Users> searchName(@PathVariable final String text){
        return userRepository.findByName(text);

    }
    @GetMapping(value = "/salary/{salary}")
    public List<Users> searchName(@PathVariable final Long salary){
        return userRepository.findBySalary(salary);

    }
    @GetMapping(value = "/all")
    public List<Users> searchName(){
        List<Users> usersList = new ArrayList<>();
        Iterable<Users> users = userRepository.findAll();
        users.forEach(usersList::add);
        return usersList;

    }
}
