package mediasoft.education.kvv.cinematograph.dao;

import java.util.List;

public interface BasicDao<T> {
    /**
     * method for add entity
     *
     * @param entity - new entity for creation
     * @return created entity
     */
    T create(T entity);

    /**
     * method for getting entity by specific id
     *
     * @param id - entity's id
     * @return entity by id
     */
    T getById(long id);

    /**
     * method for getting all data from entity
     *
     * @return data list from entity
     */
    List<T> getList();

    /**
     * method for update entity
     *
     * @param entity - updating entity
     * @return updated entity
     */
    T update(T entity);

    /**
     * method for delete entity
     *
     * @param entity deleted
     */
    T delete(T entity);

    /**
     * get list entity
     *
     * use query like:
     * ----
     * select entity
     * where fieldValue like fieldName
     * order by forSortFieldName
     * -----
     *
     * @param fieldName
     * @param fieldValue
     * @param caseIgnore ignore case in "where"
     * @param forSortFieldName
     * @param asc for sort
     * @return
     */
    List<T> findWhereFieldLikeAsSpecificAndOrderByOther(String fieldName, String fieldValue, boolean caseIgnore, String forSortFieldName, boolean asc);
}
