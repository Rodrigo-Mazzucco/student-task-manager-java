package br.com.estudarfatec.repository;

import java.util.List;

import br.com.estudarfatec.model.Disciplina;

public interface DisciplinaRepository {
	void salvar(Disciplina disciplina); // Salvar disciplina
	List<Disciplina> listar(); // Listar todas as tarefas

}


