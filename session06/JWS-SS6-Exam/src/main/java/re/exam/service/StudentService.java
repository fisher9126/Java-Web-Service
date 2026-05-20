package re.exam.service;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import re.exam.entity.Student;
import re.exam.repository.IStudentRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class StudentService {
    private final IStudentRepository studentRepository;
    public List<Student> findAll() {
        return studentRepository.findAll();
    }
    public Student findById(Long id) {
        return studentRepository.findById(id)   .orElseThrow(() ->
                new ResponseStatusException(HttpStatus.NOT_FOUND, "Course not found"));
    }
    public Student save(Student student) {
        return studentRepository.save(student);
    }
    public Student updateStudentPut(Long id, Student studentRequest) {
        Student stu = studentRepository.findById(id)   .orElseThrow(() ->
                new ResponseStatusException(HttpStatus.NOT_FOUND, "Course not found"));

        stu.setEmail(studentRequest.getEmail());
        stu.setFullName(studentRequest.getFullName());
        stu.setGpa(studentRequest.getGpa());

        return studentRepository.save(stu);
    }

    public Student updateStudentPatch(Long id, Student studentRequest) {
        Student stu = studentRepository.findById(id)   .orElseThrow(() ->
                new ResponseStatusException(HttpStatus.NOT_FOUND, "Course not found"));

        if (studentRequest.getEmail() != null) {
            stu.setEmail(studentRequest.getEmail());
        }
        if (studentRequest.getFullName() != null) {
            stu.setFullName(studentRequest.getFullName());
        }
        if (studentRequest.getGpa() != null) {
            stu.setGpa(studentRequest.getGpa());
        }

        return studentRepository.save(stu);
    }
    public void deleteById(Long id) {
        Student stu = studentRepository.findById(id)   .orElseThrow(() ->
                new ResponseStatusException(HttpStatus.NOT_FOUND, "Course not found"));

        studentRepository.deleteById(stu.getId());

    }

}





