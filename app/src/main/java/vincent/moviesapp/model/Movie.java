package vincent.moviesapp.model;

import java.util.Date;
import java.util.StringTokenizer;

/**
 * Created by Tene on 25.01.2017.
 */

public class Movie {


    private  String posterPath;
    private  boolean isAdultMovie;
    private  String  overView;
    private Date releaseDate;
    private  int [] genreId;
    private  int id;
    private String originalTitle;
    private  String originalLanguage;
    private  String title;
    private  String backDropPath = "";
    private  float popularity;
    private  int voteCount ;
    private  boolean isaVideo;
    private  float voteAverage;

    public Movie(String posterPath, boolean isAdultMovie, String overView, Date releaseDate, int[] genreId, int id, String originalTitle, String originalLanguage, String title, String backDropPath, float popularity, int voteCount, boolean isaVideo, float voteAverage) {
        this.posterPath = posterPath;
        this.isAdultMovie = isAdultMovie;
        this.overView = overView;
        this.releaseDate = releaseDate;
        this.genreId = genreId;
        this.id = id;
        this.originalTitle = originalTitle;
        this.originalLanguage = originalLanguage;
        this.title = title;
        this.backDropPath = backDropPath;
        this.popularity = popularity;
        this.voteCount = voteCount;
        this.isaVideo = isaVideo;
        this.voteAverage = voteAverage;
    }

    public float getVoteAverage() {
        return voteAverage;
    }

    public String getPosterPath() {
        return posterPath;
    }

    @Override
    public String toString() {
        return  this.getTitle() + "\n";
    }

    public boolean isAdultMovie() {
        return isAdultMovie;
    }

    public String getOverView() {
        return overView;
    }

    public Date getReleaseDate() {
        return releaseDate;
    }

    public int[] getGenreId() {
        return genreId;
    }

    public int getId() {
        return id;
    }

    public String getOriginalTitle() {
        return originalTitle;
    }

    public String getOriginalLanguage() {
        return originalLanguage;
    }

    public String getTitle() {
        return title;
    }

    public String getBackDropPath() {
        return backDropPath;
    }

    public float getPopularity() {
        return popularity;
    }

    public int getVoteCount() {
        return voteCount;
    }

    public boolean isaVideo() {
        return isaVideo;
    }
}
