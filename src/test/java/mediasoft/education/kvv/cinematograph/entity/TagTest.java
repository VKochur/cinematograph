package mediasoft.education.kvv.cinematograph.entity;

import org.junit.Test;

import static org.junit.Assert.*;

public class TagTest {

    @Test
    public void addAndRemoveMovie() {
        Tag tag = new Tag();
        Movie movie = new Movie();
        tag.addMovie(movie);
        assertTrue(tag.getMovies().contains(movie));
        assertTrue(movie.getTags().contains(tag));
        tag.removeMovie(movie);
        assertFalse(tag.getMovies().contains(movie));
        assertFalse(movie.getTags().contains(tag));
    }
}