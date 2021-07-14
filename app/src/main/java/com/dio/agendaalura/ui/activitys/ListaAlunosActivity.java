package com.dio.agendaalura.ui.activitys;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.dio.agendaalura.R;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ListaAlunosActivity extends AppCompatActivity{
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_alunos);
        List<String> alunos = new ArrayList<>(Arrays.asList("Octavio", "Rafaela", "Junior", "Ana", "Leo", "Jorge"));
        ListView listaAlunos = findViewById(R.id.listview_lista_alunos);
        listaAlunos.setAdapter(new ArrayAdapter<>(
                this,
                android.R.layout.simple_list_item_1,
                alunos));
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
}