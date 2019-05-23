
package simuladorgerenciamentodememoria;

public class No {
    private int numeroDoBloco;
    private int inicioDoEndereco;
    private int finalDoEndereco;
    private int tam;
    private No proxEnd;

    public No(int inicioDoEndereco , No proxEnd, int tam, int numeroDoBloco) {
        this.inicioDoEndereco = inicioDoEndereco;
        this.proxEnd = proxEnd;
        this.tam = tam;
        this.finalDoEndereco = this.inicioDoEndereco + this.tam;
        this.numeroDoBloco = numeroDoBloco;
    }

    public int getInicioDoEndereco() {
        return inicioDoEndereco;
    }

    public int getFinalDoEndereco() {
        return finalDoEndereco;
    }

   

    public No getproxEnd() {
        return this.proxEnd;
    }
    
    public int getTam() {
        return this.tam;
    }

    public void setInicioDoEndereco(int endereco) {
        this.inicioDoEndereco = endereco;
    }
    
    public void reduzTamanho(int tamanho) {
        this.tam -= tamanho;
    }

    public void setproxEnd(No proxEnd) {
        this.proxEnd = proxEnd;
    }

    @Override
    public String toString() {
        return "Numero do bloco: "+ this.numeroDoBloco +" [Inicio do Endereco: " + this.inicioDoEndereco + ", Final do endereco: " + this.finalDoEndereco + ", Memoria alocada: " + this.tam + "] \n" + this.proxEnd;
    }    

    public int getNumeroDoBloco() {
        return numeroDoBloco;
    }

    public void setNumeroDoBloco(int numeroDoBloco) {
        this.numeroDoBloco = numeroDoBloco;
    }
}
