package org.mycompany.repositories;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigInteger;
import java.util.List;
import java.util.stream.Collectors;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.mycompany.model.Group;

@Repository
public class GroupRepository implements EntityRepository<Group>
{
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    @Transactional
    public Group save(Group group)
    {
        entityManager.persist(group);

        return group;
    }

    @Override
    @Transactional
    public Group update(BigInteger groupId, Group group)
    {
        Group result = entityManager.find(Group.class, groupId);
        result.setGroupNumber(group.getGroupNumber());
        result.setFacultyName(group.getFacultyName());
        return result;
    }

    @Override
    @Transactional
    public Group findById(BigInteger groupId)
    {
        return findById(groupId, false);
    }

    @Override
    @Transactional
    public Group findById(BigInteger groupId, boolean prefetch)
    {
        Group group = entityManager.find(Group.class, groupId);
        if (prefetch)
        {
            group.getStudents().size();
        }

        return group;
    }

    @Override
    @Transactional
    public List<Group> findByIds(List<BigInteger> groupIds)
    {
        return groupIds.stream()
                .map(this::findById)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public List<Group> findAll()
    {
        TypedQuery<Group> groupTypedQuery =
                entityManager.createNamedQuery("findAllGroups", Group.class);

        return groupTypedQuery.getResultList();
    }

    @Override
    @Transactional
    public void delete(BigInteger groupId)
    {
        Group group = entityManager.find(Group.class, groupId);
        if (group == null)
        {
            throw new RuntimeException("This object doesn't exist");
        }
        entityManager.remove(group);
    }

}
