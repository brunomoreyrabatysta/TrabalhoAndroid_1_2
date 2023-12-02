package com.brunobatista.trabalhoandroid_1_2;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.ContentResolver;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class ListPersonActivity extends AppCompatActivity implements PersonAdapter.IOnItemClickListener {

    private ArrayList<Person> personArrayList;
    private PersonAdapter personAdapter;

    private RecyclerView recyclerView;

    private PersonDAO personDAO;

    private Button btnAddPerson;

    @Override
    protected  void onRestart() {
        super.onRestart();

        updateRecyclerView();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_person);

        recyclerView = findViewById(R.id.recyclerView);
        btnAddPerson = findViewById(R.id.btnAddPerson);

        personArrayList = new ArrayList<>();
        personAdapter = new PersonAdapter(personArrayList, this);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(personAdapter);

        personDAO = new PersonDAO(this);

        getPersonList();
        updateRecyclerView();

        btnAddPerson.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ListPersonActivity.this, PersonActivity.class);

                intent.putExtra(getResources().getString(R.string.PARAM_KEY_PERSONID), "0");

                startActivity(intent);
                //finish();

                updateRecyclerView();
            }
        });
    }

    private List<Person> getPersonList() {
        return personDAO.getAllPerson();
    }

    private void updateRecyclerView() {
        personArrayList.clear();
        personArrayList.addAll(getPersonList());
        personAdapter.notifyDataSetChanged();
    }

    @Override
    public void onItemClick(int position) {
        Person person = personArrayList.get(position);

        Intent intent = new Intent(ListPersonActivity.this, PersonActivity.class);

        intent.putExtra(getResources().getString(R.string.PARAM_KEY_PERSONID), Integer.toString(person.getId()));

        startActivity(intent);
        //finish();

        updateRecyclerView();
    }

    @Override
    public void onDeleteClick(int position) {
        Person person = personArrayList.get(position);

        personDAO.deletePerson(person);

        Toast.makeText(ListPersonActivity.this, "Pessoa excluída com sucesso!", Toast.LENGTH_LONG).show();

        updateRecyclerView();
    }
}