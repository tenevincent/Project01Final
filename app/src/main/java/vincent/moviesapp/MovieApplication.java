package vincent.moviesapp;

import android.app.Application;

import vincent.moviesapp.model.MovieMainApp;

/** Movie Application serves as global object context for this application
 * Created by Tene on 01.02.2017.
 */

public class MovieApplication extends Application {

    private MovieMainApp movieMainApp;


    /** Gets the global movie object
     *
     * @return global object
     */
    public MovieMainApp getMovieMainApp() {
        return movieMainApp;
    }


    /** Sets the object
     *
     * @param movieMainApp
     */
    public void setMovieMainApp(MovieMainApp movieMainApp) {
        this.movieMainApp = movieMainApp;
    }
}
