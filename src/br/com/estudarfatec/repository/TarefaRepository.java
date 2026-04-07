package br.com.estudarfatec.repository;

import java.util.List;

import br.com.estudarfatec.model.Tarefa;

// Interface define o contrato
public interface TarefaRepository {
	void salvar(Tarefa tarefa); // Salvar tarefa
	List<Tarefa> listar(); // Listar todas as tarefas

}
