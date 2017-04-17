package it.unibo.studio.savini.gianni.week3es1;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;


public class MainActivity extends AppCompatActivity implements ListFragment.ListListener, AddPersonFragment.AddPersonListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        addFragment(ListFragment.newInstance(), false);
    }

    @Override
    public void onClickAddPerson() {
        replaceFragment(AddPersonFragment.newInstance(), true);
    }

    @Override
    public void onPersonAdded() {
        //Creo il gestrore del fragment
        FragmentManager manager = getSupportFragmentManager();
        //Controllo se nello stack ci siano altri fragment impilati
        if(manager.getBackStackEntryCount() > 0) {
            //Se ci sono li tolgo dalla cima dello stack
            manager.popBackStackImmediate();
        }
        Fragment fragment = manager.findFragmentById(R.id.fragment_container);
        if(fragment != null && fragment instanceof ListFragment) {
            ((ListFragment) fragment).updateListView();
        }
    }

    protected void addFragment(Fragment fragment, boolean back) {
        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        transaction.add(R.id.fragment_container, fragment);
        if(back) {
            transaction.addToBackStack(null);
        }
        transaction.commit();
    }

    protected void replaceFragment(Fragment fragment, boolean back) {
        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        transaction.replace(R.id.fragment_container, fragment);
        if(back) {
            transaction.addToBackStack(null);
        }
        transaction.commit();
    }

}
