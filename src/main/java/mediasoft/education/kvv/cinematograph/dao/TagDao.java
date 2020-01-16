package mediasoft.education.kvv.cinematograph.dao;

import mediasoft.education.kvv.cinematograph.entity.Tag;

import java.util.List;

public interface TagDao extends BasicDao<Tag> {

    List<Tag> findByIdsAndOrderByName(List<Long> existedTagIds);

    List<Tag> findAllAndOrderByName(Boolean asc);

    Tag getByName(String tagName);
}
