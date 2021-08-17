package com.dio.agendaalura.ui.activitys;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.widget.Button;
import android.widget.EditText;

import com.dio.agendaalura.R;
import com.dio.agendaalura.dao.AlunosDAO;
import com.dio.agendaalura.model.Aluno;

import static com.dio.agendaalura.ui.activitys.ConstantesAcitivities.CHAVE_ALUNO;

public class FormularioAlunosActivity extends AppCompatActivity {

    private static final String TITULO_APPBAR_NOVO_ALUNO = "Novo aluno";
    private static final String TITULO_APPBAR_EDITA_ALUNO = "Edita aluno";
    private EditText campoNome;
    private EditText campoTelefone;
    private EditText campoEmail;
    private final AlunosDAO dao = new AlunosDAO();
    private Aluno alunoClicado;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_formulario_alunos);

        inicializacaoDosCampos();
        configuraBotaoSalvar();

        carregaAluno();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.acitivity_formulario_aluno_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    private void carregaAluno() {
        Intent dados = getIntent();
        if(dados.hasExtra(CHAVE_ALUNO)){
        alunoClicado = (Aluno) dados.getSerializableExtra(CHAVE_ALUNO);
            preencheCampos();
            setTitle(TITULO_APPBAR_EDITA_ALUNO);
        } else{
            alunoClicado = new Aluno();
            setTitle(TITULO_APPBAR_NOVO_ALUNO);
         }
    }

    private void preencheCampos() {
        campoNome.setText(alunoClicado.getNome());
        campoTelefone.setText(alunoClicado.getTelefone());
        campoEmail.setText(alunoClicado.getEmail());
    }


    private void configuraBotaoSalvar() {
        Button botaoSalvar = findViewById(R.id.acitivity_fomulario_salvar);
        botaoSalvar.setOnClickListener((view) -> {
        preencheAluno();
            finalizaFormulario();

//            Aluno alunoCriado = criaAluno();
//            salvaAluno(alunoCriado);

        });
    }

    private void finalizaFormulario() {
        if(alunoClicado.temIdValido()){
            dao.edita(alunoClicado);
        }else{
            dao.salva(alunoClicado);
        }

        finish();
    }

    private void inicializacaoDosCampos() {
        campoNome = findViewById(R.id.acitivity_fomulario_nome);
        campoTelefone = findViewById(R.id.acitivity_fomulario_telefone);
        campoEmail = findViewById(R.id.acitivity_fomulario_email);
    }

//    private void salvaAluno(Aluno alunoCriado) {
//        dao.salva(alunoCriado);
//
//        finish();
//    }

    private void preencheAluno() {
        String nome = campoNome.getText().toString();
        String telefone = campoTelefone.getText().toString();
        String email = campoEmail.getText().toString();

        alunoClicado.setNome(nome);
        alunoClicado.setTelefone(telefone);
        alunoClicado.setEmail(email);
    }

}