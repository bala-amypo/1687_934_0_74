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
public class StudentController {

@Autowired
private StudentService studentService;
// INSERT student
@PostMapping(&quot;/postStudent&quot;)
public Student postStd(@RequestBody Student st) {
return studentService.insertStudent(st);
}
// GET all students
@GetMapping(&quot;/getAll&quot;)
public List&lt;Student&gt; getAll() {
return studentService.getAllStudents();
}
// GET student by ID
@GetMapping(&quot;/get/{id}&quot;)
public Optional&lt;Student&gt; get(@PathVariable Long id) {
return studentService.getOneStudent(id);
}
// UPDATE student
@PutMapping(&quot;/update/{id}&quot;)
public String update(@PathVariable Long id, @RequestBody Student
newStudent) {
Optional&lt;Student&gt; student = studentService.getOneStudent(id);
if (student.isPresent()) {
newStudent.setId(id);
studentService.insertStudent(newStudent);
return &quot;Updated Success&quot;;
}
return &quot;Id not found&quot;;
}
// DELETE student
@DeleteMapping(&quot;/del/{id}&quot;)
public String deleteStudent(@PathVariable Long id) {
Optional&lt;Student&gt; student = studentService.getOneStudent(id);
if (student.isPresent()) {
studentService.deleteStudent(id);
return &quot;Deleted Success&quot;;
}
return &quot;Id Not Found&quot;;
}
}