package vincent.moviesapp;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.w3c.dom.Text;

import java.net.URL;
import java.util.ArrayList;

import vincent.moviesapp.model.AsyncMovieResponse;
import vincent.moviesapp.model.Movie;
import vincent.moviesapp.model.MovieMainApp;
import vincent.moviesapp.model.MoviesQueryTask;
import vincent.moviesapp.model.NetworkUtils;

public class MoviesDetailsActivity extends AppCompatActivity {

    private Movie movie ;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movies_details);


        // Activity Title
        String title = this.getString(R.string.actionbar_details_tile);
        MovieHelper.setMovieAppActionBarTitle(this.getSupportActionBar(), title);


        // Gets the bundle
        Bundle bundleExtras = this.getIntent().getExtras();
        if(null == bundleExtras){
            return;
        }


        int movieIdBundle = bundleExtras.getInt(MainActivity.MOVIE_DETAIL_KEY, Movie.INVALID_MOVIE_ID);

        // check invalid movie ID
        if(movieIdBundle == Movie.INVALID_MOVIE_ID){
            Toast.makeText(this,"The current movie can not be found in the database!",Toast.LENGTH_LONG).show();
            return;
        }



        MovieMainApp movieMainApp =   ((MovieApplication)this.getApplication()).getMovieMainApp();

        movie = movieMainApp.getMovieById(movieIdBundle) ;
        // Sets the title
        TextView textTitle = (TextView)findViewById(R.id.textviewTitle);
        textTitle.setText(movie.getTitle());

        // image
        ImageView movieImg = (ImageView)findViewById(R.id.imageViewDetails);
        Picasso.with(this).load(movie.getPosterAbsolutURL()).into(movieImg);

        // Release Date
        TextView releaseDateTxt = (TextView)findViewById(R.id.textViewReleaseDate);
        releaseDateTxt.setText(movie.getReleaseDate());

        // Movie Duration
        TextView duration = (TextView)findViewById(R.id.textViewMovieDuration);

        // Movie Average
        TextView average = (TextView)findViewById(R.id.textViewVoteAverage);
        average.setText(movie.getVoteAverage());

        // Overview
        TextView txtOverview = (TextView)findViewById(R.id.textViewOverview);
        txtOverview.setText(movie.getOverView());


        QueryMovieDuration(movie, duration);

        QueryMovieTrailers(movie);

        QueryMovieReviews(movie);


    }

    private void QueryMovieTrailers(final Movie movie) {

        if(movie.getListeOfTrailers() != null ){
            Toast.makeText(this,"TRAILER NON NULL",Toast.LENGTH_LONG).show();
            return;
        }

        Toast.makeText(this,"TRAILERS ARE NULL",Toast.LENGTH_LONG).show();


        URL githubSearchUrl = NetworkUtils.getMovieVideoURL(movie.getId());

        MoviesQueryTask queryTask = new MoviesQueryTask(this, new AsyncMovieResponse() {
            @Override
            public void processMoviesQueryResults(Activity context, String output) {

                try {

                    JSONObject movieJsonObject  = new JSONObject(output);
                    ArrayList<String> listeTrailers = new ArrayList<String>();
                    JSONArray jsonResults = movieJsonObject.getJSONArray("results");

                    int count = 0; // trailer counter;
                    for (int i = 0; i < jsonResults.length(); i++){
                        JSONObject item = jsonResults.getJSONObject(i);
                        String key = item.getString("key");
                        String name = item.getString("name");
                        listeTrailers.add(key);
                        count++; // count how many trailers we have
                    }

                    // Sets the trailer layout visibility
                    int SizeLayout = 2;
                    int [] arrlinearTrailers = new int[SizeLayout];
                    arrlinearTrailers[0] = R.id.linearLayoutTrailer01;
                    arrlinearTrailers[1] = R.id.linearLayoutTrailer02;
                    for (int i = 0; i <count ; i++){
                        if(i < SizeLayout){
                            LinearLayout layout = (LinearLayout)context.findViewById(arrlinearTrailers[i]) ;
                            layout.setVisibility(View.VISIBLE);
                        }
                    }


                    movie.setListeOfTrailers(listeTrailers);
                    Toast.makeText(getBaseContext(), "Trailer Count: " + listeTrailers.size() ,Toast.LENGTH_LONG).show();

                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        });
        queryTask.execute(githubSearchUrl) ;

    }


    private void QueryMovieReviews(final Movie movie) {

        if(movie == null)
            return;

        if(movie.getListeOfReviews() != null ){
            return;
        }

        URL githubSearchUrl = NetworkUtils.getMovieReviewsURL(movie.getId());

        MoviesQueryTask queryTask = new MoviesQueryTask(this, new AsyncMovieResponse() {
            @Override
            public void processMoviesQueryResults(Activity context, String output) {

                try {
                    JSONObject movieJsonObject  = new JSONObject(output);
                    ArrayList<String> listeReviews= new ArrayList<String>();
                    JSONArray jsonResults = movieJsonObject.getJSONArray("results");

                    for (int i = 0; i < jsonResults.length(); i++){
                        JSONObject item = jsonResults.getJSONObject(i);
                        String content = item.getString("content");
                        // Add a review to the list
                        listeReviews.add(content);
                    }

                    movie.setListeOfReviews(listeReviews);
                    Toast.makeText(getBaseContext(), "Trailer Eviews: " + listeReviews.size() ,Toast.LENGTH_LONG).show();

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });

        if(NetworkUtils.checkInternetConnection(this)){
            queryTask.execute(githubSearchUrl) ;
        }
        else{
            Toast.makeText(this,"No Internet Connection is available",Toast.LENGTH_LONG).show();
        }




    }



    private void QueryMovieDuration(final Movie movie, TextView duration) {

        if(movie.getDuration().equals(Movie.DURATION_KEY) ){

            Toast.makeText(this,"MOVIE DURATION NULL",Toast.LENGTH_LONG).show();

            URL githubSearchUrl = NetworkUtils.getMovieDuration(movie.getId());
            MoviesQueryTask queryTask = new MoviesQueryTask(this, new AsyncMovieResponse() {

                @Override
                public void processMoviesQueryResults(Activity context, String output) {

                    if(null == output){
                        return;
                    }
                    try {
                        JSONObject movieJsonObject  = new JSONObject(output);
                        int runtime = movieJsonObject.getInt("runtime");
                        movie.setDuration(new Integer((runtime)).toString());
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                    TextView duration01 =   (TextView)findViewById(R.id.textViewMovieDuration);
                    duration01.setText(movie.getDuration());
                }
            });
            queryTask.execute(githubSearchUrl) ;
        }

        else{
            duration.setText(movie.getDuration());
            Toast.makeText(this,"MOVIE DURATION ALREADY LOADED",Toast.LENGTH_LONG).show();

        }
    }

    public void onPlayMovieTrailer01(View view) {

        if(null == movie)
            return;;

        if(null == movie.getListeOfTrailers())
            return;

        if(movie.getListeOfTrailers().size() < 1) // there is not trailer 01
            return;


        String urlYoutube = movie.getListeOfTrailers().get(0);
        URL githubSearchUrl = NetworkUtils.getYoutubeTrailerURL(urlYoutube);
        Toast.makeText(this,githubSearchUrl.toString(),Toast.LENGTH_LONG).show();

        Intent videoClient = new Intent(Intent.ACTION_VIEW);
        videoClient.setData(Uri.parse(githubSearchUrl.toString()));
        startActivity(videoClient);
    }



    public void onPlayMovieTrailer02(View view) {

        if(null == movie)
            return;;

        if(null == movie.getListeOfTrailers())
            return;

        if(movie.getListeOfTrailers().size() < 2) // there is not trailer 02
            return;


        String urlYoutube = movie.getListeOfTrailers().get(1);
        URL githubSearchUrl = NetworkUtils.getYoutubeTrailerURL(urlYoutube);

        Toast.makeText(this,githubSearchUrl.toString(),Toast.LENGTH_LONG).show();
        Intent videoClient = new Intent(Intent.ACTION_VIEW);
        videoClient.setData(Uri.parse(githubSearchUrl.toString()));
        startActivity(videoClient);
    }


}