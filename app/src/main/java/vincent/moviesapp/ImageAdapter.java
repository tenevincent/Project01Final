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

import vincent.moviesapp.model.Movie;
import vincent.moviesapp.model.MovieMainApp;


/** ImageAdapter for the Recyclerview
 *
 */
public class ImageAdapter extends RecyclerView.Adapter<ImageViewHolder> {

    private  MovieMainApp movieApp;
    private Context context ;

    public ImageAdapter(Context ctx) {
        this.context = ctx;
        movieApp = ((MovieApplication) this.context.getApplicationContext()).getMovieMainApp();
    }

    @Override
    public ImageViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item, parent, false);
        return new ImageViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ImageViewHolder holder, final int position) {

        final Movie movie = movieApp.getListOfMovies().get(position);
        // Sets the title
        String title = movie.getTitle();
        // Sets the image
        Picasso.with(context).load(movie.getPosterAbsolutURL()).into(holder.imageView);
        holder.imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Start the Details Movie Activity for displaying details about the current movie!
                Intent intent = new Intent("vincent.moviesapp.MoviesDetailsActivity");
                Bundle bundle = new Bundle();
                bundle.putInt(MainActivity.MOVIE_DETAIL_KEY,movie.getId());
                intent.putExtras(bundle);
                view.getContext().startActivity(intent);

            }
        });
    }



    @Override
    public int getItemCount() {
        if(null != movieApp && null != movieApp.getListOfMovies())
            return movieApp.getListOfMovies().size();
        else
            return  0;

    }

}
