package me.jacksonchen.SpringBootJPADemo.Student;

import javax.persistence.*;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

//best practice to have full control of name of this
@Entity(name = "Student")
@Table(
        name = "student",
        uniqueConstraints = {
                @UniqueConstraint(name = "student_email_unique", columnNames = "email")
        }
)
public class Student {
//    @SequenceGenerator(name = "student")

    @Id
    @SequenceGenerator(
            name = "student_sequence",
            sequenceName = "student_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "student_sequence"
    )
    @Column (
        name = "id",
        updatable = false
    )
    private Long id;
    @Column(
            name = "first_name",
            nullable = false,
//            the column type
            columnDefinition = "TEXT"
    )
    private String firstName;
    @Column(
            name = "last_name",
            nullable = false,
            columnDefinition = "TEXT"
    )
    private String lastName;
    @Column(
            name = "age",
            nullable = false
    )
    private Integer age;
    @Column(
            name = "email",
            columnDefinition = "TEXT",
            nullable = false
    )
    private String email;
    @OneToOne(
            mappedBy = "student",
            orphanRemoval = true,
            fetch = FetchType.EAGER,
            cascade = {CascadeType.ALL}
    )
    @JoinColumn(
            name = "student_id_card_id",
            referencedColumnName = "id"
    )
    private StudentIdCard studentIdCard;

    //one to many
    @OneToMany(
            mappedBy = "student",
            orphanRemoval = true,
            cascade = {CascadeType.PERSIST, CascadeType.REMOVE},
            //have to change this back to lazy
            fetch = FetchType.EAGER
    )
    private List<Book> books = new ArrayList<>();

    @OneToMany (
            cascade = {CascadeType.PERSIST, CascadeType.REMOVE},
            mappedBy = "student"
    )
    private List<Enrolment> enrolments = new ArrayList<>();


    public Student() {
    }

    public Student( String firstName, String lastName, Integer age, String email) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.age = age;
        this.email = email;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }


    public void addBook(Book book){
        if(!books.contains(book)) {
            books.add(book);
            book.setStudent(this);
        }
    }
    public void removeBook(Book book){
        if(this.books.contains(book)) {
            this.books.remove(book);
            book.setStudent(null);
        }
    }

    public List<Book> getBooks() {
        return books;
    }
    public void setStudentIdCard(StudentIdCard studentIdCard) {
        this.studentIdCard = studentIdCard;
    }

    public List<Enrolment> getEnrolments() {
        return enrolments;
    }
    public void addEnrolment(Enrolment enrolment) {
        if(!enrolments.contains((enrolment))){
            enrolments.add(enrolment);
        }
    }
    public void removeEnrolment(Enrolment enrolment) {
        if(enrolments.contains((enrolment))){
            enrolments.remove(enrolment);
        }
    }

    @Override
    public String toString() {
        return "Student{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", age=" + age +
                ", email='" + email + '\'' +
                '}';
    }

}
