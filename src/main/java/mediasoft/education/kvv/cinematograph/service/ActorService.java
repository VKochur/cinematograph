package mediasoft.education.kvv.cinematograph.service;

import mediasoft.education.kvv.cinematograph.entity.Actor;

import java.util.List;

public interface ActorService {

    Actor create(Actor actor);

    List<Actor> findAll();
}
