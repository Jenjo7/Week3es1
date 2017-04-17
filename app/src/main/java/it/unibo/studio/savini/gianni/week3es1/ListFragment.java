package it.unibo.studio.savini.gianni.week3es1;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import java.util.List;

import it.unibo.studio.savini.gianni.week3es1.database.ExerciseDbManager;

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

    public static ListFragment newInstance() {
        return new ListFragment();
    }

    private ExerciseDbManager dbManager;
    private ArrayAdapter<Person> adapter;

    private ListView lsvPeople;
    private TextView textView;
    private Button btnAddPerson;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.list_fragment, container, false);

        dbManager = new ExerciseDbManager(getActivity());

        lsvPeople = (ListView) view.findViewById(R.id.lsv_people);
        textView = (TextView) view.findViewById(R.id.txv_placeholder);
        btnAddPerson = (Button) view.findViewById(R.id.btn_add_person);
        btnAddPerson.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (listener != null) {
                    listener.onClickAddPerson();
                }
            }
        });
        populateListView();
        return view;
    }

    private void populateListView() {
        List<Person> people = dbManager.getPeople();
        /**
         * Inizializzo l'adapter con i seguenti parametri;:
         * - Riferimento all'activity corrente
         * - un intero che specifica il tipo di lista (funzionalità ottenuta da android)
         * - la lista di persone.
         */
        adapter = new ArrayAdapter<>(getActivity(), android.R.layout.simple_list_item_1, people);
        //Setto la lista con l'adpter
        lsvPeople.setAdapter(adapter);
        /**
         * Ultima cosa, controllo se sono presenti persone nel database;
         * se non ci sono persone, rendo visibile la TextView (ove vi è scritto "Non sono presenti persone nel database")
         * altimenti, se sono presenti persone, la rendo invisibile
         */
        if(people.size() > 0) {
            textView.setVisibility(View.INVISIBLE);
        } else {
            textView.setVisibility(View.VISIBLE);
        }
    }

    public void updateListView() {
        //Ottengo la lista di persone
        List<Person> people = dbManager.getPeople();
        /**
         * Ultima cosa, controllo se sono presenti persone nel database;
         * se non ci sono persone, rendo visibile la TextView (ove vi è scritto "Non sono presenti persone nel database")
         * altimenti, se sono presenti persone, la rendo invisibile
         */
        adapter.clear();
        adapter.addAll(people);
        adapter.notifyDataSetChanged();

        if(people.size() > 0) {
            textView.setVisibility(View.INVISIBLE);
        } else {
            textView.setVisibility(View.VISIBLE);
        }

    }
    /**
     *  Soliti override onAttach e onDetach
     * @param context: Riferimento all'activity chiamante
     */
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof ListListener) {
            listener = (ListListener) context;
        } else {
            //Mettere un log
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        listener = null;
    }

}
