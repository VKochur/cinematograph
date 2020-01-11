package mediasoft.education.kvv.cinematograph.service;

import mediasoft.education.kvv.cinematograph.dto.TagDto;

import java.util.List;

public interface TagService {

    TagDto create(TagDto dataForCreate);

    TagDto getById(Long id);

    TagDto deleteById(Long id);

    List<TagDto> findAll();
}
