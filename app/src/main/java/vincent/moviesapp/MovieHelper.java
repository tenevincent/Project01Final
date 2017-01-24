package vincent.moviesapp;

import android.support.v7.app.ActionBar;

/**
 * Created by Tene on 24.01.2017.
 */

public class MovieHelper {


    public static void setMovieAppActionBarTitle(ActionBar actionbar, String title) {
        if(null != actionbar)
            actionbar.setTitle(title);
    }
}
