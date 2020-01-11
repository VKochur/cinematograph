package mediasoft.education.kvv.cinematograph.service.impl;

import mediasoft.education.kvv.cinematograph.dao.BasicDao;
import mediasoft.education.kvv.cinematograph.dto.mapper.DtoMapper;
import mediasoft.education.kvv.cinematograph.service.BasicService;

import javax.transaction.Transactional;
import java.util.LinkedList;
import java.util.List;
import java.util.NoSuchElementException;

/**
 * implementation creation. search and delete for entity by
 * @param <E> entity
 * @param <D> dto
 *
 */
@Transactional
public class BasicServiceImpl<E, D> implements BasicService<D> {

    //for rules translation to dto
    private DtoMapper<E,D> mapperDto;
    private BasicDao<E> dao;
    private Class<E> enityClass;

    public BasicServiceImpl(Class<E> entityClass, DtoMapper<E,D> mapperDto, BasicDao<E> dao) {
        this.enityClass = entityClass;
        this.mapperDto = mapperDto;
        this.dao = dao;
    }

    @Override
    public D create(D dataForCreate) {
        E dataAboutEntityForCreation = mapperDto.getEntityForCreation(dataForCreate);
        E createdEntity = dao.create(dataAboutEntityForCreation);
        return mapperDto.getDto(createdEntity);
    }

    @Override
    public D getById(Long id) {
        E byId = dao.getById(id);
        if (byId == null) {
            throw new NoSuchElementException("not found " + enityClass.getClass().getName() + " by id = " + id);
        } else {
            return mapperDto.getDto(byId);
        }
    }

    @Override
    public D deleteById(Long id) {
        E byId = dao.getById(id);
        if (byId == null) {
            throw new NoSuchElementException("not found " + enityClass.getClass().getName() + " by id = " + id);
        } else {
            dao.delete(byId);
            return mapperDto.getDto(byId);
        }
    }

    @Override
    public List<D> findAll() {
        List<D> dtos = new LinkedList<>();
        List<E> entities = dao.getList();
        entities.forEach(entity -> dtos.add(mapperDto.getDto(entity)));
        return dtos;
    }
}
