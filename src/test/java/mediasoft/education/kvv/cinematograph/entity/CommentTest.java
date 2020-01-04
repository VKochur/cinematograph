package mediasoft.education.kvv.cinematograph.entity;

import org.junit.Test;

import static org.junit.Assert.*;

public class CommentTest {

    @Test
    public void updateMovie() {
        Comment comment1 = new Comment();
        Movie movie1 = new Movie();
        Movie movie2 = new Movie();
        comment1.updateMovie(movie1);
        assertTrue(movie1.getComments().contains(comment1));
        movie2.addComment(comment1);
        assertFalse(movie1.getComments().contains(comment1));
        assertEquals(movie2, comment1.getMovie());
    }

    @Test
    public void removeMovie() {
        Comment comment = new Comment();
        Movie movie = new Movie();
        movie.addComment(comment);
        comment.removeMovie();
        assertNull(comment.getMovie());
        assertTrue(movie.getComments().isEmpty());
    }

    @Test
    public void addChild() {
        Comment parent1 = new Comment();
        Comment child1 = new Comment();
        Comment child2 = new Comment();
        parent1.addChild(child1);
        parent1.addChild(child2);
        assertEquals(2, parent1.getChildren().size());
        assertEquals(parent1, child1.getParent());
        assertEquals(parent1, child2.getParent());
        Comment parent2 = new Comment();
        parent2.addChild(child1);
        assertEquals(1, parent1.getChildren().size());
        assertEquals(1, parent2.getChildren().size());
        assertFalse(parent1.getChildren().contains(child1));
        assertTrue(parent2.getChildren().contains(child1));
        assertEquals(parent2, child1.getParent());
    }

    @Test
    public void updateParent() {
        Comment parent1 = new Comment();
        Comment parent2 = new Comment();
        Comment child = new Comment();
        child.updateParent(parent1);
        assertTrue(parent1.getChildren().contains(child));
        child.updateParent(parent2);
        assertFalse(parent1.getChildren().contains(child));
        assertTrue(parent2.getChildren().contains(child));
        assertEquals(parent2, child.getParent());
    }

    @Test
    public void removeChild() {
        Comment parent = new Comment();
        Comment child = new Comment();
        parent.addChild(child);
        parent.addChild(child);
        assertEquals(parent, child.getParent());
        parent.removeChild(child);
        assertTrue(parent.getChildren().isEmpty());
        assertNull(child.getParent());
    }

    @Test
    public void removeParent() {
        Comment parent = new Comment();
        Comment child = new Comment();
        child.updateParent(parent);
        assertTrue(parent.getChildren().contains(child));
        child.removeParent();
        assertFalse(parent.getChildren().contains(child));
        assertNull(child.getParent());
    }

    @Test
    public void updateOwner() {
        Comment comment = new Comment();
        User user1 = new User();
        User user2 = new User();
        user1.addComment(comment);
        assertEquals(user1, comment.getOwner());
        comment.updateOwner(user2);
        assertTrue(user1.getComments().isEmpty());
        assertTrue(user2.getComments().contains(comment));
    }

    @Test
    public void removeOwner() {
        Comment comment = new Comment();
        User user = new User();
        user.addComment(comment);
        comment.removeOwner();
        assertTrue(user.getComments().isEmpty());
    }
}