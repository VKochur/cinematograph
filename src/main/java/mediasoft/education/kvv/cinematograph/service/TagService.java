package mediasoft.education.kvv.cinematograph.service;

import mediasoft.education.kvv.cinematograph.dto.TagDto;

import java.util.List;

public interface TagService {

    TagDto create(TagDto dataForCreate);

    TagDto getById(Long id);

    TagDto deleteById(Long id);

    List<TagDto> findAll();

    List<TagDto> findAllAndOrderByName(boolean asc);

    /**
     * search tags, that have name like specific ignore case: %tagName%
     *
     * @param tagName
     * @return list ordered by name
     */
    List<TagDto> findWithNameLikeAs(String tagName);

    TagDto getByName(String name);
}
