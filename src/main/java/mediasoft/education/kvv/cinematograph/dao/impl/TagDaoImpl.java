package mediasoft.education.kvv.cinematograph.dao.impl;

import mediasoft.education.kvv.cinematograph.dao.TagDao;
import mediasoft.education.kvv.cinematograph.entity.Tag;

import javax.ejb.Stateless;

@Stateless
public class TagDaoImpl extends BasicDaoImpl<Tag> implements TagDao {
    public TagDaoImpl() {
        super(Tag.class);
    }
}
