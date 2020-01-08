package mediasoft.education.kvv.cinematograph.dao.impl;

import mediasoft.education.kvv.cinematograph.dao.ActorDao;
import mediasoft.education.kvv.cinematograph.entity.Actor;

import javax.ejb.Stateless;

@Stateless
public class ActorDaoImpl extends BasicDaoImpl<Actor> implements ActorDao {

    public ActorDaoImpl() {
        super(Actor.class);
    }
}
