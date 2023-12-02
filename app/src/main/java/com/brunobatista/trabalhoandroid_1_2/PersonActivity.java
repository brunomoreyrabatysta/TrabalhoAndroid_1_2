package com.brunobatista.trabalhoandroid_1_2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

public class PersonActivity extends AppCompatActivity {

    private Button btnCadastrar;
    private EditText edtName, edtEmail, edtPhone;
    private TextView edtTitleId, edtId, edtTitleRegister;
    private PersonDAO personDAO;
    private boolean newRegister = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_person);

        edtTitleRegister = findViewById(R.id.edtTitleRegister);
        btnCadastrar = findViewById(R.id.btnCadastrar);
        edtTitleId = findViewById(R.id.edtTitleId);
        edtId = findViewById(R.id.edtId);
        edtName = findViewById(R.id.edtName);
        edtEmail = findViewById(R.id.edtEmail);
        edtPhone = findViewById(R.id.edtPhone);

        personDAO = new PersonDAO(this);

        Intent intent = getIntent();

        System.out.println("TESTE");
        System.out.println(intent);
        System.out.println(intent.getStringExtra("KEY_PERSONID"));

        Integer personId = Integer.parseInt(intent.getStringExtra("KEY_PERSONID"));

        newRegister = personId == 0;

        edtId.setText(personId.toString());

        if (newRegister) {
            edtTitleRegister.setText("NOVO CADASTRO");
            edtName.setText("");
            edtPhone.setText("");
            edtEmail.setText("");

            edtTitleId.setVisibility(View.INVISIBLE);
            edtId.setVisibility(View.INVISIBLE);
        } else {
            edtTitleRegister.setText("ALTERAR CADASTRO");

            Person person = personDAO.getById(personId);

            edtName.setText(person.getName());
            edtPhone.setText(person.getPhone());
            edtEmail.setText(person.getEmail());
        }

        btnCadastrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(Validar()) {
                    int personId = 0;
                    if (!newRegister) {
                        try {
                            personId = Integer.parseInt(edtId.getText().toString());
                        } catch (Exception ex) {
                            personId = 0;
                        }
                    }
                    Person person = new Person(
                            personId,
                            edtName.getText().toString().trim(),
                            edtEmail.getText().toString().trim(),
                            edtPhone.getText().toString().trim()
                    );

                    long ret = personDAO.savePerson(person);

                    String mensagem = "Pessoa " + (newRegister ? "inserida" : "alterada") + " com sucesso!!!";
                    Toast.makeText(PersonActivity.this, mensagem, Toast.LENGTH_LONG).show();

                    System.out.println("CADASTRO");

                    List<Person> personList = personDAO.getAllPerson();
                    for (Person p: personList) {
                        System.out.println("ID: " + p.getId());
                        System.out.println("Nome: " + p.getName());
                        System.out.println("E-mail: " + p.getEmail());
                        System.out.println("Phone: " + p.getPhone());
                    }
                }
            }
        });
    }

    private boolean Validar() {
        String sName = edtName.getText().toString().trim();
        String sEmail = edtEmail.getText().toString().trim();
        String sPhone = edtPhone.getText().toString().trim();

        if (!newRegister) {

        }

        if (sName == null || sName.length() == 0) {
            Toast.makeText(PersonActivity.this, "O nome deve ser preenchido!", Toast.LENGTH_LONG).show();
            return false;
        } else if (sName.length() <  5) {
            Toast.makeText(PersonActivity.this, "O nome deve conter no mínimo 5 (cinco) caracteres!", Toast.LENGTH_LONG).show();
            return false;
        }

        if (sEmail == null || sEmail.length() == 0) {
            Toast.makeText(PersonActivity.this, "O e-mail deve ser preenchido!", Toast.LENGTH_LONG).show();
            return false;
        } else if (sEmail.length() <  5) {
            Toast.makeText(PersonActivity.this, "O e-mail deve conter no mínimo 5 (cinco) caracteres!", Toast.LENGTH_LONG).show();
            return false;
        }

        if (sPhone == null || sPhone.length() == 0) {
            Toast.makeText(PersonActivity.this, "O telefone deve ser preenchido!", Toast.LENGTH_LONG).show();
            return false;
        } else if (sPhone.length() <  5) {
            Toast.makeText(PersonActivity.this, "O telefone deve conter no mínimo 5 (cinco) caracteres!", Toast.LENGTH_LONG).show();
            return false;
        }

        return true;
    }
}