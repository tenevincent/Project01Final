package vincent.moviesapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        String title = this.getString(R.string.actionbar_title_main_activity);
        MovieHelper.setMovieAppActionBarTitle(this.getSupportActionBar(), title);

    }

    public void onClickDetails(View view) {

        Intent intent = new Intent("vincent.moviesapp.MoviesDetailsActivity");
        startActivity(intent);

    }
}
