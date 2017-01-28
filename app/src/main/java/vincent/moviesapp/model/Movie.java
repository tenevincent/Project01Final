package vincent.moviesapp.model;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;
import java.util.Date;
import java.util.StringTokenizer;

/**
 * Created by Tene on 25.01.2017.
 */

public class Movie implements Parcelable {


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

    public ArrayList<String> getListeOfReviews() {
        return listeOfReviews;
    }

    public void setListeOfReviews(ArrayList<String> listeOfReviews) {
        this.listeOfReviews = listeOfReviews;
    }



    public ArrayList<String> getListeOfTrailers() {
        return listeOfTrailers;
    }

    public void setListeOfTrailers(ArrayList<String> listeOfTrailers) {
        this.listeOfTrailers = listeOfTrailers;
    }

    private ArrayList<String> listeOfTrailers;
    private ArrayList<String> listeOfReviews;


    public void setDuration(String duration) {
        this.duration = duration;
    }


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

    // TODO
    public String getDuration() {
        return this.duration + "min" ;
    }



    public String getVoteAverage() {
        return new Float(voteAverage).toString() + "/10";
    }

    public String getPosterPath() {
        return posterPath;
    }

    public String getPosterAbsolutURL(){
        return  (base_image_url  + posterPath );
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

    public String getReleaseDate() {
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


}
