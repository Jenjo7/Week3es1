package it.unibo.studio.savini.gianni.week3es1;

import android.content.ContentValues;
import android.database.Cursor;
import android.provider.BaseColumns;

/**
 * Created by gianni.savini on 16/03/2017.
 */
public class Person implements BaseColumns {
    //variabili statiche  per la gestione della tabella
    public static final String TABLE_NAME = "people";

    public static final String COLUMN_NAME = "name";
    public static final String COLUMN_SURNAME = "surname";
    public static final String COLUMN_AGE = "age";
    public static final String COLUMN_EMAIL = "e_mail";

    private int id;
    private String name;
    private String surname;
    private int age;
    private String email;

    public Person(final String name, final String surname, final String email, final int age) {
        this.name = name;
        this.surname = surname;
        this.email = email;
        this.age = age;
    }

    public Person(Cursor cursor) {
        //Chiamo il costruttore sopra, con i parametri del cursor
        this(cursor.getString(cursor.getColumnIndex(COLUMN_NAME)), cursor.getString(cursor.getColumnIndex(COLUMN_SURNAME)),
                cursor.getString(cursor.getColumnIndex(COLUMN_EMAIL)), cursor.getInt(cursor.getColumnIndex(COLUMN_AGE)));
        this.id = cursor.getInt(cursor.getColumnIndex(_ID));
    }

    public ContentValues getContentValues() {
        ContentValues cv = new ContentValues();
        cv.put(COLUMN_NAME, this.name);
        cv.put(COLUMN_SURNAME, this.surname);
        cv.put(COLUMN_AGE, this.age);
        cv.put(COLUMN_EMAIL, this.email);
        return cv;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public int getAge() {
        return age;
    }

    public String getEmail() {
        return email;
    }

    @Override
    public String toString() {
        return surname + " " + name + " - " + String.valueOf(age) + " - " + email;
    }
}
