package vincent.moviesapp;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
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
import vincent.moviesapp.model.EUrlRequestType;
import vincent.moviesapp.model.Movie;
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


        Bundle bundle = this.getIntent().getExtras();

        String value = bundle.getString("movieApp") ;


        movie = bundle.getParcelable("movie");

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
            return;
        }

            URL githubSearchUrl = NetworkUtils.getMovieVideoURL(movie.getId());
            MoviesQueryTask queryTask = new MoviesQueryTask(this, new AsyncMovieResponse() {
                @Override
                public void processMoviesQueryResults(Activity context, String output) {

                    try {
                        JSONObject movieJsonObject  = new JSONObject(output);

                        ArrayList<String> listeTrailers = new ArrayList<String>();

                        JSONArray jsonResults = movieJsonObject.getJSONArray("results");

                        for (int i = 0; i < jsonResults.length(); i++){
                            JSONObject item = jsonResults.getJSONObject(i);
                            String key = item.getString("key");
                            String name = item.getString("name");
                            listeTrailers.add(key);
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
        queryTask.execute(githubSearchUrl) ;



    }



    private void QueryMovieDuration(final Movie movie, TextView duration) {

        if(movie.getDuration().equals("min") ){

            URL githubSearchUrl = NetworkUtils.getMovieDuration(movie.getId());
            MoviesQueryTask queryTask = new MoviesQueryTask(this, new AsyncMovieResponse() {
                @Override
                public void processMoviesQueryResults(Activity context, String output) {

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
        }
    }

    public void onPlayMovieTrailer01(View view) {

        String urlYoutube = movie.getListeOfTrailers().get(0);
        URL githubSearchUrl = NetworkUtils.getYoutubeTrailerURL(urlYoutube);
        Toast.makeText(this,githubSearchUrl.toString(),Toast.LENGTH_LONG).show();

        Intent videoClient = new Intent(Intent.ACTION_VIEW);
        videoClient.setData(Uri.parse(githubSearchUrl.toString()));
        startActivity(videoClient);


    }

    public void onPlayMovieTrailer02(View view) {
        String urlYoutube = movie.getListeOfTrailers().get(1);
        URL githubSearchUrl = NetworkUtils.getYoutubeTrailerURL(urlYoutube);

        Toast.makeText(this,githubSearchUrl.toString(),Toast.LENGTH_LONG).show();

        Intent videoClient = new Intent(Intent.ACTION_VIEW);
        videoClient.setData(Uri.parse(githubSearchUrl.toString()));
        startActivity(videoClient);


    }
}
