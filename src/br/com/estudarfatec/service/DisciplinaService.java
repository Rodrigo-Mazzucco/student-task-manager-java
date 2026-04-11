package br.com.estudarfatec.service;

import java.util.List;

import br.com.estudarfatec.model.Disciplina;
import br.com.estudarfatec.repository.DisciplinaRepository;

public class DisciplinaService {

    private DisciplinaRepository repository;

    public DisciplinaService(DisciplinaRepository repository) {
        this.repository = repository;
    }

    public void cadastrar(String nome) {

        // Regra 1: nome obrigatório
        if (nome == null || nome.trim().isEmpty()) {
            throw new IllegalArgumentException("Nome da disciplina não pode ser vazio.");
        }

        // Regra 2: sem duplicatas
        for (Disciplina d : repository.listar()) {
            if (d.getNome().equalsIgnoreCase(nome.trim())) {
                throw new IllegalArgumentException("Já existe uma disciplina com esse nome.");
            }
        }

        repository.salvar(new Disciplina(nome.trim()));
    }

    public List<Disciplina> listar() {
        return repository.listar();
    }
}