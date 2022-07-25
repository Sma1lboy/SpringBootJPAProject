package me.jacksonchen.SpringBootJPADemo.Student;

import javax.persistence.*;

@Entity(name = "StudentIdCard")
@Table(
        name = "student_id_card",
        uniqueConstraints = {
                @UniqueConstraint(name = "student_id_card_card_number_unique", columnNames = "card_number")
        }
)
public class StudentIdCard {
    @SequenceGenerator(
            name = "student_id_card_sequence",
            sequenceName = "student_id_card_sequence",
            allocationSize = 1
    )
    @GeneratedValue (
            strategy = GenerationType.SEQUENCE,
            generator = "student_id_card_sequence"
    )
    @Id
    @Column(
            name = "id",
            //id will never gonna change
            updatable = false
    )
    private Long id;

    public StudentIdCard(String cardNumber,Student student) {
        this.student = student;
        this.cardNumber = cardNumber;
    }

    @Override
    public String toString() {
        return "StudentIdCard{" +
                "id=" + id +
                ", student=" + student +
                ", cardNumber='" + cardNumber + '\'' +
                '}';
    }

    @OneToOne(
            cascade = CascadeType.ALL,
            //one to one default is eager
            fetch = FetchType.LAZY
    )
    @JoinColumn(
            name = "student_id",
            //this id is the id from student table
            referencedColumnName = "id",
            foreignKey = @ForeignKey(
                    name = "student_id_card_student_id_fk"
            )
    )
    private Student student;
    @Column(
            name = "card_number",
            nullable = false,
            length = 15,
            columnDefinition = "TEXT"
    )
    private String cardNumber;

    public StudentIdCard() {
    }

    public StudentIdCard(String cardNumber) {
        this.cardNumber = cardNumber;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCardNumber() {
        return cardNumber;
    }

    public void setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
    }
}
