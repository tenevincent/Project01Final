package vincent.moviesapp.model;

import android.app.Activity;
import android.content.SharedPreferences;
import android.support.v7.preference.PreferenceManager;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import vincent.moviesapp.R;

/** Main movie application object
 * Created by Tene on 25.01.2017.
 */
public class MovieMainApp {

    public static boolean HasPreferencesChanged = false;
    private int page;
    private int totalresults;
    private int totalpages;
    private ArrayList<Movie> listeOfMovies = new ArrayList<Movie>();
    private  Movie currentSelectgedMovie= null;
    private  boolean isMovieSortByMostPopular = false;


    /** Inner class containing the JSON key of the class "Movie"
     *
     */
     class Prop {

         public static final String PAGE_KEY = "page";
         public static final String total_results_KEY   = "total_results";
         public static final String total_pages_KEY   = "total_pages";
         public static final String results_KEY   = "results";

         public static final String poster_path_KEY   = "poster_path";
         public static final String adult_KEY   = "adult";
         public static final String overview_KEY   = "overview";
         public static final String release_date_KEY   = "release_date";
         public static final String genre_ids_KEY   = "genre_ids";

         public static final String id_KEY   = "id";
         public static final String original_title_KEY   = "original_title";
         public static final String original_language_KEY   = "original_language";
         public static final String title_KEY   = "title";
         public static final String backdrop_path_KEY   = "backdrop_path";
         public static final String popularity_KEY   = "popularity";
         public static final String vote_count_KEY   = "vote_count";
         public static final String video_KEY   = "video";
         public static final String vote_average_KEY   = "vote_average";
    }


    /** Gets sorting criteria: true then sort by the most popular else sort by the highest rated
     *
     * @return
     */
    public boolean isMovieSortByMostPopular() {
        return isMovieSortByMostPopular;
    }

    /** Sets the sort criteria
     *
     * @param movieSortByMostPopular sort criteria
     */
    public void setMovieSortByMostPopular(boolean movieSortByMostPopular) {
        isMovieSortByMostPopular = movieSortByMostPopular;
    }


    public  MovieMainApp(Activity activity){
        ReadPreferenceSortingParameter(activity);
    }


    private  void ReadPreferenceSortingParameter(Activity activity){
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(activity);
        String key = activity.getString(R.string.pref_sort_movie_key);
        boolean sortParam = sharedPreferences.getBoolean(key, activity.getResources().getBoolean(R.bool.pref_sort_movie_default));
        this.setMovieSortByMostPopular(sortParam);
    }


    public void extractMoviesQuery(String movieQuery){

        if(null == movieQuery)
            return;

        try {

            JSONObject weatherObj  = new JSONObject(movieQuery);
            this.page = weatherObj.getInt(Prop.PAGE_KEY);
            this.totalresults = weatherObj.getInt(Prop.total_results_KEY);
            this.totalpages  =weatherObj.getInt(Prop.total_pages_KEY);

            JSONArray temp = weatherObj.getJSONArray(Prop.results_KEY);

            for (int i = 0;  i < temp.length(); i++) {

                JSONObject item = temp.getJSONObject(i);
                // extract  these properties
                String poster_path = item.getString(Prop.poster_path_KEY);
                boolean adult = item.getBoolean(Prop.adult_KEY);
                String overview = item.getString(Prop.overview_KEY);
                String release_date = item.getString(Prop.release_date_KEY);
                JSONArray _genre_ids = item.getJSONArray(Prop.genre_ids_KEY);

                // genres Ids
                int [] genre_ids = new int[_genre_ids.length()] ;
                for (int j =0; j < _genre_ids.length(); j++){
                    int id = _genre_ids.getInt(j);
                    genre_ids[j] =id;
                }

                // extract more properties
                int id = item.getInt(Prop.id_KEY);
                String original_title = item.getString( Prop.original_title_KEY);
                String original_language = item.getString(Prop.original_language_KEY);
                String title = item.getString(Prop.title_KEY);
                String backdrop_path = item.getString(Prop.backdrop_path_KEY);
                float popularity = (float) item.getDouble(Prop.popularity_KEY);
                int vote_count = item.getInt(Prop.vote_count_KEY);
                boolean video = item.getBoolean(Prop.video_KEY);
                float vote_average = (float) item.getDouble(Prop.vote_average_KEY);

                Movie movie = new Movie(poster_path, adult,overview,release_date, genre_ids, id, original_title, original_language, title, backdrop_path, popularity, vote_count, video, vote_average);
                this.listeOfMovies.add(movie);

            }
        } catch (JSONException e) {
            e.printStackTrace();

        }
    }


    /** Gets the list of movie
     *
     * @return list of movies
     */
    public ArrayList<Movie> getListOfMovies() {
        return listeOfMovies;
    }


    /** Gets a movie object by its Id
     *
     * @param movieId movie Id
     * @return movie object
     */
    public Movie getMovieById(int movieId) {
        Movie movie = null;
        for(int i = 0; i < listeOfMovies.size(); i++){
            if(listeOfMovies.get(i).getId() == movieId){
                movie = listeOfMovies.get(i);
                break;
            }
        }
        return movie;
    }
}


