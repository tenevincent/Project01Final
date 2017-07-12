package vincent.moviesapp.model;

import android.app.Activity;

/** This interface should be implemented to listen the terminatation
 *  of the async http query task
 * Created by vincent on 25.01.2017.
 */
public interface IAsyncMovieRequestFinished {

    /** process the result of the url request
     *
     * @param context activity
     * @param output http response
     */
    void processMoviesQueryResults(Activity context, String output);
}
