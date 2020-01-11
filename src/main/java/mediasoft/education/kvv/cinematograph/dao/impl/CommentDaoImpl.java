package mediasoft.education.kvv.cinematograph.dao.impl;

import mediasoft.education.kvv.cinematograph.dao.CommentDao;
import mediasoft.education.kvv.cinematograph.entity.Comment;

import javax.ejb.Stateless;

@Stateless
public class CommentDaoImpl extends BasicDaoImpl<Comment> implements CommentDao {
    public CommentDaoImpl() {
        super(Comment.class);
    }
}
