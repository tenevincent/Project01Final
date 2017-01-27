package vincent.moviesapp.fragments;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.app.Fragment;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.net.URL;

import vincent.moviesapp.MarginDecoration;
import vincent.moviesapp.NumberedAdapter;
import vincent.moviesapp.R;
import vincent.moviesapp.model.AsyncMovieResponse;
import vincent.moviesapp.model.EUrlRequestType;
import vincent.moviesapp.model.MovieMainApp;
import vincent.moviesapp.model.MoviesQueryTask;
import vincent.moviesapp.model.NetworkUtils;


public class MainFragment extends Fragment {

    MovieMainApp movieApp;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_main, container,false);

        RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.gridViewMovies);
        recyclerView.addItemDecoration(new MarginDecoration(view.getContext()));
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new GridLayoutManager(view.getContext(), 2));
        recyclerView.setAdapter(new NumberedAdapter(view.getContext(),150));

        // when the device is rotated a query showld not be hit again
        if(null == movieApp)
            queryMoviesFromDatabase();

        return  view;
    }

    private void queryMoviesFromDatabase() {

        URL githubSearchUrl = NetworkUtils.buildUrl(EUrlRequestType.BY_TOP_RATED);
        MoviesQueryTask queryTask = new MoviesQueryTask(new AsyncMovieResponse()
        {
            @Override
            public void processMoviesQueryResults(String output) {


                    movieApp = new MovieMainApp(output);

            }
        }
        );
        queryTask.execute(githubSearchUrl) ;
    }


}
