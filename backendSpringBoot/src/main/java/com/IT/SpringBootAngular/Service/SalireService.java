package com.IT.SpringBootAngular.Service;

import com.IT.SpringBootAngular.Entitys.Salaire;
import com.IT.SpringBootAngular.Repo.SalaireRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SalireService {
    @Autowired
    private SalaireRepo salaireRepo;
    public Salaire save_salary(Salaire salaire){
        return salaireRepo.save(salaire);

    }


    public Iterable<Salaire> getAll(){
        return this.salaireRepo.findAll();
    }

    public void delete(String id){
        salaireRepo.deleteById(id);
    }

    public Salaire getById(String id){
        return salaireRepo.findById(id).get();
    }
}
