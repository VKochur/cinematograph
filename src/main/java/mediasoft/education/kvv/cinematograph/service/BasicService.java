package mediasoft.education.kvv.cinematograph.service;

import java.util.List;
import java.util.NoSuchElementException;

/**
 * Basic operation: creation, search all and by id, delete by id
 * @param <D> dto for entity
 */
public interface BasicService<D> {

    /**
     * create entity in db by specific data
     * @param dataForCreate data for new entity
     * @return created entity's dto
     */
    D create(D dataForCreate);

    /**
     * get dto from db by id
     * @param id
     * @return
     * @throws NoSuchElementException if not found
     */
    D getById(Long id) throws NoSuchElementException;

    /**
     * delete entity in db by id
     * @param id
     * @return deleted entity's dto
     * @throws NoSuchElementException if not found
     */
    D deleteById(Long id) throws NoSuchElementException;

    /**
     * list dto for all entity's values from  db
     * @return
     */
    List<D> findAll();
}
