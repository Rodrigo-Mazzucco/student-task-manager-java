package br.com.estudarfatec.model;

import java.time.LocalDate;

public class Tarefa {

	private static int contador = 1; // contador global
    private int id;
    private String titulo;
    private String descricao;
    private LocalDate dataEntrega;
    private boolean concluida;
    private Disciplina disciplina;

    public Tarefa(String titulo, String descricao, LocalDate dataEntrega, Disciplina disciplina) {
        this.id = contador++;
        this.titulo = titulo;
        this.descricao = descricao;
        this.dataEntrega = dataEntrega;
        this.disciplina = disciplina;
        this.concluida = false;
    }

    public void concluir() {
        this.concluida = true;
    }

    public int getId() {
        return id;
    }

    public String getTitulo() {
        return titulo;
    }

    public String getDescricao() {
        return descricao;
    }

    public LocalDate getDataEntrega() {
        return dataEntrega;
    }

    public boolean isConcluida() {
        return concluida;
    }

    public Disciplina getDisciplina() {
        return disciplina;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public void setDataEntrega(LocalDate dataEntrega) {
        this.dataEntrega = dataEntrega;
    }

    public void setDisciplina(Disciplina disciplina) {
        this.disciplina = disciplina;
    }

    @Override
    public String toString() {
        String status = concluida ? "[X]" : "[ ]";

        return String.format(
            "%s ID: %d | %s | %s | Entrega: %s | Disciplina: %s",
            status,
            id,
            titulo,
            descricao,
            dataEntrega,
            disciplina.getNome()
        );
    }
}