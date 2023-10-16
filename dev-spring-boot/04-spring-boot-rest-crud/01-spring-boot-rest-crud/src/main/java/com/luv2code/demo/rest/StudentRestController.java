package com.luv2code.demo.rest;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.annotation.PostConstruct;

@RestController
@RequestMapping("/api")
public class StudentRestController {
    private List<Student> theStudents;
    //define @PostConstruct to load the student data
    @PostConstruct
    public void loadData(){
        theStudents = new ArrayList<>();
        theStudents.add(new Student("poornima", "Patel"));
        theStudents.add(new Student("Mario" , "Rossi"));
        theStudents.add(new Student("Mary", "Smith"));
    }
    //define enpoint for "/students" -return a list of students
    @GetMapping("/students")
    public List<Student> getStudents(){        
        return theStudents;
    }

    //define endpoint for "/students/{studentId} return student at index"
    @GetMapping("/students/{studentId}")
    public Student getStudent(@PathVariable int studentId){
        //check the studentId against list size
        if((studentId >= theStudents.size()|| (studentId <0))){
            throw new StudentNotFoundException("Student id not found - "+ studentId);
        }
        return theStudents.get(studentId);
    }

   
}
