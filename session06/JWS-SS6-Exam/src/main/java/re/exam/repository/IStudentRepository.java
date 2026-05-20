package re.exam.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import re.exam.entity.Student;

public interface IStudentRepository extends JpaRepository<Student, Long> {

}
