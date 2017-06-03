package org.mycompany.repositories;

import java.math.BigInteger;
import java.util.List;

import org.mycompany.model.GenericEntity;

public interface EntityRepository<T extends GenericEntity>
{
    T save(T entity);

    T update(BigInteger id, T entity);

    T findById(BigInteger id);

    T findById(BigInteger id, boolean prefetch);

    List<T> findByIds(List<BigInteger> ids);

    List<T> findAll();

    void delete(BigInteger id);

}
