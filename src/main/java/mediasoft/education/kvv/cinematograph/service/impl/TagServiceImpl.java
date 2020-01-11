package mediasoft.education.kvv.cinematograph.service.impl;

import mediasoft.education.kvv.cinematograph.dao.BasicDao;
import mediasoft.education.kvv.cinematograph.dto.TagDto;
import mediasoft.education.kvv.cinematograph.dto.mapper.DtoMapper;
import mediasoft.education.kvv.cinematograph.entity.Tag;
import mediasoft.education.kvv.cinematograph.service.TagService;

import javax.annotation.PostConstruct;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.transaction.Transactional;
import java.util.List;

@Transactional
@Stateless
public class TagServiceImpl implements TagService {

    private DtoMapper<Tag, TagDto> tagDtoMapper;

    private BasicDao<Tag> tagDao;

    private BasicServiceImpl<Tag, TagDto> basicService;

    @Inject
    public void setTagDtoMapper(DtoMapper<Tag, TagDto> tagDtoMapper) {
        this.tagDtoMapper = tagDtoMapper;
    }

    @Inject
    public void setTagDao(BasicDao<Tag> tagDao) {
        this.tagDao = tagDao;
    }

    @PostConstruct
    public void initBasicService() {
        basicService = new BasicServiceImpl<>(Tag.class, tagDtoMapper, tagDao);
    }

    @Override
    public TagDto create(TagDto dataForCreate) {
        return basicService.create(dataForCreate);
    }

    @Override
    public TagDto getById(Long id) {
        return basicService.getById(id);
    }

    @Override
    public TagDto deleteById(Long id) {
        return basicService.deleteById(id);
    }

    @Override
    public List<TagDto> findAll() {
        return basicService.findAll();
    }

}
