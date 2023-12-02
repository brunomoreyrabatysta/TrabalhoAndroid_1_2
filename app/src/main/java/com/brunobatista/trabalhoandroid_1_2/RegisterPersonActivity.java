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

public class RegisterPersonActivity extends AppCompatActivity {

    private Button btnCadastrar;
    private EditText edtName, edtEmail, edtPhone;
    private TextView edtId;
    private PersonDAO personDAO;
    private boolean newRegister = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_person);

        btnCadastrar = findViewById(R.id.btnCadastrar);
        edtId = findViewById(R.id.edtId);
        edtName = findViewById(R.id.edtName);
        edtEmail = findViewById(R.id.edtEmail);
        edtPhone = findViewById(R.id.edtPhone);

        personDAO = new PersonDAO(this);

        Intent intent = getIntent();


        Integer personId = Integer.parseInt(intent.getStringExtra(getResources().getString(R.string.PARAM_KEY_PERSONID)));

        newRegister = personId == 0;

        edtId.setText(personId.toString());

        if (newRegister) {
            edtName.setText("");
            edtPhone.setText("");
            edtEmail.setText("");
        } else {
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
                    Toast.makeText(RegisterPersonActivity.this, mensagem, Toast.LENGTH_LONG).show();
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
            Toast.makeText(RegisterPersonActivity.this, "O nome deve ser preenchido!", Toast.LENGTH_LONG).show();
            return false;
        } else if (sName.length() <  5) {
            Toast.makeText(RegisterPersonActivity.this, "O nome deve conter no mínimo 5 (cinco) caracteres!", Toast.LENGTH_LONG).show();
            return false;
        }

        if (sEmail == null || sEmail.length() == 0) {
            Toast.makeText(RegisterPersonActivity.this, "O e-mail deve ser preenchido!", Toast.LENGTH_LONG).show();
            return false;
        } else if (sEmail.length() <  5) {
            Toast.makeText(RegisterPersonActivity.this, "O e-mail deve conter no mínimo 5 (cinco) caracteres!", Toast.LENGTH_LONG).show();
            return false;
        }

        if (sPhone == null || sPhone.length() == 0) {
            Toast.makeText(RegisterPersonActivity.this, "O telefone deve ser preenchido!", Toast.LENGTH_LONG).show();
            return false;
        } else if (sPhone.length() <  5) {
            Toast.makeText(RegisterPersonActivity.this, "O telefone deve conter no mínimo 5 (cinco) caracteres!", Toast.LENGTH_LONG).show();
            return false;
        }

        Person person = new Person(sName, sEmail, sPhone);

        List<Person> list =personDAO.getByNameEmailPhone(person);
        System.out.println("Teste" + list.size());
        if (list.size() > 0) {
            Toast.makeText(RegisterPersonActivity.this, "Já existe um cadastro com o mesmo nome, e-mail e telefone!", Toast.LENGTH_LONG).show();
            return false;
        }

        return true;
    }
}