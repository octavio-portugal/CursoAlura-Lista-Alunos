package com.dio.agendaalura.ui.activitys;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.ContextMenu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.annotation.NonNull;
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
        configuraListView();
        dao.salva(new Aluno("Octavio", "129999-9999", "ai@gmail.com"));
        dao.salva(new Aluno("Rafa", "129999-9999", "ui@gmail.com"));
        configuraBotaoFlutuante();
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        getMenuInflater()
                .inflate(R.menu.acitivy_lista_alunos_menu, menu);
    }

    @Override
    public boolean onContextItemSelected(@NonNull MenuItem item) {
        int itemId = item.getItemId();
        if (itemId == R.id.activity_lista_aluno_remover){
            AdapterView.AdapterContextMenuInfo menuInfo =
                    (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
            Aluno alunoEscolhido = adapter.getItem(menuInfo.position);
            removeAluno(alunoEscolhido);
        }
        return super.onContextItemSelected(item);
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
        atualizaAdapter();

        ;
    }

    private void atualizaAdapter() {
        adapter.clear();
        adapter.addAll(dao.todos());
    }

    private void configuraListView() {
        ListView listaAlunos = findViewById(R.id.listview_lista_alunos);
        configuraAdapter(listaAlunos);
        configuraListenerDeClickPorItem(listaAlunos);
//        configuraCliqueLongoLista(listaAlunos);
        registerForContextMenu(listaAlunos);

    }

//    private void configuraCliqueLongoLista(ListView listaAlunos) {
//        listaAlunos.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
//            @Override
//            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int posicao, long id) {
//                Aluno alunoEscolhido = (Aluno) adapterView.getItemAtPosition(posicao);
//                removeAluno(alunoEscolhido);
//                return false;
//            }
//        });
//    }

    private void removeAluno(Aluno alunoEscolhido) {
        dao.remove(alunoEscolhido);
        adapter.remove(alunoEscolhido);
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

    private void configuraAdapter(ListView listaAlunos) {
        adapter = new ArrayAdapter<>(
                this,
                android.R.layout.simple_list_item_1);
        listaAlunos.setAdapter(adapter);
    }
}