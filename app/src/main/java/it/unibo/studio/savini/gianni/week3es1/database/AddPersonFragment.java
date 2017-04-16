package it.unibo.studio.savini.gianni.week3es1.database;

import android.support.v4.app.Fragment;

/**
 * Created by gianni on 16/04/17.
 */

public class AddPersonFragment extends Fragment {

    public interface AddPersonListener {
        void onPersonAdded();
    }

    private AddPersonListener listener;

    public AddPersonFragment() {

    }

    public static AddPersonFragment newInstance() {
        return new AddPersonFragment();
    }
}
