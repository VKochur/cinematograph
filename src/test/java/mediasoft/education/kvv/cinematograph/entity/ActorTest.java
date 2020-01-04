package mediasoft.education.kvv.cinematograph.entity;

import org.junit.Test;

import static org.junit.Assert.*;

public class ActorTest {

    @Test
    public void testAddAndRemoveMovies() {
        Actor actor = new Actor();
        Movie movie1 = new Movie();
        Movie movie2 = new Movie();
        actor.addMovies(movie1);
        movie2.addActor(actor);
        assertEquals(2, actor.getMovies().size());
        assertEquals(1, movie2.getActors().size());
        assertTrue(movie2.getActors().contains(actor));

        actor.removeMovie(movie2);
        assertFalse(movie2.getActors().contains(actor));
        assertFalse(actor.getMovies().contains(movie2));
        assertEquals(1, actor.getMovies().size());
    }

}