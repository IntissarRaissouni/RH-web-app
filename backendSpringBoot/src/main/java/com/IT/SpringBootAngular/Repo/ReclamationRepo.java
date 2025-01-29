package com.IT.SpringBootAngular.Repo;
import com.IT.SpringBootAngular.Entitys.Reclamation;
import org.springframework.data.mongodb.repository.MongoRepository;
public interface ReclamationRepo extends MongoRepository<Reclamation, String>{

}
