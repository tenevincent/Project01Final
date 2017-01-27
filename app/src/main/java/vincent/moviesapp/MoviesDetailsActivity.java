package vincent.moviesapp;

import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MoviesDetailsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movies_details);

        String title = this.getString(R.string.actionbar_details_tile);
        MovieHelper.setMovieAppActionBarTitle(this.getSupportActionBar(), title);


    }

}
