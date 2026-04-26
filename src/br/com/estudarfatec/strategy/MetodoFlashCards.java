package br.com.estudarfatec.strategy;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class MetodoFlashCards implements MetodoEstudo {

    // 1. Criamos uma classe interna para representar o objeto Flashcard (Melhor prática de POO)
    private static class Flashcard {
        String pergunta;
        String resposta;

        Flashcard(String pergunta, String resposta) {
            this.pergunta = pergunta;
            this.resposta = resposta;
        }
    }

    public void estudar() {
        Scanner scanner = new Scanner(System.in);
        // Usamos a lista com o nosso objeto em vez de array de Strings
        List<Flashcard> flashcards = new ArrayList<>(); 

        int opcao = -1;

        do {
            System.out.println("\n=== Flashcards ===");
            System.out.println("1 - Adicionar flashcard");
            System.out.println("2 - Estudar flashcards");
            System.out.println("0 - Voltar ao menu");
            System.out.print("Opção: ");
            
            // 2. Prevenção de quebra do sistema caso o usuário digite texto em vez de número
            try {
                opcao = Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("❌ Erro: Por favor, digite um número válido.");
                continue; // Volta para o início do menu
            }

            switch (opcao) {
                case 1: // Cadastrar
                    System.out.print("Pergunta: ");
                    String pergunta = scanner.nextLine();
                    System.out.print("Resposta: ");
                    String resposta = scanner.nextLine();
                    
                    flashcards.add(new Flashcard(pergunta, resposta));
                    System.out.println("✅ Flashcard adicionado!");
                    break;

                case 2: // Estudar
                    if (flashcards.isEmpty()) {
                        System.out.println("Nenhum flashcard cadastrado. Adicione alguns primeiro!");
                        break;
                    }
                    
                    int acertos = 0; // 3. Variável para guardar o desempenho

                    for (Flashcard card : flashcards) {
                        System.out.println("\nPergunta: " + card.pergunta); // Acessamos via atributo
                        System.out.println("Pressione ENTER para ver a resposta...");
                        scanner.nextLine();
                        
                        System.out.println("Resposta: " + card.resposta);
                        System.out.println("Você acertou? (1 - Sim / 2 - Não)");
                        
                        int avaliacao = 0;
                        try {
                            avaliacao = Integer.parseInt(scanner.nextLine());
                        } catch (NumberFormatException e) {
                            // Ignora a exceção, o valor continuará 0 e cairá no "else" de erro
                        }
                        
                        if (avaliacao == 1) {
                            System.out.println("✅ Ótimo!");
                            acertos++;
                        } else {
                            System.out.println("❌ Revise esse conteúdo!");
                        }
                    }
                    
                    // Mostra o resultado final da sessão
                    System.out.println("\nSessão finalizada!");
                    System.out.println("Seu resultado: " + acertos + " de " + flashcards.size() + " corretos.");
                    break;

                case 0:
                    System.out.println("Voltando ao menu...");
                    break;

                default:
                    System.out.println("Opção inválida!");
            }

        } while (opcao != 0);
    }
}