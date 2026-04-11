package br.com.estudarfatec.controller;

import java.util.List;

import br.com.estudarfatec.model.Disciplina;
import br.com.estudarfatec.service.DisciplinaService;

public class DisciplinaController {
	
	private DisciplinaService serviceDisciplina;
	
	// Injeção de dependência
	public DisciplinaController(DisciplinaService serviceDisciplina) {
		this.serviceDisciplina= serviceDisciplina;
	}
	
	public void cadastrar(String nome) {
		
		serviceDisciplina.cadastrar(nome); // Delega ao service
	}
	
	public List<Disciplina> listar(){
		return serviceDisciplina.listar(); // Delega
	}

}
