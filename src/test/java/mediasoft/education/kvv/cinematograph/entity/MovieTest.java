package mediasoft.education.kvv.cinematograph.entity;

import org.junit.Test;
import org.junit.runners.Parameterized;

import javax.swing.plaf.basic.BasicInternalFrameTitlePane;
import java.util.Collection;

import static org.junit.Assert.*;


public class MovieTest {

    @Test
    public void addComment() {
        Movie movie = new Movie();
        movie.setName("movie1");
        for (int i = 0; i < 10; i++) {
            Comment comment = new Comment();
            comment.setText(String.format("comment_%d", i));
            movie.addComment(comment);
            if (i == 9) {
                movie.addComment(comment);
            }
        }
        assertEquals(10, movie.getComments().size());
        assertEquals(movie.getComments().get(0).getMovie(), movie);
        assertEquals(movie.getComments().get(5).getMovie(), movie);
    }

    @Test
    public void removeComment() {
        Movie movie = new Movie();
        movie.setName("movie");
        Comment comment1 = new Comment();
        comment1.setText("comment1");
        movie.addComment(comment1);

        Comment comment2 = new Comment();
        comment2.setText("comment2");
        movie.addComment(comment2);

        assertEquals(comment2.getMovie(), movie);
        movie.removeComment(comment2);
        assertEquals(comment1.getMovie(), movie);
        assertEquals(1, movie.getComments().size());
        assertNull(comment2.getMovie());
        assertFalse(movie.getComments().contains(comment2));
    }

    @Test
    public void addActor() {
        Movie[] movies = new Movie[2];
        movies[0] = new Movie();
        movies[1] = new Movie();
        String[] actorsList = {"actor1", "actor2", "actor3"};
        Actor[] actors = new Actor[actorsList.length];
        for (int i = 0, actorsListLength = actorsList.length; i < actorsListLength; i++) {
            String name = actorsList[i];
            actors[i] = new Actor();
            actors[i].setName(name);
            movies[0].addActor(actors[i]);
            movies[1].addActor(actors[i]);
        }
        assertEquals(3, movies[0].getActors().size());
        assertEquals(3, movies[1].getActors().size());
        assertEquals(2, actors[0].getMovies().size());
    }

    @Test
    public void removeActor() {
        Movie movie = new Movie();
        Actor actor1 = new Actor();
        Actor actor2 = new Actor();
        movie.addActor(actor1);
        movie.addActor(actor2);
        movie.addActor(actor2);
        assertEquals(2, movie.getActors().size());
        assertTrue(movie.getActors().contains(actor1));
        assertTrue(actor1.getMovies().contains(movie));
        movie.removeActor(actor1);
        assertFalse(movie.getActors().contains(actor1));
        assertFalse(actor1.getMovies().contains(movie));
    }

    @Test
    public void updateOwner() {
        Movie movie = new Movie();
        User user =  new User();
        movie.updateOwner(user);
        assertTrue(user.getAddedMoviesByUser().contains(movie));
        User user2 = new User();
        movie.updateOwner(user2);
        assertFalse(user.getAddedMoviesByUser().contains(movie));
        assertTrue(user2.getAddedMoviesByUser().contains(movie));
        assertEquals(user2, movie.getOwner());
    }

    @Test
    public void removeOwner() {
        Movie movie = new Movie();
        movie.removeOwner();
        User user =  new User();
        movie.updateOwner(user);
        assertEquals(user, movie.getOwner());
        assertTrue(user.getAddedMoviesByUser().contains(movie));
        movie.removeOwner();
        assertNull(movie.getOwner());
        assertFalse(user.getAddedMoviesByUser().contains(movie));
    }

    @Test
    public void addAndRemoveTag() {
        Movie movie = new Movie();
        Tag tag = new Tag();
        movie.addTag(tag);
        movie.addTag(tag);
        assertEquals(1, movie.getTags().size());
        assertTrue(tag.getMovies().contains(movie));
        movie.removeTag(tag);
        assertTrue(movie.getTags().isEmpty());
        assertTrue(tag.getMovies().isEmpty());
    }

}