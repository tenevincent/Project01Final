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

import vincent.moviesapp.MarginDecoration;
import vincent.moviesapp.NumberedAdapter;
import vincent.moviesapp.R;





public class MainFragment extends Fragment {


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_main, container,false);

        RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.gridViewMovies);
        recyclerView.addItemDecoration(new MarginDecoration(view.getContext()));
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new GridLayoutManager(view.getContext(), 2));
        recyclerView.setAdapter(new NumberedAdapter(view.getContext(),150));


        return  view;
    }


}
