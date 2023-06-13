package asn2.student_tracker.Models;

import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentRepository extends JpaRepository<Student, Integer> {
    Student findBySid(int sid);
    int deleteBySid(int sid);
}
