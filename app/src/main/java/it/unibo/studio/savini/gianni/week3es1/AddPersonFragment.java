package it.unibo.studio.savini.gianni.week3es1;

import android.content.Context;
import android.os.Bundle;
import android.provider.Settings;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import it.unibo.studio.savini.gianni.week3es1.database.ExerciseDbManager;

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

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if(context instanceof AddPersonListener) {
            listener = (AddPersonListener) context;
        } else {

        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        this.listener = null;
    }

    /**
     * Questo codice serve a creare l'interfaccia grafica della classe {@link AddPersonFragment} e
     * settare i vari componenti di input e output
     */

    private EditText edtName;
    private EditText edtSurame;
    private EditText edtAge;
    private EditText edtEmail;
    private Button btnAdd;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.add_person_fragment, container, false);
        /**
         * Inizializzo gli elementi della GUI
         */
        edtName = (EditText) rootView.findViewById(R.id.edt_name);
        edtSurame = (EditText) rootView.findViewById(R.id.edt_surname);
        edtAge = (EditText) rootView.findViewById(R.id.edt_age);
        edtEmail = (EditText) rootView.findViewById(R.id.edt_email);
        btnAdd = (Button) rootView.findViewById(R.id.btn_add_person);

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name = edtName.getText().toString();
                String surname = edtSurame.getText().toString();
                int age = Integer.valueOf(edtAge.getText().toString());
                String email = edtEmail.getText().toString();

                if(name.isEmpty() || surname.isEmpty() || email.isEmpty()) {
                    Toast.makeText(getActivity(), "Attenzione! Compilare tutti i campi", Toast.LENGTH_SHORT).show();
                } else {
                    Person person = new Person(name, surname,email, age);
                    ExerciseDbManager dbManager = new ExerciseDbManager(getActivity());
                    dbManager.addPerson(person);

                    if (listener != null) {
                        listener.onPersonAdded();
                    }
                }
            }
        });

        return rootView;
    }
}
