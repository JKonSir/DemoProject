package org.mycompany.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.io.Serializable;
import java.math.BigInteger;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@lombok.Getter
@lombok.Setter
@Entity(name = "GROUP_ENTITY")
@Table(name = "groups")
@NamedQuery(name = "findAllGroups",
        query = "SELECT g FROM GROUP_ENTITY g ORDER BY g.groupNumber")
public class Group implements GenericEntity, Serializable
{
    @Id
    @Column(name = "id", nullable = false, unique = true)
    @SequenceGenerator(name = "generate_id", sequenceName = "generate_id", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "generate_id")
    private BigInteger id;

    @Column(name = "group_number", nullable = false, unique = true)
    private Integer groupNumber;

    @Column(name = "faculty_name", nullable = false)
    private String facultyName;

    @JsonIgnore
    @OneToMany(mappedBy = "group", fetch = FetchType.LAZY)
    private List<Student> students;

    public Group()
    {
    }

    public Group(Integer groupNumber, String facultyName)
    {
        this.groupNumber = groupNumber;
        this.facultyName = facultyName;
    }

    @Override
    public String toString()
    {
        return "Group{" +
                "id=" + id +
                ", groupNumber=" + groupNumber +
                ", facultyName='" + facultyName + '\'' +
                ", students" + students +
                '}';
    }

}
