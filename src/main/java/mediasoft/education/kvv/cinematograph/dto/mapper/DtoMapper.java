package mediasoft.education.kvv.cinematograph.dto.mapper;

/**
 * mapper between entity and it's dto
 * @param <E> entity
 * @param <D> dto
 */
public interface DtoMapper<E, D> {

    /**
     * convert entity to dto
     * @param entity
     * @return
     */
    D getDto(E entity);

    /**
     * define how use data for creation new entity
     * method are used in BasicServiceImpl for creation new entity
     * @param dataForCreateNewEntity
     * @return
     */
    E getEntityForCreation(D dataForCreateNewEntity);
}
