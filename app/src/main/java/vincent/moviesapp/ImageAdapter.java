package vincent.moviesapp;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import vincent.moviesapp.model.Movie;

/**
 * Created by vincent on 26.01.2017.
 */

public class ImageAdapter extends RecyclerView.Adapter<ImageViewHolder> {

    private ArrayList<Movie> movies;
    private Context context ;

    public ImageAdapter(Context ctx, ArrayList<Movie>  listofMovies) {
        this.context = ctx;
        this.movies = listofMovies;

    }

    @Override
    public ImageViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item, parent, false);
        return new ImageViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ImageViewHolder holder, final int position) {


        final Movie movie = movies.get(position);

        // Sets the title
        String title = movie.getTitle();

        // Sets the image
        Picasso.with(context).load(movie.getPosterAbsolutURL()).into(holder.imageView);

        holder.imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Toast.makeText(holder.imageView.getContext(), movie.getTitle(), Toast.LENGTH_SHORT).show();

                Intent intent = new Intent("vincent.moviesapp.MoviesDetailsActivity");

                Bundle bundle = new Bundle();
                bundle.putParcelable("movie",movie);
                intent.putExtras(bundle);

                context.startActivity(intent);

            }
        });
    }

    @Override
    public int getItemCount() {
        return movies.size();
    }

}
