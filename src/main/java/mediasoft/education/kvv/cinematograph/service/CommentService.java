package mediasoft.education.kvv.cinematograph.service;

import mediasoft.education.kvv.cinematograph.dto.CommentDto;

import java.util.List;

public interface CommentService {

    CommentDto create(CommentDto dataForCreate);

    CommentDto getById(Long id);

    CommentDto deleteById(Long id);

    List<CommentDto> findAll();

    CommentDto updateInfo(Long id, CommentDto newData);
}
