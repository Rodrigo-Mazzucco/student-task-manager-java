package br.com.estudarfatec.strategy;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public class MetodoRevisao implements MetodoEstudo {

    @Override
    public void estudar() {
        Scanner scanner = new Scanner(System.in);
        DateTimeFormatter df = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        LocalDate hoje = LocalDate.now();

        System.out.println("\n=== 📝 Método de Revisão Sistemática ===");
        System.out.println("Baseado na Curva de Esquecimento de Ebbinghaus.");
        
        System.out.print("\nQual conteúdo você acabou de estudar? ");
        String conteudo = scanner.nextLine();

        System.out.println("\n✅ Registro concluído para: " + conteudo);
        System.out.println("------------------------------------------");
        System.out.println("Para fixar este conteúdo, siga este cronograma:");

        // Cálculo das datas de revisão
        System.out.println("1ª Revisão (em 24h):   " + hoje.plusDays(1).format(df));
        System.out.println("2ª Revisão (em 7 dias):  " + hoje.plusWeeks(1).format(df));
        System.out.println("3ª Revisão (em 30 dias): " + hoje.plusMonths(1).format(df));
        System.out.println("------------------------------------------");

        System.out.println("\nDicas para uma boa revisão:");
        System.out.println("* Não apenas leia! Tente explicar o conteúdo em voz alta.");
        System.out.println("* Faça um mapa mental ou um resumo de 5 linhas.");
        System.out.println("\nPressione ENTER para voltar ao menu.");
        scanner.nextLine();
    }
}