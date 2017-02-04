package vincent.moviesapp.model;

/**
 * Created by Tene on 25.01.2017.
 */

import android.app.Activity;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Scanner;

/**
 * These utilities will be used to communicate with the network.
 */
public class NetworkUtils {

    final static String MOVIE_POPULAR_BASE_URL =  "http://api.themoviedb.org/3/movie/popular";
    final static String MOVIE_BY_HIGHEST_RATE_BASE_URL =  "http://api.themoviedb.org/3/movie/top_rated";

    private static   String base_img_duration_url = "http://api.themoviedb.org/3/movie/MOVIE_ID";

    private static   String base_videos_url = "http://api.themoviedb.org/3/movie/MOVIE_ID/videos"; // videso URL
    private static   String base_reviews_url = "http://api.themoviedb.org/3/movie/MOVIE_ID/reviews"; // reviews URL

    private static   String base_youtube_base_url = "https://www.youtube.com/watch"; // reviews URL
    final static String YOU_TUBE_QUERY = "v";
    final static String PARAM_QUERY = "api_key";


    // TODO:  Add the movie api Key here
    final static String api_key_value = "<add your API Key>";


    /*
     * The sort field. One of stars, forks, or updated.
     * Default: results are sorted by best match if no field is specified.
     */
    final static String PARAM_SORT = "sort";
    final static String sortBy = "stars";

    /** Check whether an internet connection is available
     *
     * @param activity current activity
     * @return true if an internet connection is available
     */
    public static boolean checkInternetConnection(Activity activity) {

        // https://developer.android.com/training/monitoring-device-state/connectivity-monitoring.html#DetermineType
        ConnectivityManager cm = (ConnectivityManager)activity.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
        boolean isConnected = activeNetwork != null &&
                activeNetwork.isConnectedOrConnecting();
        return  isConnected;
    }


    /** Gets the URL for visualize the youtube trailer
     *
     * @param youtubeKeyValue youtube trailer key value
     * @return youtube trailer url
     */
    public static URL getYoutubeTrailerURL(String youtubeKeyValue) {

      String  strUrlQuery = base_youtube_base_url;
        // COMPLETED (1) Fill in this method to build the proper Github query URL
        Uri builtUri = Uri.parse(strUrlQuery).buildUpon()
                .appendQueryParameter(YOU_TUBE_QUERY, youtubeKeyValue)
                .build();

        URL url = null;
        try {
            url = new URL(builtUri.toString());
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        return url;
    }


    /**
     * Builds the URL used to query Github.
     *
     * @return The URL to use to query the weather server.
     */
    public static URL buildUrl(boolean isSortByMostPopular) {

        String strUrlQuery =MOVIE_POPULAR_BASE_URL ;
        if(!isSortByMostPopular)
            strUrlQuery = MOVIE_BY_HIGHEST_RATE_BASE_URL;

        // COMPLETED (1) Fill in this method to build the proper Github query URL
        Uri builtUri = Uri.parse(strUrlQuery).buildUpon()
                .appendQueryParameter(PARAM_QUERY, api_key_value)
                .build();

        URL url = null;
        try {
            url = new URL(builtUri.toString());
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }

        return url;
    }


    public static  URL getMovieDuration(int movieid){

        String mainURL = base_img_duration_url;
        mainURL = mainURL.replace("MOVIE_ID",new Integer(movieid).toString());

        // COMPLETED (1) Fill in this method to build the proper Github query URL
        Uri builtUri = Uri.parse(mainURL).buildUpon()
                .appendQueryParameter(PARAM_QUERY, api_key_value)
                .build();

        URL url = null;
        try {
            url = new URL(builtUri.toString());
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        return  url;
    }


    public static  URL getMovieVideoURL(int movieid){

        String mainURL = base_videos_url;
        mainURL = mainURL.replace("MOVIE_ID",new Integer(movieid).toString());

        // COMPLETED (1) Fill in this method to build the proper Github query URL
        Uri builtUri = Uri.parse(mainURL).buildUpon()
                .appendQueryParameter(PARAM_QUERY, api_key_value)
                .build();

        URL url = null;
        try {
            url = new URL(builtUri.toString());
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        return  url;
    }





    public static  URL getMovieReviewsURL(int movieid){

        String mainURL = base_reviews_url;
        mainURL = mainURL.replace("MOVIE_ID",new Integer(movieid).toString());

        // COMPLETED (1) Fill in this method to build the proper Github query URL
        Uri builtUri = Uri.parse(mainURL).buildUpon()
                .appendQueryParameter(PARAM_QUERY, api_key_value)
                .build();

        URL url = null;
        try {
            url = new URL(builtUri.toString());
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        return  url;
    }





    /**
     * This method returns the entire result from the HTTP response.
     *
     * @param url The URL to fetch the HTTP response from.
     * @return The contents of the HTTP response.
     * @throws IOException Related to network and stream reading
     */
    public static String getResponseFromHttpUrl(URL url) throws IOException {

        HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();

        try {
            InputStream in = urlConnection.getInputStream();
            Scanner scanner = new Scanner(in);
            scanner.useDelimiter("\\A");
            boolean hasInput = scanner.hasNext();
            if (hasInput) {
                return scanner.next();
            } else {
                return null;
            }
        } finally {
            urlConnection.disconnect();
        }
    }
}