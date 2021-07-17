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

import static com.dio.agendaalura.ui.activitys.ConstantesAcitivities.CHAVE_ALUNO;

public class ListaAlunosActivity extends AppCompatActivity{

    public static final String TITULO_APPBAR = "Lista de alunos";
    private final AlunosDAO dao = new AlunosDAO();
    private ArrayAdapter<Aluno> adapter;


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

        configuraListView();
    }

    private void configuraListView() {
        ListView listaAlunos = findViewById(R.id.listview_lista_alunos);
        final List<Aluno> alunos = dao.todos();
        configuraAdapter(listaAlunos, alunos);
        configuraListenerDeClickPorItem(listaAlunos);
        listaAlunos.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int posicao, long id) {
                Aluno alunoEscolhido = (Aluno) adapterView.getItemAtPosition(posicao);
                dao.remove(alunoEscolhido);
                adapter.remove(alunoEscolhido);
                return true;
            }
        });
    }

    private void configuraListenerDeClickPorItem(ListView listaAlunos) {
        listaAlunos.setOnItemClickListener(new AdapterView.OnItemClickListener () {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int posicao, long id) {
                Aluno alunoEscolhido = (Aluno) adapterView.getItemAtPosition(posicao);
                Intent vaiParaEditarAluno = new Intent(ListaAlunosActivity.this, FormularioAlunosActivity.class);
                vaiParaEditarAluno.putExtra(CHAVE_ALUNO, alunoEscolhido);
                startActivity(vaiParaEditarAluno);

                Log.i("posição aluno:", "" + alunoEscolhido);
            }
        });
    }

    private void configuraAdapter(ListView listaAlunos, List<Aluno> alunos) {
        adapter = new ArrayAdapter<>(
                this,
                android.R.layout.simple_list_item_1,
                alunos);
        listaAlunos.setAdapter(adapter);
    }
}