package mediasoft.education.kvv.cinematograph;

import mediasoft.education.kvv.cinematograph.entity.Comment;
import mediasoft.education.kvv.cinematograph.entity.Movie;

public class Main {
    public static void main(String[] args) {
        Movie movie = new Movie();
        movie.setName("movie1");
        Comment comment = new Comment();
        comment.setText("first comment");
        movie.addComment(comment);
        System.out.println(movie);
    }
}
