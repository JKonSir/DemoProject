package org.mycompany.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.io.Serializable;
import java.math.BigInteger;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import lombok.AccessLevel;
import lombok.Setter;

@lombok.Getter
@Setter
@Entity(name = "STUDENT_ENTITY")
@Table(name = "students")
@NamedQuery(name = "findAllStudent",
        query = "SELECT s FROM STUDENT_ENTITY s ORDER BY s.firstName")
public class Student implements GenericEntity, Serializable
{
    @Id
    @Column(name = "id", nullable = false, unique = true)
    @SequenceGenerator(name = "generate_id", sequenceName = "generate_id", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "generate_id")
    private BigInteger id;

    @Column(name = "first_name", nullable = false)
    private String firstName;

    @Column(name = "last_name", nullable = false)
    private String lastName;

    @Column(name = "age", nullable = false)
    private Integer age;

    @Column(name = "group_number", nullable = false)
    private Integer groupNumber;

    @Setter(value = AccessLevel.NONE)
    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "group_number",
            referencedColumnName = "group_number",
            insertable = false,
            updatable = false)
    private Group group;

    public Student()
    {
    }

    public Student(String firstName, String lastName, Integer age, Integer groupNumber)
    {
        this.firstName = firstName;
        this.lastName = lastName;
        this.age = age;
        this.groupNumber = groupNumber;
    }

    @Override
    public String toString()
    {
        return "Student{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", age=" + age + '\'' +
                ", group=" + group + '\'' +
                '}';
    }

}
