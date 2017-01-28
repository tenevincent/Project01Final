package vincent.moviesapp.model;

import android.app.Activity;

/**
 * Created by vincent on 25.01.2017.
 */
public interface AsyncMovieResponse {

    void processMoviesQueryResults(Activity context, String output);

}
