package br.com.estudarfatec.controller;

import java.time.LocalDate;
import java.util.List;

import br.com.estudarfatec.model.Disciplina;
import br.com.estudarfatec.model.Tarefa;
import br.com.estudarfatec.service.TarefaService;

public class TarefaController {

	private TarefaService serviceTarefa;

	// Injeção de dependência
	public TarefaController(TarefaService serviceTarefa) {
		this.serviceTarefa = serviceTarefa;
	}

	public void cadastrar(String titulo, String descricao, LocalDate dataEntrega, Disciplina disciplina) {

		serviceTarefa.cadastrar(titulo, descricao, dataEntrega, disciplina); // Delega ao service
	}

	public List<Tarefa> listar() {
		return serviceTarefa.listar(); // Delega
	}

}
