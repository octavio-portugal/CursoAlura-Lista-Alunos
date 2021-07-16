package com.dio.agendaalura.ui.activitys;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.dio.agendaalura.R;
import com.dio.agendaalura.dao.AlunosDAO;
import com.dio.agendaalura.model.Aluno;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

public class ListaAlunosActivity extends AppCompatActivity{

    public static final String TITULO_APPBAR = "Lista de alunos";
    private final AlunosDAO dao = new AlunosDAO();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_alunos);
        setTitle(TITULO_APPBAR);
        dao.salva(new Aluno("Octavio", "129999-9999", "ai@gmail.com"));
        dao.salva(new Aluno("Rafa", "129999-9999", "ui@gmail.com"));


        configuraBotaoFlutuante();


        /**

        //setando o meu componente de visualização

        TextView primeiroAluno = findViewById(R.id.texto1);
        TextView segundoAluno = findViewById(R.id.texto2);
        TextView terceiroAluno = findViewById(R.id.texto3);
        //setando o valor da varial
        primeiroAluno.setText(alunos.get(0));
        segundoAluno.setText(alunos.get(1));
        terceiroAluno.setText(alunos.get(2));
         */
    }

    private void configuraBotaoFlutuante() {
        FloatingActionButton addAluno = findViewById(R.id.floatingActionButton_add_aluno);
        addAluno.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                abreFormularioAlunoActivity();
            }
        });
    }

    private void abreFormularioAlunoActivity() {
        startActivity(new Intent(this,
                FormularioAlunosActivity.class));
    }

    @Override
    protected void onResume() {
        super.onResume();

        configuraAdapterListView();
    }

    private void configuraAdapterListView() {
        ListView listaAlunos = findViewById(R.id.listview_lista_alunos);
        final List<Aluno> alunos = dao.todos();
        listaAlunos.setAdapter(new ArrayAdapter<>(
                this,
                android.R.layout.simple_list_item_1,
                alunos));
        listaAlunos.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int posicao, long id) {
                Aluno alunoEscolhido = alunos.get(posicao);
                Intent vaiParaEditarAluno = new Intent(ListaAlunosActivity.this, FormularioAlunosActivity.class);
                vaiParaEditarAluno.putExtra("aluno", alunoEscolhido);
                startActivity(vaiParaEditarAluno);

                Log.i("posição aluno:", "" + alunoEscolhido);
            }
        });
    }
}