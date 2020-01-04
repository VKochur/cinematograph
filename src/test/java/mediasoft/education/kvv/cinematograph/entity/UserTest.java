package mediasoft.education.kvv.cinematograph.entity;

import org.junit.Test;

import static org.junit.Assert.*;

public class UserTest {

    @Test
    public void testAddAndRemoveMovieAsLoadedByUser() {
        User user = new User();
        Movie movie1 = new Movie();
        Movie movie2 = new Movie();
        user.addMovieAsLoadedByUser(movie1);
        user.addMovieAsLoadedByUser(movie2);
        assertEquals(user, movie1.getOwner());
        assertEquals(user, movie2.getOwner());
        user.removeMovieAsLoadedByUser(movie1);
        assertNull(movie1.getOwner());
        movie2.removeOwner();
        assertTrue(user.getAddedMoviesByUser().isEmpty());
    }

    public void testAddAndRemoveComment() {
        User user = new User();
        Comment comment1 = new Comment();
        Comment comment2 = new Comment();
        user.addComment(comment1);
        user.addComment(comment2);
        Comment comment3 = new Comment();
        comment3.setOwner(user);
        assertEquals(3, user.getComments().size());
        assertEquals(user, comment2.getOwner());
        assertTrue(user.getComments().contains(comment3));

        user.removeComment(comment2);
        comment1.removeOwner();
        assertEquals(1, user.getComments().size());
        assertFalse(user.getComments().contains(comment1));
        assertNull(comment2.getOwner());
    }
}