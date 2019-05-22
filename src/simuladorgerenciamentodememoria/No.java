
package simuladorgerenciamentodememoria;

public class No {
    private int endereco;
    private int tam;
    private No proxEnd;

    public No(int endereco, No proxEnd, int tam) {
        this.endereco = endereco;
        this.proxEnd = proxEnd;
        this.tam = tam;
    }

    public int getEndereco() {
        return this.endereco;
    }

    public No getproxEnd() {
        return this.proxEnd;
    }
    
    public int getTam() {
        return this.tam;
    }

    public void setendereco(int endereco) {
        this.endereco = endereco;
    }

    public void setproxEnd(No proxEnd) {
        this.proxEnd = proxEnd;
    }

    @Override
    public String toString() {
        return "{" + this.endereco + ","  + this.proxEnd + '}';
    }    
}
