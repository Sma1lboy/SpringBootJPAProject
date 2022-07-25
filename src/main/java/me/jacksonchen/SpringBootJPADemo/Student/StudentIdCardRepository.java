package me.jacksonchen.SpringBootJPADemo.Student;
import org.springframework.data.repository.CrudRepository;

public interface StudentIdCardRepository
        extends CrudRepository<StudentIdCard, Long> {

}
