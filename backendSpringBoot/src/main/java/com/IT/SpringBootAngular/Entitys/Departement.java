package com.IT.SpringBootAngular.Entitys;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.List;

@Document(collection = "Departement")
public class Departement {
    @Id
    private String id;
    private String departName;
    private String departDetails;
    @DBRef
    private List<Employee> employeeList;
    @JsonIgnore
    @DBRef
    private HRadmin hRadmin;

    public Departement(String departName, String departDetails, HRadmin hRadmin) {

        this.departName = departName;
        this.hRadmin = hRadmin;
        this.departDetails = departDetails;
    }

    public Departement() {
    }

    public String getDepartName() {
        return departName;
    }

    public void setDepartName(String departName) {
        this.departName = departName;
    }

    public List<Employee> getEmployeeList() {
        return employeeList;
    }

    public void setEmployeeList(List<Employee> employeeList) {
        this.employeeList = employeeList;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public HRadmin gethRadmin() {
        return hRadmin;
    }

    public void sethRadmin(HRadmin hRadmin) {
        this.hRadmin = hRadmin;
    }

    public String getDepartDetails() {
        return departDetails;
    }

    public void setDepartDetails(String departDetails) {
        this.departDetails = departDetails;
    }

    public void addEmployee(Employee emp) {
        if (this.employeeList == null)
            this.employeeList = new ArrayList<>();
        employeeList.add(emp);
    }

    public String removeEmployee(Employee emp) {
        try {
            employeeList.remove(emp);
        } catch (Exception e) {
            e.printStackTrace();
            return "employee not exist in department";
        }
        return null;
    }

}
