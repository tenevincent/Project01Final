package vincent.moviesapp.model;

import java.util.Date;

/**
 * Created by Tene on 25.01.2017.
 */

public class Movie {

    private  int id;
    private  int [] genreId;
    private String originalTitle;
    private  String title;
    private  String backDropPath = "";
    private  float popularity;
    private  int voteCount ;
    private  boolean isaVideo;
    private  float voteAverage;

    public float getPopularity() {
        return popularity;
    }

    public float getVoteAverage() {
        return voteAverage;
    }

    private  String posterPath;
    private  boolean isAdultMovie;
    private  String  overView;
    private Date releaseDate;
}
