/*
 * João Pedro Barberino Silva
 * Matheus Vicente Rodrigues da Silva
 */
package simuladorgerenciamentodememoria;

/**
 *
 * @author mathe
 */
public class SimuladorGerenciamentoDeMemória {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        listaLigada lista = new listaLigada();
        
        lista.addOrdenado(10);
        lista.addOrdenado(20);
        lista.addOrdenado(15);
        lista.addOrdenado(1);
        lista.addOrdenado(30);
        
        System.out.println(lista);
    }
}
