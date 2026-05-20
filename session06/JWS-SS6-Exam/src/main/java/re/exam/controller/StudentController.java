package re.exam.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import re.exam.entity.Student;
import re.exam.service.StudentService;

import java.util.List;

@RestController
@RequestMapping("/api/students")
@RequiredArgsConstructor
public class StudentController {
    private final StudentService studentService;
    @GetMapping
    public List<Student> findAll() {
        return studentService.findAll();
    }
    @GetMapping("/{id}")
    public Student findOne(@PathVariable Long id) {
        return studentService.findById(id);
    }
    @PostMapping
    public Student save(@RequestBody Student student) {
        return studentService.save(student);
    }
    @PutMapping("/{id}")
    public Student update(@PathVariable Long id,@RequestBody Student student) {
        return studentService.updateStudentPut(id, student);
    }
    @PatchMapping("/{id}")
    public Student patch(@PathVariable Long id,@RequestBody Student student) {
        return studentService.updateStudentPatch(id, student);
    }
    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable Long id) {
        studentService.deleteById(id);
    }

}

