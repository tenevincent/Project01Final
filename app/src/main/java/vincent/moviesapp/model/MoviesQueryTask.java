package vincent.moviesapp.model;

import android.os.AsyncTask;

import java.io.IOException;
import java.net.URL;


/**
 * Created by vincent on 25.01.2017.
 */
public class MoviesQueryTask  extends AsyncTask<URL, Void, String>  {

    AsyncMovieResponse responseQueryTask ;

    public MoviesQueryTask(AsyncMovieResponse responseTask) {
        responseQueryTask = responseTask;
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


        responseQueryTask.processMoviesQueryResults(moviesSearchResults);


       // if (moviesSearchResults != null && !moviesSearchResults.equals("")) {
           // mSearchResultsTextView.setText(moviesSearchResults);
       // }


    }


}
