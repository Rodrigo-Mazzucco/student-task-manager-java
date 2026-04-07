package br.com.estudarfatec.controller;

import java.util.List;

import br.com.estudarfatec.model.Disciplina;
import br.com.estudarfatec.repository.DisciplinaRepository;

public class DisciplinaController {
	
	private DisciplinaRepository repositoryDisciplina;
	
	// Injeção de dependência
	public DisciplinaController(DisciplinaRepository repositoryDisciplina) {
		this.repositoryDisciplina= repositoryDisciplina;
	}
	
	public void cadastrar(String nome) {
		Disciplina disciplina = new Disciplina(nome);
		
		repositoryDisciplina.salvar(disciplina); // Delega ao repository
	}
	
	public List<Disciplina> listar(){
		return repositoryDisciplina.listar(); // Delega
	}

}
