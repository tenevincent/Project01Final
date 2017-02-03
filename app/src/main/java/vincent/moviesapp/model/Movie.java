package vincent.moviesapp.model;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;
import java.util.Date;
import java.util.StringTokenizer;


/** Represents a movie object
 *
 */
public class Movie {

    private String base_image_url = "http://image.tmdb.org/t/p/w185/";
    private  String posterPath;
    private  boolean isAdultMovie;
    private  String  overView;
    private String releaseDate;
    private  int [] genreId;
    private  int id;
    private  String originalTitle;
    private  String originalLanguage;
    private  String title;
    private  String backDropPath = "";
    private  float popularity;
    private  int voteCount ;
    private  boolean isaVideo;
    private  float voteAverage;
    private  String duration = "";

    public  static  final int INVALID_MOVIE_ID = -11111;
    public  static  final String DURATION_KEY = "min";


    private ArrayList<String> listeOfTrailers;
    private ArrayList<String> listeOfReviews;

    /** Gets the list of movie reviews
     *
     * @return movie reviews
     */
    public ArrayList<String> getListeOfReviews() {
        return listeOfReviews;
    }


    /** Sets the list of movie reviews
     *
     * @param listeOfReviews liste of movie reviews
     */
    public void setListeOfReviews(ArrayList<String> listeOfReviews) {
        this.listeOfReviews = listeOfReviews;
    }


    /** Gets the list of movie trailers
     *
     * @return list of movie trailers
     */
    public ArrayList<String> getListeOfTrailers() {
        return listeOfTrailers;
    }


    /** Sets the list of movie trailers
     *
     * @param listeOfTrailers list of movie trailers
     */
    public void setListeOfTrailers(ArrayList<String> listeOfTrailers) {
        this.listeOfTrailers = listeOfTrailers;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }


    /** constructor init
     *
     * @param posterPath poster path
     * @param isAdultMovie is an adult movie
     * @param overView movie overview
     * @param releaseDate movie release date
     * @param genreId movie genreId
     * @param id movie id
     * @param originalTitle movie original title
     * @param originalLanguage movie language
     * @param title movie title
     * @param backDropPath movie backdrop path
     * @param popularity popularity
     * @param voteCount voter count
     * @param isaVideo is this movie a video
     * @param voteAverage vote average
     */
    public Movie(String posterPath, boolean isAdultMovie, String overView, String releaseDate, int[] genreId, int id, String originalTitle, String originalLanguage, String title, String backDropPath, float popularity, int voteCount, boolean isaVideo, float voteAverage) {
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

    /** Gets the movie duration
     *
     * @return movie duration
     */
    public String getDuration() {
        return this.duration + DURATION_KEY ;
    }


    /** Gets the vote average
     *
     * @return vote average
     */
    public String getVoteAverage() {
        return new Float(voteAverage).toString() + "/10";
    }

    /** Gets the movie poster path
     *
     * @return movie poster path
     */
    public String getPosterPath() {
        return posterPath;
    }

    /** Gets the poster absolute URL
     *
     * @return absolut poster URL
     */
    public String getPosterAbsolutURL(){
        return  (base_image_url  + posterPath );
    }

    /** print the movie object
     *
     * @return
     */
    @Override
    public String toString() {
        return  this.getTitle() + "\n";
    }

    /** Is a movie for adults?
     *
     * @return true if the movie is for adults
     */
    public boolean isAdultMovie() {
        return isAdultMovie;
    }


    /** Gets the movie overview
     *
     * @return overview
     */
    public String getOverView() {
        return overView;
    }

    /** Gets the movie overview
     *
     * @return overview
     */
    public String getReleaseDate() {
        return releaseDate;
    }

    /** Gets the movie genreId
     *
     * @return movie genre Id
     */
    public int[] getGenreId() {
        return genreId;
    }


    /** Gets the movie Id
     *
     * @return movie Id
     */
    public int getId() {
        return id;
    }

    /** Gets the movie original title
     *
     * @return movie original title
     */
    public String getOriginalTitle() {
        return originalTitle;
    }

    /** Gets the movie original language
     *
     * @return movie language
     */
    public String getOriginalLanguage() {
        return originalLanguage;
    }

    /** Gets the movie title
     *
     * @return title
     */
    public String getTitle() {
        return title;
    }


    /** Gets the back drop path
     *
     * @return path
     */
    public String getBackDropPath() {
        return backDropPath;
    }

    /** Gets the popularity of the movie
     *
     * @return
     */
    public float getPopularity() {
        return popularity;
    }

    /** Count of voters
     *
     * @return Voters count
     */
    public int getVoteCount() {
        return voteCount;
    }


    /*** True if this movie is a video
     *
     * @return true/false
     */
    public boolean isaVideo() {
        return isaVideo;
    }

/*
    public static final Parcelable.Creator<Movie> CREATOR
            = new Parcelable.Creator<Movie>() {
        public Movie createFromParcel(Parcel in) {
            return new Movie(in);
        }

        public Movie[] newArray(int size) {
            return new Movie[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {

        dest.writeString(posterPath);
        dest.writeString(base_image_url);
        dest.writeString(title);
        dest.writeString(releaseDate);

        dest.writeString(duration);
        dest.writeFloat(voteAverage);
        dest.writeString(overView);
        dest.writeInt(id);
    }


    private Movie(Parcel in) {

        posterPath = in.readString();
        base_image_url = in.readString();
        title = in.readString();
        releaseDate = in.readString();

        duration = in.readString();
        voteAverage = in.readFloat();
        overView = in.readString();
        id = in.readInt();
    }

    */


}
