package br.com.estudarfatec;

import java.util.List;
import java.util.Scanner;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import br.com.estudarfatec.controller.DisciplinaController;
import br.com.estudarfatec.controller.TarefaController;
import br.com.estudarfatec.model.Disciplina;
import br.com.estudarfatec.model.Tarefa;
import br.com.estudarfatec.repository.DisciplinaRepository;
import br.com.estudarfatec.repository.TarefaRepository;

import br.com.estudarfatec.repository.DisciplinaRepositoryMemoria;
import br.com.estudarfatec.repository.TarefaRepositoryMemoria;

public class Main {

    public static void main(String[] args) {
    	
    	// Cria a implementação
    	TarefaRepository repositoryTarefa = new TarefaRepositoryMemoria();
    	DisciplinaRepository repositoryDisciplina = new DisciplinaRepositoryMemoria();
    	
    	// Injeta no controller
    	TarefaController controllerTarefa = new TarefaController(repositoryTarefa);
    	DisciplinaController controllerDisciplina = new DisciplinaController(repositoryDisciplina);
    	
    	List<Disciplina> disciplinas = controllerDisciplina.listar();
    	List<Tarefa> tarefas = controllerTarefa.listar();

    	
        Scanner scanner = new Scanner(System.in);
        
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

        int opcao = 0;


        System.out.println("=== Bem-vindo ao EstudaFatec ===");

        do {
            System.out.println("\nEscolha uma opção:");
            System.out.println("1 - Cadastrar disciplina");
            System.out.println("2 - Cadastrar tarefa");
            System.out.println("3 - Listar tarefas");
            System.out.println("4 - Concluir tarefa");
            System.out.println("5 - Sair");

            System.out.print("Opção: ");
            opcao = scanner.nextInt();
            scanner.nextLine(); // limpar buffer

            switch (opcao) {

                case 1: // Cadastrar disciplina
                    System.out.print("Digite o nome da disciplina: ");
                    String nomeDisciplina = scanner.nextLine();
                    controllerDisciplina.cadastrar(nomeDisciplina);
                    System.out.println("Disciplina cadastrada!");
                    break;

                case 2: // Cadastrar tarefa
                    if (disciplinas.isEmpty()) {
                        System.out.println("Cadastre uma disciplina antes!");
                        break;
                    }

                    System.out.print("Digite o título da tarefa: ");
                    String titulo = scanner.nextLine();

                    System.out.print("Digite a descrição da tarefa: ");
                    String descricao = scanner.nextLine();

                    System.out.print("Digite a data de entrega (dd/MM/yyyy): ");
                    String dataStr = scanner.nextLine();
                    LocalDate dataEntrega;
                    try {
                        dataEntrega = LocalDate.parse(dataStr, formatter);
                    } catch (DateTimeParseException e) {
                        System.out.println("Data inválida! Use dd/MM/yyyy.");
                        break;
                    }

                    // Escolher disciplina
                    System.out.println("Escolha a disciplina:");
                    for (int i = 0; i <  disciplinas.size(); i++) {
                        System.out.println(i + " - " + disciplinas.get(i).getNome());
                    }
                    
                    int indiceDisciplina = scanner.nextInt();
                    scanner.nextLine(); // limpar buffer
                    if (indiceDisciplina < 0 || indiceDisciplina >= disciplinas.size()) {
                        System.out.println("Disciplina inválida!");
                        break;
                    }
                    
                    Disciplina disciplinaEscolhida = disciplinas.get(indiceDisciplina);

                   
                   controllerTarefa.cadastrar(titulo,descricao,dataEntrega, disciplinaEscolhida);
                    System.out.println("Tarefa cadastrada!");
                    break;

                case 3: // Listar tarefas por disciplina
                    if (controllerTarefa.listar().isEmpty()) {
                        System.out.println("Nenhuma tarefa cadastrada.");
                        break;
                    }

                    System.out.println("=== Tarefas por Disciplina ===");

                    for (Disciplina d : disciplinas) {
                        System.out.println("\nDisciplina: " + d.getNome());
                        boolean temTarefas = false;
                        for (Tarefa t : tarefas) {
                            if (t.getDisciplina().equals(d)) {
                                System.out.println(t);
                                temTarefas = true;
                            }
                        }
                        if (!temTarefas) {
                            System.out.println("Nenhuma tarefa para esta disciplina.");
                        }
                    }
                    break;
                    
                    
                case 4: // Concluir tarefa
                    if (tarefas.isEmpty()) {
                        System.out.println("Nenhuma tarefa cadastrada.");
                        break;
                    }
                    System.out.println("Escolha a tarefa para concluir:");
                    for (Tarefa t : tarefas) {
                        System.out.println(t.getId() + " - " + t.getTitulo());
                    }
                    int idEscolhido = scanner.nextInt();
                    scanner.nextLine();
                    boolean achou = false;
                    for (Tarefa t : tarefas) {
                        if (t.getId() == idEscolhido) {
                            t.concluir();
                            System.out.println("Tarefa concluída!");
                            achou = true;
                            break;
                        }
                    }
                    if (!achou) System.out.println("Tarefa não encontrada.");
                    break;

                case 5:
                    System.out.println("Saindo...");
                    break;

                default:
                    System.out.println("Opção inválida!");
            }

        } while (opcao != 5);

        scanner.close();
    }
}