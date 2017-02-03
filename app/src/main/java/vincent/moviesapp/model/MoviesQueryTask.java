package vincent.moviesapp.model;

import android.app.Activity;
import android.app.NotificationManager;
import android.os.AsyncTask;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.NotificationCompat;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.UnknownHostException;

import vincent.moviesapp.AppUtils;
import vincent.moviesapp.R;

import static android.content.Context.NOTIFICATION_SERVICE;


/**
 * Created by vincent on 25.01.2017.
 */
public class MoviesQueryTask  extends AsyncTask<URL, Void, String>  {


    AsyncMovieResponse responseQueryTask ;
    Activity activity;
    URL searchUrl  =null;
    int unknownHostCode = -11;


    /** Class constructor
     *
     * @param activity given activity or context
     * @param responseTask response task object
     */
    public MoviesQueryTask(Activity activity, AsyncMovieResponse responseTask) {
        this.activity = activity;
        this.responseQueryTask = responseTask;
    }


    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }

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

            try{
                int mNotificationId = 001;
                String title = "Unreachable Server Hostname" ;
                String message = "Unable to reach the movie database server!" ;
                String [] events = new String[2];
                events[0] = "Please make sure that you have an available internet connection!";
                events[1] = "";
                AppUtils.LaunchToastNotification(this.activity,mNotificationId,R.mipmap.ic_launcher,title,message,events);
                String dlgMessage = message + events[0];
                new AlertDialog.Builder(activity).setMessage(dlgMessage).setPositiveButton("OK", null).show();
            }catch (Exception ex){

            }

        }


        if(null == moviesSearchResults){
            return;
        }



        responseQueryTask.processMoviesQueryResults(this.activity,  moviesSearchResults);
    }



}
