package mediasoft.education.kvv.cinematograph.service.impl;

import mediasoft.education.kvv.cinematograph.dao.BasicDao;
import mediasoft.education.kvv.cinematograph.dto.CommentDto;
import mediasoft.education.kvv.cinematograph.dto.mapper.DtoMapper;
import mediasoft.education.kvv.cinematograph.entity.Comment;
import mediasoft.education.kvv.cinematograph.service.CommentService;

import javax.annotation.PostConstruct;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.List;
import java.util.NoSuchElementException;

@Transactional
@Stateless
public class CommentServiceImpl implements CommentService {

    private DtoMapper<Comment, CommentDto> commentDtoMapper;

    private BasicDao<Comment> commentDao;

    private BasicServiceImpl<Comment, CommentDto> basicService;

    @Inject
    public void setCommentDtoMapper(DtoMapper<Comment, CommentDto> commentDtoMapper) {
        this.commentDtoMapper = commentDtoMapper;
    }

    @Inject
    public void setCommentDao(BasicDao<Comment> commentDao) {
        this.commentDao = commentDao;
    }

    @PostConstruct
    public void initBasicService() {
        basicService = new BasicServiceImpl<>(Comment.class, commentDtoMapper, commentDao);
    }

    /**
     * implements doesn't use basicService, because need to set time
     *
     * @param dataForCreate
     * @return
     */
    @Override
    public CommentDto create(CommentDto dataForCreate) {
        Comment dataForCreation = commentDtoMapper.getEntityForCreation(dataForCreate);
        Comment createdComment = commentDao.create(dataForCreation);
        //set time
        createdComment.setDateTime(LocalDateTime.now());
        return commentDtoMapper.getDto(createdComment);
    }

    @Override
    public CommentDto getById(Long id) {
        return basicService.getById(id);
    }

    @Override
    public CommentDto deleteById(Long id) {
        return basicService.deleteById(id);
    }

    @Override
    public List<CommentDto> findAll() {
        return basicService.findAll();
    }

    @Override
    public CommentDto updateInfo(Long id, CommentDto newData) {
        Comment byId = commentDao.getById(id);
        if (byId == null) {
            throw new NoSuchElementException("not found comment by id = " + id);
        } else {
            byId.setText(newData.getText());
            byId.setDateTime(LocalDateTime.now());
            return commentDtoMapper.getDto(byId);
        }
    }

    @Override
    public CommentDto addComment(Long idParentComment, CommentDto commentDtoForCreation) {
        Comment byId = commentDao.getById(idParentComment);
        if (byId == null) {
            throw new NoSuchElementException("not found comment by id = " + idParentComment);
        } else {
            Comment entityForCreation = commentDtoMapper.getEntityForCreation(commentDtoForCreation);
            Comment createdComment = commentDao.create(entityForCreation);
            byId.addChild(createdComment);
            return commentDtoMapper.getDto(createdComment);
        }
    }
}
