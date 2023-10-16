package com.luv2code.cruddemo.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.luv2code.cruddemo.entity.Student;

import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;

@Repository
public class StudentDAOImpl implements StudentDAO {
    
//define field for entity manager
    private EntityManager entityManager;

//inject entity manager using constructor injection

    @Autowired
    public StudentDAOImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }


//implement save method
    @Override
    @Transactional
    public void save(Student theStudent) {
        entityManager.persist(theStudent);
        
    }


    @Override
    public Student findById(Integer id) {
        
        return entityManager.find(Student.class, id);
    }


    @Override
    public List<Student> findAll() {
        // create query
        TypedQuery<Student> theQuery = entityManager.createQuery("FROM Student", Student.class);
        //return query results

        return theQuery.getResultList();
    }


    @Override
    public List<Student> findByLastName(String theLastName) {
        // create query
        TypedQuery<Student> theQuery = entityManager.createQuery("FROM Student WHERE lastName=:theData", Student.class);

        //set parameters
        theQuery.setParameter("theData", theLastName);
        //return results
        return theQuery.getResultList();
    }


    @Override
    @Transactional
    public void update(Student theStudent) {
        entityManager.merge(theStudent);
        
    }


    @Override
    @Transactional
    public void delete(Integer id) {
        // retrieve the student
        Student myStudent = entityManager.find(Student.class, id);

        //delete the student
        entityManager.remove(myStudent);
        
    }


    @Override
    @Transactional
    public int deleteAll() {
        return entityManager.createQuery("DELETE FROM Student").executeUpdate();        
    }
    
}
