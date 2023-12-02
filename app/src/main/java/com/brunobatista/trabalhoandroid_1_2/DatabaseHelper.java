package com.brunobatista.trabalhoandroid_1_2;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

public class DatabaseHelper extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 1;

    private static  final String DATABASE_NAME = "PersonDB";

    private static final String TABLE_NAME = "person";

    private static  final String KEY_ID = "id";

    private static final String KEY_NAME = "name";

    private static final String KEY_EMAIL = "email";

    private static final String KEY_PHONE = "phone";

    public DatabaseHelper(Context context) {
        super (context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String createTabelSQL = "CREATE TABLE IF NOT EXISTS " + TABLE_NAME + " (" +
                KEY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                KEY_NAME + " TEXT," +
                KEY_EMAIL + " TEXT, " +
                KEY_PHONE + " TEXT )";

        db.execSQL(createTabelSQL);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }

    public long addPerson(Person person) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_NAME, person.getName());
        values.put(KEY_EMAIL, person.getEmail());
        values.put(KEY_PHONE, person.getPhone());

        long ret = db.insert(TABLE_NAME, null, values);
        db.close();
        return ret;
    }

    public long updatePerson(Person person) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_NAME, person.getName());
        values.put(KEY_EMAIL, person.getEmail());
        values.put(KEY_PHONE, person.getPhone());

        long ret = db.update(TABLE_NAME,
                values,
                KEY_ID + " = ?",
                new String[] {String.valueOf(person.getId())}
        );

        db.close();
        return ret;
    }

    public long deletePerson(Person person) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_NAME, person.getName());
        values.put(KEY_EMAIL, person.getEmail());
        values.put(KEY_PHONE, person.getPhone());

        long ret = db.delete(TABLE_NAME,
                KEY_ID + " = ?",
                new String[] {String.valueOf(person.getId())}
        );
        db.close();
        return ret;
    }

    public long deletePerson(int id) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();

        long ret = db.delete(TABLE_NAME,
                KEY_ID + " = ?",
                new String[] {String.valueOf(id)}
        );
        db.close();
        return ret;
    }

    public List<Person> getAllPerson() {
        List<Person> personList = new ArrayList<>();
        String SQL = "SELECT * FROM " + TABLE_NAME;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(SQL, null);

        if (cursor.moveToFirst()) {
            do {
                Person person = new Person(
                        cursor.getInt(0),
                        cursor.getString(1),
                        cursor.getString(2),
                        cursor.getString(3)
                );
                personList.add(person);
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return personList;
    }

    public Person getByIdPerson(int id) {
        Person person = new Person();
        String SQL = "SELECT * FROM " + TABLE_NAME + " WHERE " + KEY_ID + " = " + id;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(SQL, null);

        if (cursor.moveToFirst()) {
            person = new Person(
                    cursor.getInt(0),
                    cursor.getString(1),
                    cursor.getString(2),
                    cursor.getString(3)
            );
        }
        cursor.close();
        db.close();
        return person;
    }

    public List<Person> getByNameEmailPhone(Person person) {
        List<Person> personList = new ArrayList<>();
        String SQL = "SELECT * FROM " + TABLE_NAME;
        SQL += " WHERE " + KEY_NAME + " = '" + person.getName() + "' ";
        SQL += "   AND " + KEY_EMAIL + " = '" + person.getEmail() + "' ";
        SQL += "   AND " + KEY_PHONE + " = '" + person.getPhone() + "' ";
        SQL += "   AND " + KEY_ID + " <> " + person.getId() + " ";

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(SQL, null);

        if (cursor.moveToFirst()) {
            System.out.println("Teste1" );
            do {
                System.out.println("Teste2" );
                Person p = new Person(
                        cursor.getInt(0),
                        cursor.getString(1),
                        cursor.getString(2),
                        cursor.getString(3)
                );
                personList.add(p);
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return personList;
    }
}
