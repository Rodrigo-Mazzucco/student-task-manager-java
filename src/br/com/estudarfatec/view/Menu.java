package br.com.estudarfatec.view;

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
import br.com.estudarfatec.service.DisciplinaService;
import br.com.estudarfatec.service.TarefaService;
import br.com.estudarfatec.strategy.MetodoFlashCards;
import br.com.estudarfatec.strategy.MetodoPomodoro;
import br.com.estudarfatec.strategy.MetodoRevisao;

public class Menu {
	
    public void iniciar() { //  tudo dentro desse método


	// Cria a implementação
	TarefaRepository repositoryTarefa = new TarefaRepositoryMemoria();
	DisciplinaRepository repositoryDisciplina = new DisciplinaRepositoryMemoria();
	
	// Services (regras de negócio) — recebem os repositórios
	TarefaService serviceTarefa = new TarefaService(repositoryTarefa);
	DisciplinaService serviceDisciplina = new DisciplinaService(repositoryDisciplina);
	
	// Injeta no controller
	TarefaController controllerTarefa = new TarefaController(serviceTarefa);
	DisciplinaController controllerDisciplina = new DisciplinaController(serviceDisciplina);
	
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
        System.out.println("5 - Métodos de estudo");
        System.out.println("6 - Sair");

        System.out.print("Opção: ");
        opcao = scanner.nextInt();
        scanner.nextLine(); // limpar buffer

        switch (opcao) {

            case 1: // Cadastrar disciplina
                System.out.print("Digite o nome da disciplina: ");
                String nomeDisciplina = scanner.nextLine();
                try {
                    controllerDisciplina.cadastrar(nomeDisciplina);
                    System.out.println("Disciplina cadastrada!");
                } catch (IllegalArgumentException e) {
                    System.out.println("Erro: " + e.getMessage()); // ex: "Já existe uma disciplina com esse nome."
                }
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

               
                try {
                    controllerTarefa.cadastrar(titulo, descricao, dataEntrega, disciplinaEscolhida);
                    System.out.println("Tarefa cadastrada!");
                } catch (IllegalArgumentException e) {
                    System.out.println("Erro: " + e.getMessage());
                }
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
                
            case 5: // Escolher método
                int opcaoMetodo = 0;

                do {
                    System.out.println("\n=== Métodos de Estudo ===");
                    System.out.println("1 - Pomodoro Clássico");
                    // System.out.println("? - Pomodoro Longo"); Implementar no futuro
                    // System.out.println("? - Pomodoro Personalizado"); Implementar no futuro
                    System.out.println("2 - Flashcards");
                    System.out.println("3 - Revisão");
                    System.out.println("4 - Voltar");

                    System.out.print("Opção: ");
                    opcaoMetodo = scanner.nextInt();
                    scanner.nextLine();

                    switch (opcaoMetodo) {
                        case 1: // Metodo Pomodoro clássico
                            MetodoPomodoro pomodoro = new MetodoPomodoro();
                            pomodoro.estudar();
                            break;
                        case 2: // Metodo FlashCards
                            MetodoFlashCards flashCards = new MetodoFlashCards();
                            flashCards.estudar();
                            break;
                        case 3: // Metodo Revisão
                            MetodoRevisao revisao = new MetodoRevisao();
                            revisao.estudar();
                            break;
                        case 4:
                            System.out.println("Voltando ao menu...");
                            break;
                        default:
                            System.out.println("Opção inválida!");
                    }

                } while (opcaoMetodo != 4); // fica no submenu até escolher Voltar

                break; //  volta pro menu principal
               
            	
            	
            	
            	
            	
            	
            	
            case 6:
                System.out.println("Saindo...");
                break;

            default:
                System.out.println("Opção inválida!");
        }

    } while (opcao != 6);

    scanner.close();
    }
}
