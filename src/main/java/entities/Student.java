package entities;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@XmlRootElement(name = "Student")
public class Student {

    private Long id;
    private String firstName;
    private String lastName;
    private String email;
    //One to one relationship
    private University university;
    //One to many relationship
    private List<Degree> degrees;
    private List<Subject> subjects;

    public Student(){};

    public Student(Long id, String firstName, String lastName, String email) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
    }

    public University getUniversity() {
        return university;
    }
    public void setUniversity(University university) {
        this.university = university;
    }

    public List<Degree> getDegrees() {
        return degrees;
    }
    public void setDegrees(List<Degree> degrees) {
        this.degrees = degrees;
    }

    public List<Subject> getSubjects() {
        return subjects;
    }
    public void setSubjects(List<Subject> subjects) {
        this.subjects = subjects;
    }

    @XmlAttribute
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }

    @XmlElement
    public String getFirstName() {
        return firstName;
    }
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    @XmlElement
    public String getLastName() {
        return lastName;
    }
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    @XmlElement
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return "Student --> " + "\n" +
                "Id: " + id + "\n" +
                "Name: " + firstName + "\n" +
                "Last name: " + lastName + "\n" +
                "E-mail: " + email + ".";
    }
}
