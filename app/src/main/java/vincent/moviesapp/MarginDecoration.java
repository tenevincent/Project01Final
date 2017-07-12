package vincent.moviesapp;

import android.content.Context;
import android.graphics.Rect;
import android.support.v7.widget.RecyclerView;
import android.view.View;

/** Helper method, used to set custom margin for the reclyclerview UI
 * Created by vincent on 26.01.2017.
 */

public class MarginDecoration extends RecyclerView.ItemDecoration  {

    private int margin;

    public MarginDecoration(Context context) {
        margin = context.getResources().getDimensionPixelSize(R.dimen.item_margin);
    }

    @Override
    public void getItemOffsets(
            Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
        outRect.set(margin, margin, margin, margin);
    }
}
