package org.mycompany.repositories;

import org.mycompany.model.Student;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigInteger;
import java.util.List;
import java.util.stream.Collectors;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

@Repository
public class StudentRepository implements EntityRepository<Student>
{
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    @Transactional
    public Student save(Student student)
    {
        entityManager.persist(student);

        return student;
    }

    @Override
    @Transactional
    public Student update(BigInteger studentId, Student student)
    {
        Student result = entityManager.find(Student.class, studentId);
        result.setFirstName(student.getFirstName());
        result.setLastName(student.getLastName());
        result.setAge(student.getAge());
        result.setGroupNumber(student.getGroupNumber());
        return result;
    }

    @Override
    @Transactional
    public Student findById(BigInteger studentId)
    {
        return findById(studentId, true);
    }

    @Override
    @Transactional
    public Student findById(BigInteger studentId, boolean prefetch)
    {
        return entityManager.find(Student.class, studentId);
    }

    @Override
    @Transactional
    public List<Student> findByIds(List<BigInteger> studentIds)
    {
        return studentIds.stream()
                .map(this::findById)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public List<Student> findAll()
    {
        TypedQuery<Student> studentTypedQuery =
                entityManager.createNamedQuery("findAllStudent", Student.class);

        return studentTypedQuery.getResultList();
    }

    @Override
    @Transactional
    public void delete(BigInteger studentId) throws RuntimeException
    {
        Student student = entityManager.find(Student.class, studentId);
        if (student == null)
        {
            throw new RuntimeException("This object doesn't exist");
        }
        entityManager.remove(student);
    }

}
