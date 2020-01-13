package mediasoft.education.kvv.cinematograph.service;

import mediasoft.education.kvv.cinematograph.dto.CommentDto;

import java.util.List;

public interface CommentService {

    CommentDto create(CommentDto dataForCreate);

    CommentDto getById(Long id);

    CommentDto deleteById(Long id);

    List<CommentDto> findAll();

    CommentDto updateInfo(Long id, CommentDto newData);

    /**
     * add comment to current comment
     * @param idExistedComment id parent comment
     * @param commentDtoForCreation data for creation new child comment
     * @return created comment's dto
     */
    CommentDto addComment(Long  idExistedComment, CommentDto commentDtoForCreation);
}
