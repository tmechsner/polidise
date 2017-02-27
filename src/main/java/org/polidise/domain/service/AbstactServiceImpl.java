package org.polidise.domain.service;

import org.springframework.dao.InvalidDataAccessApiUsageException;
import org.springframework.data.repository.CrudRepository;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

/**
 * @param <D> Domain Object Type
 * @param <R> Repository Object Type
 * @param <E> Entity Object Type
 */
public abstract class AbstactServiceImpl<D, R extends CrudRepository<E, Integer>, E>
        implements AbstactService<D , E> {

    /**
     * Repository for database interaction.
     */
    protected R repository;

    public AbstactServiceImpl(R repository) {
        this.repository = repository;
    }

    /**
     * Find one entity by primary key and create a domain object of it.
     *
     * @param id The id of the object to find.
     * @return Domain object created of the fetched entity.
     */
    public D findOne(Integer id) {
        E entity;
        try {
            entity = repository.findOne(id);
        } catch (IllegalArgumentException | InvalidDataAccessApiUsageException ex) {
            return null;
        }
        if (entity != null) {
            D domainObject = init(entity);
            if (domainObject != null) {
                return domainObject;
            }
        }
        return null;
    }

    /**
     * Find entities for the given ids and create domain objects for them.
     *
     * @param ids Find entities for these ids.
     * @return A list of domain objects for all requested entities.
     */
    public List<D> findAll(Iterable<Integer> ids) {
        List<D> ret = new LinkedList<>();
        if (ids != null) {
            repository.findAll(ids).forEach(entity -> {
                ret.add(init(entity));
            });
        }
        return ret;
    }

    /**
     * Find all entities and create domain objects for them.
     *
     * @return A list of domain objects for all existing entities.
     */
    public List<D> getAll() {
        List<D> result = new ArrayList<>();
        Iterable<E> all = repository.findAll();
        for (E entity : all) {
            result.add(init(entity));
        }
        return result;
    }

    /**
     * Find all entities
     *
     * @return A list of entities for all existing entities.
     */
    public List<E> getAllEntities() {
        List<E> result = new ArrayList<>();
        Iterable<E> all = repository.findAll();
        for (E entity : all) {
            result.add(entity);
        }
        return result;
    }


    /**
     * Find all entities that match the given filter and create domain objects from those.
     *
     * @param filter A filter predicate applied to each existing entity.
     * @return A list of all domain objects matching the given filter predicate.
     */
    public List<D> getFiltered(Predicate<D> filter) {
        return getAll().parallelStream()
            .filter(filter)
            .collect(Collectors.toList());
    }

    /**
     * Delete one entity uniquely identified by it's id.
     *
     * @param id The id of the entity to delete.
     * @return True on success, false otherwise.
     */
    public boolean delete(Integer id) {
        E toDelete;
        try {
            toDelete = repository.findOne(id);
        } catch (IllegalArgumentException | InvalidDataAccessApiUsageException ex) {
            return false;
        }
        if (toDelete != null) {
            repository.delete(toDelete);
            return true;
        }
        return false;
    }



    /**
     * Create a domain object from an entity.
     *
     * @param entity The entity to create a domain object from.
     * @return The newly created domain object.
     */
    protected abstract D init(E entity);

}
