package mediasoft.education.kvv.cinematograph.service.impl;

import mediasoft.education.kvv.cinematograph.dao.ActorDao;
import mediasoft.education.kvv.cinematograph.dto.ActorDto;
import mediasoft.education.kvv.cinematograph.dto.mapper.ActorDtoMapper;
import mediasoft.education.kvv.cinematograph.entity.Actor;
import mediasoft.education.kvv.cinematograph.service.ActorService;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.transaction.Transactional;
import java.util.LinkedList;
import java.util.List;
import java.util.NoSuchElementException;

@Transactional
@Stateless
public class ActorServiceImpl implements ActorService {

    private ActorDao actorDao;

    private ActorDtoMapper actorDtoMapper;

    @Inject
    public void setActorDao(ActorDao actorDao) {
        this.actorDao = actorDao;
    }

    @Inject
    public void setActorDtoMapper(ActorDtoMapper actorDtoMapper) {
        this.actorDtoMapper = actorDtoMapper;
    }

    @Override
    public ActorDto create(ActorDto actorDto) {
        Actor forCreate = new Actor();
        forCreate.setName(actorDto.getName());
        forCreate.setInfoUrl(actorDto.getInfoUrl());
        Actor createdActor = actorDao.create(forCreate);
        return actorDtoMapper.getDto(createdActor);
    }

    @Override
    public ActorDto updateInfo(Long existedActorId, ActorDto newData) {
        Actor byId = actorDao.getById(existedActorId);
        if (byId == null) {
            throw new NoSuchElementException("not found actor by id = " + existedActorId);
        } else {
            byId.setName(newData.getName());
            byId.setInfoUrl(newData.getInfoUrl());
            return actorDtoMapper.getDto(byId);
        }
    }

    @Override
    public ActorDto getById(Long id) {
        Actor byId = actorDao.getById(id);
        if (byId == null) {
            throw new NoSuchElementException("not found actor by id = " + id);
        } else {
            return actorDtoMapper.getDto(byId);
        }
    }

    @Override
    public ActorDto deleteById(Long id) {
        Actor byId = actorDao.getById(id);
        if (byId == null) {
            throw new NoSuchElementException("not found actor by id = " + id);
        } else {
            actorDao.delete(byId);
            return actorDtoMapper.getDto(byId);
        }
    }

    @Override
    public List<ActorDto> findAll() {
        List<ActorDto> actorDtos = new LinkedList<>();
        List<Actor> list = actorDao.getList();
        list.forEach(actor -> actorDtos.add(actorDtoMapper.getDto(actor)));
        return actorDtos;
    }

    @Override
    public List<ActorDto> getBySimilarName(String actorName) {
        String FIELD_NAME = "name";
        List<Actor> actors = actorDao.findWhereFieldLikeAsSpecificAndOrderByOther(
                FIELD_NAME,
                "%"+actorName+"%",
                true,
                FIELD_NAME,
                true);
        List<ActorDto> actorDtos = new LinkedList<>();
        actors.forEach(actor -> actorDtos.add(actorDtoMapper.getDto(actor)));
        return actorDtos;
    }
}
