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
        
         String tam = listaBlocosLivres.getTamDoEndereco(0) > 0 ? 
                ""+listaBlocosLivres.getTamDoEndereco(0) : 
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
            // Alocar memória pra execução do processo
            case 1:
                System.out.println("Quanta memória você quer alocar?");
                int qtdAlocada = ler.nextInt();
                alocar(listaBlocosLivres, listaBlocosAlocados, qtdAlocada);
                System.out.println(listaBlocosLivres);
                System.out.println(listaBlocosAlocados);
                break;
            
            // Finalizar processo, ou seja, liberar memória que o processo estava usando
            case 2:
            
                break;
            
            // Imprimir na tela a situação atual da memória: blocos de memórias livres e blocos de 
            // memória alocados.
            case 3:
                // chama funcao de printar livre e alocado (livre, alocado)
                break;

            default:
                System.out.println("Digite um valor válido!");
                menu(listaBlocosLivres, listaBlocosAlocados);
                break;
        }        
    }
    
    public static void alocar (listaLigada listaBlocosLivres, listaLigada listaBlocosAlocados, int qtdAlocada) {

        // Checa se tem memoria disponivel
        if(listaBlocosLivres.getInicio().getTam() >= qtdAlocada) {
            listaBlocosAlocados.addFim(listaBlocosLivres.getInicio().getEndereco(), qtdAlocada);
            listaBlocosLivres.atualizaInicio(qtdAlocada);
        } else {
            System.out.println("Não há espaço o suficiente, remova um processo e tente novamente!");
            menu(listaBlocosLivres, listaBlocosAlocados);
        }
    
    }
}
