package vincent.moviesapp.model;

import android.app.Activity;
import android.os.AsyncTask;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import vincent.moviesapp.R;



/**
 * Created by vincent on 25.01.2017.
 */
public class MoviesQueryTask  extends AsyncTask<URL, Void, String>  {

    AsyncMovieResponse responseQueryTask ;
    Activity activity;
    ProgressBar pogressBar01 = null;
    int code = 0;
    URL searchUrl  =null;


    public MoviesQueryTask(Activity activity, AsyncMovieResponse responseTask) {

        this.activity = activity;
        this.responseQueryTask = responseTask;
        pogressBar01 = (ProgressBar)activity.findViewById(R.id.progressBar);
    }



    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        if(null != pogressBar01)
            pogressBar01.setVisibility(View.VISIBLE);
    }

    @Override
    protected String doInBackground(URL... params) {

        searchUrl = params[0];
        String moviesSearchResults = null;

        try {
            HttpURLConnection connection = (HttpURLConnection) searchUrl.openConnection();
            code = connection.getResponseCode();
        } catch (IOException e1) {
            e1.printStackTrace();
        }

        if(code == 200) {
            // reachable
        } else {
        }


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
        if(null != pogressBar01)
             pogressBar01.setVisibility(View.INVISIBLE);

        if(code == 200){
            Toast.makeText(activity,"The server " + searchUrl.toString() +
                  " is not reachable!"  ,Toast.LENGTH_LONG).show();
        }
    }


}
