package br.com.estudarfatec.strategy;

import java.util.Scanner;

public class MetodoPomodoro implements MetodoEstudo {

    public void estudar() {
        System.out.println("25 minutos de estudo + 5 minutos de pausa");
        System.out.println("Pressione 0 e ENTER para voltar ao menu.\n");

        final long TEMPO_ESTUDO = 1500; // 25 minutos
        final long TEMPO_PAUSA  = 300;  // 5 minutos  

        final boolean[] rodando = {true};

        // Criamos uma Thread separada apenas para gerenciar o relógio
        Thread timerThread = new Thread(() -> {
            while (rodando[0]) {
                // Inicia o tempo de estudo
                executarContagem(TEMPO_ESTUDO, "⏱ Estudo", rodando);
                
                // Se o usuário digitou 0 durante o estudo, interrompe o ciclo
                if (!rodando[0]) break;

                System.out.println("\n\n⏸ Pausa! Descanse.");
                
                // Inicia o tempo de pausa
                executarContagem(TEMPO_PAUSA, "☕ Pausa", rodando);
                
                // Se o usuário digitou 0 durante a pausa, interrompe
                if (!rodando[0]) break;

                System.out.println("\n\n▶ Volte ao estudo!");
            }
        });
        
        timerThread.start();

        // Loop principal aguardando o input do usuário
        Scanner scanner = new Scanner(System.in);
        
        while (rodando[0]) {
            String entrada = scanner.nextLine();
            if (entrada.equals("0")) {
                rodando[0] = false;
                System.out.println("\nVoltando ao menu...");
                break;
            }
        }
    }

    /**
     * Método centralizado para fazer a contagem regressiva
     */
    private void executarContagem(long segundosTotais, String icone, boolean[] rodando) {
        long restantes = segundosTotais;
        
        while (restantes >= 0 && rodando[0]) {
            long minutos = restantes / 60;
            long segundos = restantes % 60;

            // \r volta o cursor para o início. Os espaços no final ajudam a "limpar" restos de texto maior.
            System.out.print(String.format("\r%s %02d:%02d     ", icone, minutos, segundos));
            
            // O flush força o console a desenhar o texto na tela imediatamente, mesmo sem o \n
            System.out.flush();

            try {
                Thread.sleep(1000); // Aguarda 1 segundo
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
            
            restantes--;
        }
    }
}