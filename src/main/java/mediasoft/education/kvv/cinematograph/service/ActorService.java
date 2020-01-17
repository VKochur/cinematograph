package mediasoft.education.kvv.cinematograph.service;

import mediasoft.education.kvv.cinematograph.dto.ActorDto;

import java.util.List;

public interface ActorService {

    /**
     * create new actor
     * @param actor
     * @return created actor
     */
    ActorDto create(ActorDto actor);

    /**
     * update info
     * @param existedActorId
     * @param newData
     * @return updated actor
     */
    ActorDto updateInfo(Long existedActorId, ActorDto newData);

    /**
     * get actor from db by id
     * @param id
     * @return
     */
    ActorDto getById(Long id);

    /**
     * delete actor in db by id
     * @param id
     * @return deleted actor
     */
    ActorDto deleteById(Long id);

    /**
     * all actor from db
     * @return
     */
    List<ActorDto> findAll();

    /**
     * search actors, that have name like specific ignore case: %actorName%
     *
     * @param actorName
     * @return list ordered by name
     */
    List<ActorDto> getBySimilarName(String actorName);
}
