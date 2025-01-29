package com.IT.SpringBootAngular.Repo;
import org.springframework.data.mongodb.repository.MongoRepository;
import com.IT.SpringBootAngular.Entitys.Salaire;
public interface SalaireRepo extends MongoRepository<Salaire, String>{

}
