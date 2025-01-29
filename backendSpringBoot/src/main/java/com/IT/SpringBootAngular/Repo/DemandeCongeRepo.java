package com.IT.SpringBootAngular.Repo;

import com.IT.SpringBootAngular.Entitys.DemandeConge;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface DemandeCongeRepo extends MongoRepository<DemandeConge,String> {
}
