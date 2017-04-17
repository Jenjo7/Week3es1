package it.unibo.studio.savini.gianni.week3es1.database;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

import it.unibo.studio.savini.gianni.week3es1.Person;

/**
 * Created by gianni.savini on 16/03/2017.
 */
public class ExerciseDbManager {

    private ExerciseDbHelper dbHelper;

    public ExerciseDbManager(Context context) {
        dbHelper = new ExerciseDbHelper(context);
    }

    public boolean addPerson(Person person) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        //db.insert ritorna il numero di collonne che sono state aggiunte
        return db.insert(Person.TABLE_NAME, null, person.getContentValues()) > 0;
    }

    public boolean updatePerson(Person person) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        return db.update(Person.TABLE_NAME, person.getContentValues(), Person._ID + " = ? ",
                new String[]{String.valueOf(person.getId())}) > 0;
    }

    public boolean deletePerson(Person person) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
         /*
        db.delete ritorna il numero di collonne che sono state cancellate...
        quindi se tutto va bene il numero delle colonne elimi
         */
        return db.delete(Person.TABLE_NAME, Person._ID + " = ? ",
                new String[]{String.valueOf(person.getId())}) > 0;
    }

    public List<Person> getPeople() {
        SQLiteDatabase db = dbHelper.getReadableDatabase();

        List<Person> people = new ArrayList<>();
        Cursor cursor = null;


        try {
            String query = "SELECT * FROM " + Person.TABLE_NAME + " ORDER BY " + Person.COLUMN_NAME + " ASC";
            //
            cursor = db.rawQuery(query, null);
            while (cursor.moveToNext()) {
                Person person = new Person(cursor);
                people.add(person);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if(cursor != null) {
                cursor.close();
            }
            db.close();
        }

        return people;
    }

}
