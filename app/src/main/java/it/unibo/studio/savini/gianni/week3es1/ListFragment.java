package it.unibo.studio.savini.gianni.week3es1;

import android.support.v4.app.Fragment;

/**
 * Created by gianni on 16/04/17.
 */

public class ListFragment extends Fragment {

    public interface ListListener {
        void onClickAddPerson();
    }

    private ListListener listener;

    public ListFragment() {

    }

    public ListFragment newInstance() {
        return new ListFragment();
    }

}
