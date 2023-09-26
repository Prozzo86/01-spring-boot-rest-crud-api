package com.LearnSpring.demo.rest;

import com.LearnSpring.demo.entity.Student;
import jakarta.annotation.PostConstruct;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api")
public class StudentRestController {

    // define a field / load the field with data / do it once
    private List<Student> theStudents;

    // define @PostConstruct to load the student data
    @PostConstruct
    public void loadData() {

        theStudents = new ArrayList<>();

        theStudents.add(new Student("Dean", "Matana"));
        theStudents.add(new Student("Ines", "Mrzljak"));
        theStudents.add(new Student("Marija", "Mihalina"));
    }

    // define endpoint for "/students" - return a list of students

    @GetMapping("/students")
    public List<Student> getStudents() {

        return theStudents;
    }

    // define endpoint or "/students/{studentId}" - return student at index
    //                      path variable {studentId}
    @GetMapping("/students/{studentId}")
    //                        ovdje bindam tu varijablu na nacin da je nazovem istim imenom i pomocu anotacije @PathVariable
    public Student getStudent(@PathVariable int studentId) {

        // just index into the list... keep it simple for now

        // check studentId again list size

        if ((studentId >= theStudents.size()) || (studentId < 0)) {
            throw new StudentNotFoundException("Student not found: " + studentId);
        }

        return theStudents.get(studentId);
    }
}
