package com.example.demo.controller;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import com.example.demo.entity.Student;
import com.example.demo.service.StudentService;

@RestController
@RequestMapping("/students")
public class StudentController {

    @Autowired
    private StudentService studentService;

    // INSERT student
    @PostMapping
    public Student postStd(@RequestBody Student st) {
        return studentService.insertStudent(st);
    }

    // GET all students
    @GetMapping
    public List<Student> getAll() {
        return studentService.getAllStudents();
    }

    // GET student by ID
    @GetMapping("/{id}")
    public Optional<Student> get(@PathVariable Long id) {
        return studentService.getOneStudent(id);
    }

    // UPDATE student
    @PutMapping("/{id}")
    public String update(@PathVariable Long id, @RequestBody Student newStudent) {
        Optional<Student> student = studentService.getOneStudent(id);
        if (student.isPresent()) {
            newStudent.setId(id);
            studentService.insertStudent(newStudent);
            return "Updated Success";
        }
        return "Id not found";
    }

    // DELETE student
    @DeleteMapping("/{id}")
    public String deleteStudent(@PathVariable Long id) {
        Optional<Student> student = studentService.getOneStudent(id);
        if (student.isPresent()) {
            studentService.deleteStudent(id);
            return "Deleted Success";
        }
        return "Id Not Found";
    }
}
