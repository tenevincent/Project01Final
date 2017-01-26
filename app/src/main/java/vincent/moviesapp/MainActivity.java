package vincent.moviesapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;
import android.support.v7.widget.RecyclerView;

import java.net.URL;

import vincent.moviesapp.model.AsyncMovieResponse;
import vincent.moviesapp.model.MoviesQueryTask;
import vincent.moviesapp.model.NetworkUtils;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        String title = this.getString(R.string.actionbar_title_main_activity);
        MovieHelper.setMovieAppActionBarTitle(this.getSupportActionBar(), title);




        // TODO

        /*
        String githubQuery = "http://api.themoviedb.org/3/movie/popular?api_key=[YOUR_API_KEY]";
        URL githubSearchUrl = NetworkUtils.buildUrl(githubQuery);


        MoviesQueryTask queryTask = new MoviesQueryTask( new AsyncMovieResponse()
        {
            @Override
            public void processMoviesQueryResults(String output) {
              // TODO
            }
        }
        );
        queryTask.execute(githubSearchUrl) ;
        */


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_main_activity, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_sortby_popular:
                //  handleSortByMostPopular();
                Toast.makeText(MainActivity.this, "menu_sortby_popular", Toast.LENGTH_LONG).show();
                Intent intent = new Intent("vincent.moviesapp.MoviesDetailsActivity");
                startActivity(intent);

                return true;
            case R.id.menu_sortby_toprated:

                Toast.makeText(MainActivity.this, "menu_sortby_toprated", Toast.LENGTH_LONG).show();
                //  handleSortByTopRated();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    /*
    public void onClickDetails(View view) {
        Intent intent = new Intent("vincent.moviesapp.MoviesDetailsActivity");
        startActivity(intent);
    }
    */





    }

