package com.rits.springelasticsearch.load;

import com.rits.springelasticsearch.jparepository.UsersJpaRespository;
import com.rits.springelasticsearch.model.Users;
import com.rits.springelasticsearch.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.elasticsearch.core.ElasticsearchOperations;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;

@Component
public class Loaders {

    @Autowired
    ElasticsearchOperations operations;

    @Autowired
    UserRepository userRepository;

    @Autowired
    UsersJpaRespository usersJpaRespository;

    @PostConstruct
    @Transactional
    public void loadAll(){

        operations.putMapping(Users.class);
        System.out.println("Loading Data");
        usersJpaRespository.save(getData());

        userRepository.save(usersJpaRespository.findAll());
        System.out.println("Loading Completed");


    }
    private List<Users> getData(){
        List<Users> users = new ArrayList<>();
        users.add(new Users("Ritesh",123l,"Dev",1000l));
        users.add(new Users("Gopal",13l,"Dev",200l));
        users.add(new Users("Rahul",12l,"Dev",100l));
        users.add(new Users("Sweta",1213l,"Dev",300l));
        return users;
    }
}
