package mediasoft.education.kvv.cinematograph.service.impl;

import mediasoft.education.kvv.cinematograph.dao.ActorDao;
import mediasoft.education.kvv.cinematograph.entity.Actor;
import mediasoft.education.kvv.cinematograph.service.ActorService;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.transaction.Transactional;
import java.util.List;

@Stateless
public class ActorServiceImpl implements ActorService {

    @Inject
    private ActorDao actorDao;

    @Override
    public Actor create(Actor actor) {
        return actorDao.create(actor);
    }

    @Transactional
    @Override
    public List<Actor> findAll() {
        List<Actor> list = actorDao.getList();
        list.forEach(actor -> actor.getMovies().size());
        return list;
    }
}
