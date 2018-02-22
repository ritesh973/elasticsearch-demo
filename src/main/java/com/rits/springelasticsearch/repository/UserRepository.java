package com.rits.springelasticsearch.repository;

import com.rits.springelasticsearch.model.Users;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

import java.util.List;

public interface UserRepository extends ElasticsearchRepository<Users,Long> {

    List<Users> findByName(String name);
    List<Users> findBySalary(Long salary);

}
