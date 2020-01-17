package mediasoft.education.kvv.cinematograph.servlet.util;

import mediasoft.education.kvv.cinematograph.dto.CommentDto;
import mediasoft.education.kvv.cinematograph.service.CommentService;

import javax.ejb.Stateless;
import javax.inject.Inject;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

@Stateless
public class Util {

    @Inject
    private CommentService commentService;

    public String defineHtmlCodeForCommentsView(List<Long> commentsIds) {
        int MAX_LEVEL_FOR_INNER_COMMENTS = 5;
        return defineHtmlCodeForCommentsView(commentsIds, MAX_LEVEL_FOR_INNER_COMMENTS, "");
    }

    /**
     * Generate html code for show comments
     *
     * @param commentsIds
     * @param deepMax     max level for show inner comments (in order to avoid loops even if it exists)
     * @return
     */
    private String defineHtmlCodeForCommentsView(List<Long> commentsIds, int deepMax, String startIndent) {
        List<CommentDto> commentDtosOrderedByDate = new LinkedList<>();
        for (Long commentsId : commentsIds) {
            commentDtosOrderedByDate.add(commentService.getById(commentsId));
        }
        Collections.sort(commentDtosOrderedByDate, (o1, o2) -> {
            //todo такое переопределение не гарантирует выполнение транзитивности,
            //A = null & B = null => A=B, что на самом деле не факт.
            //в данном моменте подходит, т.к. null даты не ожидаются, но стоит пересмотреть
            if (o1 == null) {if  (o2 == null) return 0;}
            if (o1.getDateTime() == null || o2.getDateTime() == null) return 0;
            return o1.getDateTime().compareTo(o2.getDateTime());
        });

        String indent = "<span>--------</span>";
        String lineBreak = "<br>";
        StringBuffer code = new StringBuffer();
        for (CommentDto commentDto : commentDtosOrderedByDate) {
            code.append(startIndent).append(indent).append(commentDto.getText()).append("(").append(commentDto.getDateTime()).append(")").append(lineBreak);

            List<CommentDto> children = commentDto.getChildren();
            List<Long> childrenIds = new LinkedList<>();
            children.forEach(childCommentDto -> childrenIds.add(childCommentDto.getId()));

            if (deepMax > 0) {
                code.append(defineHtmlCodeForCommentsView(childrenIds, deepMax - 1, new StringBuilder(startIndent).append(indent).toString()));
            }
        }
        return code.toString();
    }


}
