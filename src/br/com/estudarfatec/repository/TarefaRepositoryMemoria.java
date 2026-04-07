package br.com.estudarfatec.repository;

import java.util.ArrayList;
import java.util.List;

import br.com.estudarfatec.model.Tarefa;

// Implementa o contrato da interface: TarefaRepository
public class TarefaRepositoryMemoria implements TarefaRepository {
	
	private List<Tarefa> tarefas = new ArrayList<>();
	
	@Override
	public void salvar(Tarefa tarefa) {
		tarefas.add(tarefa); // Armazena na lista
	}
	
	@Override
	public List<Tarefa> listar(){
		return tarefas;
	}

}
