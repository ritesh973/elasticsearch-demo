package com.rits.springelasticsearch.jparepository;

import com.rits.springelasticsearch.model.Users;
import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.stereotype.Repository;

@Repository
public interface UsersJpaRespository extends JpaRepository<Users,Long> {
}
