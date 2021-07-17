package com.dio.agendaalura.dao;

import androidx.annotation.Nullable;

import com.dio.agendaalura.model.Aluno;

import java.util.ArrayList;
import java.util.List;

public class AlunosDAO {

    private final static List<Aluno> alunos = new ArrayList<>();
    private static int contadorDeIds = 1;

    public void salva(Aluno aluno) {
        aluno.setId(contadorDeIds);
        alunos.add(aluno);
        atualizaIds();
    }

    private void atualizaIds() {
        contadorDeIds++;
    }

    public void edita (Aluno aluno){
        Aluno alunoEcontrado = buscaAlunoPeloId(aluno);
        if (alunoEcontrado != null){
            int posicaoDoAluno = alunos.indexOf(alunoEcontrado);
            alunos.set(posicaoDoAluno, aluno);
        }
    }

    @Nullable
    private Aluno buscaAlunoPeloId(Aluno aluno) {
        for (Aluno a:
             alunos) {
            if(a.getId() == aluno.getId() ){
                return a;
            }
        }
        return null;
    }

    public List<Aluno> todos() {
        return new ArrayList<>(alunos);
    }

    public void remove(Aluno alunoEscolhido) {
        Aluno alunoDevolvido = buscaAlunoPeloId(alunoEscolhido);
        if ( alunoDevolvido != null){
            alunos.remove(alunoDevolvido);
        }
    }
}
