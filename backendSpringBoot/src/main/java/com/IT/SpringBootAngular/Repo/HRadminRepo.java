package com.IT.SpringBootAngular.Repo;

import com.IT.SpringBootAngular.Entitys.HRadmin;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface HRadminRepo extends MongoRepository<HRadmin,String> {
    boolean existsByEmail(String email);
    Optional<HRadmin> findByEmail(String email);

}
