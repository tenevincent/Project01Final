package vincent.moviesapp.fragments;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.app.Fragment;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.net.URL;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import vincent.moviesapp.R;
import vincent.moviesapp.model.AsyncMovieResponse;
import vincent.moviesapp.model.EUrlRequestType;
import vincent.moviesapp.model.Movie;
import vincent.moviesapp.model.MoviesQueryTask;
import vincent.moviesapp.model.NetworkUtils;


public class DetailsFragment extends Fragment {


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        final View view = inflater.inflate(R.layout.fragment_details, container,false);
        return  view;

    }
}
