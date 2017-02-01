package vincent.moviesapp;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;
import android.support.v7.widget.RecyclerView;

import java.net.URL;

import vincent.moviesapp.model.AsyncMovieResponse;
import vincent.moviesapp.model.EUrlRequestType;
import vincent.moviesapp.model.MovieMainApp;
import vincent.moviesapp.model.MoviesQueryTask;
import vincent.moviesapp.model.NetworkUtils;

public class MainActivity extends AppCompatActivity {



    public  static  final  String MOVIE_DETAIL_KEY = "movie";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        String title = this.getString(R.string.actionbar_title_main_activity);
        MovieHelper.setMovieAppActionBarTitle(this.getSupportActionBar(), title);


        MovieMainApp movieApp = ((MovieApplication)this.getApplication()).getMovieMainApp();

        // when the device is rotated a query showld not be hit again
        if(null == movieApp && NetworkUtils.checkInternetConnection(this)){
            queryMoviesFromDatabase(EUrlRequestType.BY_TOP_RATED);
            Toast.makeText(MainActivity.this, "movieApp IS NULL", Toast.LENGTH_LONG).show();
        }
        else if (null != movieApp){
            updateRecyclerViewUI(this);
            Toast.makeText(MainActivity.this, "movieApp non NULL", Toast.LENGTH_LONG).show();
        }

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
                queryMoviesFromDatabase(EUrlRequestType.BY_MOST_POPULAR);
                return true;
            case R.id.menu_sortby_toprated:
                Toast.makeText(MainActivity.this, "menu_sortby_toprated", Toast.LENGTH_LONG).show();
                queryMoviesFromDatabase(EUrlRequestType.BY_TOP_RATED);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }


    private void queryMoviesFromDatabase(EUrlRequestType urlRequestBy) {

        URL githubSearchUrl = NetworkUtils.buildUrl(urlRequestBy);

        MoviesQueryTask queryTask = new MoviesQueryTask(this,  new AsyncMovieResponse()
        {
            @Override
            public void processMoviesQueryResults(Activity activity, String output) {

                ((MovieApplication)activity.getApplication()).setMovieMainApp(new MovieMainApp(output));
                updateRecyclerViewUI(activity);
            }
        }
        );

       if(NetworkUtils.checkInternetConnection(this)){
            queryTask.execute(githubSearchUrl) ;
        }
        else{
            Toast.makeText(this,"No Internet Connection is available - Main Activity",Toast.LENGTH_LONG).show();
        }
    }

    private void updateRecyclerViewUI(Activity activity) {
        //  MovieMainApp movieApp = ((MovieApplication)activity.getApplication()).getMovieMainApp();
        RecyclerView recyclerView = (RecyclerView) activity.findViewById(R.id.gridViewMovies);
        recyclerView.addItemDecoration(new MarginDecoration(activity.getBaseContext()));
        recyclerView.setHasFixedSize(false);

        // First param is number of columns and second param is orientation i.e Vertical or Horizontal
        StaggeredGridLayoutManager gridLayoutManager = new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(gridLayoutManager);
        //  recyclerView.setLayoutManager(new GridLayoutManager(view.getContext(), 2));
        ImageAdapter imageAdapter = new ImageAdapter(activity.getBaseContext());
        recyclerView.setAdapter(imageAdapter);
        imageAdapter.notifyDataSetChanged();
    }


}

