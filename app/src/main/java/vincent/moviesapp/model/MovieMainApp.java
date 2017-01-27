package vincent.moviesapp.model;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;

/**
 * Created by Tene on 25.01.2017.
 */

public class MovieMainApp {

    private int page;
    private int totalresults;
    private int totalpages;
    private ArrayList<Movie> listeOfMovies = new ArrayList<Movie>();
    private  Movie currentSelectgedMovie= null;

    public  MovieMainApp(String movieQuery){
        extractMoviesQuery(movieQuery);
    }



    private void extractMoviesQuery(String movieQuery){


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

                Date date  =null;
                try {
                    SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
                    date = formatter.parse(release_date);
                } catch (ParseException e) {
                    e.printStackTrace();
                }

                Movie movie = new Movie(poster_path, adult,overview,date, genre_ids, id, original_title, original_language, title, backdrop_path, popularity, vote_count, video, vote_average);
                this.listeOfMovies.add(movie);

            }


        } catch (JSONException e) {
            e.printStackTrace();
        }


    }







    public Movie getCurrentSelectgedMovie() {
        return currentSelectgedMovie;
    }

    public void setCurrentSelectgedMovie(Movie currentSelectgedMovie) {
        this.currentSelectgedMovie = currentSelectgedMovie;
    }



    public ArrayList<Movie> getListeOfMovies() {
        return listeOfMovies;
    }

}


/* TODO to be removed!
List<Movie> chairs = new ArrayList<Movie>();
// Sort by getPopularity:
Collections.sort(chairs, new MovieByMostPopularComparator());
// Sort by getVoteAverage:
Collections.sort(chairs, new MovieByHighestRateComparator());
*/


class MovieByMostPopularComparator implements Comparator<Movie> {

    @Override
    public int compare(Movie movie1, Movie movie2) {

        Float popularity1 = Float.valueOf(movie1.getPopularity());
        Float popularity2 = Float.valueOf(movie2.getPopularity());
        return popularity1.compareTo(popularity2);
    }
}



class MovieByHighestRateComparator implements Comparator<Movie> {

    @Override
    public int compare(Movie movie1, Movie movie2) {
        Float vote01 = Float.valueOf(movie1.getVoteAverage());
        Float vote02 = Float.valueOf(movie2.getVoteAverage());
        return vote01.compareTo(vote02);
    }
}

