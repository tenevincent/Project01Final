package vincent.moviesapp.model;

import android.app.Activity;
import android.os.AsyncTask;
import android.support.v7.app.AlertDialog;
import android.widget.Toast;

import java.io.IOException;
import java.net.URL;
import java.net.UnknownHostException;

import vincent.moviesapp.AppUtils;
import vincent.moviesapp.R;


/** Class used to perform network query task
 * Created by vincent on 25.01.2017.
 */
public class MoviesQueryTask  extends AsyncTask<URL, Void, String>  {

    IAsyncMovieRequestFinished responseQueryTask ;
    Activity activity;
    URL searchUrl  =null;
    int unknownHostCode = -11;

    /** Class constructor
     *
     * @param activity given activity or context
     * @param responseTask response task object
     */
    public MoviesQueryTask(Activity activity, IAsyncMovieRequestFinished responseTask) {
        this.activity = activity;
        this.responseQueryTask = responseTask;
    }


    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }


    /** Do background task
     *
     * @param params
     * @return
     */
    @Override
    protected String doInBackground(URL... params) {
        searchUrl = params[0];
        String moviesSearchResults = null;
        try {
            moviesSearchResults = NetworkUtils.getResponseFromHttpUrl(searchUrl);
        }
        catch (UnknownHostException e) {
            e.printStackTrace();
            unknownHostCode = 1;
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        return moviesSearchResults;
    }


    // COMPLETED (3) Override onPostExecute to display the results in the TextView
    @Override
    protected void onPostExecute(String moviesSearchResults) {

        if(unknownHostCode == 1){
            AppUtils.AlertDialogServerNotFound(activity);
        }

        if(null == moviesSearchResults){
            return;
        }

        responseQueryTask.processMoviesQueryResults(this.activity,  moviesSearchResults);
    }




}
