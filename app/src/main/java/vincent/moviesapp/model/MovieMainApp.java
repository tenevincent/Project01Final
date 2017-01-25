package vincent.moviesapp.model;

import java.util.ArrayList;
import java.util.Comparator;

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

