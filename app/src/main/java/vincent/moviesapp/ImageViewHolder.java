package vincent.moviesapp;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import org.w3c.dom.Text;


/** Image Holder for the Recyclerview!
 *
 */
public class ImageViewHolder extends RecyclerView.ViewHolder {
    public ImageView imageView;

    public ImageViewHolder(View itemView) {
        super(itemView);
        imageView = (ImageView) itemView.findViewById(R.id.imageMovie);
    }

}
