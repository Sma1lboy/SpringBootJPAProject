package me.jacksonchen.SpringBootJPADemo.Student;

import javax.persistence.*;

@Table(name = "enrolment")
@Entity(name = "Enrolment")
public class Enrolment {
    @EmbeddedId
    private EnrolmentId enrolmentId;
    @ManyToOne
    @MapsId("studentId")
    @JoinColumn(
            name = "student_id",
            foreignKey =  @ForeignKey (
                name = "enrolment_student_id_fk"
            )
    )
    private Student student;
    @ManyToOne
    @MapsId("courseId")
    @JoinColumn(name = "course_id",
            foreignKey =  @ForeignKey (
                    name = "enrolment_course_fk"
            ))
    private Course course;

    public Enrolment() {
    }

    public Enrolment(EnrolmentId enrolmentId, Student student, Course course) {
        this.enrolmentId = enrolmentId;
        this.student = student;
        this.course = course;
    }

    public Enrolment(Student student, Course course) {
        this.student = student;
        this.course = course;
    }

    public EnrolmentId getEnrolmentId() {
        return enrolmentId;
    }

    public void setEnrolmentId(EnrolmentId enrolmentId) {
        this.enrolmentId = enrolmentId;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }
}
