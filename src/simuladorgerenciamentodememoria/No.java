
package simuladorgerenciamentodememoria;

public class No {
    private int inicioDoEndereco;
    private int finalDoEndereco;
    private int tam;
    private No proxEnd;

    public No(int inicioDoEndereco , No proxEnd, int tam) {
        this.inicioDoEndereco = inicioDoEndereco;
        this.proxEnd = proxEnd;
        this.tam = tam;
        this.finalDoEndereco = this.tam;
    }

    public int getEndereco() {
        return this.inicioDoEndereco;
    }

    public No getproxEnd() {
        return this.proxEnd;
    }
    
    public int getTam() {
        return this.tam;
    }

    public void setendereco(int endereco) {
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
        return "{" + this.inicioDoEndereco + ","  + this.proxEnd + '}';
    }    
}
