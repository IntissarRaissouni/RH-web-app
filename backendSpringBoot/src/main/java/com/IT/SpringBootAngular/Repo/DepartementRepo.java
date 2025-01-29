package com.IT.SpringBootAngular.Repo;

import com.IT.SpringBootAngular.Entitys.Departement;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface DepartementRepo extends MongoRepository<Departement,String> {
}
