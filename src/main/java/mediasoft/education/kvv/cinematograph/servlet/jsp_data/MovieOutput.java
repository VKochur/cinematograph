package mediasoft.education.kvv.cinematograph.servlet.jsp_data;

import mediasoft.education.kvv.cinematograph.dto.ActorDto;
import mediasoft.education.kvv.cinematograph.dto.MovieDto;

import java.util.Set;

public class MovieOutput {

    private MovieDto data;
    private String htmlCodeComments;
    private Set<ActorDto> actors;

    public MovieDto getData() {
        return data;
    }

    public void setData(MovieDto data) {
        this.data = data;
    }

    public String getHtmlCodeComments() {
        return htmlCodeComments;
    }

    public void setHtmlCodeComments(String htmlCodeComments) {
        this.htmlCodeComments = htmlCodeComments;
    }

    public void setActors(Set<ActorDto> actors) {
        this.actors =actors;
    }

    public Set<ActorDto> getActors() {
        return actors;
    }
}
