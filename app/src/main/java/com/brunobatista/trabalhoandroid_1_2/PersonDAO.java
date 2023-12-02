package com.brunobatista.trabalhoandroid_1_2;

import android.content.Context;

import java.util.List;

public class PersonDAO {

    private DatabaseHelper databaseHelper;

    public PersonDAO(Context context) {
        databaseHelper = new DatabaseHelper(context);
    }

    public long savePerson (Person person) {
        if (person.getId() == 0) {
            return databaseHelper.addPerson(person);
        } else {
            return databaseHelper.updatePerson(person);
        }
    }

    public long addPerson(Person person) {
        return databaseHelper.addPerson(person);
    }

    public long updatePerson(Person person) {
        return databaseHelper.updatePerson(person);
    }

    public long deletePerson(Person person) {
        return databaseHelper.deletePerson(person);
    }

    public long deletePerson(int id) {
        return databaseHelper.deletePerson(id);
    }

    public List<Person> getAllPerson() {
        return databaseHelper.getAllPerson();
    }

    public Person getById(int id) {
        return databaseHelper.getByIdPerson(id);
    }

    public List<Person> getByNameEmailPhone (Person person) { return databaseHelper.getByNameEmailPhone(person); }
}
