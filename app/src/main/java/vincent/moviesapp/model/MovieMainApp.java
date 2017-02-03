package vincent.moviesapp.model;

import android.app.Activity;
import android.content.SharedPreferences;
import android.support.v7.preference.PreferenceManager;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;

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
            this.page = weatherObj.getInt("page");
            this.totalresults = weatherObj.getInt("total_results");
            this.totalpages  =weatherObj.getInt("total_pages");

            JSONArray temp = weatherObj.getJSONArray("results");

            for (int i = 0;  i < temp.length(); i++) {

                JSONObject item = temp.getJSONObject(i);

                String poster_path = item.getString("poster_path");
                boolean adult = item.getBoolean("adult");
                String overview = item.getString("overview");
                String release_date = item.getString("release_date");
                JSONArray _genre_ids = item.getJSONArray("genre_ids");

                // genres Ids
                int [] genre_ids = new int[_genre_ids.length()] ;
                for (int j =0; j < _genre_ids.length(); j++){
                    int id = _genre_ids.getInt(j);
                    genre_ids[j] =id;
                }


                int id = item.getInt("id");
                String original_title = item.getString("original_title");
                String original_language = item.getString("original_language");
                String title = item.getString("title");

                String backdrop_path = item.getString("backdrop_path");

                float popularity = (float) item.getDouble("popularity");
                int vote_count = item.getInt("vote_count");
                boolean video = item.getBoolean("video");
                float vote_average = (float) item.getDouble("vote_average");

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


