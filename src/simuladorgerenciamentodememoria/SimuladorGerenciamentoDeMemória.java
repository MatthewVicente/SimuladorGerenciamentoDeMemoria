/*
 * João Pedro Barberino Silva
 * Matheus Vicente Rodrigues da Silva
 */
package simuladorgerenciamentodememoria;

import java.util.Scanner;

/**
 *
 * @author mathe
 */
public class SimuladorGerenciamentoDeMemória {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        listaLigada listaBlocosLivres = new listaLigada(4000, "listaBlocosLivres");
        listaLigada listaBlocosAlocados = new listaLigada(0, "listaBlocosAlocados");

        menu(listaBlocosLivres, listaBlocosAlocados);

    }

    public static void menu(listaLigada listaBlocosLivres, listaLigada listaBlocosAlocados) {
        Scanner ler = new Scanner(System.in);
        int escolha;


//        System.out.println("|-------------------------------------------------|");
//        System.out.println("       Olá, o sistema possui atualmente " + listaBlocosLivres.getMemoriaDisponivel() + " bytes de memoria restantes");
//        System.out.println();
//        System.out.println("       Qual das operacoes a seguir deseja realizar?");
//        System.out.println("       1 - Alocar memória para execução do processo;");
//        System.out.println("       2 - Finalizar processo, ou seja, liberar a memoria que ele estava usando;");
//        System.out.println("       3 - Imprimir na tela a situacao atual da memoria: blocos de memoria livres e blocos de memoria alocados.");
//        System.out.println();
        listaBlocosLivres.checaContigua();
        printaListas(listaBlocosLivres, listaBlocosAlocados);
        System.out.println("|-------------------------------------------------|");
        System.out.printf("Escolha: ");
        escolha = ler.nextInt();
        switch (escolha) {
            // Alocar memória pra execução do processo
            case 1:
                System.out.println("Quanta memória você quer alocar?");
                int qtdAlocada = ler.nextInt();
                alocar(listaBlocosLivres, listaBlocosAlocados, qtdAlocada);
                menu(listaBlocosLivres, listaBlocosAlocados);
                break;

            // Finalizar processo, ou seja, liberar memória que o processo estava usando
            case 2:
                System.out.println("Qual processo voce quer remover?");
                int numeroDoBloco = ler.nextInt();
                remover(listaBlocosLivres, listaBlocosAlocados, numeroDoBloco);
                listaBlocosLivres.checaContigua();
                menu(listaBlocosLivres, listaBlocosAlocados);
                break;

            // Imprimir na tela a situação atual da memória: blocos de memórias livres e blocos de 
            // memória alocados.
            case 3:
                printaListas(listaBlocosLivres, listaBlocosAlocados);
                break;

            default:
                System.out.println("Digite um valor válido!");
                menu(listaBlocosLivres, listaBlocosAlocados);
                break;
        }
    }

    public static void alocar(listaLigada listaBlocosLivres, listaLigada listaBlocosAlocados, int qtdAlocada) {
        // Checa se tem memoria disponivel
        if (qtdAlocada > 0) {
            No melhorNo = listaBlocosLivres.buscaMelhorNo(qtdAlocada);
            if (melhorNo != null && melhorNo.getTam() >= qtdAlocada) {
                listaBlocosAlocados.addFim(melhorNo.getInicioDoEndereco(), qtdAlocada);
                listaBlocosLivres.atualizaNo(melhorNo, qtdAlocada);
                listaBlocosLivres.apagaVazio();
            } else {
                System.out.println("Não há espaço suficiente, remova um processo e tente novamente!");
            }
        }
    }

    public static void remover(listaLigada listaBlocosLivres, listaLigada listaBlocosAlocados, int numeroDoBloco) {
        No removido = listaBlocosAlocados.removeProcesso(numeroDoBloco);
        if (removido != null) {
            listaBlocosLivres.addOrdenado(removido);
        } else {
            System.out.println("Esse processo já foi removido ou não existe!");
        }
    }

    public static void printaListas(listaLigada listaBlocosLivres, listaLigada listaBlocosAlocados) {
        System.out.println("");
        System.out.println("Lista de Blocos Livres: ");
        System.out.println(listaBlocosLivres);
        System.out.println("");
        System.out.println("Lista de Blocos Alocados: ");
        System.out.println(listaBlocosAlocados);
        System.out.println("");
        //menu(listaBlocosLivres, listaBlocosAlocados);
    }
}
