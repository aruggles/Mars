/**
 * Copyright 2011 Adam Ruggles.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package net.adamruggles.mars.dao;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;

/**
 * Abstract Implementation of the Entity DAO interface.
 * @author Adam
 * @version $Id$
 *
 * Created on Sep 2, 2011 at 9:44:29 PM
 * @param <T> Model object the entity data access object operates on.
 */
public abstract class AbstractEntityJpaDAO<T> implements EntityDAO<T> {
    /**
     * The entity class represented by the generic variable T.
     */
    private final Class<T> entityClass;
    /**
     * The entity manager.
     */
    @PersistenceContext
    private EntityManager em;
    /**
     * Default Constructor.
     * Sets the entity class.
     */
    @SuppressWarnings("unchecked")
    public AbstractEntityJpaDAO() {
        this.entityClass = (Class<T>) ((ParameterizedType) getClass()
                .getGenericSuperclass()).getActualTypeArguments()[0];
    }
    /**
     * {@inheritDoc}
     */
    @Override @SuppressWarnings("unchecked")
    public List<T> findAll() {
        final Query q = em.createQuery("SELECT e FROM " + entityClass.getSimpleName() + " e");
        return q.getResultList();
    }
    /**
     * {@inheritDoc}
     */
    @Override @SuppressWarnings("unchecked")
    public List<T> findAll(final int index, final int max) {
        final Query q = em.createQuery("SELECT e FROM " + entityClass.getSimpleName() + " e");
        q.setFirstResult(index);
        q.setMaxResults(max);
        return q.getResultList();
    }
    /**
     * {@inheritDoc}
     */
    @Override
    public T findById(final Serializable id) {
        try {
            return em.find(entityClass, id);
        } catch (final NoResultException ex) {
            return null;
        }
    }
    /**
     *  {@inheritDoc}
     */
    @Override
    public Long getCount() {
        final CriteriaBuilder qb = em.getCriteriaBuilder();
        final CriteriaQuery<Long> cq = qb.createQuery(Long.class);
        cq.select(qb.count(cq.from(entityClass)));
        return em.createQuery(cq).getSingleResult();
    }
    /**
     * Returns em.
     * @return the em.
     */
    public EntityManager getEm() {
        return em;
    }
    /**
     * Returns entityClass.
     * @return the entityClass.
     */
    public Class<T> getEntityClass() {
        return entityClass;
    }
    /**
     *  {@inheritDoc}
     */
    @Override
    public void merge(final T entity) {
        em.merge(entity);
    }
    /**
     * {@inheritDoc}
     */
    @Override
    public void persist(final T entity) {
        em.persist(entity);
    }
    /**
     *  {@inheritDoc}
     */
    @Override
    public void remove(final T entity) {
        em.remove(entity);
    }
}
