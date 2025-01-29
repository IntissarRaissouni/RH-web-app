package com.IT.SpringBootAngular.Repo;

import com.IT.SpringBootAngular.Entitys.Attendance;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface AttendanceRepo extends MongoRepository<Attendance,String> {


}
