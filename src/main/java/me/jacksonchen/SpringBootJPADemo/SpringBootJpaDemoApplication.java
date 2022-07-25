package me.jacksonchen.SpringBootJPADemo;

import com.github.javafaker.Faker;
import me.jacksonchen.SpringBootJPADemo.Student.*;
import org.hibernate.Hibernate;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@SpringBootApplication
public class SpringBootJpaDemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringBootJpaDemoApplication.class, args);
	}
	@Bean
	CommandLineRunner commandLineRunner(
			StudentRepository studentRepository,
			StudentIdCardRepository studentIdCardRepositor,
			BookRepository bookRepositoryy) {
		return args -> {
			generateData(studentRepository,studentIdCardRepositor);
			bookRepositoryy.findById(1L).ifPresent( book -> {
						Student s = book.getStudent();
						System.out.println(s);

					}
			);


		};
	}

	void generateData(
			StudentRepository studentRepository,
			StudentIdCardRepository studentIdCardRepository) {
		//how to use faker to generate fake data
		Faker faker = new Faker();
		for(int i = 0; i < 3; i++) {
			String firstName = faker.name().firstName();
			String lastName = faker.name().lastName();
			String email = String.format("%s.%s@gmail.com", firstName, lastName);
			Student student = new Student(
					firstName,
					lastName,
					faker.number().numberBetween(17,55),
					email
			);
			Book book = new Book (faker.book().title(), LocalDateTime.now());
			student.addBook(book);


			StudentIdCard studentIdCard = new StudentIdCard(
					"" + faker.number().numberBetween(1000, 99999),
					student
			);
			Course course = new Course("hello world", "egnlish apartment");

			student.setStudentIdCard(studentIdCard);
			studentRepository.save(student);

		}
	}
}
