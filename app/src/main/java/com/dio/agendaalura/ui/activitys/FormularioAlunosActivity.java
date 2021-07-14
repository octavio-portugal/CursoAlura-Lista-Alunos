package com.dio.agendaalura.ui.activitys;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.dio.agendaalura.R;
import com.dio.agendaalura.model.Aluno;

public class FormularioAlunosActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_formulario_alunos);
        setTitle("Formulario Adição de Alunos");

        final EditText campoNome = findViewById(R.id.acitivity_fomulario_nome);
        final EditText campoTelefone = findViewById(R.id.acitivity_fomulario_telefone);
        final EditText campoEmail = findViewById(R.id.acitivity_fomulario_email);

        Button botaoSalvar = findViewById(R.id.acitivity_fomulario_salvar);
        botaoSalvar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String nome = campoNome.getText().toString();
                String telefone = campoTelefone.getText().toString();
                String email = campoEmail.getText().toString();

                Aluno alunoCriado = new Aluno(nome, telefone, email);
                Toast.makeText(FormularioAlunosActivity.this,
                        alunoCriado.getNome() + "-" +
                                alunoCriado.getTelefone() +
                                "-" + alunoCriado.getEmail(),
                        Toast.LENGTH_SHORT).show();
            }
        });


    }

}