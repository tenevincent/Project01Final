package vincent.moviesapp;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.preference.PreferenceManager;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;
import android.support.v7.widget.RecyclerView;

import java.net.URL;

import vincent.moviesapp.model.IAsyncMovieRequestFinished;
import vincent.moviesapp.model.MovieMainApp;
import vincent.moviesapp.model.MoviesQueryTask;
import vincent.moviesapp.model.NetworkUtils;

public class MainActivity extends AppCompatActivity implements SharedPreferences.OnSharedPreferenceChangeListener {


    MovieMainApp movieApp ;
    public  static  String MOVIE_DETAIL_KEY = "movie";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Sets the actionbar
        String title = this.getString(R.string.actionbar_title_main_activity);
        ActionBar actionbar = this.getSupportActionBar();
        if(null != actionbar)
            actionbar.setTitle(title);
        
        movieApp = ((MovieApplication)this.getApplication()).getMovieMainApp();

        // when the device is rotated a query showld not be hit again
        if(null == movieApp && NetworkUtils.checkInternetConnection(this)){ // at the first time this activity is created!
            movieApp = new MovieMainApp(this);
            queryMoviesFromDatabase(movieApp.isMovieSortByMostPopular());
        }
        else if (MovieMainApp.HasPreferencesChanged){ // The preferences has changed!
            movieApp = new MovieMainApp(this);
            queryMoviesFromDatabase(movieApp.isMovieSortByMostPopular());
            MovieMainApp.HasPreferencesChanged = false;
        }
        else if (null != movieApp){ // the main movie object is not null!
            updateRecyclerViewUI(this);
        }

        registerPreferencesListener();
    }




    private void registerPreferencesListener() {
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        sharedPreferences.registerOnSharedPreferenceChangeListener(this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        // unregister the preference listener to avoid memory leak!
        PreferenceManager.getDefaultSharedPreferences(this)
                .unregisterOnSharedPreferenceChangeListener(this);
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

            case R.id.movies_settings:
                Intent intentSettings = new Intent("vincent.moviesapp.SettingsActivity");
                startActivity(intentSettings);
                return  true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void queryMoviesFromDatabase(boolean isSortByMostPopular) {

        URL githubSearchUrl = NetworkUtils.buildUrl(isSortByMostPopular);

        MoviesQueryTask queryTask = new MoviesQueryTask(this,  new IAsyncMovieRequestFinished()
        {
            @Override
            public void processMoviesQueryResults(Activity activity, String output) {

                movieApp = null;
                movieApp = new MovieMainApp(activity);
                movieApp.extractMoviesQuery(output);
                ((MovieApplication)activity.getApplication()).setMovieMainApp(movieApp);
                updateRecyclerViewUI(activity);

            }
        }
        );

        if(NetworkUtils.checkInternetConnection(this)){
            queryTask.execute(githubSearchUrl) ;
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



    @Override
    public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String key) {

        if (key.equals(this.getString(R.string.pref_sort_movie_key))) {
            boolean sortParameter = sharedPreferences.getBoolean(key, getResources().getBoolean(R.bool.pref_sort_movie_default));
            MovieMainApp.HasPreferencesChanged = false; // init to false
            // check if the new parameter is different from the old and save it on the model
            if(sortParameter != movieApp.isMovieSortByMostPopular()){
                movieApp.setMovieSortByMostPopular(sortParameter);
                MovieMainApp.HasPreferencesChanged = true;
            }
        }
    }




}

