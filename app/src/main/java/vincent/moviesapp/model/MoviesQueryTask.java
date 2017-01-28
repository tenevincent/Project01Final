package vincent.moviesapp.model;

import android.app.Activity;
import android.os.AsyncTask;

import java.io.IOException;
import java.net.URL;


/**
 * Created by vincent on 25.01.2017.
 */
public class MoviesQueryTask  extends AsyncTask<URL, Void, String>  {

    AsyncMovieResponse responseQueryTask ;
    Activity activity;


    public MoviesQueryTask(Activity activity, AsyncMovieResponse responseTask) {

        this.activity = activity;
        this.responseQueryTask = responseTask;
    }


    @Override
    protected String doInBackground(URL... params) {

        URL searchUrl = params[0];
        String moviesSearchResults = null;
        try {
            moviesSearchResults = NetworkUtils.getResponseFromHttpUrl(searchUrl);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return moviesSearchResults;
    }

    // COMPLETED (3) Override onPostExecute to display the results in the TextView
    @Override
    protected void onPostExecute(String moviesSearchResults) {

        responseQueryTask.processMoviesQueryResults(this.activity,  moviesSearchResults);
    }


}
