package me.jacksonchen.SpringBootJPADemo.Student;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

public interface StudentRepository extends JpaRepository<Student, Long> {
    //Student is the entity name in Student class rather than the table name
    //best practice has @Query on every method you create, therefore you have full
    //control of it
    @Query("SELECT s FROM Student AS s WHERE s.email = ?1")
    Optional<Student> findStudentByEmail(String email);
    List<Student> findStudentByFirstNameEqualsAndAgeEquals(String firstName, Integer age);
    @Query("SELECT s FROM Student AS s WHERE s.firstName = ?1 AND  s.age >= ?2")
    List<Student> findStudentByFirstNameEqualsAndAgeGreaterThanEqual(String firstName, Integer age);

    @Query(value = "select * from student where first_name = :firstName and age >= :age", nativeQuery = true)
    List<Student> findStudentByFirstNameEqualsAndAgeGreaterThanEqualNative(@Param("firstName") String firstName, @Param("age") Integer age);

    @Transactional
    @Modifying
    @Query("delete from Student u where u.id = ?1")
    int deleteStudentById(Long id);
}
