package vincent.moviesapp.model;

/**
 * Created by Tene on 25.01.2017.
 */

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
    final static String MOVIE_BY_HIGHEST_RATE_BASE_URL =  "http://api.themoviedb.org/3/movie/popular";


    final static String PARAM_QUERY = "api_key";
    final static String api_key_value = "810f83d9a006d9817f993a80e48ad029";


    /*
     * The sort field. One of stars, forks, or updated.
     * Default: results are sorted by best match if no field is specified.
     */
    final static String PARAM_SORT = "sort";
    final static String sortBy = "stars";

    /**
     * Builds the URL used to query Github.
     *
     * @return The URL to use to query the weather server.
     */
    public static URL buildUrl(EUrlRequestType eUrlQueryType) {

        String strUrlQuery =MOVIE_POPULAR_BASE_URL ;
        if(eUrlQueryType == EUrlRequestType.BY_TOP_RATED)
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