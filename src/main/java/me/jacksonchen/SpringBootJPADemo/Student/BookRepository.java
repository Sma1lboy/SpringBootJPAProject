package me.jacksonchen.SpringBootJPADemo.Student;

import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRepository extends JpaRepository<Book, Long> {

}
