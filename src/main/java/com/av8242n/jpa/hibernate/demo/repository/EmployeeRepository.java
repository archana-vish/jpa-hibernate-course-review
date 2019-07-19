package com.av8242n.jpa.hibernate.demo.repository;

import com.av8242n.jpa.hibernate.demo.entity.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.List;

@Repository
@Transactional
public class EmployeeRepository {


    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @PersistenceContext
    EntityManager entityManager;


    public void insert(Employee employee) {
        entityManager.persist(employee);
    }

    public List<Employee> retrieveAllEmployees() {
        return entityManager.createQuery("select e from Employee e", Employee.class).getResultList();
    }

    public List<PartTimeEmployee> retrievePartTimeEmployees() {
        return entityManager.createQuery("select e from PartTimeEmployee e", PartTimeEmployee.class).getResultList();
    }

    public List<FullTimeEmployee> retrieveFullTimeEmployees() {
        return entityManager.createQuery("select e from FullTimeEmployee e", FullTimeEmployee.class).getResultList();
    }



}
