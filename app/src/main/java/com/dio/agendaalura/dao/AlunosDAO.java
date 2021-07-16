package com.dio.agendaalura.dao;

import com.dio.agendaalura.model.Aluno;

import java.util.ArrayList;
import java.util.List;

public class AlunosDAO {

    private final static List<Aluno> alunos = new ArrayList<>();
    private static int contadorDeIds = 1;

    public void salva(Aluno aluno) {
        aluno.setId(contadorDeIds);
        alunos.add(aluno);
        contadorDeIds++;
    }

    public void edita (Aluno aluno){
        Aluno alunoEcontrado = null;
        for (Aluno a:
             alunos) {
            if(a.getId() == aluno.getId() ){
                alunoEcontrado = a;
            }
        }
        if (alunoEcontrado != null){
            int posicaoDoAluno = alunos.indexOf(alunoEcontrado);
            alunos.set(posicaoDoAluno, aluno);
        }
    }

    public List<Aluno> todos() {
        return new ArrayList<>(alunos);
    }
}
