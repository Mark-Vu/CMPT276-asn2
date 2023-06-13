package asn2.student_tracker.Models;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentRepository extends JpaRepository<Student, Integer> {
    Student findBySid(int sid);
    int deleteBySid(int sid);
}
