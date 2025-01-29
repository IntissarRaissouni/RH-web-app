package com.IT.SpringBootAngular.Repo;
import com.IT.SpringBootAngular.Entitys.Demande;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface DemandeRepo extends MongoRepository<Demande,String> {

}
