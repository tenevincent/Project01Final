package vincent.moviesapp.model;

import java.util.ArrayList;

/**
 * Created by Tene on 25.01.2017.
 */

public class MovieMainApp {

    private ArrayList<Movie> listeOfMovies = new ArrayList<Movie>();
    private  Movie currentSelectgedMovie= null;


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
