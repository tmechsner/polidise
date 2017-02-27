package org.polidise.domain.service;

import java.util.List;
import java.util.function.Predicate;

/**
 * Basis with general methods for all other services.
 */
public interface AbstactService<D , E> {
    /**
     * @return Retrieves all domain objects.
     */
    List<D> getAll();

    /**
     * @return Retrieves all entities.
     */
    List<E> getAllEntities();

    /**
     * @param ids Ids of domain objects to return.
     * @return List of requested domain objects.
     */
    List<D> findAll(Iterable<Integer> ids);

    /**
     * Finds exactly one domain object by id.
     *
     * @param id The id to find.
     * @return Object with the id.
     */
    D findOne(Integer id);

    /**
     * Retrieves a filtered list of domain objects.
     *
     * @param filter Filter to apply on the whole dataset to retrieve the admired output.
     * @return Filtered subset of all domain objects.
     */
    List<D> getFiltered(Predicate<D> filter);

    /**
     * Deletes an entity from the db.
     *
     * @param id Id of the entity to delete.
     * @return Success state. True on success, false on failure or if the entity did not exist.
     */
    boolean delete(Integer id);
}
