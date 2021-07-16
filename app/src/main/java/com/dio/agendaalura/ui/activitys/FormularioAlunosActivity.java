package com.dio.agendaalura.ui.activitys;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import com.dio.agendaalura.R;
import com.dio.agendaalura.dao.AlunosDAO;
import com.dio.agendaalura.model.Aluno;

import java.io.Serializable;

public class FormularioAlunosActivity extends AppCompatActivity {

    public static final String TITULO_APPBAR = "Novo aluno";
    private EditText campoNome;
    private EditText campoTelefone;
    private EditText campoEmail;
    private final AlunosDAO dao = new AlunosDAO();
    private Aluno alunoClicado;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_formulario_alunos);
        setTitle(TITULO_APPBAR);
        inicializacaoDosCampos();
        configuraBotaoSalvar();

        Intent dados = getIntent();
        alunoClicado = (Aluno) dados.getSerializableExtra("aluno");
        campoNome.setText(alunoClicado.getNome());
        campoTelefone.setText(alunoClicado.getTelefone());
        campoEmail.setText(alunoClicado.getEmail());


    }

    private void configuraBotaoSalvar() {
        Button botaoSalvar = findViewById(R.id.acitivity_fomulario_salvar);
        botaoSalvar.setOnClickListener((view) -> {
        preencheAluno();
        dao.edita(alunoClicado);
        finish();

//            Aluno alunoCriado = criaAluno();
//            salvaAluno(alunoCriado);

        });
    }

    private void inicializacaoDosCampos() {
        campoNome = findViewById(R.id.acitivity_fomulario_nome);
        campoTelefone = findViewById(R.id.acitivity_fomulario_telefone);
        campoEmail = findViewById(R.id.acitivity_fomulario_email);
    }

    private void salvaAluno(Aluno alunoCriado) {
        dao.salva(alunoCriado);

        finish();
    }

    private void preencheAluno() {
        String nome = campoNome.getText().toString();
        String telefone = campoTelefone.getText().toString();
        String email = campoEmail.getText().toString();

        alunoClicado.setNome(nome);
        alunoClicado.setTelefone(telefone);
        alunoClicado.setEmail(email);
    }

}