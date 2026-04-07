package br.com.estudarfatec.repository;

import java.util.ArrayList;
import java.util.List;

import br.com.estudarfatec.model.Disciplina;

//Implementa o contrato da interface: DisciplinaRepository
public class DisciplinaRepositoryMemoria implements DisciplinaRepository{
	
	private List<Disciplina> disciplinas = new ArrayList<>();
	
	@Override
	public void salvar(Disciplina disciplina) {
		disciplinas.add(disciplina); // Armazena na lista
	}
	
	@Override
	public List<Disciplina> listar(){
		return disciplinas;
	}

}