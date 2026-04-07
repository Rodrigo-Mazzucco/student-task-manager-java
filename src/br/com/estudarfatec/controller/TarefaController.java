package br.com.estudarfatec.controller;

import java.time.LocalDate;
import java.util.List;

import br.com.estudarfatec.model.Disciplina;
import br.com.estudarfatec.model.Tarefa;
import br.com.estudarfatec.repository.TarefaRepository;

public class TarefaController {
	
	private TarefaRepository repositoryTarefa;
	
	// Injeção de dependência
	public TarefaController(TarefaRepository repositoryTarefa) {
		this.repositoryTarefa =  repositoryTarefa;
	}
	
	public void cadastrar(String titulo, String descricao, LocalDate dataEntrega, Disciplina disciplina) {
		Tarefa tarefa = new Tarefa(titulo, descricao, dataEntrega, disciplina);
		
		repositoryTarefa.salvar(tarefa); // Delega ao repository
	}
	
	public List<Tarefa> listar(){
		return repositoryTarefa.listar(); // Delega
	}

}
