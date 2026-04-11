package br.com.estudarfatec.service;

import java.time.LocalDate;
import java.util.List;

import br.com.estudarfatec.model.Disciplina;
import br.com.estudarfatec.model.Tarefa;
import br.com.estudarfatec.repository.TarefaRepository;

public class TarefaService {

    private TarefaRepository repository;

    // O repository é injetado (mesma ideia do Controller)
    public TarefaService(TarefaRepository repository) {
        this.repository = repository;
    }

    public void cadastrar(String titulo, String descricao, LocalDate dataEntrega, Disciplina disciplina) {

        // Regra 1: título obrigatório
        if (titulo == null || titulo.trim().isEmpty()) {
            throw new IllegalArgumentException("Título não pode ser vazio.");
        }

        // Regra 2: disciplina obrigatória
        if (disciplina == null) {
            throw new IllegalArgumentException("Disciplina não pode ser nula.");
        }

        // Regra 3: data não pode ser no passado
        if (dataEntrega != null && dataEntrega.isBefore(LocalDate.now())) {
            throw new IllegalArgumentException("Data de entrega não pode ser no passado.");
        }

        Tarefa tarefa = new Tarefa(titulo, descricao, dataEntrega, disciplina);
        repository.salvar(tarefa);
    }

    public List<Tarefa> listar() {
        return repository.listar();
    }
}