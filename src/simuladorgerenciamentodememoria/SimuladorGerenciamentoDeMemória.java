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
        listaLigada listaBlocosLivres = new listaLigada(4000, false);
        listaLigada listaBlocosAlocados = new listaLigada(0, true);
        
        menu(listaBlocosLivres, listaBlocosAlocados);        
        
        
    }
    
    public static void menu(listaLigada listaBlocosLivres, listaLigada listaBlocosAlocados){
        Scanner ler = new Scanner(System.in);
        int escolha;
        
         String tam = listaBlocosLivres.getTam(0) > 0 ? 
                ""+listaBlocosLivres.getTam(0) : 
                "(Endereco inexistente)";
        
        System.out.println("|-------------------------------------------------|");
        System.out.println("       Olá, o sistema possui atualmente " + tam);
        System.out.println("");
        System.out.println("       Qual das operacoes a seguir deseja realizar?");
        System.out.println("       1 - Alocar memória para execução do processo;\n" +
                           "       2 - Finalizar processo, ou seja, liberar memória que o"
                         + " processo estava usando;\n" +
                           "       3 - Imprimir na tela a situação atual da memória: "
                         + "blocos de memórias livres e blocos de \n "
                         + "        memória alocados.");
        System.out.println("");
        System.out.println("|-------------------------------------------------|");
        System.out.printf("Escolha: ");
        escolha = ler.nextInt();
       
        switch(escolha) {
            case 1:
                break;
            case 2:
                break;
            case 3:
                // chama funcao de printar livre e alocado (livre, alocado)
                break;
            default:
                System.out.println("Digite um valor válido!");
                menu(listaBlocosLivres, listaBlocosAlocados);
                break;
        }
        
    }
}
